package softuni.exam.models.dto.json;

import com.google.gson.annotations.Expose;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;

public class PassengerImportDto {

    @Expose
    @Length(min = 2)
    private String firstName;
    @Expose
    @Length(min = 2)
    private String lastName;
    @Expose
    @Min(value = 1)
    private int age;
    @Expose
    private String phoneNumber;
    @Expose
    @Email(regexp = "^[\\w.]+@[\\w]{3,}.[a-zA-Z]{3,}$")
    private String email;
    @Expose
    private String town;


    public PassengerImportDto() {
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

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }
}
