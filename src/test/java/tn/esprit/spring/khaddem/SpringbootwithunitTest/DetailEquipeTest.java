package tn.esprit.spring.khaddem.SpringbootwithunitTest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import tn.esprit.spring.khaddem.entities.DetailEquipe;

 class DetailEquipeTest {

    @Test
    void testDetailEquipeEntity() {
        DetailEquipe detailEquipe = new DetailEquipe();
        detailEquipe.setSalle(123);
        detailEquipe.setThematique("Test Thematique");

        assertEquals(123, detailEquipe.getSalle());
        assertEquals("Test Thematique", detailEquipe.getThematique());
    }

}
