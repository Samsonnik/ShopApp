package by.samsonnik.shopSpringApp.demo.models;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Entity
@Component
@Table (name = "client")
public class Client {

    @Id
    @Column(name = "client_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "first_name")
    @Size(min = 3, message = "First name should be more 3 characters")
    private String firstName;

    @Column(name = "second_name")
    @Size(min = 3, message = "Second name should be more 3 characters")
    private String secondName;

    @Column(name = "email")
    @Size(min = 10, message = "Email should be more 3 characters")
    private String email;

    @Column(name = "town")
    @Size(min = 3, message = "Town should be more 3 characters")
    private String town;

    @Column(name = "age")
    private int age;

    @Column(name = "password")
    @Size(min = 5, message = "Your pass should be more than 5 characters")
    private String password;

    public Client() {
    }

    public Client(String firstName, String secondName, String email, String town, int age, String password) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.email = email;
        this.town = town;
        this.age = age;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Client client = (Client) o;
        return id == client.id && age == client.age && Objects.equals(firstName, client.firstName) && Objects.equals(secondName, client.secondName) && Objects.equals(email, client.email) && Objects.equals(town, client.town) && Objects.equals(password, client.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, secondName, email, town, age, password);
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", secondName='" + secondName + '\'' +
                ", email='" + email + '\'' +
                ", town='" + town + '\'' +
                ", age=" + age +
                ", password='" + password + '\'' +
                '}';
    }
}
