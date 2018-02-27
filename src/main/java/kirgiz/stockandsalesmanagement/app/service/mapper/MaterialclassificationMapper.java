package kirgiz.stockandsalesmanagement.app.service.mapper;

import kirgiz.stockandsalesmanagement.app.domain.*;
import kirgiz.stockandsalesmanagement.app.service.dto.MaterialclassificationDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Materialclassification and its DTO MaterialclassificationDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface MaterialclassificationMapper extends EntityMapper<MaterialclassificationDTO, Materialclassification> {


    @Mapping(target = "material1S", ignore = true)
    @Mapping(target = "material4S", ignore = true)
    Materialclassification toEntity(MaterialclassificationDTO materialclassificationDTO);

    default Materialclassification fromId(Long id) {
        if (id == null) {
            return null;
        }
        Materialclassification materialclassification = new Materialclassification();
        materialclassification.setId(id);
        return materialclassification;
    }
}
