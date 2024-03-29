package tn.esprit.spring.khaddem.SpringbootwithunitTest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import tn.esprit.spring.khaddem.dto.DetailEquipeDTO;
import tn.esprit.spring.khaddem.entities.DetailEquipe;
import tn.esprit.spring.khaddem.repositories.DetailEquipeRepository;
import tn.esprit.spring.khaddem.services.IDetailEquipeService;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
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
    void testDetailEquipeEntity() {
        DetailEquipe detailEquipe = new DetailEquipe();
        detailEquipe.setSalle(123);
        detailEquipe.setThematique("Test Thematique");

        assertEquals(123, detailEquipe.getSalle());
        assertEquals("Test Thematique", detailEquipe.getThematique());
    }
    @Test
    void testEquals() {
        DetailEquipe detailEquipe1 = new DetailEquipe();
        detailEquipe1.setIdDetailEquipe(1);
        detailEquipe1.setSalle(123);
        detailEquipe1.setThematique("Thematique A");

        DetailEquipe detailEquipe2 = new DetailEquipe();
        detailEquipe2.setIdDetailEquipe(1);
        detailEquipe2.setSalle(123);
        detailEquipe2.setThematique("Thematique A");

        DetailEquipe detailEquipe3 = new DetailEquipe();
        detailEquipe3.setIdDetailEquipe(2);
        detailEquipe3.setSalle(456);
        detailEquipe3.setThematique("Thematique B");

        assertEquals(detailEquipe1, detailEquipe2); // Check that two detailEquipes with the same ID, salle, and thematique are equal
        assertNotEquals(detailEquipe1, detailEquipe3); // Check that two detailEquipes with different IDs, salle, and thematique are not equal
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
     void testFindAll() {
        // Create and save some DetailEquipe entities
        DetailEquipe detailEquipe1 = new DetailEquipe();
        detailEquipe1.setSalle(123);
        detailEquipe1.setThematique("Thematique 1");
        detailEquipeRepository.save(detailEquipe1);

        DetailEquipe detailEquipe2 = new DetailEquipe();
        detailEquipe2.setSalle(456);
        detailEquipe2.setThematique("Thematique 2");
        detailEquipeRepository.save(detailEquipe2);

        // Find all DetailEquipe entities
        List<DetailEquipe> allDetailEquipes = detailEquipeRepository.findAll();

        assertEquals(2, allDetailEquipes.size());
    }

    @Test
    void testSaveAndFindById() {
        // Create a DetailEquipe entity
        DetailEquipe detailEquipe = new DetailEquipe();
        detailEquipe.setSalle(123);
        detailEquipe.setThematique("Test Thematique");

        // Save the entity to the repository
        DetailEquipe savedDetailEquipe = detailEquipeRepository.save(detailEquipe);

        // Find the entity by ID
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
        // Prepare a DetailEquipeDTO with data
        DetailEquipeDTO detailEquipeDTO = new DetailEquipeDTO();
        detailEquipeDTO.setSalle(123);
        detailEquipeDTO.setThematique("Test Thematique");

        // Convert DetailEquipeDTO to JSON
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

                    // Validate the response data if needed
                    assertEquals(123, addedDetailEquipe.getSalle());
                    assertEquals("Test Thematique", addedDetailEquipe.getThematique());
                });
    }


    @Test
    void testUpdateDetailEquipe() throws Exception {
        // Prepare a DetailEquipeDTO with data
        DetailEquipeDTO detailEquipeDTO = new DetailEquipeDTO();
        detailEquipeDTO.setIdDetailEquipe(1); // Existing DetailEquipe ID
        detailEquipeDTO.setSalle(123);
        detailEquipeDTO.setThematique("Updated Thematique");

        // Convert DetailEquipeDTO to JSON
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
                    // Convert the response JSON to a DetailEquipe object
                    String responseContent = result.getResponse().getContentAsString();
                    ObjectMapper objectMapper = new ObjectMapper();
                    DetailEquipe retrievedDetailEquipe = objectMapper.readValue(responseContent, DetailEquipe.class);

                    // Validate the retrieved DetailEquipe
                    assertEquals(1, retrievedDetailEquipe.getIdDetailEquipe());
                    // Add more validation as needed
                });
    }







}
