package kirgiz.stockandsalesmanagement.app.repository.search;

import kirgiz.stockandsalesmanagement.app.domain.Movementsdashboard;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the Movementsdashboard entity.
 */
public interface MovementsdashboardSearchRepository extends ElasticsearchRepository<Movementsdashboard, Long> {
}
