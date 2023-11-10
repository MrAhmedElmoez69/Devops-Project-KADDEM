package tn.esprit.spring.khaddem.SpringbootwithunitTest;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import tn.esprit.spring.khaddem.dto.DepartementDTO;
import tn.esprit.spring.khaddem.entities.Departement;
import tn.esprit.spring.khaddem.services.IDepartementService;

@SpringBootTest
@AutoConfigureMockMvc
class DepartementTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private IDepartementService departementService;
    @Test
    void testDepartementEntity() {
        Departement departement = new Departement();
        departement.setIdDepartement(1);
        departement.setNomDepart("Test Department");

        assertEquals(1, departement.getIdDepartement());
        assertEquals("Test Department", departement.getNomDepart());
    }

    @Test
    void testDepartementDTO() {
        // Create a DepartementDTO with sample data
        DepartementDTO departementDTO = new DepartementDTO();
        departementDTO.setIdDepartement(1);
        departementDTO.setNomDepart("Test Department");

        // Check if the getter methods return the expected values
        assertEquals(1, departementDTO.getIdDepartement());
        assertEquals("Test Department", departementDTO.getNomDepart());

        // You can add more checks for other fields as needed
    }

    @Test
    void testDepartementEquality() {
        Departement departement1 = new Departement();
        departement1.setIdDepartement(1);
        departement1.setNomDepart("Department A");

        Departement departement2 = new Departement();
        departement2.setIdDepartement(1);
        departement2.setNomDepart("Department A");

        assertEquals(departement1, departement2);
    }

    @Test
    void testEquals() {
        Departement departement1 = new Departement();
        departement1.setIdDepartement(1);
        departement1.setNomDepart("Department A");

        Departement departement2 = new Departement();
        departement2.setIdDepartement(1);
        departement2.setNomDepart("Department A");

        Departement departement3 = new Departement();
        departement3.setIdDepartement(2);
        departement3.setNomDepart("Department B");

        assertEquals(departement1, departement2); // Check that two departements with the same ID and name are equal
        assertNotEquals(departement1, departement3); // Check that two departements with different IDs and names are not equal
    }

    @Test
    void testDepartementInequality() {
        Departement departement1 = new Departement();
        departement1.setIdDepartement(1);
        departement1.setNomDepart("Department A");

        Departement departement2 = new Departement();
        departement2.setIdDepartement(2);
        departement2.setNomDepart("Department B");

        assertNotEquals(departement1, departement2);
    }

    @Test
    void testSettersAndGetters() {
        Departement departement = new Departement();
        departement.setIdDepartement(1);
        departement.setNomDepart("Test Department");

        assertEquals(1, departement.getIdDepartement());
        assertEquals("Test Department", departement.getNomDepart());

    }

    @Test
    void testGetDepartements() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/departement/retrieve-all-departements"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"));
    }


    @Test
    void testAddDepartement() throws Exception {
        // Prepare a DepartementDTO with data
        DepartementDTO departementDTO = new DepartementDTO();
        departementDTO.setNomDepart("Test Department");

        // Convert DepartementDTO to JSON
        ObjectMapper objectMapper = new ObjectMapper();
        String departementJson = objectMapper.writeValueAsString(departementDTO);

        mockMvc.perform(MockMvcRequestBuilders.post("/departement/add-departement")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(departementJson))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(result -> {
                    String responseContent = result.getResponse().getContentAsString();
                    Departement addedDepartement = objectMapper.readValue(responseContent, Departement.class);

                    // Validate the response data if needed
                    assertEquals("Test Department", addedDepartement.getNomDepart());
                });
    }

    @Test
    void testUpdateDepartement() throws Exception {
        // Prepare a DepartementDTO with data
        DepartementDTO departementDTO = new DepartementDTO();
        departementDTO.setIdDepartement(1); // Existing departement ID
        departementDTO.setNomDepart("Updated Department");

        // Convert DepartementDTO to JSON
        ObjectMapper objectMapper = new ObjectMapper();
        String departementJson = objectMapper.writeValueAsString(departementDTO);

        mockMvc.perform(MockMvcRequestBuilders.put("/departement/update-departement")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(departementJson))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(result -> {
                    String responseContent = result.getResponse().getContentAsString();
                    Departement updatedDepartement = objectMapper.readValue(responseContent, Departement.class);

                    // Validate the response data if needed
                    assertEquals("Updated Department", updatedDepartement.getNomDepart());
                });
    }

    @Test
    void testRetrieveDepartement() throws Exception {
        // Perform a GET request to retrieve a specific departement by ID (e.g., ID 8)
        mockMvc.perform(MockMvcRequestBuilders.get("/departement/retrieve-departement/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(result -> {
                    // Convert the response JSON to a Departement object
                    String responseContent = result.getResponse().getContentAsString();
                    ObjectMapper objectMapper = new ObjectMapper();
                    Departement retrievedDepartement = objectMapper.readValue(responseContent, Departement.class);

                    // Validate the retrieved departement
                    assertEquals(1, retrievedDepartement.getIdDepartement());
                    // Add more validation as needed
                });
    }

    @Test
    void testHashCode() {
        Departement departement1 = new Departement();
        departement1.setIdDepartement(1);
        departement1.setNomDepart("Department A");

        Departement departement2 = new Departement();
        departement2.setIdDepartement(1);
        departement2.setNomDepart("Department A");

        assertEquals(departement1.hashCode(), departement2.hashCode());
    }

    

}
