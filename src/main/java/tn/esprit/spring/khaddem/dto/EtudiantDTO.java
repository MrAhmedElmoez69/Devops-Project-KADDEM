package tn.esprit.spring.khaddem.dto;

import lombok.Getter;
import lombok.Setter;
import tn.esprit.spring.khaddem.entities.Option;


@Getter
@Setter
public class EtudiantDTO {
    private Integer idEtudiant;
    private String prenomE;
    private String nomE;
    private Option op;
}