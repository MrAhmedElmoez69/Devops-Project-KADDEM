package tn.esprit.spring.khaddem.SpringbootwithunitTest;
import org.springframework.http.MediaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import tn.esprit.spring.khaddem.controllers.EquipeRestController;
import tn.esprit.spring.khaddem.dto.DepartementDTO;
import tn.esprit.spring.khaddem.dto.EquipeDTO;
import tn.esprit.spring.khaddem.entities.*;
import tn.esprit.spring.khaddem.services.IEquipeService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@SpringBootTest
@AutoConfigureMockMvc
class EquipeTest {

    @Autowired
    private MockMvc mockMvc;

    private Equipe equipe;
    private EquipeDTO equipeDTO;

    @Mock
    private IEquipeService equipeService;

    @InjectMocks
    private EquipeRestController equipeController;

    private ObjectMapper objectMapper = new ObjectMapper();
    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(equipeController).build();
    }

    @BeforeEach
    public void setUp() {
        equipe = new Equipe();
    }

    @Test
    void testEquipeEntity() {
        Equipe equipe = new Equipe();
        equipe.setIdEquipe(1);
        equipe.setNomEquipe("Test Equipe");

        assertEquals(1, equipe.getIdEquipe());
        assertEquals("Test Equipe", equipe.getNomEquipe());
    }

    @Test
    void testEnumValues() {
        assertEquals(3, Niveau.values().length);
        assertEquals(Niveau.JUNIOR, Niveau.valueOf("JUNIOR"));
        assertEquals(Niveau.SENIOR, Niveau.valueOf("SENIOR"));
        assertEquals(Niveau.EXPERT, Niveau.valueOf("EXPERT"));
    }

    @Test
    void testEnumToString() {
        assertEquals("JUNIOR", Niveau.JUNIOR.toString());
        assertEquals("SENIOR", Niveau.SENIOR.toString());
        assertEquals("EXPERT", Niveau.EXPERT.toString());
    }

    @Test
    void testGettersAndSetters() {
        Integer idEquipe = 1;
        String nomEquipe = "Test Equipe";
        Niveau niveau = Niveau.JUNIOR;
        List<Etudiant> etudiantList = new ArrayList<>();
        DetailEquipe detailEquipe = new DetailEquipe();

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

        assertEquals(1, equipeDTO.getIdEquipe());
        assertEquals("Equipe Earthrealm", equipeDTO.getNomEquipe());
        assertEquals(Niveau.JUNIOR, equipeDTO.getNiveau());
    }

    @Test
    void testGetterAndSetter_DTO() {
        EquipeDTO equipeDTO = new EquipeDTO();

        equipeDTO.setIdEquipe(1);
        equipeDTO.setNomEquipe("Test Equipe");
        equipeDTO.setNiveau(Niveau.JUNIOR);

        assertEquals(1, equipeDTO.getIdEquipe());
        assertEquals("Test Equipe", equipeDTO.getNomEquipe());
        assertEquals(Niveau.JUNIOR, equipeDTO.getNiveau());
    }


    @Test
    void testRetrieveEquipe_Rest() throws Exception {
        // Assuming there is an existing equipe with ID 1 in the service
        Equipe equipe = new Equipe();
        equipe.setIdEquipe(1);
        equipe.setNomEquipe("equipe1");
        equipe.setNiveau(Niveau.JUNIOR);

        List<Equipe> equipes = Collections.singletonList(equipe);

        when(equipeService.retrieveAllEquipes()).thenReturn(equipes);

        // Act
        List<Equipe> result = equipeController.getEquipes();

        // Assert
        assertEquals(equipes, result);
    }

    @Test
    void testGetEquipes_Rest() throws Exception {
        // Assuming there are existing equipes in the service
        mockMvc.perform(MockMvcRequestBuilders.get("/equipe/retrieve-all-equipes"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"));
    }

    @Test
    void testAddEquipe_Rest() throws Exception {
        EquipeDTO equipeDTO = new EquipeDTO();
        equipeDTO.setNomEquipe("Test Equipe");

        when(equipeService.addEquipe(any(Equipe.class))).thenReturn(new Equipe());

        mockMvc.perform(MockMvcRequestBuilders.post("/equipe/add-equipe")
                        .contentType("application/json")
                        .content(new ObjectMapper().writeValueAsString(equipeDTO)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"));
    }

//    @Test
//    void testUpdateEquipe_Rest() {
//        EquipeDTO equipeDTO = new EquipeDTO();
//        equipeDTO.setIdEquipe(1);
//        equipeDTO.setNomEquipe("Updated Test Equipe");
//        equipeDTO.setNiveau(Niveau.JUNIOR);
//
//        Equipe equipe = new Equipe();
//        equipe.setIdEquipe(equipeDTO.getIdEquipe());
//        equipe.setNomEquipe(equipeDTO.getNomEquipe());
//        equipe.setNiveau(equipeDTO.getNiveau());
//        when(equipeService.updateEquipe(any(Equipe.class))).thenReturn(new Equipe());
//
//        Equipe result = equipeController.updateEquipe(equipeDTO);
//
//        // Assert
//        assertEquals(equipe, result);
//    }
}
