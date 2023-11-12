package tn.esprit.spring.khaddem.SpringbootwithunitTest;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tn.esprit.spring.khaddem.controllers.UniversiteRestController;
import tn.esprit.spring.khaddem.dto.UniversiteDTO;
import tn.esprit.spring.khaddem.entities.Departement;
import tn.esprit.spring.khaddem.entities.Universite;
import tn.esprit.spring.khaddem.repositories.DepartementRepository;
import tn.esprit.spring.khaddem.repositories.UniversiteRepository;
import tn.esprit.spring.khaddem.services.UniversiteServiceImpl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class UniversiteServiceTest {

    @InjectMocks
    private UniversiteServiceImpl universiteService;

    @Mock
    private UniversiteRepository universiteRepository;

    @Mock
    private DepartementRepository departementRepository;

    @Test
    void testRetrieveAllUniversites_ShouldReturnEmptyList() {
        List<Universite> expectedUniversites = new ArrayList<>();
        when(universiteRepository.findAll()).thenReturn(expectedUniversites);

        List<Universite> actualUniversites = universiteService.retrieveAllUniversites();

        assertEquals(expectedUniversites, actualUniversites);
    }

    @Test
    void testRetrieveUniversite_ShouldReturnUniversiteById() {
        int universiteId = 1;
        Universite expectedUniversite = new Universite();
        when(universiteRepository.findById(universiteId)).thenReturn(Optional.of(expectedUniversite));

        Universite actualUniversite = universiteService.retrieveUniversite(universiteId);

        assertEquals(expectedUniversite, actualUniversite);
    }

    @Test
    void testAddUniversite_ShouldAddUniversite() {
        UniversiteDTO universiteDTO = new UniversiteDTO();
        universiteDTO.setNomUniv("Test University");
        universiteDTO.setAdresse("Test Location");

        Universite addedUniversite = new Universite();
        addedUniversite.setNomUniv(universiteDTO.getNomUniv());
        addedUniversite.setAdresse(universiteDTO.getAdresse());

        when(universiteRepository.save(addedUniversite)).thenReturn(addedUniversite);

        Universite actualUniversite = universiteService.addUniversite(addedUniversite);

        assertEquals(addedUniversite, actualUniversite);
    }

    @Test
    void testUpdateUniversite_ShouldUpdateUniversite() {
        // Prepare the input data
        int universiteId = 1;
        String updatedName = "Test University";
        String updatedAddress = "Test Location";
        UniversiteDTO universiteDTO = new UniversiteDTO();
        universiteDTO.setIdUniversite(universiteId);
        universiteDTO.setNomUniv(updatedName);
        universiteDTO.setAdresse(updatedAddress);

        // Mock the behavior of the service
        Universite existingUniversite = new Universite();
        existingUniversite.setIdUniversite(universiteId);
        existingUniversite.setNomUniv("Old University");
        existingUniversite.setAdresse("Old Location");

        when(universiteRepository.findByNomUniv(updatedName)).thenReturn(Optional.of(existingUniversite));
        when(universiteRepository.save(any(Universite.class))).thenAnswer(invocation -> invocation.getArgument(0));

        // Create an instance of the controller
        UniversiteRestController universiteController = new UniversiteRestController(universiteService);

        // Call the method you want to test
        UniversiteDTO updatedUniversiteDTO = universiteController.updateUniversite(universiteDTO);

        // Assert the result
        assertEquals(updatedName, updatedUniversiteDTO.getNomUniv());
        assertEquals(updatedAddress, updatedUniversiteDTO.getAdresse());
    }




    @Test
    void testRemoveUniversite_ShouldRemoveUniversiteById() {
        // Create an existing university
        Universite existingUniversite = new Universite();
        existingUniversite.setIdUniversite(1);
        existingUniversite.setNomUniv("Test University");
        existingUniversite.setAdresse("Test Location");

        // Mock the repository call
        // Remove the unnecessary stubbing for findById(1)
        // when(universiteRepository.findById(1)).thenReturn(Optional.of(existingUniversite));

        // Call the service method
        universiteService.removeUniversite(1);

        // Verify that the deleteById method was called with the correct ID
        verify(universiteRepository, times(1)).deleteById(1);
    }



    @Test
    void testAssignUniversiteToDepartement_ShouldAssignDepartementToUniversite() {
        // Create an existing university
        Universite existingUniversite = new Universite();
        existingUniversite.setIdUniversite(1);
        existingUniversite.setNomUniv("Test University");
        existingUniversite.setAdresse("Test Location");

        // Create an existing department
        Departement existingDepartement = new Departement();
        existingDepartement.setIdDepartement(1);
        existingDepartement.setNomDepart("Test Department");

        // Mock the repository calls
        when(universiteRepository.findById(1)).thenReturn(Optional.of(existingUniversite));
        when(departementRepository.findById(1)).thenReturn(Optional.of(existingDepartement));

        // Call the service method
        universiteService.assignUniversiteToDepartement(1, 1);

        // Add assertions to check if the department is assigned to the university
        assertNotNull(existingUniversite.getDepartements());
        assertTrue(existingUniversite.getDepartements().contains(existingDepartement));
    }

    @Test
    void testUniversiteAllArgsConstructor() {
        // Create an object using the all-args constructor
        Universite universite = new Universite(1, "Test University", "Test Location", new ArrayList<>());

        // Verify the state of the object
        assertEquals(1, universite.getIdUniversite());
        assertEquals("Test University", universite.getNomUniv());
        assertEquals("Test Location", universite.getAdresse());
        assertTrue(universite.getDepartements().isEmpty()); // Assuming your departements list is initially empty
    }

}

