package softuni.exam.models.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "tickets")
public class Ticket extends BaseEntity{
    @Column(name = "serial_number",unique = true)
    private String serialNumber;
    @Column
    private BigDecimal price;
    @Column
    private LocalDateTime takeoff;

    @ManyToOne
    @JoinColumn(name = "from_town_id")
    private Town fromTown;

    @ManyToOne
    @JoinColumn(name = "to_town_id")
    private Town toTown;

    @ManyToOne
    private Plane plane;

    @ManyToOne
    private Passenger passenger;

    public Ticket() {
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

    public Town getFromTown() {
        return fromTown;
    }

    public void setFromTown(Town fromTown) {
        this.fromTown = fromTown;
    }

    public Town getToTown() {
        return toTown;
    }

    public void setToTown(Town toTown) {
        this.toTown = toTown;
    }

    public Plane getPlane() {
        return plane;
    }

    public void setPlane(Plane plane) {
        this.plane = plane;
    }

    public Passenger getPassenger() {
        return passenger;
    }

    public void setPassenger(Passenger passenger) {
        this.passenger = passenger;
    }
}
