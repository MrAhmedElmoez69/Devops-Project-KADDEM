package SpringbootwithunitTest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import tn.esprit.spring.khaddem.entities.Departement;
import tn.esprit.spring.khaddem.repositories.DepartementRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
class DepartementRepositoryTest {

    @Autowired
    private DepartementRepository departementRepository;

    @Test
    void testSaveDepartement() {
        // Create a Departement
        Departement departement = new Departement();
        departement.setNomDepart("Test Departement");

        // Save the Departement to the repository
        departementRepository.save(departement);

        // Retrieve the saved Departement
        Departement savedDepartement = departementRepository.findById(departement.getIdDepartement()).orElse(null);

        // Check if the saved Departement exists and has the correct name
        assertNotNull(savedDepartement);
        assertEquals("Test Departement", savedDepartement.getNomDepart());
    }

}
