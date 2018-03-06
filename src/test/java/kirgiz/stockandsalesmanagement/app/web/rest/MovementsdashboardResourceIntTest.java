package kirgiz.stockandsalesmanagement.app.web.rest;

import kirgiz.stockandsalesmanagement.app.MaterialAndStockManagementApp;

import kirgiz.stockandsalesmanagement.app.domain.Movementsdashboard;
import kirgiz.stockandsalesmanagement.app.repository.MovementsdashboardRepository;
import kirgiz.stockandsalesmanagement.app.service.MovementsdashboardService;
import kirgiz.stockandsalesmanagement.app.repository.search.MovementsdashboardSearchRepository;
import kirgiz.stockandsalesmanagement.app.web.rest.errors.ExceptionTranslator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

import static kirgiz.stockandsalesmanagement.app.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the MovementsdashboardResource REST controller.
 *
 * @see MovementsdashboardResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = MaterialAndStockManagementApp.class)
public class MovementsdashboardResourceIntTest {

    private static final LocalDate DEFAULT_MOVEMENT_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_MOVEMENT_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_MATERIAL_CLASS = "AAAAAAAAAA";
    private static final String UPDATED_MATERIAL_CLASS = "BBBBBBBBBB";

    private static final String DEFAULT_INITIAL_LOCATION = "AAAAAAAAAA";
    private static final String UPDATED_INITIAL_LOCATION = "BBBBBBBBBB";

    private static final String DEFAULT_FINAL_LOCATION = "AAAAAAAAAA";
    private static final String UPDATED_FINAL_LOCATION = "BBBBBBBBBB";

    private static final BigDecimal DEFAULT_PROFIT_OR_LOSS = new BigDecimal(1);
    private static final BigDecimal UPDATED_PROFIT_OR_LOSS = new BigDecimal(2);

    private static final Integer DEFAULT_NUMBER_OF_ITEMS = 1;
    private static final Integer UPDATED_NUMBER_OF_ITEMS = 2;

    @Autowired
    private MovementsdashboardRepository movementsdashboardRepository;

    @Autowired
    private MovementsdashboardService movementsdashboardService;

    @Autowired
    private MovementsdashboardSearchRepository movementsdashboardSearchRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restMovementsdashboardMockMvc;

    private Movementsdashboard movementsdashboard;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final MovementsdashboardResource movementsdashboardResource = new MovementsdashboardResource(movementsdashboardService);
        this.restMovementsdashboardMockMvc = MockMvcBuilders.standaloneSetup(movementsdashboardResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Movementsdashboard createEntity(EntityManager em) {
        Movementsdashboard movementsdashboard = new Movementsdashboard()
            .movementDate(DEFAULT_MOVEMENT_DATE)
            .materialClass(DEFAULT_MATERIAL_CLASS)
            .initialLocation(DEFAULT_INITIAL_LOCATION)
            .finalLocation(DEFAULT_FINAL_LOCATION)
            .profitOrLoss(DEFAULT_PROFIT_OR_LOSS)
            .numberOfItems(DEFAULT_NUMBER_OF_ITEMS);
        return movementsdashboard;
    }

    @Before
    public void initTest() {
        movementsdashboardSearchRepository.deleteAll();
        movementsdashboard = createEntity(em);
    }

    @Test
    @Transactional
    public void createMovementsdashboard() throws Exception {
        int databaseSizeBeforeCreate = movementsdashboardRepository.findAll().size();

        // Create the Movementsdashboard
        restMovementsdashboardMockMvc.perform(post("/api/movementsdashboards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(movementsdashboard)))
            .andExpect(status().isCreated());

        // Validate the Movementsdashboard in the database
        List<Movementsdashboard> movementsdashboardList = movementsdashboardRepository.findAll();
        assertThat(movementsdashboardList).hasSize(databaseSizeBeforeCreate + 1);
        Movementsdashboard testMovementsdashboard = movementsdashboardList.get(movementsdashboardList.size() - 1);
        assertThat(testMovementsdashboard.getMovementDate()).isEqualTo(DEFAULT_MOVEMENT_DATE);
        assertThat(testMovementsdashboard.getMaterialClass()).isEqualTo(DEFAULT_MATERIAL_CLASS);
        assertThat(testMovementsdashboard.getInitialLocation()).isEqualTo(DEFAULT_INITIAL_LOCATION);
        assertThat(testMovementsdashboard.getFinalLocation()).isEqualTo(DEFAULT_FINAL_LOCATION);
        assertThat(testMovementsdashboard.getProfitOrLoss()).isEqualTo(DEFAULT_PROFIT_OR_LOSS);
        assertThat(testMovementsdashboard.getNumberOfItems()).isEqualTo(DEFAULT_NUMBER_OF_ITEMS);

        // Validate the Movementsdashboard in Elasticsearch
        Movementsdashboard movementsdashboardEs = movementsdashboardSearchRepository.findOne(testMovementsdashboard.getId());
        assertThat(movementsdashboardEs).isEqualToIgnoringGivenFields(testMovementsdashboard);
    }

    @Test
    @Transactional
    public void createMovementsdashboardWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = movementsdashboardRepository.findAll().size();

        // Create the Movementsdashboard with an existing ID
        movementsdashboard.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMovementsdashboardMockMvc.perform(post("/api/movementsdashboards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(movementsdashboard)))
            .andExpect(status().isBadRequest());

        // Validate the Movementsdashboard in the database
        List<Movementsdashboard> movementsdashboardList = movementsdashboardRepository.findAll();
        assertThat(movementsdashboardList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkMovementDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = movementsdashboardRepository.findAll().size();
        // set the field null
        movementsdashboard.setMovementDate(null);

        // Create the Movementsdashboard, which fails.

        restMovementsdashboardMockMvc.perform(post("/api/movementsdashboards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(movementsdashboard)))
            .andExpect(status().isBadRequest());

        List<Movementsdashboard> movementsdashboardList = movementsdashboardRepository.findAll();
        assertThat(movementsdashboardList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkMaterialClassIsRequired() throws Exception {
        int databaseSizeBeforeTest = movementsdashboardRepository.findAll().size();
        // set the field null
        movementsdashboard.setMaterialClass(null);

        // Create the Movementsdashboard, which fails.

        restMovementsdashboardMockMvc.perform(post("/api/movementsdashboards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(movementsdashboard)))
            .andExpect(status().isBadRequest());

        List<Movementsdashboard> movementsdashboardList = movementsdashboardRepository.findAll();
        assertThat(movementsdashboardList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkInitialLocationIsRequired() throws Exception {
        int databaseSizeBeforeTest = movementsdashboardRepository.findAll().size();
        // set the field null
        movementsdashboard.setInitialLocation(null);

        // Create the Movementsdashboard, which fails.

        restMovementsdashboardMockMvc.perform(post("/api/movementsdashboards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(movementsdashboard)))
            .andExpect(status().isBadRequest());

        List<Movementsdashboard> movementsdashboardList = movementsdashboardRepository.findAll();
        assertThat(movementsdashboardList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkFinalLocationIsRequired() throws Exception {
        int databaseSizeBeforeTest = movementsdashboardRepository.findAll().size();
        // set the field null
        movementsdashboard.setFinalLocation(null);

        // Create the Movementsdashboard, which fails.

        restMovementsdashboardMockMvc.perform(post("/api/movementsdashboards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(movementsdashboard)))
            .andExpect(status().isBadRequest());

        List<Movementsdashboard> movementsdashboardList = movementsdashboardRepository.findAll();
        assertThat(movementsdashboardList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkNumberOfItemsIsRequired() throws Exception {
        int databaseSizeBeforeTest = movementsdashboardRepository.findAll().size();
        // set the field null
        movementsdashboard.setNumberOfItems(null);

        // Create the Movementsdashboard, which fails.

        restMovementsdashboardMockMvc.perform(post("/api/movementsdashboards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(movementsdashboard)))
            .andExpect(status().isBadRequest());

        List<Movementsdashboard> movementsdashboardList = movementsdashboardRepository.findAll();
        assertThat(movementsdashboardList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllMovementsdashboards() throws Exception {
        // Initialize the database
        movementsdashboardRepository.saveAndFlush(movementsdashboard);

        // Get all the movementsdashboardList
        restMovementsdashboardMockMvc.perform(get("/api/movementsdashboards?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(movementsdashboard.getId().intValue())))
            .andExpect(jsonPath("$.[*].movementDate").value(hasItem(DEFAULT_MOVEMENT_DATE.toString())))
            .andExpect(jsonPath("$.[*].materialClass").value(hasItem(DEFAULT_MATERIAL_CLASS.toString())))
            .andExpect(jsonPath("$.[*].initialLocation").value(hasItem(DEFAULT_INITIAL_LOCATION.toString())))
            .andExpect(jsonPath("$.[*].finalLocation").value(hasItem(DEFAULT_FINAL_LOCATION.toString())))
            .andExpect(jsonPath("$.[*].profitOrLoss").value(hasItem(DEFAULT_PROFIT_OR_LOSS.intValue())))
            .andExpect(jsonPath("$.[*].numberOfItems").value(hasItem(DEFAULT_NUMBER_OF_ITEMS)));
    }

    @Test
    @Transactional
    public void getMovementsdashboard() throws Exception {
        // Initialize the database
        movementsdashboardRepository.saveAndFlush(movementsdashboard);

        // Get the movementsdashboard
        restMovementsdashboardMockMvc.perform(get("/api/movementsdashboards/{id}", movementsdashboard.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(movementsdashboard.getId().intValue()))
            .andExpect(jsonPath("$.movementDate").value(DEFAULT_MOVEMENT_DATE.toString()))
            .andExpect(jsonPath("$.materialClass").value(DEFAULT_MATERIAL_CLASS.toString()))
            .andExpect(jsonPath("$.initialLocation").value(DEFAULT_INITIAL_LOCATION.toString()))
            .andExpect(jsonPath("$.finalLocation").value(DEFAULT_FINAL_LOCATION.toString()))
            .andExpect(jsonPath("$.profitOrLoss").value(DEFAULT_PROFIT_OR_LOSS.intValue()))
            .andExpect(jsonPath("$.numberOfItems").value(DEFAULT_NUMBER_OF_ITEMS));
    }

    @Test
    @Transactional
    public void getNonExistingMovementsdashboard() throws Exception {
        // Get the movementsdashboard
        restMovementsdashboardMockMvc.perform(get("/api/movementsdashboards/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMovementsdashboard() throws Exception {
        // Initialize the database
        movementsdashboardService.save(movementsdashboard);

        int databaseSizeBeforeUpdate = movementsdashboardRepository.findAll().size();

        // Update the movementsdashboard
        Movementsdashboard updatedMovementsdashboard = movementsdashboardRepository.findOne(movementsdashboard.getId());
        // Disconnect from session so that the updates on updatedMovementsdashboard are not directly saved in db
        em.detach(updatedMovementsdashboard);
        updatedMovementsdashboard
            .movementDate(UPDATED_MOVEMENT_DATE)
            .materialClass(UPDATED_MATERIAL_CLASS)
            .initialLocation(UPDATED_INITIAL_LOCATION)
            .finalLocation(UPDATED_FINAL_LOCATION)
            .profitOrLoss(UPDATED_PROFIT_OR_LOSS)
            .numberOfItems(UPDATED_NUMBER_OF_ITEMS);

        restMovementsdashboardMockMvc.perform(put("/api/movementsdashboards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedMovementsdashboard)))
            .andExpect(status().isOk());

        // Validate the Movementsdashboard in the database
        List<Movementsdashboard> movementsdashboardList = movementsdashboardRepository.findAll();
        assertThat(movementsdashboardList).hasSize(databaseSizeBeforeUpdate);
        Movementsdashboard testMovementsdashboard = movementsdashboardList.get(movementsdashboardList.size() - 1);
        assertThat(testMovementsdashboard.getMovementDate()).isEqualTo(UPDATED_MOVEMENT_DATE);
        assertThat(testMovementsdashboard.getMaterialClass()).isEqualTo(UPDATED_MATERIAL_CLASS);
        assertThat(testMovementsdashboard.getInitialLocation()).isEqualTo(UPDATED_INITIAL_LOCATION);
        assertThat(testMovementsdashboard.getFinalLocation()).isEqualTo(UPDATED_FINAL_LOCATION);
        assertThat(testMovementsdashboard.getProfitOrLoss()).isEqualTo(UPDATED_PROFIT_OR_LOSS);
        assertThat(testMovementsdashboard.getNumberOfItems()).isEqualTo(UPDATED_NUMBER_OF_ITEMS);

        // Validate the Movementsdashboard in Elasticsearch
        Movementsdashboard movementsdashboardEs = movementsdashboardSearchRepository.findOne(testMovementsdashboard.getId());
        assertThat(movementsdashboardEs).isEqualToIgnoringGivenFields(testMovementsdashboard);
    }

    @Test
    @Transactional
    public void updateNonExistingMovementsdashboard() throws Exception {
        int databaseSizeBeforeUpdate = movementsdashboardRepository.findAll().size();

        // Create the Movementsdashboard

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restMovementsdashboardMockMvc.perform(put("/api/movementsdashboards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(movementsdashboard)))
            .andExpect(status().isCreated());

        // Validate the Movementsdashboard in the database
        List<Movementsdashboard> movementsdashboardList = movementsdashboardRepository.findAll();
        assertThat(movementsdashboardList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteMovementsdashboard() throws Exception {
        // Initialize the database
        movementsdashboardService.save(movementsdashboard);

        int databaseSizeBeforeDelete = movementsdashboardRepository.findAll().size();

        // Get the movementsdashboard
        restMovementsdashboardMockMvc.perform(delete("/api/movementsdashboards/{id}", movementsdashboard.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate Elasticsearch is empty
        boolean movementsdashboardExistsInEs = movementsdashboardSearchRepository.exists(movementsdashboard.getId());
        assertThat(movementsdashboardExistsInEs).isFalse();

        // Validate the database is empty
        List<Movementsdashboard> movementsdashboardList = movementsdashboardRepository.findAll();
        assertThat(movementsdashboardList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void searchMovementsdashboard() throws Exception {
        // Initialize the database
        movementsdashboardService.save(movementsdashboard);

        // Search the movementsdashboard
        restMovementsdashboardMockMvc.perform(get("/api/_search/movementsdashboards?query=id:" + movementsdashboard.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(movementsdashboard.getId().intValue())))
            .andExpect(jsonPath("$.[*].movementDate").value(hasItem(DEFAULT_MOVEMENT_DATE.toString())))
            .andExpect(jsonPath("$.[*].materialClass").value(hasItem(DEFAULT_MATERIAL_CLASS.toString())))
            .andExpect(jsonPath("$.[*].initialLocation").value(hasItem(DEFAULT_INITIAL_LOCATION.toString())))
            .andExpect(jsonPath("$.[*].finalLocation").value(hasItem(DEFAULT_FINAL_LOCATION.toString())))
            .andExpect(jsonPath("$.[*].profitOrLoss").value(hasItem(DEFAULT_PROFIT_OR_LOSS.intValue())))
            .andExpect(jsonPath("$.[*].numberOfItems").value(hasItem(DEFAULT_NUMBER_OF_ITEMS)));
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Movementsdashboard.class);
        Movementsdashboard movementsdashboard1 = new Movementsdashboard();
        movementsdashboard1.setId(1L);
        Movementsdashboard movementsdashboard2 = new Movementsdashboard();
        movementsdashboard2.setId(movementsdashboard1.getId());
        assertThat(movementsdashboard1).isEqualTo(movementsdashboard2);
        movementsdashboard2.setId(2L);
        assertThat(movementsdashboard1).isNotEqualTo(movementsdashboard2);
        movementsdashboard1.setId(null);
        assertThat(movementsdashboard1).isNotEqualTo(movementsdashboard2);
    }
}
