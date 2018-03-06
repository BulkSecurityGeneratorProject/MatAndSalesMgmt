package kirgiz.stockandsalesmanagement.app.service.impl;

import kirgiz.stockandsalesmanagement.app.service.MovementsdashboardService;
import kirgiz.stockandsalesmanagement.app.domain.Movementsdashboard;
import kirgiz.stockandsalesmanagement.app.repository.MovementsdashboardRepository;
import kirgiz.stockandsalesmanagement.app.repository.search.MovementsdashboardSearchRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * Service Implementation for managing Movementsdashboard.
 */
@Service
@Transactional
public class MovementsdashboardServiceImpl implements MovementsdashboardService {

    private final Logger log = LoggerFactory.getLogger(MovementsdashboardServiceImpl.class);

    private final MovementsdashboardRepository movementsdashboardRepository;

    private final MovementsdashboardSearchRepository movementsdashboardSearchRepository;

    public MovementsdashboardServiceImpl(MovementsdashboardRepository movementsdashboardRepository, MovementsdashboardSearchRepository movementsdashboardSearchRepository) {
        this.movementsdashboardRepository = movementsdashboardRepository;
        this.movementsdashboardSearchRepository = movementsdashboardSearchRepository;
    }

    /**
     * Save a movementsdashboard.
     *
     * @param movementsdashboard the entity to save
     * @return the persisted entity
     */
    @Override
    public Movementsdashboard save(Movementsdashboard movementsdashboard) {
        log.debug("Request to save Movementsdashboard : {}", movementsdashboard);
        Movementsdashboard result = movementsdashboardRepository.save(movementsdashboard);
        movementsdashboardSearchRepository.save(result);
        return result;
    }

    /**
     * Get all the movementsdashboards.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<Movementsdashboard> findAll(Pageable pageable) {
        log.debug("Request to get all Movementsdashboards");
        return movementsdashboardRepository.findAll(pageable);
    }

    /**
     * Get one movementsdashboard by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Movementsdashboard findOne(Long id) {
        log.debug("Request to get Movementsdashboard : {}", id);
        return movementsdashboardRepository.findOne(id);
    }

    /**
     * Delete the movementsdashboard by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Movementsdashboard : {}", id);
        movementsdashboardRepository.delete(id);
        movementsdashboardSearchRepository.delete(id);
    }

    /**
     * Search for the movementsdashboard corresponding to the query.
     *
     * @param query the query of the search
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<Movementsdashboard> search(String query, Pageable pageable) {
        log.debug("Request to search for a page of Movementsdashboards for query {}", query);
        Page<Movementsdashboard> result = movementsdashboardSearchRepository.search(queryStringQuery(query), pageable);
        return result;
    }
}
