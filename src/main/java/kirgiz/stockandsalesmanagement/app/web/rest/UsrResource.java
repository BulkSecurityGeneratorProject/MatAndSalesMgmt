package kirgiz.stockandsalesmanagement.app.web.rest;

import com.codahale.metrics.annotation.Timed;
import kirgiz.stockandsalesmanagement.app.service.UsrService;
import kirgiz.stockandsalesmanagement.app.web.rest.errors.BadRequestAlertException;
import kirgiz.stockandsalesmanagement.app.web.rest.util.HeaderUtil;
import kirgiz.stockandsalesmanagement.app.web.rest.util.PaginationUtil;
import kirgiz.stockandsalesmanagement.app.service.dto.UsrDTO;
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
 * REST controller for managing Usr.
 */
@RestController
@RequestMapping("/api")
public class UsrResource {

    private final Logger log = LoggerFactory.getLogger(UsrResource.class);

    private static final String ENTITY_NAME = "usr";

    private final UsrService usrService;

    public UsrResource(UsrService usrService) {
        this.usrService = usrService;
    }

    /**
     * POST  /usrs : Create a new usr.
     *
     * @param usrDTO the usrDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new usrDTO, or with status 400 (Bad Request) if the usr has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/usrs")
    @Timed
    public ResponseEntity<UsrDTO> createUsr(@Valid @RequestBody UsrDTO usrDTO) throws URISyntaxException {
        log.debug("REST request to save Usr : {}", usrDTO);
        if (usrDTO.getId() != null) {
            throw new BadRequestAlertException("A new usr cannot already have an ID", ENTITY_NAME, "idexists");
        }
        UsrDTO result = usrService.save(usrDTO);
        return ResponseEntity.created(new URI("/api/usrs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /usrs : Updates an existing usr.
     *
     * @param usrDTO the usrDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated usrDTO,
     * or with status 400 (Bad Request) if the usrDTO is not valid,
     * or with status 500 (Internal Server Error) if the usrDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/usrs")
    @Timed
    public ResponseEntity<UsrDTO> updateUsr(@Valid @RequestBody UsrDTO usrDTO) throws URISyntaxException {
        log.debug("REST request to update Usr : {}", usrDTO);
        if (usrDTO.getId() == null) {
            return createUsr(usrDTO);
        }
        UsrDTO result = usrService.save(usrDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, usrDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /usrs : get all the usrs.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of usrs in body
     */
    @GetMapping("/usrs")
    @Timed
    public ResponseEntity<List<UsrDTO>> getAllUsrs(Pageable pageable) {
        log.debug("REST request to get a page of Usrs");
        Page<UsrDTO> page = usrService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/usrs");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /usrs/:id : get the "id" usr.
     *
     * @param id the id of the usrDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the usrDTO, or with status 404 (Not Found)
     */
    @GetMapping("/usrs/{id}")
    @Timed
    public ResponseEntity<UsrDTO> getUsr(@PathVariable Long id) {
        log.debug("REST request to get Usr : {}", id);
        UsrDTO usrDTO = usrService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(usrDTO));
    }

    /**
     * DELETE  /usrs/:id : delete the "id" usr.
     *
     * @param id the id of the usrDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/usrs/{id}")
    @Timed
    public ResponseEntity<Void> deleteUsr(@PathVariable Long id) {
        log.debug("REST request to delete Usr : {}", id);
        usrService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * SEARCH  /_search/usrs?query=:query : search for the usr corresponding
     * to the query.
     *
     * @param query the query of the usr search
     * @param pageable the pagination information
     * @return the result of the search
     */
    @GetMapping("/_search/usrs")
    @Timed
    public ResponseEntity<List<UsrDTO>> searchUsrs(@RequestParam String query, Pageable pageable) {
        log.debug("REST request to search for a page of Usrs for query {}", query);
        Page<UsrDTO> page = usrService.search(query, pageable);
        HttpHeaders headers = PaginationUtil.generateSearchPaginationHttpHeaders(query, page, "/api/_search/usrs");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

}
