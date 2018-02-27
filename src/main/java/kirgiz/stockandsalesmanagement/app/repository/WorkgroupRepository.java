package kirgiz.stockandsalesmanagement.app.repository;

import kirgiz.stockandsalesmanagement.app.domain.Workgroup;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the Workgroup entity.
 */
@SuppressWarnings("unused")
@Repository
public interface WorkgroupRepository extends JpaRepository<Workgroup, Long> {

}
