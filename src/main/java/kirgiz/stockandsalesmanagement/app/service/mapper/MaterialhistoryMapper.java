package kirgiz.stockandsalesmanagement.app.service.mapper;

import kirgiz.stockandsalesmanagement.app.domain.*;
import kirgiz.stockandsalesmanagement.app.service.dto.MaterialhistoryDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Materialhistory and its DTO MaterialhistoryDTO.
 */
@Mapper(componentModel = "spring", uses = {MaterialMapper.class, TransferclassificationMapper.class, ThirdMapper.class})
public interface MaterialhistoryMapper extends EntityMapper<MaterialhistoryDTO, Materialhistory> {

    @Mapping(source = "transferclassification2.id", target = "transferclassification2Id")
    @Mapping(source = "transferclassification2.name", target = "transferclassification2Name")
    @Mapping(source = "warehousefrom.id", target = "warehousefromId")
    @Mapping(source = "warehousefrom.name", target = "warehousefromName")
    @Mapping(source = "warehouseto.id", target = "warehousetoId")
    @Mapping(source = "warehouseto.name", target = "warehousetoName")
    MaterialhistoryDTO toDto(Materialhistory materialhistory);

    @Mapping(source = "transferclassification2Id", target = "transferclassification2")
    @Mapping(source = "warehousefromId", target = "warehousefrom")
    @Mapping(source = "warehousetoId", target = "warehouseto")
    Materialhistory toEntity(MaterialhistoryDTO materialhistoryDTO);

    default Materialhistory fromId(Long id) {
        if (id == null) {
            return null;
        }
        Materialhistory materialhistory = new Materialhistory();
        materialhistory.setId(id);
        return materialhistory;
    }
}
