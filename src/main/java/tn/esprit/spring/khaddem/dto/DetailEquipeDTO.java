package tn.esprit.spring.khaddem.dto;

public class DetailEquipeDTO {


    private Integer idDetailEquipe;
    private Integer salle;
    private String thematique;



    public DetailEquipeDTO() {
    }

    public Integer getIdDetailEquipe() {
        return idDetailEquipe;
    }

    public void setIdDetailEquipe(Integer idDetailEquipe) {
        this.idDetailEquipe = idDetailEquipe;
    }

    public Integer getSalle() {
        return salle;
    }

    public void setSalle(Integer salle) {
        this.salle = salle;
    }

    public String getThematique() {
        return thematique;
    }

    public void setThematique(String thematique) {
        this.thematique = thematique;
    }
}






