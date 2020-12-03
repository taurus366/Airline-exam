package softuni.exam.models.dto.xml.ticket;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "tickets")
@XmlAccessorType(XmlAccessType.FIELD)
public class TicketRootImportDto {

    @XmlElement(name = "ticket")
    private List<TicketImportXMLDto> ticketImportXMLDtos;

    public TicketRootImportDto() {
    }

    public List<TicketImportXMLDto> getTicketImportXMLDtos() {
        return ticketImportXMLDtos;
    }

    public void setTicketImportXMLDtos(List<TicketImportXMLDto> ticketImportXMLDtos) {
        this.ticketImportXMLDtos = ticketImportXMLDtos;
    }
}
