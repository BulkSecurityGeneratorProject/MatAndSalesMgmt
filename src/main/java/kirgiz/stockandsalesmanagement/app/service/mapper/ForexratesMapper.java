package kirgiz.stockandsalesmanagement.app.service.mapper;

import kirgiz.stockandsalesmanagement.app.domain.*;
import kirgiz.stockandsalesmanagement.app.service.dto.ForexratesDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Forexrates and its DTO ForexratesDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface ForexratesMapper extends EntityMapper<ForexratesDTO, Forexrates> {



    default Forexrates fromId(Long id) {
        if (id == null) {
            return null;
        }
        Forexrates forexrates = new Forexrates();
        forexrates.setId(id);
        return forexrates;
    }
}
