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
        assertEquals(4, Option.values().length);
        assertEquals(Option.GAMIX, Option.valueOf("GAMIX"));
        assertEquals(Option.SE, Option.valueOf("SE"));
        assertEquals(Option.SAE, Option.valueOf("SAE"));
        assertEquals(Option.INFINI, Option.valueOf("INFINI"));
    }

    @Test
    void testEnumToString() {
        assertEquals("GAMIX", Option.GAMIX.toString());
        assertEquals("SE", Option.SE.toString());
        assertEquals("SAE", Option.SAE.toString());
        assertEquals("INFINI", Option.INFINI.toString());
    }

    @Test
    void testEtudiantConstructorAndGetterSetter() {
        Integer idEtudiant = 1;
        String prenomE = "John";
        String nomE = "Doe";
        Option op = Option.GAMIX;
        Departement departement = new Departement();
        List<Equipe> equipes = new ArrayList<>();
        List<Contrat> contrats = new ArrayList<>();

        Etudiant etudiant = new Etudiant(idEtudiant, prenomE, nomE, op, departement, equipes, contrats);

        assertEquals(idEtudiant, etudiant.getIdEtudiant());
        assertEquals(prenomE, etudiant.getPrenomE());
        assertEquals(nomE, etudiant.getNomE());
        assertEquals(op, etudiant.getOp());
        assertEquals(departement, etudiant.getDepartement());
        assertEquals(equipes, etudiant.getEquipes());
        assertEquals(contrats, etudiant.getContrats());

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

        assertEquals(1, etudiantDTO.getIdEtudiant());
        assertEquals("Test Etudiant", etudiantDTO.getNomE());
        assertEquals(Option.GAMIX, etudiantDTO.getOp());
    }

    @Test
    void testRetrieveAllEtudiants() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/etudiant/retrieve-all-etudiants"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(result -> {
                    String responseContent = result.getResponse().getContentAsString();
                    Etudiant[] etudiants = new ObjectMapper().readValue(responseContent, Etudiant[].class);


                    assertEquals(0, etudiants.length);
                });
    }

    @Test
    void testAddEtudiant() throws Exception {
        EtudiantDTO etudiantDTO = new EtudiantDTO();
        etudiantDTO.setNomE("Test Etudiant");

        when(etudiantService.addEtudiant(any(Etudiant.class))).thenAnswer(invocation -> invocation.getArgument(0));

        ObjectMapper objectMapper = new ObjectMapper();
        String etudiantJson = objectMapper.writeValueAsString(etudiantDTO);

        mockMvc.perform(MockMvcRequestBuilders.post("/etudiant/add-etudiant")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(etudiantJson))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(content().contentType("application/json"));
    }

    @Test
    void testUpdateEtudiant() throws Exception {
        EtudiantDTO etudiantDTO = new EtudiantDTO();
        etudiantDTO.setIdEtudiant(1);
        etudiantDTO.setNomE("Updated Test Etudiant");

        when(etudiantService.retrieveEtudiant(etudiantDTO.getIdEtudiant())).thenReturn(new Etudiant());
        when(etudiantService.updateEtudiant(any(Etudiant.class))).thenAnswer(invocation -> invocation.getArgument(0));

        ObjectMapper objectMapper = new ObjectMapper();
        String etudiantJson = objectMapper.writeValueAsString(etudiantDTO);

        mockMvc.perform(MockMvcRequestBuilders.put("/etudiant/update-etudiant")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(etudiantJson))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(content().contentType("application/json"));
    }

    @Test
    void testRemoveEtudiant() throws Exception {
        Integer etudiantId = 1;

        doNothing().when(etudiantService).removeEtudiant(etudiantId);

        mockMvc.perform(MockMvcRequestBuilders.delete("/etudiant/removeEtudiant/{idEtudiant}", etudiantId))
                .andExpect(MockMvcResultMatchers.status().isOk());

        verify(etudiantService, times(1)).removeEtudiant(etudiantId);
    }








}