package kirgiz.stockandsalesmanagement.app.service;

import kirgiz.stockandsalesmanagement.app.domain.Workgroup;
import kirgiz.stockandsalesmanagement.app.repository.WorkgroupRepository;
import kirgiz.stockandsalesmanagement.app.repository.search.WorkgroupSearchRepository;
import kirgiz.stockandsalesmanagement.app.service.dto.WorkgroupDTO;
import kirgiz.stockandsalesmanagement.app.service.mapper.WorkgroupMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * Service Implementation for managing Workgroup.
 */
@Service
@Transactional
public class WorkgroupService {

    private final Logger log = LoggerFactory.getLogger(WorkgroupService.class);

    private final WorkgroupRepository workgroupRepository;

    private final WorkgroupMapper workgroupMapper;

    private final WorkgroupSearchRepository workgroupSearchRepository;

    public WorkgroupService(WorkgroupRepository workgroupRepository, WorkgroupMapper workgroupMapper, WorkgroupSearchRepository workgroupSearchRepository) {
        this.workgroupRepository = workgroupRepository;
        this.workgroupMapper = workgroupMapper;
        this.workgroupSearchRepository = workgroupSearchRepository;
    }

    /**
     * Save a workgroup.
     *
     * @param workgroupDTO the entity to save
     * @return the persisted entity
     */
    public WorkgroupDTO save(WorkgroupDTO workgroupDTO) {
        log.debug("Request to save Workgroup : {}", workgroupDTO);
        Workgroup workgroup = workgroupMapper.toEntity(workgroupDTO);
        workgroup = workgroupRepository.save(workgroup);
        WorkgroupDTO result = workgroupMapper.toDto(workgroup);
        workgroupSearchRepository.save(workgroup);
        return result;
    }

    /**
     * Get all the workgroups.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Transactional(readOnly = true)
    public Page<WorkgroupDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Workgroups");
        return workgroupRepository.findAll(pageable)
            .map(workgroupMapper::toDto);
    }

    /**
     * Get one workgroup by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Transactional(readOnly = true)
    public WorkgroupDTO findOne(Long id) {
        log.debug("Request to get Workgroup : {}", id);
        Workgroup workgroup = workgroupRepository.findOne(id);
        return workgroupMapper.toDto(workgroup);
    }

    /**
     * Delete the workgroup by id.
     *
     * @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete Workgroup : {}", id);
        workgroupRepository.delete(id);
        workgroupSearchRepository.delete(id);
    }

    /**
     * Search for the workgroup corresponding to the query.
     *
     * @param query the query of the search
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Transactional(readOnly = true)
    public Page<WorkgroupDTO> search(String query, Pageable pageable) {
        log.debug("Request to search for a page of Workgroups for query {}", query);
        Page<Workgroup> result = workgroupSearchRepository.search(queryStringQuery(query), pageable);
        return result.map(workgroupMapper::toDto);
    }
}
