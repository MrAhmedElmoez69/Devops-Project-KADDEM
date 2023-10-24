package tn.esprit.spring.khaddem.SpringbootwithunitTest;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import tn.esprit.spring.khaddem.entities.Contrat;
import tn.esprit.spring.khaddem.entities.Specialite;

import java.util.Calendar;
import java.util.Date;

class ContratTest {

    @Test
    void testContratEntity() {
        Contrat contrat = new Contrat();
        contrat.setIdContrat(1);
        contrat.setDateDebutContrat(new Date(2024, Calendar.JANUARY, 1));
        contrat.setDateFinContrat(new Date(2024, Calendar.FEBRUARY, 30));
        contrat.setSpecialite(Specialite.IA);
        contrat.setArchived(true);
        contrat.setMontantContrat(123);

        assertEquals(1, contrat.getIdContrat());
        assertEquals(Specialite.IA, contrat.getSpecialite());
        assertEquals(true, contrat.getArchived());
        assertEquals(123, contrat.getMontantContrat());
    }
}

