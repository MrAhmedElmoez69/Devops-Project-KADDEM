package tn.esprit.spring.khaddem.SpringbootwithunitTest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import tn.esprit.spring.khaddem.dto.DetailEquipeDTO;
import tn.esprit.spring.khaddem.entities.DetailEquipe;
import tn.esprit.spring.khaddem.entities.Equipe;
import tn.esprit.spring.khaddem.repositories.DetailEquipeRepository;
import tn.esprit.spring.khaddem.services.IDetailEquipeService;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

@SpringBootTest
@AutoConfigureMockMvc
@RunWith(MockitoJUnitRunner.class)
class DetailEquipeTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private IDetailEquipeService detailEquipeService;
    @Autowired
    private DetailEquipeRepository detailEquipeRepository;
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateDetailEquipe() {
        Integer idDetailEquipe = 1;
        Integer salle = 101;
        String thematique = "Test Thematique";
        Equipe equipe = new Equipe();

        DetailEquipe detailEquipe = new DetailEquipe(idDetailEquipe, salle, thematique, equipe);


        assertEquals(idDetailEquipe, detailEquipe.getIdDetailEquipe());
        assertEquals(salle, detailEquipe.getSalle());
        assertEquals(thematique, detailEquipe.getThematique());
        assertEquals(equipe, detailEquipe.getEquipe());
    }

    @Test
    void testDefaultConstructor() {

        DetailEquipe detailEquipe = new DetailEquipe();


        assertNotNull(detailEquipe);
    }

    @Test
    void testEqualsAndHashCode() {

        DetailEquipe detailEquipe1 = new DetailEquipe(1, 101, "Thematique 1", new Equipe());
        DetailEquipe detailEquipe2 = new DetailEquipe(1, 101, "Thematique 1", new Equipe());


        assertEquals(detailEquipe1, detailEquipe2);
        assertEquals(detailEquipe1.hashCode(), detailEquipe2.hashCode());


        detailEquipe2.setSalle(102);


        assertNotEquals(detailEquipe1, detailEquipe2);
        assertNotEquals(detailEquipe1.hashCode(), detailEquipe2.hashCode());
    }

    @Test
    void testNotEquals() {

        DetailEquipe detailEquipe1 = new DetailEquipe();
        detailEquipe1.setIdDetailEquipe(1);
        detailEquipe1.setSalle(101);
        detailEquipe1.setThematique("Theme1");

        DetailEquipe detailEquipe2 = new DetailEquipe();
        detailEquipe2.setIdDetailEquipe(2);
        detailEquipe2.setSalle(102);
        detailEquipe2.setThematique("Theme2");


        assertNotEquals(detailEquipe1, detailEquipe2);
    }


    @Test
    void testNotHashCode() {

        DetailEquipe detailEquipe1 = new DetailEquipe();
        detailEquipe1.setIdDetailEquipe(1);
        detailEquipe1.setSalle(101);
        detailEquipe1.setThematique("Theme1");

        DetailEquipe detailEquipe2 = new DetailEquipe();
        detailEquipe2.setIdDetailEquipe(2);
        detailEquipe2.setSalle(102);
        detailEquipe2.setThematique("Theme2");


        assertNotEquals(detailEquipe1.hashCode(), detailEquipe2.hashCode());
    }


    @Test
    void testDetailEquipeEntity() {
        DetailEquipe detailEquipe = new DetailEquipe();
        detailEquipe.setSalle(123);
        detailEquipe.setThematique("Test Thematique");

        assertEquals(123, detailEquipe.getSalle());
        assertEquals("Test Thematique", detailEquipe.getThematique());
    }
    @Test
    void testEquals() {

        DetailEquipe detailEquipe1 = new DetailEquipe(1, 101, "Thematique 1", new Equipe());
        DetailEquipe detailEquipe2 = new DetailEquipe(1, 101, "Thematique 1", new Equipe());

        assertEquals(detailEquipe1, detailEquipe2);

        Object someOtherObject = new Object();

        assertNotEquals(detailEquipe1, someOtherObject);
    }
    @Test
    void testDetailEquipeInequality() {
        DetailEquipe detailEquipe1 = new DetailEquipe();
        detailEquipe1.setIdDetailEquipe(1);
        detailEquipe1.setSalle(123);
        detailEquipe1.setThematique("Thematique A");

        DetailEquipe detailEquipe2 = new DetailEquipe();
        detailEquipe2.setIdDetailEquipe(2);
        detailEquipe2.setSalle(456);
        detailEquipe2.setThematique("Thematique B");

        assertNotEquals(detailEquipe1, detailEquipe2);
    }

    @Test
    void testHashCode() {
        DetailEquipe detailEquipe1 = new DetailEquipe();
        detailEquipe1.setIdDetailEquipe(1);
        detailEquipe1.setSalle(123);
        detailEquipe1.setThematique("Thematique A");

        DetailEquipe detailEquipe2 = new DetailEquipe();
        detailEquipe2.setIdDetailEquipe(1);
        detailEquipe2.setSalle(123);
        detailEquipe2.setThematique("Thematique A");

        assertEquals(detailEquipe1.hashCode(), detailEquipe2.hashCode());
    }

    @Test
    void testEqualsWithEquipe() {

        Equipe equipe = mock(Equipe.class);
        DetailEquipe detailEquipe1 = new DetailEquipe(1, 10, "Thematique 1", equipe);
        DetailEquipe detailEquipe2 = new DetailEquipe(1, 10, "Thematique 1", equipe);


        assertEquals(detailEquipe1, detailEquipe2);
    }


    @Test
    void testFindAll() {

        DetailEquipe detailEquipe1 = new DetailEquipe();
        detailEquipe1.setSalle(123);
        detailEquipe1.setThematique("Thematique 1");
        detailEquipeRepository.save(detailEquipe1);

        DetailEquipe detailEquipe2 = new DetailEquipe();
        detailEquipe2.setSalle(456);
        detailEquipe2.setThematique("Thematique 2");
        detailEquipeRepository.save(detailEquipe2);


        List<DetailEquipe> allDetailEquipes = detailEquipeRepository.findAll();

        assertEquals(2, allDetailEquipes.size());
    }

    @Test
    void testSaveAndFindById() {

        DetailEquipe detailEquipe = new DetailEquipe();
        detailEquipe.setSalle(123);
        detailEquipe.setThematique("Test Thematique");


        DetailEquipe savedDetailEquipe = detailEquipeRepository.save(detailEquipe);


        Optional<DetailEquipe> foundDetailEquipe = detailEquipeRepository.findById(savedDetailEquipe.getIdDetailEquipe());

        assertTrue(foundDetailEquipe.isPresent());
        assertEquals(savedDetailEquipe, foundDetailEquipe.get());
    }
    @Test
    void testGetDetailEquipes() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/detailequipe/retrieve-all-detail-equipes"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"));
    }
    @Test
    void testAddDetailEquipe() throws Exception {

        DetailEquipeDTO detailEquipeDTO = new DetailEquipeDTO();
        detailEquipeDTO.setSalle(123);
        detailEquipeDTO.setThematique("Test Thematique");


        ObjectMapper objectMapper = new ObjectMapper();
        String detailEquipeJson = objectMapper.writeValueAsString(detailEquipeDTO);

        mockMvc.perform(MockMvcRequestBuilders.post("/detailequipe/add-detail-equipe")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(detailEquipeJson))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(result -> {
                    String responseContent = result.getResponse().getContentAsString();
                    DetailEquipe addedDetailEquipe = objectMapper.readValue(responseContent, DetailEquipe.class);


                    assertEquals(123, addedDetailEquipe.getSalle());
                    assertEquals("Test Thematique", addedDetailEquipe.getThematique());
                });
    }


    @Test
    void testUpdateDetailEquipe() throws Exception {

        DetailEquipeDTO detailEquipeDTO = new DetailEquipeDTO();
        detailEquipeDTO.setIdDetailEquipe(1); // Existing DetailEquipe ID
        detailEquipeDTO.setSalle(123);
        detailEquipeDTO.setThematique("Updated Thematique");


        ObjectMapper objectMapper = new ObjectMapper();
        String detailEquipeJson = objectMapper.writeValueAsString(detailEquipeDTO);

        mockMvc.perform(MockMvcRequestBuilders.put("/detailequipe/update-detail-equipe")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(detailEquipeJson))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(result -> {
                    String responseContent = result.getResponse().getContentAsString();
                    DetailEquipe updatedDetailEquipe = objectMapper.readValue(responseContent, DetailEquipe.class);

                    // Validate the response data
                    assertEquals(123, updatedDetailEquipe.getSalle());
                    assertEquals("Updated Thematique", updatedDetailEquipe.getThematique());
                });
    }



    @Test
    void testRetrieveDetailEquipe() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/detailequipe/retrieve-detail-equipe/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(result -> {

                    String responseContent = result.getResponse().getContentAsString();
                    ObjectMapper objectMapper = new ObjectMapper();
                    DetailEquipe retrievedDetailEquipe = objectMapper.readValue(responseContent, DetailEquipe.class);


                    assertEquals(1, retrievedDetailEquipe.getIdDetailEquipe());

                });
    }







}