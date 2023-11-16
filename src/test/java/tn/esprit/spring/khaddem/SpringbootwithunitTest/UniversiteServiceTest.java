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
import java.util.NoSuchElementException;
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
        int universiteId = 1;
        String updatedName = "Test University";
        String updatedAddress = "Test Location";
        UniversiteDTO universiteDTO = new UniversiteDTO();
        universiteDTO.setIdUniversite(universiteId);
        universiteDTO.setNomUniv(updatedName);
        universiteDTO.setAdresse(updatedAddress);

        Universite existingUniversite = new Universite();
        existingUniversite.setIdUniversite(universiteId);
        existingUniversite.setNomUniv("Old University");
        existingUniversite.setAdresse("Old Location");

        when(universiteRepository.findByNomUniv(updatedName)).thenReturn(Optional.of(existingUniversite));
        when(universiteRepository.save(any(Universite.class))).thenAnswer(invocation -> invocation.getArgument(0));

        UniversiteRestController universiteController = new UniversiteRestController(universiteService);

        UniversiteDTO updatedUniversiteDTO = universiteController.updateUniversite(universiteDTO);

        assertEquals(updatedName, updatedUniversiteDTO.getNomUniv());
        assertEquals(updatedAddress, updatedUniversiteDTO.getAdresse());
    }

    @Test
    void testUpdateUniversite_NomUnivNotFound() {
        when(universiteRepository.findByNomUniv(anyString())).thenReturn(Optional.empty());

        UniversiteDTO updatedUniversiteDTO = new UniversiteDTO();
        updatedUniversiteDTO.setNomUniv("NonExistentUniversity");
        updatedUniversiteDTO.setAdresse("New Address");

        assertThrows(NoSuchElementException.class, () -> universiteService.updateUniversite(updatedUniversiteDTO));

        verify(universiteRepository, times(1)).findByNomUniv("NonExistentUniversity");
    }


    @Test
    void testRemoveUniversite_ShouldRemoveUniversiteById() {
        // Create an existing university
        Universite existingUniversite = new Universite();
        existingUniversite.setIdUniversite(1);
        existingUniversite.setNomUniv("Test University");
        existingUniversite.setAdresse("Test Location");


        universiteService.removeUniversite(1);

        verify(universiteRepository, times(1)).deleteById(1);
    }



    @Test
    void testAssignUniversiteToDepartement_ShouldAssignDepartementToUniversite() {
        Universite existingUniversite = new Universite();
        existingUniversite.setIdUniversite(1);
        existingUniversite.setNomUniv("Test University");
        existingUniversite.setAdresse("Test Location");

        Departement existingDepartement = new Departement();
        existingDepartement.setIdDepartement(1);
        existingDepartement.setNomDepart("Test Department");

        when(universiteRepository.findById(1)).thenReturn(Optional.of(existingUniversite));
        when(departementRepository.findById(1)).thenReturn(Optional.of(existingDepartement));

        universiteService.assignUniversiteToDepartement(1, 1);

        assertNotNull(existingUniversite.getDepartements());
        assertTrue(existingUniversite.getDepartements().contains(existingDepartement));
    }
    @Test
    void testAssignUniversiteToDepartement_UniversiteOrDepartementNotFound() {
        when(universiteRepository.findById(anyInt())).thenReturn(Optional.empty());
        when(departementRepository.findById(anyInt())).thenReturn(Optional.empty());

        assertThrows(NoSuchElementException.class, () -> universiteService.assignUniversiteToDepartement(1, 1));

        verify(universiteRepository, times(1)).findById(1);
        verify(departementRepository, times(1)).findById(1);
    }

    @Test
    void testAssignUniversiteToDepartement_BothEntitiesFound() {
        Universite universite = new Universite();
        when(universiteRepository.findById(1)).thenReturn(Optional.of(universite));

        Departement departement = new Departement();
        when(departementRepository.findById(1)).thenReturn(Optional.of(departement));

        universiteService.assignUniversiteToDepartement(1, 1);

        assertTrue(universite.getDepartements().contains(departement));
    }


    @Test
    void testUniversiteAllArgsConstructor() {
        Universite universite = new Universite(1, "Test University", "Test Location", new ArrayList<>());

        assertEquals(1, universite.getIdUniversite());
        assertEquals("Test University", universite.getNomUniv());
        assertEquals("Test Location", universite.getAdresse());
        assertTrue(universite.getDepartements().isEmpty());
    }


}