package tn.esprit.spring.khaddem.SpringbootwithunitTest;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.runner.RunWith;
import org.mockito.Mockito;
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

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

/////tests jdod bech n7assen coverage

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
                    assertEquals(0, universites.length); // Assuming you expect an empty list in this case
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
                    // Convert the JSON response to an Universite object
                    String responseContent = result.getResponse().getContentAsString();
                    Universite addedUniversite = objectMapper.readValue(responseContent, Universite.class);

                    // Add your assertions here
                    // For example, check if the added university matches the input DTO
                    assertEquals("Test University", addedUniversite.getNomUniv());
                    assertEquals("Test Location", addedUniversite.getAdresse());
                });
    }



}
