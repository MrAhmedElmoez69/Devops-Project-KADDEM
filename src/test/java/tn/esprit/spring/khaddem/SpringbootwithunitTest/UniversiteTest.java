package tn.esprit.spring.khaddem.SpringbootwithunitTest;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import tn.esprit.spring.khaddem.dto.UniversiteDTO;
import tn.esprit.spring.khaddem.entities.Departement;
import tn.esprit.spring.khaddem.entities.Universite;
import tn.esprit.spring.khaddem.repositories.UniversiteRepository;
import tn.esprit.spring.khaddem.services.UniversiteServiceImpl;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@AutoConfigureMockMvc
@RunWith(MockitoJUnitRunner.class)
class UniversiteTest {


    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private UniversiteRepository universiteRepository;

    @Autowired
    private UniversiteServiceImpl universiteService;


    @Test
    void testUniversiteEntity() {
        Universite universite = new Universite();
        universite.setNomUniv("Test University");
        universite.setAdresse("Test Location");

        assertEquals("Test University", universite.getNomUniv());
        assertEquals("Test Location", universite.getAdresse());
    }

    @Test
    void testUniversiteDTO() {
        // Create a UniversiteDTO with sample data
        UniversiteDTO universiteDTO = new UniversiteDTO();
        universiteDTO.setIdUniversite(1);
        universiteDTO.setNomUniv("Test University");
        universiteDTO.setAdresse("123 Test Street");

        // Create a list of DepartementDTOs and set it in the UniversiteDTO
        List<Departement> departements = new ArrayList<>();
        Departement departement1 = new Departement();
        departement1.setIdDepartement(1);
        departement1.setNomDepart("Department A");
        departements.add(departement1);
        universiteDTO.setDepartements(departements);

        // Check if the getter methods return the expected values
        assertEquals(1, universiteDTO.getIdUniversite());
        assertEquals("Test University", universiteDTO.getNomUniv());
        assertEquals("123 Test Street", universiteDTO.getAdresse());

        // You can add more checks for other fields as needed
    }


    @Test
    void testGetUniversites() throws Exception {
        // Simulate an HTTP GET request to retrieve all universities
        mockMvc.perform(MockMvcRequestBuilders.get("/universite/retrieve-all-universites"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(result -> {
                    // Convert the JSON response to a list of Universite objects
                    String responseContent = result.getResponse().getContentAsString();
                    Universite[] universites = new ObjectMapper().readValue(responseContent, Universite[].class);

                    // Add your assertions here
                    // For example, check the number of universities, or specific university details
                });
    }

    @Test
    void testAddUniversite() throws Exception {
        // Create a UniversiteDTO with sample data
        UniversiteDTO universiteDTO = new UniversiteDTO();
        universiteDTO.setNomUniv("Test University");
        universiteDTO.setAdresse("Test Location");

        // Convert UniversiteDTO to JSON
        ObjectMapper objectMapper = new ObjectMapper();
        String universiteJson = objectMapper.writeValueAsString(universiteDTO);

        // Simulate an HTTP POST request to add a new university
        mockMvc.perform(MockMvcRequestBuilders.post("/universite/add-universite")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(universiteJson))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(result -> {
                    // Convert the JSON response to a Universite object
                    String responseContent = result.getResponse().getContentAsString();
                    Universite addedUniversite = objectMapper.readValue(responseContent, Universite.class);

                    // Add your assertions here
                    // For example, check if the added university matches the input DTO
                    assertEquals("Test University", addedUniversite.getNomUniv());
                    assertEquals("Test Location", addedUniversite.getAdresse());
                });
    }


    /*
    @Test
    void testUpdateUniversite() throws Exception {
        // Create a UniversiteDTO with updated data
        UniversiteDTO updatedUniversiteDTO = new UniversiteDTO();
        updatedUniversiteDTO.setNomUniv("Updated University");
        updatedUniversiteDTO.setAdresse("Updated Location");

        // Convert the updated UniversiteDTO to JSON
        ObjectMapper objectMapper = new ObjectMapper();
        String updatedUniversiteJson = objectMapper.writeValueAsString(updatedUniversiteDTO);

        // Simulate an HTTP PUT request to update an existing university
        mockMvc.perform(MockMvcRequestBuilders.put("/universite/update-universite")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(updatedUniversiteJson))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(result -> {
                    // Convert the JSON response to a UniversiteDTO object
                    String responseContent = result.getResponse().getContentAsString();
                    UniversiteDTO updatedUniversiteResponse = objectMapper.readValue(responseContent, UniversiteDTO.class);

                    // Add your assertions here
                    // For example, check if the updated university matches the input DTO
                    assertEquals("Updated University", updatedUniversiteResponse.getNomUniv());
                    assertEquals("Updated Location", updatedUniversiteResponse.getAdresse());
                });
    }


     */

/*    @Test
    void testAssignUniversiteToDepartement() throws Exception {
        // Simulate an HTTP PUT request to assign a university to a department
        mockMvc.perform(MockMvcRequestBuilders.put("/universite/assignUniversiteToDepartement/1/1"))
                .andExpect(MockMvcResultMatchers.status().isOk());

        // Add your assertions here
        // For example, check if the university is successfully assigned to the department
    }



 */
    @Test
    void testUniversiteConstructor() {
    Universite universite = new Universite();
    assertNotNull(universite);
}

    @Test
     void testUniversiteGettersAndSetters() {
        Universite universite = new Universite();
        universite.setIdUniversite(1);
        universite.setNomUniv("Esprit");
        universite.setAdresse("71489632Esprit");

        assertEquals(1, universite.getIdUniversite());
        assertEquals("Esprit", universite.getNomUniv());
        assertEquals("71489632Esprit", universite.getAdresse());
    }


    @Test
     void testUniversiteToString() {
        Universite universite = new Universite();
        universite.setIdUniversite(1);
        universite.setNomUniv("Esprit");
        universite.setAdresse("71489632Esprit");

        String expectedString = "Universite{idUniversite=1, nomUniv='Esprit', adresse='71489632Esprit', departements=null}";
        assertEquals(expectedString, universite.toString());
    }



    @Test
     void testAddUniversite_service() {
        Universite universite = new Universite();
        when(universiteRepository.save(universite)).thenReturn(universite);

        Universite result = universiteService.addUniversite(universite);

        assertEquals(universite, result);
    }


    /*
    @Test
     void testRetrieveAllUniversites_service() {
        // Arrange
        List<Universite> universites = new ArrayList<>();
        when(universiteRepository.findAll()).thenReturn(universites);

        // Act
        List<Universite> result = universiteService.retrieveAllUniversites();

        // Assert
        assertEquals(universites, result);
    }


     */
}
