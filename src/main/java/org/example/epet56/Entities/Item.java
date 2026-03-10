package org.example.epet56.Entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Representa un artículo (item) en la tienda.
 * Esta entidad se mapea a la colección "store" en MongoDB.
 */
@Document(collection = "store")
public class Item {
    /**
     * Identificador único del artículo.
     * Mapeado al campo "_id" en la base de datos MongoDB.
     */
    @Id
    private String _id;
    /** Título o nombre del artículo. */
    private String title;
    /** Precio del artículo. */
    private Double price;
    /** Categoría a la que pertenece el artículo. */
    private String category;
    /** Descripción detallada del artículo. */
    private String description;
    /** Calificación o puntuación del artículo. */
    private double rate;
    /** Cantidad de unidades en stock del artículo. */
    private int count;
    /** Color del artículo. */
    private String color;
    /** Fabricante del artículo. */
    private String manufacturer;
    /** Código EAN (European Article Number) del artículo. */
    private int ean;
    /** URL de la imagen del artículo. */
    private String image;

    /**
     * Constructor por defecto.
     */
    public Item() {
    }

    /**
     * Constructor para crear un Item con ID y categoría.
     * @param _id El identificador único del artículo.
     * @param category La categoría del artículo.
     */
    public Item(String _id, String category) {
        this._id = _id;
        this.category = category;
    }

    /**
     * Constructor completo para crear un Item con todos sus atributos.
     * @param _id Identificador único.
     * @param title Título del artículo.
     * @param price Precio del artículo.
     * @param category Categoría del artículo.
     * @param description Descripción del artículo.
     * @param rate Calificación del artículo.
     * @param count Stock disponible.
     * @param color Color del artículo.
     * @param manufacturer Fabricante del artículo.
     * @param ean Código EAN.
     * @param image URL de la imagen.
     */
    public Item(String _id, String title, Double price, String category, String description, double rate, int count, String color, String manufacturer, int ean, String image) {
        this._id = _id;
        this.title = title;
        this.price = price;
        this.category = category;
        this.description = description;
        this.rate = rate;
        this.count = count;
        this.color = color;
        this.manufacturer = manufacturer;
        this.ean = ean;
        this.image = image;
    }

    /**
     * Obtiene el ID del artículo.
     * @return El ID del artículo.
     */
    public String get_id() {
        return _id;
    }

    /**
     * Establece el ID del artículo.
     * @param _id El nuevo ID del artículo.
     */
    public void set_id(String _id) {
        this._id = _id;
    }

    /**
     * Obtiene el título del artículo.
     * @return El título del artículo.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Establece el título del artículo.
     * @param title El nuevo título del artículo.
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Obtiene el precio del artículo.
     * @return El precio del artículo.
     */
    public Double getPrice() {
        return price;
    }

    /**
     * Establece el precio del artículo.
     * @param price El nuevo precio del artículo.
     */
    public void setPrice(Double price) {
        this.price = price;
    }

    /**
     * Obtiene la categoría del artículo.
     * @return La categoría del artículo.
     */
    public String getCategory() {
        return category;
    }

    /**
     * Establece la categoría del artículo.
     * @param category La nueva categoría del artículo.
     */
    public void setCategory(String category) {
        this.category = category;
    }

    /**
     * Obtiene la descripción del artículo.
     * @return La descripción del artículo.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Establece la descripción del artículo.
     * @param description La nueva descripción del artículo.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Obtiene la calificación del artículo.
     * @return La calificación del artículo.
     */
    public double getRate() {
        return rate;
    }

    /**
     * Establece la calificación del artículo.
     * @param rate La nueva calificación del artículo.
     */
    public void setRate(double rate) {
        this.rate = rate;
    }

    /**
     * Obtiene la cantidad en stock del artículo.
     * @return La cantidad en stock.
     */
    public int getCount() {
        return count;
    }

    /**
     * Establece la cantidad en stock del artículo.
     * @param count La nueva cantidad en stock.
     */
    public void setCount(int count) {
        this.count = count;
    }

    /**
     * Obtiene el color del artículo.
     * @return El color del artículo.
     */
    public String getColor() {
        return color;
    }

    /**
     * Establece el color del artículo.
     * @param color El nuevo color del artículo.
     */
    public void setColor(String color) {
        this.color = color;
    }

    /**
     * Obtiene el fabricante del artículo.
     * @return El fabricante del artículo.
     */
    public String getManufacturer() {
        return manufacturer;
    }

    /**
     * Establece el fabricante del artículo.
     * @param manufacturer El nuevo fabricante del artículo.
     */
    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    /**
     * Obtiene el código EAN del artículo.
     * @return El código EAN.
     */
    public int getEan() {
        return ean;
    }

    /**
     * Establece el código EAN del artículo.
     * @param ean El nuevo código EAN.
     */
    public void setEan(int ean) {
        this.ean = ean;
    }

    /**
     * Obtiene la URL de la imagen del artículo.
     * @return La URL de la imagen.
     */
    public String getImage() {
        return image;
    }

    /**
     * Establece la URL de la imagen del artículo.
     * @param image La nueva URL de la imagen.
     */
    public void setImage(String image) {
        this.image = image;
    }
}