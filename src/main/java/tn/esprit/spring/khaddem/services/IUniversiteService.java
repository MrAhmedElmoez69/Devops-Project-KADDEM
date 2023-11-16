package tn.esprit.spring.khaddem.services;

import tn.esprit.spring.khaddem.dto.UniversiteDTO;
import tn.esprit.spring.khaddem.entities.Universite;

import java.util.List;

public interface IUniversiteService {

    List<Universite> retrieveAllUniversites();

    Universite addUniversite (Universite u);

    UniversiteDTO updateUniversite (UniversiteDTO updatedUniversiteDTO);

    Universite retrieveUniversite (Integer idUniversite);

    void removeUniversite (Integer idUniversite);

    void assignUniversiteToDepartement(Integer universiteId, Integer departementId);
}