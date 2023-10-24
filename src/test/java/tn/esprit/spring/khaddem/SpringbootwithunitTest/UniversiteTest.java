package tn.esprit.spring.khaddem.SpringbootwithunitTest;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import tn.esprit.spring.khaddem.entities.Universite;

class UniversiteTest {

    @Test
    void testUniversiteEntity() {
        Universite universite = new Universite();
        universite.setNomUniv("Test University");
        universite.setAdresse("Test Location");

        assertEquals("Test University", universite.getNomUniv());
        assertEquals("Test Location", universite.getAdresse());
    }
}
