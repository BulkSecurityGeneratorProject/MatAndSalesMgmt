package kirgiz.stockandsalesmanagement.app.service.mapper;

import kirgiz.stockandsalesmanagement.app.domain.*;
import kirgiz.stockandsalesmanagement.app.service.dto.MaterialDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Material and its DTO MaterialDTO.
 */
@Mapper(componentModel = "spring", uses = {MaterialclassificationMapper.class, CurrencyMapper.class, LotMapper.class})
public interface MaterialMapper extends EntityMapper<MaterialDTO, Material> {

    @Mapping(source = "materialclassification6.id", target = "materialclassification6Id")
    @Mapping(source = "materialclassification6.name", target = "materialclassification6Name")
    @Mapping(source = "buycurrency.id", target = "buycurrencyId")
    @Mapping(source = "buycurrency.iSOCode", target = "buycurrencyISOCode")
    @Mapping(source = "sellcurrency.id", target = "sellcurrencyId")
    @Mapping(source = "sellcurrency.iSOCode", target = "sellcurrencyISOCode")
    @Mapping(source = "lot5.id", target = "lot5Id")
    @Mapping(source = "lot5.code", target = "lot5Code")
    @Mapping(source = "materialclassification1.id", target = "materialclassification1Id")
    @Mapping(source = "materialclassification1.code", target = "materialclassification1Code")
    MaterialDTO toDto(Material material);

    @Mapping(source = "materialclassification6Id", target = "materialclassification6")
    @Mapping(source = "buycurrencyId", target = "buycurrency")
    @Mapping(source = "sellcurrencyId", target = "sellcurrency")
    @Mapping(source = "lot5Id", target = "lot5")
    @Mapping(source = "materialclassification1Id", target = "materialclassification1")
    Material toEntity(MaterialDTO materialDTO);

    default Material fromId(Long id) {
        if (id == null) {
            return null;
        }
        Material material = new Material();
        material.setId(id);
        return material;
    }
}
