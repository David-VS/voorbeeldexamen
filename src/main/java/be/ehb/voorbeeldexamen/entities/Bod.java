package be.ehb.voorbeeldexamen.entities;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
public class Bod {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotBlank
    private double prijs;
    @OneToOne
    @JoinColumn(name = "productId")
    private Product productId;
    @OneToOne
    @JoinColumn(name = "persoonID")
    private Persoon persoonId;

    public Bod() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getPrijs() {
        return prijs;
    }

    public void setPrijs(double prijs) {
        this.prijs = prijs;
    }

    public Product getProductId() {
        return productId;
    }

    public void setProductId(Product productId) {
        this.productId = productId;
    }

    public Persoon getPersoonId() {
        return persoonId;
    }

    public void setPersoonId(Persoon persoonId) {
        this.persoonId = persoonId;
    }
}
