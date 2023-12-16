package by.samsonnik.shopSpringApp.demo.models;

import jakarta.persistence.*;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Entity
@Component
@Table(name = "products")
public class Product {

    @Id
    @Column(name = "product_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "product_name")
    private String name;

    @Column(name = "product_type")
    private String type;

    @Column(name = "product_number")
    private int numberOnTheStore;

    @Column(name = "product_image")
    private String imagePath;

    @Column(name = "cost")
    private double cost;

    public Product() {
    }

    public Product(int id, String name, String type, int numberOnTheStore, String imagePath, double cost) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.numberOnTheStore = numberOnTheStore;
        this.imagePath = imagePath;
        this.cost = cost;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String type) {
        this.name = type;
    }

    public int getNumberOnTheStore() {
        return numberOnTheStore;
    }

    public void setNumberOnTheStore(int numberOnTheStore) {
        this.numberOnTheStore = numberOnTheStore;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return id == product.id && numberOnTheStore == product.numberOnTheStore && Objects.equals(name, product.name) && Objects.equals(type, product.type) && Objects.equals(imagePath, product.imagePath);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, type, numberOnTheStore, imagePath);
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", numberOnTheStore=" + numberOnTheStore +
                ", imagePath='" + imagePath + '\'' +
                ", cost=" + cost +
                '}';
    }
}
