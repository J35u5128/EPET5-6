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

@RestController
@RequestMapping("/store")
public class ItemController {

    private final ItemRepository repo;

    public ItemController(ItemRepository repo) {
        this.repo = repo;
    }

    @GetMapping("/category/{category}")
    public List<Item> detalleCategoria(@PathVariable String category) {
        return repo.findByCategory(category);
    }

    @GetMapping("/{id}")
    public Item detalle(@PathVariable String id) {
        return repo.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException("Item " + id + " no encontrado"));
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public Item crear(@RequestBody @Valid Item item) {
        return repo.save(item);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void eliminar(@PathVariable String id) {
        repo.deleteById(id);
    }

    @PutMapping("/update/{category}")
    @PreAuthorize("hasRole('ADMIN')")
    public Item actualizar(@PathVariable String category, @RequestBody @Valid Item item) {
        Item existente = detalle(category);
        existente.setCategory(item.getCategory());
        return repo.save(existente);
    }

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
