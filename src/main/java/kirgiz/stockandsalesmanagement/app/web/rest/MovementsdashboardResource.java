package kirgiz.stockandsalesmanagement.app.web.rest;

import com.codahale.metrics.annotation.Timed;
import kirgiz.stockandsalesmanagement.app.domain.Movementsdashboard;
import kirgiz.stockandsalesmanagement.app.service.MovementsdashboardService;
import kirgiz.stockandsalesmanagement.app.web.rest.errors.BadRequestAlertException;
import kirgiz.stockandsalesmanagement.app.web.rest.util.HeaderUtil;
import kirgiz.stockandsalesmanagement.app.web.rest.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * REST controller for managing Movementsdashboard.
 */
@RestController
@RequestMapping("/api")
public class MovementsdashboardResource {

    private final Logger log = LoggerFactory.getLogger(MovementsdashboardResource.class);

    private static final String ENTITY_NAME = "movementsdashboard";

    private final MovementsdashboardService movementsdashboardService;

    public MovementsdashboardResource(MovementsdashboardService movementsdashboardService) {
        this.movementsdashboardService = movementsdashboardService;
    }

    /**
     * POST  /movementsdashboards : Create a new movementsdashboard.
     *
     * @param movementsdashboard the movementsdashboard to create
     * @return the ResponseEntity with status 201 (Created) and with body the new movementsdashboard, or with status 400 (Bad Request) if the movementsdashboard has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/movementsdashboards")
    @Timed
    public ResponseEntity<Movementsdashboard> createMovementsdashboard(@Valid @RequestBody Movementsdashboard movementsdashboard) throws URISyntaxException {
        log.debug("REST request to save Movementsdashboard : {}", movementsdashboard);
        if (movementsdashboard.getId() != null) {
            throw new BadRequestAlertException("A new movementsdashboard cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Movementsdashboard result = movementsdashboardService.save(movementsdashboard);
        return ResponseEntity.created(new URI("/api/movementsdashboards/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /movementsdashboards : Updates an existing movementsdashboard.
     *
     * @param movementsdashboard the movementsdashboard to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated movementsdashboard,
     * or with status 400 (Bad Request) if the movementsdashboard is not valid,
     * or with status 500 (Internal Server Error) if the movementsdashboard couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/movementsdashboards")
    @Timed
    public ResponseEntity<Movementsdashboard> updateMovementsdashboard(@Valid @RequestBody Movementsdashboard movementsdashboard) throws URISyntaxException {
        log.debug("REST request to update Movementsdashboard : {}", movementsdashboard);
        if (movementsdashboard.getId() == null) {
            return createMovementsdashboard(movementsdashboard);
        }
        Movementsdashboard result = movementsdashboardService.save(movementsdashboard);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, movementsdashboard.getId().toString()))
            .body(result);
    }

    /**
     * GET  /movementsdashboards : get all the movementsdashboards.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of movementsdashboards in body
     */
    @GetMapping("/movementsdashboards")
    @Timed
    public ResponseEntity<List<Movementsdashboard>> getAllMovementsdashboards(Pageable pageable) {
        log.debug("REST request to get a page of Movementsdashboards");
        Page<Movementsdashboard> page = movementsdashboardService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/movementsdashboards");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /movementsdashboards/:id : get the "id" movementsdashboard.
     *
     * @param id the id of the movementsdashboard to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the movementsdashboard, or with status 404 (Not Found)
     */
    @GetMapping("/movementsdashboards/{id}")
    @Timed
    public ResponseEntity<Movementsdashboard> getMovementsdashboard(@PathVariable Long id) {
        log.debug("REST request to get Movementsdashboard : {}", id);
        Movementsdashboard movementsdashboard = movementsdashboardService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(movementsdashboard));
    }

    /**
     * DELETE  /movementsdashboards/:id : delete the "id" movementsdashboard.
     *
     * @param id the id of the movementsdashboard to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/movementsdashboards/{id}")
    @Timed
    public ResponseEntity<Void> deleteMovementsdashboard(@PathVariable Long id) {
        log.debug("REST request to delete Movementsdashboard : {}", id);
        movementsdashboardService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * SEARCH  /_search/movementsdashboards?query=:query : search for the movementsdashboard corresponding
     * to the query.
     *
     * @param query the query of the movementsdashboard search
     * @param pageable the pagination information
     * @return the result of the search
     */
    @GetMapping("/_search/movementsdashboards")
    @Timed
    public ResponseEntity<List<Movementsdashboard>> searchMovementsdashboards(@RequestParam String query, Pageable pageable) {
        log.debug("REST request to search for a page of Movementsdashboards for query {}", query);
        Page<Movementsdashboard> page = movementsdashboardService.search(query, pageable);
        HttpHeaders headers = PaginationUtil.generateSearchPaginationHttpHeaders(query, page, "/api/_search/movementsdashboards");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

}
