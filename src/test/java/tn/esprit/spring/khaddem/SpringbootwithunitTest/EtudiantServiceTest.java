package tn.esprit.spring.khaddem.SpringbootwithunitTest;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import tn.esprit.spring.khaddem.dto.EtudiantDTO;
import tn.esprit.spring.khaddem.entities.*;


import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@AutoConfigureMockMvc
@RunWith(MockitoJUnitRunner.class)
class EtudiantServiceTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    void testRetrieveAllEtudiants() throws Exception {
        // Simulate an HTTP GET request to retrieve all students
        mockMvc.perform(MockMvcRequestBuilders.get("/etudiant/retrieve-all-etudiants"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(result -> {
                    // Convert the JSON response to a list of Etudiant objects
                    String responseContent = result.getResponse().getContentAsString();
                    Etudiant[] etudiants = new ObjectMapper().readValue(responseContent, Etudiant[].class);

                    // Add your assertions here
                    // For example, check the number of students, or specific student details
                    assertEquals(0, etudiants.length); // Assuming you expect an empty list in this case
                });
    }

    @Test
    void testAddEtudiant() throws Exception {
        // Create an EtudiantDTO with sample data
        EtudiantDTO etudiantDTO = new EtudiantDTO();
        etudiantDTO.setNomE("Test Etudiant");
        etudiantDTO.setOp(Option.GAMIX);

        // Convert EtudiantDTO to JSON
        ObjectMapper objectMapper = new ObjectMapper();
        String etudiantJson = objectMapper.writeValueAsString(etudiantDTO);

        // Simulate an HTTP POST request to add a new student
        mockMvc.perform(MockMvcRequestBuilders.post("/etudiant/add-etudiant")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(etudiantJson))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(result -> {
                    // Convert the JSON response to an Etudiant object
                    String responseContent = result.getResponse().getContentAsString();
                    Etudiant addedEtudiant = objectMapper.readValue(responseContent, Etudiant.class);

                    // Add your assertions here
                    // For example, check if the added student matches the input DTO
                    assertEquals("Test Etudiant", addedEtudiant.getNomE());
                    assertEquals(Option.GAMIX, addedEtudiant.getOp());
                });
    }


}
