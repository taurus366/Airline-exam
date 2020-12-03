package softuni.exam.models.dto.xml.plane;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "planes")
@XmlAccessorType(XmlAccessType.FIELD)
public class PlaneRootImportDto {

    @XmlElement(name = "plane")
    private List<PlaneImportXMLDto> importXMLDtos;

    public PlaneRootImportDto() {
    }

    public List<PlaneImportXMLDto> getImportXMLDtos() {
        return importXMLDtos;
    }

    public void setImportXMLDtos(List<PlaneImportXMLDto> importXMLDtos) {
        this.importXMLDtos = importXMLDtos;
    }
}
