package com.springbootmicroservices.advertisement.mappers;


import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.springbootmicroservices.advertisement.dto.TeaFarmerDTO;
import com.springbootmicroservices.advertisement.entity.TeaFarmer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueMappingStrategy;
import org.mapstruct.factory.Mappers;

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@Mapper(componentModel = "spring", nullValueMappingStrategy = NullValueMappingStrategy.RETURN_NULL)
public interface TeaFarmerMapper {
    TeaFarmerMapper INSTANCE = Mappers.getMapper(TeaFarmerMapper.class);

    @Mapping(target = "factory.factoryId", source = "factoryId")
    @Mapping(target = "ward.wardID", source = "wardId")
    @Mapping(target = "subCounty.subCountyID", source = "subCountyId")
    @Mapping(target = "county.countyID", source = "countyId")
    @Mapping(target = "region.regionID", source = "regionId")
    @Mapping(target = "teaVariety.varietyID", source = "teaVarietyId")
    @Mapping(target = "teaCultivar.cultivarID", source = "teaCultivarId")
    @Mapping(target = "farmingType.farmingTypeID", source = "farmingTypeId")
    @Mapping(target = "paymentMethod.paymentMethodID", source = "paymentMethodId")
    TeaFarmer teaFarmerDTOToEntity(TeaFarmerDTO teaFarmerDTO);

    @Mapping(target = "factoryId", source = "factory.factoryId")
    @Mapping(target = "wardId", source = "ward.wardID")
    @Mapping(target = "subCountyId", source = "subCounty.subCountyID")
    @Mapping(target = "countyId", source = "county.countyID")
    @Mapping(target = "regionId", source = "region.regionID")
    @Mapping(target = "teaVarietyId", source = "teaVariety.varietyID")
    @Mapping(target = "teaCultivarId", source = "teaCultivar.cultivarID")
    @Mapping(target = "farmingTypeId", source = "farmingType.farmingTypeID")
    @Mapping(target = "paymentMethodId", source = "paymentMethod.paymentMethodID")
    @Mapping(target = "cultivarName", source = "teaCultivar.cultivarName")
    TeaFarmerDTO teaFarmerToDTO(TeaFarmer teaFarmer);
}
