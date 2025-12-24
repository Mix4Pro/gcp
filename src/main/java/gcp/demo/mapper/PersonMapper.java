package gcp.demo.mapper;

import gcp.demo.dto.request.PersonRequestDto;
import gcp.demo.dto.response.PersonResponseDto;
import gcp.demo.entity.PersonEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;


@Mapper (componentModel = "spring")
public interface PersonMapper {

    @Mapping(target = "id", ignore = true)
    PersonEntity toEntityFromRequest(PersonRequestDto personRequestDto);

    PersonResponseDto toResponseFromEntity (PersonEntity personEntity);

    PersonResponseDto toResponseFromRequest (PersonRequestDto personRequestDto);

    List<PersonResponseDto>  toListResponseDtoFromPersonEntity (List<PersonEntity> listOfPeopleEntities);
}
