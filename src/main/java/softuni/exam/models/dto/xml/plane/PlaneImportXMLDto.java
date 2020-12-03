package softuni.exam.models.dto.xml.plane;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Min;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "plane")
@XmlAccessorType(XmlAccessType.FIELD)
public class PlaneImportXMLDto {
    @XmlElement(name = "register-number")
    @Length(min = 5)
    private String registerNumber;
    @XmlElement(name = "capacity")
    @Min(value = 1)
    private int capacity;
    @XmlElement(name = "airline")
    @Length(min = 2)
    private String airline;

    public PlaneImportXMLDto() {
    }

    public String getRegisterNumber() {
        return registerNumber;
    }

    public void setRegisterNumber(String registerNumber) {
        this.registerNumber = registerNumber;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public String getAirline() {
        return airline;
    }

    public void setAirline(String airline) {
        this.airline = airline;
    }
}
