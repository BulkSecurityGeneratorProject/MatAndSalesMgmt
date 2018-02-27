package kirgiz.stockandsalesmanagement.app.repository;

import kirgiz.stockandsalesmanagement.app.domain.Usr;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the Usr entity.
 */
@SuppressWarnings("unused")
@Repository
public interface UsrRepository extends JpaRepository<Usr, Long> {

}
