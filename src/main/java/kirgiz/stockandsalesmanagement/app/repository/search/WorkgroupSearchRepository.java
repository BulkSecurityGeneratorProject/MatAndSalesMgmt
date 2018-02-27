package kirgiz.stockandsalesmanagement.app.repository.search;

import kirgiz.stockandsalesmanagement.app.domain.Workgroup;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the Workgroup entity.
 */
public interface WorkgroupSearchRepository extends ElasticsearchRepository<Workgroup, Long> {
}
