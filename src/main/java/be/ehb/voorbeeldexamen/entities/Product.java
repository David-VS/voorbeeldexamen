package be.ehb.voorbeeldexamen.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@JsonIgnoreProperties("owner")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotBlank
    private String naam;
    @DecimalMin(value = "0.5")
    private double startprijs;
    @Future
    private LocalDateTime eindtijd;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id")
    private Persoon owner;

    public Product() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public double getStartprijs() {
        return startprijs;
    }

    public void setStartprijs(double startprijs) {
        this.startprijs = startprijs;
    }

    public LocalDateTime getEindtijd() {
        return eindtijd;
    }

    public void setEindtijd(LocalDateTime eindtijd) {
        this.eindtijd = eindtijd;
    }

    public Persoon getOwner() {
        return owner;
    }

    public void setOwner(Persoon owner) {
        this.owner = owner;
    }
}
