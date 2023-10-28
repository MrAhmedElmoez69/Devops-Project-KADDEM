package tn.esprit.spring.khaddem.dto;

import lombok.*;
import tn.esprit.spring.khaddem.entities.Departement;

import javax.persistence.CascadeType;
import javax.persistence.OneToMany;
import java.util.List;

@Getter
@Setter
public class UniversiteDTO {
    private String nomUniv;
    private String adresse;

    private Integer idUniversite;
    @OneToMany(mappedBy = "universite", cascade = CascadeType.ALL)
    private List<Departement> departements;
}
