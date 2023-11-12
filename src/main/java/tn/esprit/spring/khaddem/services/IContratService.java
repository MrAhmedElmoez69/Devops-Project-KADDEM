package tn.esprit.spring.khaddem.services;

import tn.esprit.spring.khaddem.entities.Contrat;

import java.util.Date;
import java.util.List;

public interface IContratService {
    List<Contrat> retrieveAllContrats();

    Contrat retrieveContrat(Integer idContrat);

    Contrat addContrat(Contrat c);


}

