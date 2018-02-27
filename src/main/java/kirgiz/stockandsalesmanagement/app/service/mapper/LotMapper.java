package kirgiz.stockandsalesmanagement.app.service.mapper;

import kirgiz.stockandsalesmanagement.app.domain.*;
import kirgiz.stockandsalesmanagement.app.service.dto.LotDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Lot and its DTO LotDTO.
 */
@Mapper(componentModel = "spring", uses = {CurrencyMapper.class})
public interface LotMapper extends EntityMapper<LotDTO, Lot> {

    @Mapping(source = "buycurrency1.id", target = "buycurrency1Id")
    @Mapping(source = "buycurrency1.iSOCode", target = "buycurrency1ISOCode")
    @Mapping(source = "sellcurrency1.id", target = "sellcurrency1Id")
    @Mapping(source = "sellcurrency1.iSOCode", target = "sellcurrency1ISOCode")
    LotDTO toDto(Lot lot);

    @Mapping(target = "material4S", ignore = true)
    @Mapping(source = "buycurrency1Id", target = "buycurrency1")
    @Mapping(source = "sellcurrency1Id", target = "sellcurrency1")
    Lot toEntity(LotDTO lotDTO);

    default Lot fromId(Long id) {
        if (id == null) {
            return null;
        }
        Lot lot = new Lot();
        lot.setId(id);
        return lot;
    }
}
