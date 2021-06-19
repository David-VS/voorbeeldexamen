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
}
