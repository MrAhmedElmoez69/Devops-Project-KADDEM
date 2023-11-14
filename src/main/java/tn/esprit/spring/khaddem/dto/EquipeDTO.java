package tn.esprit.spring.khaddem.dto;

import lombok.Getter;
import lombok.Setter;
import tn.esprit.spring.khaddem.entities.Niveau;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
@Getter
@Setter
public class EquipeDTO {
    private Integer idEquipe;
    private String nomEquipe;
    @Enumerated(EnumType.STRING)
    private Niveau niveau;
}
