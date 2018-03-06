package kirgiz.stockandsalesmanagement.app.service;

import kirgiz.stockandsalesmanagement.app.domain.Movementsdashboard;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing Movementsdashboard.
 */
public interface MovementsdashboardService {

    /**
     * Save a movementsdashboard.
     *
     * @param movementsdashboard the entity to save
     * @return the persisted entity
     */
    Movementsdashboard save(Movementsdashboard movementsdashboard);

    /**
     * Get all the movementsdashboards.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<Movementsdashboard> findAll(Pageable pageable);

    /**
     * Get the "id" movementsdashboard.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Movementsdashboard findOne(Long id);

    /**
     * Delete the "id" movementsdashboard.
     *
     * @param id the id of the entity
     */
    void delete(Long id);

    /**
     * Search for the movementsdashboard corresponding to the query.
     *
     * @param query the query of the search
     * 
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<Movementsdashboard> search(String query, Pageable pageable);
}
