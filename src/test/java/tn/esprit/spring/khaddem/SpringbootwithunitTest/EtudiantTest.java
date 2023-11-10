package tn.esprit.spring.khaddem.SpringbootwithunitTest;

import org.junit.jupiter.api.Test;
import tn.esprit.spring.khaddem.entities.Etudiant;

import static org.junit.jupiter.api.Assertions.assertEquals;

class EtudiantTest {
    @Test
    void testEtudiantEntity() {
        Etudiant etudiant = new Etudiant();
        etudiant.setIdEtudiant(1);
        etudiant.setNomE("Test Etudiant");

        assertEquals(1, etudiant.getIdEtudiant());
        assertEquals("Test Etudiant", etudiant.getNomE());
    }
}
