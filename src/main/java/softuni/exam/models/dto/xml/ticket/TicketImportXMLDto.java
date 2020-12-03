package softuni.exam.models.dto.xml.ticket;

import org.hibernate.validator.constraints.Length;
import softuni.exam.config.TakeOffXMLAdapter;
import softuni.exam.models.dto.xml.passenger.PassengerImportXMLDto;

import javax.validation.constraints.Min;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@XmlRootElement(name = "ticket")
@XmlAccessorType(XmlAccessType.FIELD)
public class TicketImportXMLDto {

    @XmlElement(name = "serial-number")
    @Length(min = 2)
    private String serialNumber;

    @XmlElement(name = "price")
    @Min(1)
    private BigDecimal price;

    @XmlElement(name = "take-off")
    @XmlJavaTypeAdapter(TakeOffXMLAdapter.class)
    private LocalDateTime takeoff;

    @XmlElement(name = "to-town")
    private ToTownImportXMLDto toTownImportXMLDto;

    @XmlElement(name = "from-town")
    private FromTownImportXMLDto fromTownImportXMLDto;

    @XmlElement(name = "passenger")
    private PassengerImportXMLDto passengerEmail;

    @XmlElement(name = "plane")
    private PlaneImportXMLDto2 registerNumber;


    public TicketImportXMLDto() {
    }

    public PassengerImportXMLDto getPassengerEmail() {
        return passengerEmail;
    }

    public void setPassengerEmail(PassengerImportXMLDto passengerEmail) {
        this.passengerEmail = passengerEmail;
    }

    public PlaneImportXMLDto2 getRegisterNumber() {
        return registerNumber;
    }

    public void setRegisterNumber(PlaneImportXMLDto2 registerNumber) {
        this.registerNumber = registerNumber;
    }

    public ToTownImportXMLDto getToTownImportXMLDto() {
        return toTownImportXMLDto;
    }

    public void setToTownImportXMLDto(ToTownImportXMLDto toTownImportXMLDto) {
        this.toTownImportXMLDto = toTownImportXMLDto;
    }

    public FromTownImportXMLDto getFromTownImportXMLDto() {
        return fromTownImportXMLDto;
    }

    public void setFromTownImportXMLDto(FromTownImportXMLDto fromTownImportXMLDto) {
        this.fromTownImportXMLDto = fromTownImportXMLDto;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public LocalDateTime getTakeoff() {
        return takeoff;
    }

    public void setTakeoff(LocalDateTime takeoff) {
        this.takeoff = takeoff;
    }
}
