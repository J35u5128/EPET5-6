package org.example.epet56.Controller;

import org.example.epet56.Entities.Item;
import org.example.epet56.Exceptions.RecursoNoEncontradoException;
import jakarta.validation.Valid;
import org.example.epet56.Repositories.ItemRepository;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Controlador REST para la gestión de ítems en la tienda.
 * Expone endpoints para operaciones CRUD y consultas específicas sobre ítems.
 */
@RestController
@RequestMapping("/store")
public class ItemController {

    private final ItemRepository repo;

    /**
     * Constructor del controlador de ítems.
     * @param repo Repositorio de ítems para interactuar con la base de datos.
     */
    public ItemController(ItemRepository repo) {
        this.repo = repo;
    }

    /**
     * Obtiene una lista de ítems filtrados por una categoría específica.
     * @param category La categoría de los ítems a buscar.
     * @return Una lista de objetos {@link Item} que pertenecen a la categoría especificada.
     */
    @GetMapping("/category/{category}")
    public List<Item> detalleCategoria(@PathVariable String category) {
        return repo.findByCategory(category);
    }

    /**
     * Obtiene los detalles de un ítem específico por su ID.
     * @param id El ID del ítem a buscar.
     * @return El objeto {@link Item} correspondiente al ID.
     * @throws RecursoNoEncontradoException Si no se encuentra ningún ítem con el ID proporcionado.
     */
    @GetMapping("/{id}")
    public Item detalle(@PathVariable String id) {
        return repo.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException("Item " + id + " no encontrado"));
    }

    /**
     * Crea un nuevo ítem en la base de datos.
     * Requiere que el usuario tenga el rol 'ADMIN'.
     * @param item El objeto {@link Item} a crear, validado según las anotaciones de Jakarta Validation.
     * @return El ítem creado y guardado en la base de datos.
     */
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public Item crear(@RequestBody @Valid Item item) {
        return repo.save(item);
    }

    /**
     * Elimina un ítem de la base de datos por su ID.
     * Requiere que el usuario tenga el rol 'ADMIN'.
     * @param id El ID del ítem a eliminar.
     */
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void eliminar(@PathVariable String id) {
        repo.deleteById(id);
    }

    /**
     * Actualiza la categoría de todos los ítems que actualmente pertenecen a una categoría antigua
     * a una nueva categoría.
     * Requiere que el usuario tenga el rol 'ADMIN'.
     * @param oldCategory La categoría actual de los ítems que se desean actualizar.
     * @param body Un mapa que contiene la nueva categoría bajo la clave "newCategory".
     * @return Una lista de los ítems que fueron actualizados con la nueva categoría.
     * @throws IllegalArgumentException Si la nueva categoría proporcionada está vacía.
     * @throws RecursoNoEncontradoException Si no se encuentran ítems con la categoría antigua.
     */
    @PutMapping("/update/category/{oldCategory}")
    @PreAuthorize("hasRole('ADMIN')")
    public List<Item> actualizarCategoria(@PathVariable String oldCategory, @RequestBody Map<String, String> body) {
        String newCategory = body.get("newCategory");
        if (newCategory == null || newCategory.isEmpty()) {
            throw new IllegalArgumentException("La nueva categoría no puede estar vacía");
        }

        List<Item> itemsToUpdate = repo.findByCategory(oldCategory);
        if (itemsToUpdate.isEmpty()) {
            throw new RecursoNoEncontradoException("No se encontraron items con la categoría " + oldCategory);
        }

        for (Item item : itemsToUpdate) {
            item.setCategory(newCategory);
        }

        return repo.saveAll(itemsToUpdate);
    }

    /**
     * Obtiene diversas estadísticas sobre los ítems en la tienda.
     * @param stock El umbral de stock para identificar ítems con bajo inventario (por defecto 1).
     * @return Un mapa que contiene las siguientes estadísticas:
     *         - "numero de items": El número total de ítems.
     *         - "items con un valor de stock menor a {stock}": Una lista de ítems con un conteo menor al umbral de stock.
     *         - "listado con los nombres de los fabricantes": Una lista de nombres de fabricantes únicos.
     */
    @GetMapping("/stats")
    public Map<String, Object> stats(@RequestParam(defaultValue = "1") int stock) {
        Map<String, Object> stats = new HashMap<>();
        stats.put("numero de items", repo.count());
        stats.put("items con un valor de stock menor a " + stock, repo.findByCountLessThan(stock));
        List<String> manufacturers = repo.findAllManufacturers().stream()
                .map(Item::getManufacturer)
                .distinct()
                .collect(Collectors.toList());
        stats.put("listado con los nombres de los fabricantes", manufacturers);
        return stats;
    }
}
