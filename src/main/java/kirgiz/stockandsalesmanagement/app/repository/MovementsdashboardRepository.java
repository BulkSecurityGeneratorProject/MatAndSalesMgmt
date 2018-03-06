package kirgiz.stockandsalesmanagement.app.repository;

import kirgiz.stockandsalesmanagement.app.domain.Movementsdashboard;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the Movementsdashboard entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MovementsdashboardRepository extends JpaRepository<Movementsdashboard, Long> {

}
