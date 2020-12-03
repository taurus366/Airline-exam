package softuni.exam.models.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "towns")
public class Town extends BaseEntity{
    @Column
    private String name;
    @Column
    private int population;
    @Column
    private String guide;


    @OneToMany(mappedBy = "town")
    private List<Passenger> passengers;



    public List<Passenger> getPassengers() {
        return passengers;
    }

    public void setPassengers(List<Passenger> passengers) {
        this.passengers = passengers;
    }



    public Town() {
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    public String getGuide() {
        return guide;
    }

    public void setGuide(String guide) {
        this.guide = guide;
    }
}
