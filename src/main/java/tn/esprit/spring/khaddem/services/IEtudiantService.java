package tn.esprit.spring.khaddem.services;

import tn.esprit.spring.khaddem.entities.Etudiant;
import tn.esprit.spring.khaddem.entities.Niveau;
import tn.esprit.spring.khaddem.entities.Specialite;

import java.util.List;

public interface IEtudiantService {

    List<Etudiant> retrieveAllEtudiants();
    Etudiant addEtudiant(Etudiant e);
    Etudiant updateEtudiant(Etudiant e);
    Etudiant retrieveEtudiant(Integer idEtudiant);
    void removeEtudiant(Integer idEtudiant);

    public void assignEtudiantToDepartement(Integer etudiantId, Integer departementId) ;


}
