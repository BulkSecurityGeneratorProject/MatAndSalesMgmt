package kirgiz.stockandsalesmanagement.app.service;

import kirgiz.stockandsalesmanagement.app.domain.Usr;
import kirgiz.stockandsalesmanagement.app.repository.UsrRepository;
import kirgiz.stockandsalesmanagement.app.repository.search.UsrSearchRepository;
import kirgiz.stockandsalesmanagement.app.service.dto.UsrDTO;
import kirgiz.stockandsalesmanagement.app.service.mapper.UsrMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * Service Implementation for managing Usr.
 */
@Service
@Transactional
public class UsrService {

    private final Logger log = LoggerFactory.getLogger(UsrService.class);

    private final UsrRepository usrRepository;

    private final UsrMapper usrMapper;

    private final UsrSearchRepository usrSearchRepository;

    public UsrService(UsrRepository usrRepository, UsrMapper usrMapper, UsrSearchRepository usrSearchRepository) {
        this.usrRepository = usrRepository;
        this.usrMapper = usrMapper;
        this.usrSearchRepository = usrSearchRepository;
    }

    /**
     * Save a usr.
     *
     * @param usrDTO the entity to save
     * @return the persisted entity
     */
    public UsrDTO save(UsrDTO usrDTO) {
        log.debug("Request to save Usr : {}", usrDTO);
        Usr usr = usrMapper.toEntity(usrDTO);
        usr = usrRepository.save(usr);
        UsrDTO result = usrMapper.toDto(usr);
        usrSearchRepository.save(usr);
        return result;
    }

    /**
     * Get all the usrs.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Transactional(readOnly = true)
    public Page<UsrDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Usrs");
        return usrRepository.findAll(pageable)
            .map(usrMapper::toDto);
    }

    /**
     * Get one usr by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Transactional(readOnly = true)
    public UsrDTO findOne(Long id) {
        log.debug("Request to get Usr : {}", id);
        Usr usr = usrRepository.findOne(id);
        return usrMapper.toDto(usr);
    }

    /**
     * Delete the usr by id.
     *
     * @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete Usr : {}", id);
        usrRepository.delete(id);
        usrSearchRepository.delete(id);
    }

    /**
     * Search for the usr corresponding to the query.
     *
     * @param query the query of the search
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Transactional(readOnly = true)
    public Page<UsrDTO> search(String query, Pageable pageable) {
        log.debug("Request to search for a page of Usrs for query {}", query);
        Page<Usr> result = usrSearchRepository.search(queryStringQuery(query), pageable);
        return result.map(usrMapper::toDto);
    }
}
