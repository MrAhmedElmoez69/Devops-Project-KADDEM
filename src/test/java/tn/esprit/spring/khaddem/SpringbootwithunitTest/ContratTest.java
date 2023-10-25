package tn.esprit.spring.khaddem.SpringbootwithunitTest;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import tn.esprit.spring.khaddem.entities.Contrat;
import tn.esprit.spring.khaddem.entities.Specialite;

import java.util.Calendar;


class ContratTest {

    @Test
    void testContratEntity() {
        Contrat contrat = new Contrat();
        contrat.setIdContrat(1);

        Calendar calendarDebut = Calendar.getInstance();
        calendarDebut.set(2024, Calendar.JANUARY, 1);
        contrat.setDateDebutContrat(calendarDebut.getTime());

        Calendar calendarFin = Calendar.getInstance();
        calendarFin.set(2024, Calendar.FEBRUARY, 29); // Note: 2024 is a leap year, so February has 29 days.
        contrat.setDateFinContrat(calendarFin.getTime());

        contrat.setSpecialite(Specialite.IA);
        contrat.setArchived(true);
        contrat.setMontantContrat(123);

        assertEquals(1, contrat.getIdContrat());
        assertEquals(Specialite.IA, contrat.getSpecialite());
        assertEquals(true, contrat.getArchived());
        assertEquals(123, contrat.getMontantContrat());
    }
}

