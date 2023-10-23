package tn.esprit.spring.khaddem.SpringbootwithunitTest;


import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import tn.esprit.spring.khaddem.entities.Departement;

 class DepartementTest {

    @Test
     void testDepartementEntity() {
        Departement departement = new Departement();
        departement.setIdDepartement(1);
        departement.setNomDepart("Test Department");

        assertEquals(1, departement.getIdDepartement());
        assertEquals("Test Department", departement.getNomDepart());
    }
}