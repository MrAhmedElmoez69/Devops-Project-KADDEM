package tn.esprit.spring.khaddem.SpringbootwithunitTest;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import tn.esprit.spring.khaddem.dto.EtudiantDTO;
import tn.esprit.spring.khaddem.entities.*;
import tn.esprit.spring.khaddem.repositories.EtudiantRepository;
import tn.esprit.spring.khaddem.services.EtudiantServiceImpl;


import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@AutoConfigureMockMvc
@RunWith(MockitoJUnitRunner.class)
class EtudiantTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private EtudiantRepository etudiantRepository;

    @Autowired
    private EtudiantServiceImpl etudiantService;


    @Test
    void testEtudiantEntity() {
        Etudiant etudiant = new Etudiant();
        etudiant.setNomE("Test Etudiant");

        // Add assertions for your entity properties
        assertEquals("Test Etudiant", etudiant.getNomE());
    }

    @Test
    void testEtudiantConstructorAndGetterSetter() {
        // Sample data
        Integer idEtudiant = 1;
        String prenomE = "John";
        String nomE = "Doe";
        Option op = Option.GAMIX;
        Departement departement = new Departement();
        List<Equipe> equipes = new ArrayList<>();
        List<Contrat> contrats = new ArrayList<>();

        // Create an Etudiant using the constructor
        Etudiant etudiant = new Etudiant(idEtudiant, prenomE, nomE, op, departement, equipes, contrats);

        // Test the constructor
        assertEquals(idEtudiant, etudiant.getIdEtudiant());
        assertEquals(prenomE, etudiant.getPrenomE());
        assertEquals(nomE, etudiant.getNomE());
        assertEquals(op, etudiant.getOp());
        assertEquals(departement, etudiant.getDepartement());
        assertEquals(equipes, etudiant.getEquipes());
        assertEquals(contrats, etudiant.getContrats());

        // Test the getter and setter methods
        Departement newDepartement = new Departement();
        etudiant.setDepartement(newDepartement);
        assertEquals(newDepartement, etudiant.getDepartement());

        List<Equipe> newEquipes = new ArrayList<>();
        etudiant.setEquipes(newEquipes);
        assertEquals(newEquipes, etudiant.getEquipes());

        List<Contrat> newContrats = new ArrayList<>();
        etudiant.setContrats(newContrats);
        assertEquals(newContrats, etudiant.getContrats());
    }

    @Test
    void testEtudiantDTO() {
        EtudiantDTO etudiantDTO = new EtudiantDTO();
        etudiantDTO.setIdEtudiant(1);
        etudiantDTO.setNomE("Test Etudiant");
        etudiantDTO.setOp(Option.GAMIX);

        // Add assertions for your DTO properties
        assertEquals(1, etudiantDTO.getIdEtudiant());
        assertEquals("Test Etudiant", etudiantDTO.getNomE());
        assertEquals(Option.GAMIX, etudiantDTO.getOp());
    }

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
