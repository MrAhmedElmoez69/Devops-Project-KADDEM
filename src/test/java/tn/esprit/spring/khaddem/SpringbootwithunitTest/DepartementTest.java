package tn.esprit.spring.khaddem.SpringbootwithunitTest;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

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

    @Test
    void testDepartementEquality() {
        Departement departement1 = new Departement();
        departement1.setIdDepartement(1);
        departement1.setNomDepart("Department A");

        Departement departement2 = new Departement();
        departement2.setIdDepartement(1);
        departement2.setNomDepart("Department A");

        assertEquals(departement1, departement2);
    }


    @Test
    void testDepartementInequality() {
        Departement departement1 = new Departement();
        departement1.setIdDepartement(1);
        departement1.setNomDepart("Department A");

        Departement departement2 = new Departement();
        departement2.setIdDepartement(2);
        departement2.setNomDepart("Department B");

        assertNotEquals(departement1, departement2);
    }

    @Test
    void testSettersAndGetters() {
        Departement departement = new Departement();
        departement.setIdDepartement(1);
        departement.setNomDepart("Test Department");

        assertEquals(1, departement.getIdDepartement());
        assertEquals("Test Department", departement.getNomDepart());

    }

}
