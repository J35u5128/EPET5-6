package org.example.epet56.Entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "store")
public class Item {
    @Id
    private String _id;
    private String title;
    private Double price;
    private String category;
    private String description;
    private double rate;
    private int count;
    private String color;
    private String manufacturer;
    private int ean;
    private String image;

    public Item() {
    }

    public Item(String _id, String category) {
        this._id = _id;
        this.category = category;
    }

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

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public int getEan() {
        return ean;
    }

    public void setEan(int ean) {
        this.ean = ean;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
