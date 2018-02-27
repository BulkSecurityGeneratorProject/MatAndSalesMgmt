package kirgiz.stockandsalesmanagement.app.service.mapper;

import kirgiz.stockandsalesmanagement.app.domain.*;
import kirgiz.stockandsalesmanagement.app.service.dto.UsrDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Usr and its DTO UsrDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface UsrMapper extends EntityMapper<UsrDTO, Usr> {



    default Usr fromId(Long id) {
        if (id == null) {
            return null;
        }
        Usr usr = new Usr();
        usr.setId(id);
        return usr;
    }
}
