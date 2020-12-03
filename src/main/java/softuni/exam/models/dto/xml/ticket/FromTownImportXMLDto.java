package softuni.exam.models.dto.xml.ticket;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "from-town")
@XmlAccessorType(XmlAccessType.FIELD)
public class FromTownImportXMLDto {

    @XmlElement(name = "name")
    private String name;

    public FromTownImportXMLDto() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
