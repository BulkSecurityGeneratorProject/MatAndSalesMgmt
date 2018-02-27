package kirgiz.stockandsalesmanagement.app.web.rest;

import kirgiz.stockandsalesmanagement.app.MaterialAndStockManagementApp;

import kirgiz.stockandsalesmanagement.app.domain.Usr;
import kirgiz.stockandsalesmanagement.app.repository.UsrRepository;
import kirgiz.stockandsalesmanagement.app.service.UsrService;
import kirgiz.stockandsalesmanagement.app.repository.search.UsrSearchRepository;
import kirgiz.stockandsalesmanagement.app.service.dto.UsrDTO;
import kirgiz.stockandsalesmanagement.app.service.mapper.UsrMapper;
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
import java.util.List;

import static kirgiz.stockandsalesmanagement.app.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the UsrResource REST controller.
 *
 * @see UsrResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = MaterialAndStockManagementApp.class)
public class UsrResourceIntTest {

    private static final String DEFAULT_CODE = "AAAAAAAAAA";
    private static final String UPDATED_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_FIRST_NAME = "AAAAAAAAAA";
    private static final String UPDATED_FIRST_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_LAST_NAME = "AAAAAAAAAA";
    private static final String UPDATED_LAST_NAME = "BBBBBBBBBB";

    private static final Boolean DEFAULT_ISACTIVE = false;
    private static final Boolean UPDATED_ISACTIVE = true;

    private static final String DEFAULT_COMMENTS = "AAAAAAAAAA";
    private static final String UPDATED_COMMENTS = "BBBBBBBBBB";

    @Autowired
    private UsrRepository usrRepository;

    @Autowired
    private UsrMapper usrMapper;

    @Autowired
    private UsrService usrService;

    @Autowired
    private UsrSearchRepository usrSearchRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restUsrMockMvc;

    private Usr usr;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final UsrResource usrResource = new UsrResource(usrService);
        this.restUsrMockMvc = MockMvcBuilders.standaloneSetup(usrResource)
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
    public static Usr createEntity(EntityManager em) {
        Usr usr = new Usr()
            .code(DEFAULT_CODE)
            .firstName(DEFAULT_FIRST_NAME)
            .lastName(DEFAULT_LAST_NAME)
            .isactive(DEFAULT_ISACTIVE)
            .comments(DEFAULT_COMMENTS);
        return usr;
    }

    @Before
    public void initTest() {
        usrSearchRepository.deleteAll();
        usr = createEntity(em);
    }

    @Test
    @Transactional
    public void createUsr() throws Exception {
        int databaseSizeBeforeCreate = usrRepository.findAll().size();

        // Create the Usr
        UsrDTO usrDTO = usrMapper.toDto(usr);
        restUsrMockMvc.perform(post("/api/usrs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(usrDTO)))
            .andExpect(status().isCreated());

        // Validate the Usr in the database
        List<Usr> usrList = usrRepository.findAll();
        assertThat(usrList).hasSize(databaseSizeBeforeCreate + 1);
        Usr testUsr = usrList.get(usrList.size() - 1);
        assertThat(testUsr.getCode()).isEqualTo(DEFAULT_CODE);
        assertThat(testUsr.getFirstName()).isEqualTo(DEFAULT_FIRST_NAME);
        assertThat(testUsr.getLastName()).isEqualTo(DEFAULT_LAST_NAME);
        assertThat(testUsr.isIsactive()).isEqualTo(DEFAULT_ISACTIVE);
        assertThat(testUsr.getComments()).isEqualTo(DEFAULT_COMMENTS);

        // Validate the Usr in Elasticsearch
        Usr usrEs = usrSearchRepository.findOne(testUsr.getId());
        assertThat(usrEs).isEqualToIgnoringGivenFields(testUsr);
    }

    @Test
    @Transactional
    public void createUsrWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = usrRepository.findAll().size();

        // Create the Usr with an existing ID
        usr.setId(1L);
        UsrDTO usrDTO = usrMapper.toDto(usr);

        // An entity with an existing ID cannot be created, so this API call must fail
        restUsrMockMvc.perform(post("/api/usrs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(usrDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Usr in the database
        List<Usr> usrList = usrRepository.findAll();
        assertThat(usrList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkCodeIsRequired() throws Exception {
        int databaseSizeBeforeTest = usrRepository.findAll().size();
        // set the field null
        usr.setCode(null);

        // Create the Usr, which fails.
        UsrDTO usrDTO = usrMapper.toDto(usr);

        restUsrMockMvc.perform(post("/api/usrs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(usrDTO)))
            .andExpect(status().isBadRequest());

        List<Usr> usrList = usrRepository.findAll();
        assertThat(usrList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkFirstNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = usrRepository.findAll().size();
        // set the field null
        usr.setFirstName(null);

        // Create the Usr, which fails.
        UsrDTO usrDTO = usrMapper.toDto(usr);

        restUsrMockMvc.perform(post("/api/usrs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(usrDTO)))
            .andExpect(status().isBadRequest());

        List<Usr> usrList = usrRepository.findAll();
        assertThat(usrList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkLastNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = usrRepository.findAll().size();
        // set the field null
        usr.setLastName(null);

        // Create the Usr, which fails.
        UsrDTO usrDTO = usrMapper.toDto(usr);

        restUsrMockMvc.perform(post("/api/usrs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(usrDTO)))
            .andExpect(status().isBadRequest());

        List<Usr> usrList = usrRepository.findAll();
        assertThat(usrList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkIsactiveIsRequired() throws Exception {
        int databaseSizeBeforeTest = usrRepository.findAll().size();
        // set the field null
        usr.setIsactive(null);

        // Create the Usr, which fails.
        UsrDTO usrDTO = usrMapper.toDto(usr);

        restUsrMockMvc.perform(post("/api/usrs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(usrDTO)))
            .andExpect(status().isBadRequest());

        List<Usr> usrList = usrRepository.findAll();
        assertThat(usrList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllUsrs() throws Exception {
        // Initialize the database
        usrRepository.saveAndFlush(usr);

        // Get all the usrList
        restUsrMockMvc.perform(get("/api/usrs?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(usr.getId().intValue())))
            .andExpect(jsonPath("$.[*].code").value(hasItem(DEFAULT_CODE.toString())))
            .andExpect(jsonPath("$.[*].firstName").value(hasItem(DEFAULT_FIRST_NAME.toString())))
            .andExpect(jsonPath("$.[*].lastName").value(hasItem(DEFAULT_LAST_NAME.toString())))
            .andExpect(jsonPath("$.[*].isactive").value(hasItem(DEFAULT_ISACTIVE.booleanValue())))
            .andExpect(jsonPath("$.[*].comments").value(hasItem(DEFAULT_COMMENTS.toString())));
    }

    @Test
    @Transactional
    public void getUsr() throws Exception {
        // Initialize the database
        usrRepository.saveAndFlush(usr);

        // Get the usr
        restUsrMockMvc.perform(get("/api/usrs/{id}", usr.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(usr.getId().intValue()))
            .andExpect(jsonPath("$.code").value(DEFAULT_CODE.toString()))
            .andExpect(jsonPath("$.firstName").value(DEFAULT_FIRST_NAME.toString()))
            .andExpect(jsonPath("$.lastName").value(DEFAULT_LAST_NAME.toString()))
            .andExpect(jsonPath("$.isactive").value(DEFAULT_ISACTIVE.booleanValue()))
            .andExpect(jsonPath("$.comments").value(DEFAULT_COMMENTS.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingUsr() throws Exception {
        // Get the usr
        restUsrMockMvc.perform(get("/api/usrs/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateUsr() throws Exception {
        // Initialize the database
        usrRepository.saveAndFlush(usr);
        usrSearchRepository.save(usr);
        int databaseSizeBeforeUpdate = usrRepository.findAll().size();

        // Update the usr
        Usr updatedUsr = usrRepository.findOne(usr.getId());
        // Disconnect from session so that the updates on updatedUsr are not directly saved in db
        em.detach(updatedUsr);
        updatedUsr
            .code(UPDATED_CODE)
            .firstName(UPDATED_FIRST_NAME)
            .lastName(UPDATED_LAST_NAME)
            .isactive(UPDATED_ISACTIVE)
            .comments(UPDATED_COMMENTS);
        UsrDTO usrDTO = usrMapper.toDto(updatedUsr);

        restUsrMockMvc.perform(put("/api/usrs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(usrDTO)))
            .andExpect(status().isOk());

        // Validate the Usr in the database
        List<Usr> usrList = usrRepository.findAll();
        assertThat(usrList).hasSize(databaseSizeBeforeUpdate);
        Usr testUsr = usrList.get(usrList.size() - 1);
        assertThat(testUsr.getCode()).isEqualTo(UPDATED_CODE);
        assertThat(testUsr.getFirstName()).isEqualTo(UPDATED_FIRST_NAME);
        assertThat(testUsr.getLastName()).isEqualTo(UPDATED_LAST_NAME);
        assertThat(testUsr.isIsactive()).isEqualTo(UPDATED_ISACTIVE);
        assertThat(testUsr.getComments()).isEqualTo(UPDATED_COMMENTS);

        // Validate the Usr in Elasticsearch
        Usr usrEs = usrSearchRepository.findOne(testUsr.getId());
        assertThat(usrEs).isEqualToIgnoringGivenFields(testUsr);
    }

    @Test
    @Transactional
    public void updateNonExistingUsr() throws Exception {
        int databaseSizeBeforeUpdate = usrRepository.findAll().size();

        // Create the Usr
        UsrDTO usrDTO = usrMapper.toDto(usr);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restUsrMockMvc.perform(put("/api/usrs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(usrDTO)))
            .andExpect(status().isCreated());

        // Validate the Usr in the database
        List<Usr> usrList = usrRepository.findAll();
        assertThat(usrList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteUsr() throws Exception {
        // Initialize the database
        usrRepository.saveAndFlush(usr);
        usrSearchRepository.save(usr);
        int databaseSizeBeforeDelete = usrRepository.findAll().size();

        // Get the usr
        restUsrMockMvc.perform(delete("/api/usrs/{id}", usr.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate Elasticsearch is empty
        boolean usrExistsInEs = usrSearchRepository.exists(usr.getId());
        assertThat(usrExistsInEs).isFalse();

        // Validate the database is empty
        List<Usr> usrList = usrRepository.findAll();
        assertThat(usrList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void searchUsr() throws Exception {
        // Initialize the database
        usrRepository.saveAndFlush(usr);
        usrSearchRepository.save(usr);

        // Search the usr
        restUsrMockMvc.perform(get("/api/_search/usrs?query=id:" + usr.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(usr.getId().intValue())))
            .andExpect(jsonPath("$.[*].code").value(hasItem(DEFAULT_CODE.toString())))
            .andExpect(jsonPath("$.[*].firstName").value(hasItem(DEFAULT_FIRST_NAME.toString())))
            .andExpect(jsonPath("$.[*].lastName").value(hasItem(DEFAULT_LAST_NAME.toString())))
            .andExpect(jsonPath("$.[*].isactive").value(hasItem(DEFAULT_ISACTIVE.booleanValue())))
            .andExpect(jsonPath("$.[*].comments").value(hasItem(DEFAULT_COMMENTS.toString())));
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Usr.class);
        Usr usr1 = new Usr();
        usr1.setId(1L);
        Usr usr2 = new Usr();
        usr2.setId(usr1.getId());
        assertThat(usr1).isEqualTo(usr2);
        usr2.setId(2L);
        assertThat(usr1).isNotEqualTo(usr2);
        usr1.setId(null);
        assertThat(usr1).isNotEqualTo(usr2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(UsrDTO.class);
        UsrDTO usrDTO1 = new UsrDTO();
        usrDTO1.setId(1L);
        UsrDTO usrDTO2 = new UsrDTO();
        assertThat(usrDTO1).isNotEqualTo(usrDTO2);
        usrDTO2.setId(usrDTO1.getId());
        assertThat(usrDTO1).isEqualTo(usrDTO2);
        usrDTO2.setId(2L);
        assertThat(usrDTO1).isNotEqualTo(usrDTO2);
        usrDTO1.setId(null);
        assertThat(usrDTO1).isNotEqualTo(usrDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(usrMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(usrMapper.fromId(null)).isNull();
    }
}
