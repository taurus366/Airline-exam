package softuni.exam.models.dto.xml.passenger;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "passenger")
@XmlAccessorType(XmlAccessType.FIELD)
public class PassengerImportXMLDto {

    @XmlElement(name = "email")
    private String email;

    public PassengerImportXMLDto() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
