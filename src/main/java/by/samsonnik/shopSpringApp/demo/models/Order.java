package by.samsonnik.shopSpringApp.demo.models;

import jakarta.persistence.*;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Entity
@Component
@Table(name = "orders")
public class Order {

    @Id
    @Column(name = "order_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "client_id")
    private int clientId;

    @Column(name = "product_id")
    private int productId;

    @Column(name = "product_number")
    private int productNumber;

    @Column(name = "total_cost")
    private double totalCost;

    @Column(name = "town_to_delivery")
    private String townToDelivery;

    @Column(name = "client_phone")
    private String clientPhone;

    @Column(name = "client_address")
    private String clientAddress;

    @Column(name = "client_email")
    private String clientEmail;

    public Order() {
    }

    public Order(int clientId, int productId, int productNumber, double totalCost, String townToDelivery,
                 String clientPhone, String clientAddress, String clientEmail) {
        this.clientId = clientId;
        this.productId = productId;
        this.productNumber = productNumber;
        this.totalCost = totalCost;
        this.townToDelivery = townToDelivery;
        this.clientPhone = clientPhone;
        this.clientAddress = clientAddress;
        this.clientEmail = clientEmail;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getProductNumber() {
        return productNumber;
    }

    public void setProductNumber(int productNumber) {
        this.productNumber = productNumber;
    }

    public double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }

    public String getTownToDelivery() {
        return townToDelivery;
    }

    public void setTownToDelivery(String townToDelivery) {
        this.townToDelivery = townToDelivery;
    }

    public String getClientPhone() {
        return clientPhone;
    }

    public void setClientPhone(String clientPhone) {
        this.clientPhone = clientPhone;
    }

    public String getClientAddress() {
        return clientAddress;
    }

    public void setClientAddress(String clientAddress) {
        this.clientAddress = clientAddress;
    }

    public String getClientEmail() {
        return clientEmail;
    }

    public void setClientEmail(String clientEmail) {
        this.clientEmail = clientEmail;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return id == order.id && clientId == order.clientId && productId == order.productId && productNumber == order.productNumber && Double.compare(order.totalCost, totalCost) == 0 && Objects.equals(townToDelivery, order.townToDelivery) && Objects.equals(clientPhone, order.clientPhone) && Objects.equals(clientAddress, order.clientAddress) && Objects.equals(clientEmail, order.clientEmail);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, clientId, productId, productNumber, totalCost, townToDelivery, clientPhone, clientAddress, clientEmail);
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", clientId=" + clientId +
                ", productId=" + productId +
                ", productNumber=" + productNumber +
                ", totalCost=" + totalCost +
                ", townToDelivery='" + townToDelivery + '\'' +
                ", clientPhone='" + clientPhone + '\'' +
                ", clientAddress='" + clientAddress + '\'' +
                ", clientEmail='" + clientEmail + '\'' +
                '}';
    }
}
