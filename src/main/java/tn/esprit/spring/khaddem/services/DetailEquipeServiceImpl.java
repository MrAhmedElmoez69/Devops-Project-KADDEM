package tn.esprit.spring.khaddem.services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import tn.esprit.spring.khaddem.entities.DetailEquipe;
import tn.esprit.spring.khaddem.repositories.DetailEquipeRepository;

import javax.transaction.Transactional;
import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class DetailEquipeServiceImpl implements IDetailEquipeService {

    DetailEquipeRepository detailEquipeRepository;

    @Override
    public List<DetailEquipe> retrieveAllDetailEquipes() {
        return detailEquipeRepository.findAll();
    }

    @Transactional
    public DetailEquipe addDetailEquipe(DetailEquipe detailEquipe) {
        detailEquipeRepository.save(detailEquipe);
        return detailEquipe;
    }

    @Override
    public DetailEquipe updateDetailEquipe(DetailEquipe detailEquipe) {
        detailEquipeRepository.save(detailEquipe);
        return detailEquipe;
    }

    @Override
    public DetailEquipe retrieveDetailEquipe(Integer idDetailEquipe) {
        return detailEquipeRepository.findById(idDetailEquipe).orElse(null);
    }
}
