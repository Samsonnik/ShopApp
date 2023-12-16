package by.samsonnik.shopSpringApp.demo.models;


import jakarta.validation.constraints.Size;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class OrderDTO {

    @Size(min = 3, message = "Town should be more 3 characters")
    private String townToDelivery;

    @Size(min = 12, message = "Phone should be more 3 characters")
    private String clientPhone;

    @Size(min = 3, message = "Address should be more 3 characters")
    private String clientAddress;

    @Size(min = 12, message = "Phone should be more 3 characters")
    private String clientEmail;

    private boolean sendCheck;

    public OrderDTO() {
    }

    public OrderDTO(String townToDelivery, String clientPhone, String clientAddress, String clientEmail) {
        this.townToDelivery = townToDelivery;
        this.clientPhone = clientPhone;
        this.clientAddress = clientAddress;
        this.clientEmail = clientEmail;
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

    public boolean isSendCheck() {
        return sendCheck;
    }

    public void setSendCheck(boolean sendCheck) {
        this.sendCheck = sendCheck;
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
        OrderDTO orderDTO = (OrderDTO) o;
        return sendCheck == orderDTO.sendCheck && Objects.equals(townToDelivery, orderDTO.townToDelivery) && Objects.equals(clientPhone, orderDTO.clientPhone) && Objects.equals(clientAddress, orderDTO.clientAddress) && Objects.equals(clientEmail, orderDTO.clientEmail);
    }

    @Override
    public int hashCode() {
        return Objects.hash(townToDelivery, clientPhone, clientAddress, clientEmail, sendCheck);
    }

    @Override
    public String toString() {
        return "OrderDTO{" +
                "townToDelivery='" + townToDelivery + '\'' +
                ", clientPhone='" + clientPhone + '\'' +
                ", clientAddress='" + clientAddress + '\'' +
                ", clientEmail='" + clientEmail + '\'' +
                ", sendCheck=" + sendCheck +
                '}';
    }
}
