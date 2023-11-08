package tn.esprit.spring.khaddem.SpringbootwithunitTest;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;
import tn.esprit.spring.khaddem.controllers.DepartementRestController;
import tn.esprit.spring.khaddem.dto.DepartementDTO;
import tn.esprit.spring.khaddem.entities.Departement;
import tn.esprit.spring.khaddem.repositories.DepartementRepository;
import tn.esprit.spring.khaddem.services.DepartementServiceImpl;
import tn.esprit.spring.khaddem.services.IDepartementService;

import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@ExtendWith(MockitoExtension.class)
class DepartementServiceTest {

    @InjectMocks
    private DepartementServiceImpl departementService;

    @Mock
    private DepartementRepository departementRepository;



    @Test
    void testRetrieveAllDepartements() {
        List<Departement> expectedDepartements = new ArrayList<>();
        when(departementRepository.findAll()).thenReturn(expectedDepartements);

        List<Departement> actualDepartements = departementService.retrieveAllDepartements();

        assertEquals(expectedDepartements, actualDepartements);
    }

    @Test
    void testRetrieveDepartement() {
        int departementId = 1;
        Departement expectedDepartement = new Departement();
        when(departementRepository.findById(departementId)).thenReturn(java.util.Optional.of(expectedDepartement));

        Departement actualDepartement = departementService.retrieveDepartement(departementId);

        assertEquals(expectedDepartement, actualDepartement);
    }

    @Test
    void testAddDepartement() {
        Departement departement = new Departement();
        departement.setNomDepart("New Department");
        when(departementRepository.save(departement)).thenReturn(departement);

        Departement addedDepartement = departementService.addDepartement(departement);

        assertEquals(departement, addedDepartement);
    }

    @Test
    void testRetrieveDepartementsByUniversite() {
        int idUniversite = 1;
        List<Departement> expectedDepartements = new ArrayList<>();
        IDepartementService departementService = Mockito.mock(IDepartementService.class);
        when(departementService.retrieveDepartementsByUniversite(idUniversite)).thenReturn(expectedDepartements);
        DepartementRestController departementController = new DepartementRestController(departementService);
        List<Departement> actualDepartements = departementController.retrieveDepartementsByUniversite(idUniversite);
        assertEquals(expectedDepartements, actualDepartements);
    }



    @Test
    void testUpdateDepartement() {
        int departementId = 1;
        String updatedName = "Updated Department";
        DepartementDTO departementDTO = new DepartementDTO();
        departementDTO.setIdDepartement(departementId);
        departementDTO.setNomDepart(updatedName);

        Departement existingDepartement = new Departement();
        existingDepartement.setIdDepartement(departementId);
        existingDepartement.setNomDepart("Old Department");

        when(departementRepository.findById(departementId)).thenReturn(Optional.of(existingDepartement));

        DepartementRestController departementController = new DepartementRestController(departementService);

        Departement updatedDepartement = departementController.updateDepartement(departementDTO);

        assertEquals(updatedName, updatedDepartement.getNomDepart());
    }




}

