package gcp.demo.mapper;

import gcp.demo.dto.request.PersonRequestDto;
import gcp.demo.dto.response.PersonResponseDto;
import gcp.demo.entity.PersonEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper (componentModel = "spring")
public interface PersonMapper {
    @Mapping(target = "id" , ignore = true)
    @Mapping(target = "gender" , expression = "java(String.valueOf(personRequestDto.gender()))")
    @Mapping(target = "documentType" , expression = "java(String.valueOf(personRequestDto.documentType()))")
    @Mapping(target = "residency" , expression = "java(String.valueOf(personRequestDto.residency()))")
    PersonEntity toEntity(PersonRequestDto personRequestDto);

    @Mapping(target = "gender", expression = "java(PersonGender.valueOf(personEntity.gender()))")
    @Mapping(target = "documentType" , expression = "java(PersonDocumentType.valueOf(personEntity.documentType()))")
    @Mapping(target = "residency", expression = "java(PersonResidency.valueOf(personEntity.residency()))")
    PersonResponseDto toResponseFromEntity (PersonEntity personEntity);

    PersonResponseDto toResponseFromRequest (PersonRequestDto personRequestDto);
}
