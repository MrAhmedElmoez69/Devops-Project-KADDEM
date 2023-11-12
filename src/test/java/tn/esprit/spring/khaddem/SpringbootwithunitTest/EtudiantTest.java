package tn.esprit.spring.khaddem.SpringbootwithunitTest;

import org.junit.jupiter.api.Test;
import tn.esprit.spring.khaddem.entities.Etudiant;
import tn.esprit.spring.khaddem.entities.Option;

import static org.junit.jupiter.api.Assertions.assertEquals;

class EtudiantTest {
    @Test
    void testEtudiantEntity() {
        // Arrange
        Etudiant etudiant = new Etudiant();
        etudiant.setIdEtudiant(1);
        etudiant.setNomE("Test Etudiant");

        // Act & Assert
        assertEquals(1, etudiant.getIdEtudiant());
        assertEquals("Test Etudiant", etudiant.getNomE());
    }

    @Test
    void testEtudiantEntityWithOption() {
        // Arrange
        Etudiant etudiant = new Etudiant();
        etudiant.setIdEtudiant(1);
        etudiant.setNomE("Test Etudiant");
        etudiant.setOp(Option.GAMIX);

        // Act & Assert
        assertEquals(1, etudiant.getIdEtudiant());
        assertEquals("Test Etudiant", etudiant.getNomE());
        assertEquals(Option.GAMIX, etudiant.getOp());
    }

    @Test
    void testEtudiantEntityToString() {
        // Arrange
        Etudiant etudiant = new Etudiant();
        etudiant.setIdEtudiant(1);
        etudiant.setNomE("Test Etudiant");
        etudiant.setOp(Option.GAMIX);

        // Act & Assert
        String expectedToString = "Etudiant{" +
                "idEtudiant=" + etudiant.getIdEtudiant() +
                ", prenomE='" + etudiant.getPrenomE() + '\'' +
                ", nomE='" + etudiant.getNomE() + '\'' +
                ", op=" + etudiant.getOp() +
                ", departement=" + etudiant.getDepartement() +
                ", equipes=" + etudiant.getEquipes() +
                ", contrats=" + etudiant.getContrats() +
                '}';
        assertEquals(expectedToString, etudiant.toString());
    }

    @Test
    void testEtudiantEntityWithDifferentOption() {
        // Arrange
        Etudiant etudiant = new Etudiant();
        etudiant.setIdEtudiant(1);
        etudiant.setNomE("Test Etudiant");
        etudiant.setOp(Option.SAE);

        // Act & Assert
        assertEquals(1, etudiant.getIdEtudiant());
        assertEquals("Test Etudiant", etudiant.getNomE());
        assertEquals(Option.SAE, etudiant.getOp());
    }
}
