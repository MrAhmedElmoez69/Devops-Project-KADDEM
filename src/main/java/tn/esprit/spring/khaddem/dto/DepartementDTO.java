package tn.esprit.spring.khaddem.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DepartementDTO {
    private Integer idDepartement;
    private String nomDepart;
    private UniversiteDTO universite;
}