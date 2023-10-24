package tn.esprit.spring.khaddem.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DetailEquipe implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idDetailEquipe;
    private Integer salle;
    private String thematique;
    @OneToOne(mappedBy = "detailEquipe")
    @JsonIgnore
    private  Equipe equipe;
    //aaaaa

    public Integer getIdDetailEquipe() {
        return idDetailEquipe;
    }

    public void setIdDetailEquipe(Integer idDetailEquipe) {
        this.idDetailEquipe = idDetailEquipe;
    }

    public void setSalle(Integer salle) {
        this.salle = salle;
    }

    public void setThematique(String thematique) {
        this.thematique = thematique;
    }

    public Integer getSalle() {
        return salle;
    }

    public String getThematique() {
        return thematique;
    }
}
