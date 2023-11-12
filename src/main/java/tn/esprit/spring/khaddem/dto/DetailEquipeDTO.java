package tn.esprit.spring.khaddem.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import tn.esprit.spring.khaddem.entities.Equipe;

import javax.persistence.OneToOne;

@Getter
@Setter
public class DetailEquipeDTO {
    private Integer idDetailEquipe;
    private Integer salle;
    private String thematique;

}






