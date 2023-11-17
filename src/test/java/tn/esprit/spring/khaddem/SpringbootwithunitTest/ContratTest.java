package tn.esprit.spring.khaddem.SpringbootwithunitTest;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import tn.esprit.spring.khaddem.controllers.ContratRestController;
import tn.esprit.spring.khaddem.dto.ContratDTO;
import tn.esprit.spring.khaddem.entities.Contrat;
import tn.esprit.spring.khaddem.entities.Etudiant;
import tn.esprit.spring.khaddem.entities.Specialite;
import tn.esprit.spring.khaddem.services.IContratService;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
class ContratTest {



    private Contrat contrat1;
    private Contrat contrat2;
    @Mock
    private IContratService contratService;

    @InjectMocks
    private ContratRestController contratRestController;

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
        // Create two Contrat objects for testing
        contrat1 = new Contrat();
        contrat1.setIdContrat(1);
        contrat1.setDateDebutContrat(new Date());
        contrat1.setDateFinContrat(new Date());
        contrat1.setSpecialite(Specialite.IA);
        contrat1.setArchived(true);
        contrat1.setMontantContrat(1000);
        contrat1.setEtudiant(new Etudiant());

        contrat2 = new Contrat();
        contrat2.setIdContrat(1);
        contrat2.setDateDebutContrat(new Date());
        contrat2.setDateFinContrat(new Date());
        contrat2.setSpecialite(Specialite.IA);
        contrat2.setArchived(true);
        contrat2.setMontantContrat(1000);
        contrat2.setEtudiant(new Etudiant());
    }

    @Test
     void testGetters() {
        assertEquals(1, contrat1.getIdContrat());
        assertEquals(true, contrat1.getArchived());
        assertEquals(1000, contrat1.getMontantContrat());
        // Add more getter tests for other properties
    }

    @Test
     void testSetters() {
        contrat1.setIdContrat(2);
        contrat1.setArchived(false);
        contrat1.setMontantContrat(2000);
        // Add more setter tests for other properties

        assertEquals(2, contrat1.getIdContrat());
        assertEquals(false, contrat1.getArchived());
        assertEquals(2000, contrat1.getMontantContrat());
    }


    @Test
    void testContratEntity() {
        Contrat contrat = new Contrat();
        contrat.setIdContrat(1);

        Calendar calendarDebut = Calendar.getInstance();
        calendarDebut.set(2024, Calendar.JANUARY, 1);
        contrat.setDateDebutContrat(calendarDebut.getTime());

        Calendar calendarFin = Calendar.getInstance();
        calendarFin.set(2024, Calendar.FEBRUARY, 29); // Note: 2024 is a leap year, so February has 29 days.
        contrat.setDateFinContrat(calendarFin.getTime());

        contrat.setSpecialite(Specialite.IA);
        contrat.setArchived(true);
        contrat.setMontantContrat(123);

        assertEquals(1, contrat.getIdContrat());
        assertEquals(Specialite.IA, contrat.getSpecialite());
        assertEquals(true, contrat.getArchived());
        assertEquals(123, contrat.getMontantContrat());
    }

    @Test
    void testGetContrats() {
        // Mock the service method
        List<Contrat> contratList = new ArrayList<>();
        Contrat contrat1 = new Contrat();
        // Initialize contrat1 fields
        contratList.add(contrat1);
        Mockito.when(contratService.retrieveAllContrats()).thenReturn(contratList);

        // Perform the test
        List<Contrat> contrats = contratRestController.getContrats();

        // Assert the results
        assertEquals(1, contrats.size());
        // Add more assertions as needed
    }


    @Test
    void testRetrieveContrat() {
        // Mock the service method
        int contratId = 1; // Provide a valid contract ID
        Contrat contrat = new Contrat();
        // Initialize contrat fields
        Mockito.when(contratService.retrieveContrat(contratId)).thenReturn(contrat);

        // Perform the test
        Contrat retrievedContrat = contratRestController.retrieveContrat(contratId);

        // Assert the results
        assertNotNull(retrievedContrat);
        // Add more assertions as needed
    }


    @Test
    void testAddContrat() throws Exception {
        // Prepare a ContratDTO with data
        ContratDTO contratDTO = new ContratDTO();
        // Set the fields in contratDTO

        // Convert ContratDTO to JSON
        ObjectMapper objectMapper = new ObjectMapper();
        String contratJson = objectMapper.writeValueAsString(contratDTO);

        // Mock the service method
        Contrat addedContrat = new Contrat();
        // Set the fields in addedContrat to match the expected result
        addedContrat.setIdContrat(1); // Provide the expected ID
        // Set other fields as needed

        Mockito.when(contratService.addContrat(Mockito.any(Contrat.class))).thenReturn(addedContrat);

        // Perform the test
        mockMvc.perform(MockMvcRequestBuilders.post("/contrat/add-contrat")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(contratJson))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(result -> {
                    String responseContent = result.getResponse().getContentAsString();
                    Contrat returnedContrat = objectMapper.readValue(responseContent, Contrat.class);

                    // Validate the response data
                    assertEquals(addedContrat.getIdContrat(), returnedContrat.getIdContrat());
                    // Add more validation as needed
                });
    }


    @Test
    void testSpecialiteEnum() {
        // Test if the Specialite enum values match the expected values
        assertEquals(Specialite.IA, Specialite.valueOf("IA"));
        assertEquals(Specialite.RESEAU, Specialite.valueOf("RESEAU"));
        assertEquals(Specialite.CLOUD, Specialite.valueOf("CLOUD"));
        assertEquals(Specialite.SECURITE, Specialite.valueOf("SECURITE"));
    }

    @Test
    void testContratDTO() {
        ContratDTO contratDTO = new ContratDTO();
        contratDTO.setIdContrat(1);
        contratDTO.setDateDebutContrat(new Date());
        contratDTO.setDateFinContrat(new Date());
        contratDTO.setSpecialite(Specialite.IA);
        contratDTO.setArchived(true);
        contratDTO.setMontantContrat(1000);

        assertEquals(1, contratDTO.getIdContrat());
        assertEquals(Specialite.IA, contratDTO.getSpecialite());
        assertEquals(true, contratDTO.getArchived());
        assertEquals(1000, contratDTO.getMontantContrat());

    }

}



 