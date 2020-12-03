package softuni.exam.models.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "passengers")
public class Passenger extends BaseEntity{
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column
    private int age;
    @Column(name = "phone_number")
    private String phoneNumber;
    @Column(unique = true)
    private String email;

    @ManyToOne
    private Town town;

    @OneToMany(mappedBy = "passenger",fetch = FetchType.EAGER)
    private List<Ticket> passengers;

    public Passenger() {
    }

    public List<Ticket> getPassengers() {
        return passengers;
    }

    public void setPassengers(List<Ticket> passengers) {
        this.passengers = passengers;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Town getTown() {
        return town;
    }

    public void setTown(Town town) {
        this.town = town;
    }
}
