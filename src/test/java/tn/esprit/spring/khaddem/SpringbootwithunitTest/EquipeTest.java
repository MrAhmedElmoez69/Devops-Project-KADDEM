package tn.esprit.spring.khaddem.SpringbootwithunitTest;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import tn.esprit.spring.khaddem.controllers.EquipeRestController;
import tn.esprit.spring.khaddem.dto.EquipeDTO;
import tn.esprit.spring.khaddem.entities.*;
import tn.esprit.spring.khaddem.services.IEquipeService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.mockito.ArgumentMatchers.any;

import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@SpringBootTest
@AutoConfigureMockMvc
public class EquipeTest {



    @Autowired
    private MockMvc mockMvc;

    private Equipe equipe;
    private EquipeDTO equipeDTO;


    @BeforeEach
    public void setUp() {
        equipe = new Equipe();
    }


    @Mock
    private IEquipeService equipeService;

    @InjectMocks
    private EquipeRestController equipeController;



    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(equipeController).build();
    }

    @Test
    void testEquipeEntity(){
        Equipe equipe = new Equipe();
        equipe.setIdEquipe(1);
        equipe.setNomEquipe("Test Equipe");

        assertEquals(1, equipe.getIdEquipe());
        assertEquals("Test Equipe", equipe.getNomEquipe());
    }


    @Test
     void testEnumValues() {
        // Test if the enum contains the expected values
        assertEquals(3, Niveau.values().length);
        assertEquals(Niveau.JUNIOR, Niveau.valueOf("JUNIOR"));
        assertEquals(Niveau.SENIOR, Niveau.valueOf("SENIOR"));
        assertEquals(Niveau.EXPERT, Niveau.valueOf("EXPERT"));
    }
    @Test
     void testEnumToString() {
        // Test the toString() method for each enum value
        assertEquals("JUNIOR", Niveau.JUNIOR.toString());
        assertEquals("SENIOR", Niveau.SENIOR.toString());
        assertEquals("EXPERT", Niveau.EXPERT.toString());
    }

    @Test
    void testEquipeConstructorAndGetterSetter() {
        // Sample data
        Integer idEquipe = 1;
        String nomEquipe = "Team A";
        Niveau niveau = Niveau.JUNIOR;
        List<Etudiant> etudiants = new ArrayList<>(); // You may initialize this with some sample data
        DetailEquipe detailEquipe = new DetailEquipe();

        // Create an Etudiant using the constructor
        Equipe equipe = new Equipe(idEquipe, nomEquipe, niveau, etudiants, detailEquipe);

        // Test the constructor
        assertEquals(idEquipe, equipe.getIdEquipe());
        assertEquals(nomEquipe, equipe.getNomEquipe());
        assertEquals(niveau, equipe.getNiveau());
        assertEquals(etudiants, equipe.getEtudiants());
        assertEquals(detailEquipe, equipe.getDetailEquipe());

        // Test the getter and setter methods
        // Setter sample values
        Integer newIdEquipe = 2;
        String newNomEquipe = "Team B";
        Niveau newNiveau = Niveau.SENIOR;
        List<Etudiant> newEtudiants = new ArrayList<>(); // You may initialize this with some sample data
        DetailEquipe newDetailEquipe = new DetailEquipe();

        // Use setter methods
        equipe.setIdEquipe(newIdEquipe);
        equipe.setNomEquipe(newNomEquipe);
        equipe.setNiveau(newNiveau);
        equipe.setEtudiants(newEtudiants);
        equipe.setDetailEquipe(newDetailEquipe);

        // Test getter methods after using setter
        assertEquals(newIdEquipe, equipe.getIdEquipe());
        assertEquals(newNomEquipe, equipe.getNomEquipe());
        assertEquals(newNiveau, equipe.getNiveau());
        assertEquals(newEtudiants, equipe.getEtudiants());
        assertEquals(newDetailEquipe, equipe.getDetailEquipe());

    }


    @Test
    public void testConstructorAndGetter() {
        // Create an instance of the Equipe class using the constructor and Builder
        Integer idEquipe = 1;
        String nomEquipe = "Team A";
        Niveau niveau = Niveau.JUNIOR;
        List<Etudiant> etudiants = new ArrayList<>(); // You may initialize this with some sample data
        DetailEquipe detailEquipe = new DetailEquipe(); // You may initialize this with some sample data

        Equipe equipe = Equipe.builder()
                .idEquipe(idEquipe)
                .nomEquipe(nomEquipe)
                .niveau(niveau)
                .etudiants(etudiants)
                .detailEquipe(detailEquipe)
                .build();

        // Test getter methods
        assertEquals(idEquipe, equipe.getIdEquipe());
        assertEquals(nomEquipe, equipe.getNomEquipe());
        assertEquals(niveau, equipe.getNiveau());
        assertEquals(etudiants, equipe.getEtudiants());
        assertEquals(detailEquipe, equipe.getDetailEquipe());
    }

    @Test
    public void testSetter() {
        // Create an instance of the Equipe class
        Equipe equipe = new Equipe();

        // Set some sample values to the instance
        Integer idEquipe = 1;
        String nomEquipe = "Team A";
        Niveau niveau = Niveau.JUNIOR;
        List<Etudiant> etudiants = new ArrayList<>(); // You may initialize this with some sample data
        DetailEquipe detailEquipe = new DetailEquipe(); // You may initialize this with some sample data

        equipe.setIdEquipe(idEquipe);
        equipe.setNomEquipe(nomEquipe);
        equipe.setNiveau(niveau);
        equipe.setEtudiants(etudiants);
        equipe.setDetailEquipe(detailEquipe);

        // Test getter methods
        assertEquals(idEquipe, equipe.getIdEquipe());
        assertEquals(nomEquipe, equipe.getNomEquipe());
        assertEquals(niveau, equipe.getNiveau());
        assertEquals(etudiants, equipe.getEtudiants());
        assertEquals(detailEquipe, equipe.getDetailEquipe());
    }

    @Test
    public void testGetters() {
        // Create an instance of the Equipe class
        Equipe equipe = new Equipe();

        // Set some sample values to the instance
        Integer idEquipe = 1;
        String nomEquipe = "Team A";
        Niveau niveau = Niveau.JUNIOR;
        List<Etudiant> etudiants = new ArrayList<>(); // You may initialize this with some sample data
        DetailEquipe detailEquipe = new DetailEquipe(); // You may initialize this with some sample data

        equipe.setIdEquipe(idEquipe);
        equipe.setNomEquipe(nomEquipe);
        equipe.setNiveau(niveau);
        equipe.setEtudiants(etudiants);
        equipe.setDetailEquipe(detailEquipe);

        // Test getter methods
        assertEquals(idEquipe, equipe.getIdEquipe());
        assertEquals(nomEquipe, equipe.getNomEquipe());
        assertEquals(niveau, equipe.getNiveau());
        assertEquals(etudiants, equipe.getEtudiants());
        assertEquals(detailEquipe, equipe.getDetailEquipe());
    }

    @Test
     void testGettersAndSetters() {
        // Test the getter and setter methods
        Integer idEquipe = 1;
        String nomEquipe = "Test Equipe";
        Niveau niveau = Niveau.JUNIOR;
        List<Etudiant> etudiantList = new ArrayList<>(); // Create a List of Etudiant
        DetailEquipe detailEquipe = new DetailEquipe(); // Create a DetailEquipe instance

        equipe.setIdEquipe(idEquipe);
        equipe.setNomEquipe(nomEquipe);
        equipe.setNiveau(niveau);
        equipe.setEtudiants(etudiantList);
        equipe.setDetailEquipe(detailEquipe);

        assertEquals(idEquipe, equipe.getIdEquipe());
        assertEquals(nomEquipe, equipe.getNomEquipe());
        assertEquals(niveau, equipe.getNiveau());
        assertEquals(etudiantList, equipe.getEtudiants());
        assertEquals(detailEquipe, equipe.getDetailEquipe());
    }

    @Test
    void testEquipeDTO() {
        EquipeDTO equipeDTO = new EquipeDTO();
        equipeDTO.setIdEquipe(1);
        equipeDTO.setNomEquipe("Equipe Earthrealm");
        equipeDTO.setNiveau(Niveau.JUNIOR);

        // Check if the getter methods return the expected values
        assertEquals(1, equipeDTO.getIdEquipe());
        assertEquals("Equipe Earthrealm", equipeDTO.getNomEquipe());
        assertEquals(Niveau.JUNIOR, equipeDTO.getNiveau());

        // You can add more checks for other fields as needed
    }

    @Test
    void testGetterAndSetter_DTO() {
        EquipeDTO equipeDTO = new EquipeDTO();

        // Set values using setters
        equipeDTO.setIdEquipe(1);
        equipeDTO.setNomEquipe("Test Equipe");
        equipeDTO.setNiveau(Niveau.JUNIOR);

        // Check if getters return the expected values
        assertEquals(1, equipeDTO.getIdEquipe());
        assertEquals("Test Equipe", equipeDTO.getNomEquipe());
        assertEquals(Niveau.JUNIOR, equipeDTO.getNiveau());
    }


    @Test
     void testRetrieveEquipe_Rest() throws Exception {
        Integer equipeId = 1;
        Equipe equipe = new Equipe(); // Create an Equipe object for testing

        // Mock the behavior of the service method
        when(equipeService.retrieveEquipe(equipeId)).thenReturn(equipe);

        mockMvc.perform(get("/equipe/retrieve-equipe/{equipe-id}", equipeId))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("idEquipe").value(equipeId));
    }


    @Test
    void testGetEquipes_Rest() throws Exception {
        // Prepare a list of Equipe objects for the mock service response
        List<Equipe> equipes = Arrays.asList(new Equipe(), new Equipe());

        // Mock the behavior of the service method
        when(equipeService.retrieveAllEquipes()).thenReturn(equipes);

        // Perform the GET request to retrieve all equipes
        mockMvc.perform(MockMvcRequestBuilders.get("/equipe/retrieve-all-equipes"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"));
    }

    @Test
    void testAddEquipe_Rest() throws Exception {
        // Prepare an EquipeDTO with sample data
        EquipeDTO equipeDTO = new EquipeDTO();
        equipeDTO.setNomEquipe("Test Equipe");

        // Mock the behavior of the service method
        when(equipeService.addEquipe(any(Equipe.class))).thenAnswer(invocation -> invocation.getArgument(0));

        // Convert EquipeDTO to JSON
        ObjectMapper objectMapper = new ObjectMapper();
        String equipeJson = objectMapper.writeValueAsString(equipeDTO);

        // Perform the POST request to add an equipe
        mockMvc.perform(MockMvcRequestBuilders.post("/equipe/add-equipe")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(equipeJson))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"));
    }
    @Test
    void testUpdateEquipe_Rest() throws Exception {
        // Prepare an EquipeDTO with sample data
        EquipeDTO equipeDTO = new EquipeDTO();
        equipeDTO.setIdEquipe(1); // Existing equipe ID
        equipeDTO.setNomEquipe("Updated Test Equipe");

        // Mock the behavior of the service methods
        when(equipeService.retrieveEquipe(equipeDTO.getIdEquipe())).thenReturn(new Equipe());
        when(equipeService.updateEquipe(any(Equipe.class))).thenAnswer(invocation -> invocation.getArgument(0));

        // Convert EquipeDTO to JSON
        ObjectMapper objectMapper = new ObjectMapper();
        String equipeJson = objectMapper.writeValueAsString(equipeDTO);

        // Perform the PUT request to update an equipe
        mockMvc.perform(MockMvcRequestBuilders.put("/equipe/update-equipe")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(equipeJson))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"));
    }


}
