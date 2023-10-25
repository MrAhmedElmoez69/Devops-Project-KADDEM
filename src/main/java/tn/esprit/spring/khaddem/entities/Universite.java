package tn.esprit.spring.khaddem.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Universite implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idUniversite;
    // Add getters and setters for nomUniv and adresse
    @Getter
    private String nomUniv;
    @Getter
    private String adresse;
    @Getter
    @OneToMany(mappedBy = "universite", cascade = CascadeType.ALL)
    private List<Departement> departements;

    public void setNomUniv(String nomUniv) {
        this.nomUniv = nomUniv;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

}