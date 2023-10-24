package tn.esprit.spring.khaddem.SpringbootwithunitTest;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;
import tn.esprit.spring.khaddem.dto.DepartementDTO;
import tn.esprit.spring.khaddem.entities.Departement;
import tn.esprit.spring.khaddem.entities.Universite;
import tn.esprit.spring.khaddem.repositories.DepartementRepository;
import tn.esprit.spring.khaddem.repositories.UniversiteRepository;
import tn.esprit.spring.khaddem.services.DepartementServiceImpl;

import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.ArrayList;
import java.util.List;


import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@ExtendWith(MockitoExtension.class)
class DepartementServiceTest {

    @InjectMocks
    private DepartementServiceImpl departementService;

    @Mock
    private DepartementRepository departementRepository;

    @Mock
    private UniversiteRepository universiteRepository;


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




}