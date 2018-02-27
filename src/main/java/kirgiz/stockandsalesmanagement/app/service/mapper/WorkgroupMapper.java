package kirgiz.stockandsalesmanagement.app.service.mapper;

import kirgiz.stockandsalesmanagement.app.domain.*;
import kirgiz.stockandsalesmanagement.app.service.dto.WorkgroupDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Workgroup and its DTO WorkgroupDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface WorkgroupMapper extends EntityMapper<WorkgroupDTO, Workgroup> {



    default Workgroup fromId(Long id) {
        if (id == null) {
            return null;
        }
        Workgroup workgroup = new Workgroup();
        workgroup.setId(id);
        return workgroup;
    }
}
