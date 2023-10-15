package tn.esprit.spring.khaddem.services;

import tn.esprit.spring.khaddem.entities.DetailEquipe;

import java.util.List;

public interface IDetailEquipeService {
    List<DetailEquipe> retrieveAllDetailEquipes();

    DetailEquipe addDetailEquipe(DetailEquipe detailEquipe);

    DetailEquipe updateDetailEquipe(DetailEquipe detailEquipe);

    DetailEquipe retrieveDetailEquipe(Integer idDetailEquipe);
}
