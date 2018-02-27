package kirgiz.stockandsalesmanagement.app.service.mapper;

import kirgiz.stockandsalesmanagement.app.domain.*;
import kirgiz.stockandsalesmanagement.app.service.dto.ThirdDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Third and its DTO ThirdDTO.
 */
@Mapper(componentModel = "spring", uses = {AddressMapper.class, ThirdclassificationMapper.class, CivilityMapper.class})
public interface ThirdMapper extends EntityMapper<ThirdDTO, Third> {

    @Mapping(source = "thirdClassification9.id", target = "thirdClassification9Id")
    @Mapping(source = "thirdClassification9.name", target = "thirdClassification9Name")
    @Mapping(source = "civility1.id", target = "civility1Id")
    @Mapping(source = "civility1.name", target = "civility1Name")
    ThirdDTO toDto(Third third);

    @Mapping(target = "materialhistory1S", ignore = true)
    @Mapping(target = "materialhistory2S", ignore = true)
    @Mapping(source = "thirdClassification9Id", target = "thirdClassification9")
    @Mapping(source = "civility1Id", target = "civility1")
    Third toEntity(ThirdDTO thirdDTO);

    default Third fromId(Long id) {
        if (id == null) {
            return null;
        }
        Third third = new Third();
        third.setId(id);
        return third;
    }
}
