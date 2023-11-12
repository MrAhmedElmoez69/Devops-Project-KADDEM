package tn.esprit.spring.khaddem.services;

import tn.esprit.spring.khaddem.entities.Universite;

import java.util.List;

public interface IUniversiteService {

    List<Universite> retrieveAllUniversites();

    Universite addUniversite (Universite u);

    Universite retrieveUniversite (Integer idUniversite);

}