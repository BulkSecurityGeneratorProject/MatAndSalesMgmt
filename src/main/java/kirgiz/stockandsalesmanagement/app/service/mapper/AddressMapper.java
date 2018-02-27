package kirgiz.stockandsalesmanagement.app.service.mapper;

import kirgiz.stockandsalesmanagement.app.domain.*;
import kirgiz.stockandsalesmanagement.app.service.dto.AddressDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Address and its DTO AddressDTO.
 */
@Mapper(componentModel = "spring", uses = {AddressclassificationMapper.class, CountryMapper.class})
public interface AddressMapper extends EntityMapper<AddressDTO, Address> {

    @Mapping(source = "addressclassification8.id", target = "addressclassification8Id")
    @Mapping(source = "addressclassification8.name", target = "addressclassification8Name")
    @Mapping(source = "country1.id", target = "country1Id")
    @Mapping(source = "country1.name", target = "country1Name")
    AddressDTO toDto(Address address);

    @Mapping(source = "addressclassification8Id", target = "addressclassification8")
    @Mapping(source = "country1Id", target = "country1")
    @Mapping(target = "thirdaddresses", ignore = true)
    Address toEntity(AddressDTO addressDTO);

    default Address fromId(Long id) {
        if (id == null) {
            return null;
        }
        Address address = new Address();
        address.setId(id);
        return address;
    }
}
