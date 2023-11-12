package tn.esprit.spring.khaddem.dto;

import lombok.Getter;
import lombok.Setter;
import tn.esprit.spring.khaddem.entities.Option;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Getter
@Setter
public class EtudiantDTO {
    private Integer idEtudiant;
    private String prenomE;
    private String nomE;
    @Enumerated(EnumType.STRING)
    private Option op;
}
