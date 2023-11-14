package tn.esprit.spring.khaddem.SpringbootwithunitTest;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import tn.esprit.spring.khaddem.controllers.EtudiantRestController;
import tn.esprit.spring.khaddem.dto.EtudiantDTO;
import tn.esprit.spring.khaddem.entities.*;
import tn.esprit.spring.khaddem.services.EtudiantServiceImpl;


import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;

@SpringBootTest
@AutoConfigureMockMvc
@RunWith(MockitoJUnitRunner.class)
class EtudiantTest {
    @Autowired
    private MockMvc mockMvc;

    private Etudiant etudiant;
    private EtudiantDTO etudiantDTO;

    @BeforeEach
    public void setUp() {
        etudiant = new Etudiant();
    }
    @Mock
    private EtudiantServiceImpl etudiantService;

    @InjectMocks
    private EtudiantRestController etudiantController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(etudiantController).build();
    }

    @Test
    void testEtudiantEntity() {
        Etudiant etudiant = new Etudiant();
        etudiant.setIdEtudiant(1);
        etudiant.setNomE("Test Etudiant");

        assertEquals(1, etudiant.getIdEtudiant());
        assertEquals("Test Etudiant", etudiant.getNomE());
    }

    @Test
    void testEnumValues() {
        // Test if the enum contains the expected values
        assertEquals(4, Option.values().length);
        assertEquals(Option.GAMIX, Option.valueOf("GAMIX"));
        assertEquals(Option.SE, Option.valueOf("SE"));
        assertEquals(Option.SAE, Option.valueOf("SAE"));
        assertEquals(Option.INFINI, Option.valueOf("INFINI"));
    }

    @Test
    void testEnumToString() {
        // Test the toString() method for each enum value
        assertEquals("GAMIX", Option.GAMIX.toString());
        assertEquals("SE", Option.SE.toString());
        assertEquals("SAE", Option.SAE.toString());
        assertEquals("INFINI", Option.INFINI.toString());
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
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
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
        // Prepare an EquipeDTO with sample data
        EtudiantDTO etudiantDTO = new EtudiantDTO();
        etudiantDTO.setNomE("Test Etudiant");

        // Mock the behavior of the service method
        when(etudiantService.addEtudiant(any(Etudiant.class))).thenAnswer(invocation -> invocation.getArgument(0));

        // Convert EtudiantDTO to JSON
        ObjectMapper objectMapper = new ObjectMapper();
        String etudiantJson = objectMapper.writeValueAsString(etudiantDTO);

        // Perform the POST request to add an etudiant
        mockMvc.perform(MockMvcRequestBuilders.post("/etudiant/add-etudiant")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(etudiantJson))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(content().contentType("application/json"));
    }

    @Test
    void testUpdateEtudiant() throws Exception {
        // Prepare an EtudiantDTO with sample data
        EtudiantDTO etudiantDTO = new EtudiantDTO();
        etudiantDTO.setIdEtudiant(1); // Existing equipe ID
        etudiantDTO.setNomE("Updated Test Etudiant");

        // Mock the behavior of the service methods
        when(etudiantService.retrieveEtudiant(etudiantDTO.getIdEtudiant())).thenReturn(new Etudiant());
        when(etudiantService.updateEtudiant(any(Etudiant.class))).thenAnswer(invocation -> invocation.getArgument(0));

        // Convert EquipeDTO to JSON
        ObjectMapper objectMapper = new ObjectMapper();
        String etudiantJson = objectMapper.writeValueAsString(etudiantDTO);

        // Perform the PUT request to update an equipe
        mockMvc.perform(MockMvcRequestBuilders.put("/etudiant/update-etudiant")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(etudiantJson))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(content().contentType("application/json"));
    }

    @Test
    void testRemoveEtudiant() throws Exception {
        // Sample data
        Integer etudiantId = 1;

        // Mock the behavior of the service method
        doNothing().when(etudiantService).removeEtudiant(etudiantId);

        // Perform the DELETE request to remove an etudiant
        mockMvc.perform(MockMvcRequestBuilders.delete("/etudiant/removeEtudiant/{idEtudiant}", etudiantId))
                .andExpect(MockMvcResultMatchers.status().isOk());

        // Optionally, you can add further assertions or verifications based on your business logic
        // For example, you can verify that the service method was called with the correct etudiantId
        verify(etudiantService, times(1)).removeEtudiant(etudiantId);
    }








}
