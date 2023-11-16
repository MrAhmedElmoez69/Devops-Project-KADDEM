package tn.esprit.spring.khaddem.entities;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Universite implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idUniversite;
    private String nomUniv;
    private String adresse;

    @OneToMany(mappedBy = "universite", cascade = CascadeType.ALL)
    private List<Departement> departements;
    @Override
    public String toString() {
        return "Universite{" +
                "idUniversite=" + idUniversite +
                ", nomUniv='" + nomUniv + '\'' +
                ", adresse='" + adresse + '\'' +
                ", departements=" + departements +
                '}';
    }

}