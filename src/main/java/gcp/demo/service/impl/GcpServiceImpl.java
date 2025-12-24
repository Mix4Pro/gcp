package gcp.demo.service.impl;

import gcp.demo.dto.request.PersonRequestDto;
import gcp.demo.dto.response.PersonResponseDto;
import gcp.demo.entity.PersonEntity;
import gcp.demo.exception.PersonExistsInTheDBException;
import gcp.demo.exception.PersonIncorrectPinflException;
import gcp.demo.exception.PersonNotFoundException;
import gcp.demo.mapper.PersonMapper;
import gcp.demo.repository.PeopleRepository;
import gcp.demo.service.GcpService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

@Service
@RequiredArgsConstructor
public class GcpServiceImpl implements GcpService {
    private static final AtomicInteger nextId = new AtomicInteger(3);

    private final PeopleRepository peopleRepository;
    private final List<PersonEntity> listOfPeople;
    private final PersonMapper personMapper;

    public List<PersonResponseDto> getAllUsersInfo () {
        List<PersonEntity> listOfPeople = peopleRepository.findAll();

        return personMapper.toListResponseDtoFromPersonEntity(listOfPeople);
    }


    public PersonResponseDto getPersonById(long id) {
        return personMapper.toResponseFromEntity(
            peopleRepository.findById(id).orElseThrow(()-> new PersonNotFoundException("Person not found"))
        );
    }

    public PersonResponseDto getPersonByPinfl (String pinfl) {
        if(!pinfl.matches("\\d{14}")) {
            throw new PersonIncorrectPinflException("ПИНФЛ должен состоять из 14 цифр");
        }

        return personMapper.toResponseFromEntity(
            peopleRepository.findByPinfl(pinfl).orElseThrow(()-> new PersonNotFoundException("Person not found"))
        );
    }

//    public PersonResponseDto addPersonToDB (PersonRequestDto personRequestDto) {
//        for(PersonEntity person_i : listOfPeople) {
//            if(person_i.pinfl().equals(personRequestDto.pinfl())) {
//                throw new PersonExistsInTheDBException("Человек с такими данными уже зарегестрирован");
//            }
//        }
//
//        PersonEntity entityWithoutId = personMapper.toEntity(personRequestDto);
//        long uniqueId = nextId.getAndIncrement();
//        PersonEntity personEntity = new PersonEntity(
//            uniqueId,
//            entityWithoutId.fio(),
//            entityWithoutId.address(),
//            entityWithoutId.phoneNumber(),
//            entityWithoutId.email(),
//            entityWithoutId.pinfl(),
//            entityWithoutId.age(),
//            entityWithoutId.gender(),
//            entityWithoutId.documentType(),
//            entityWithoutId.photoUrl(),
//            entityWithoutId.documentGivenDate(),
//            entityWithoutId.residency()
//        );
//
//
//        peopleRepository.addPersonInfo(personEntity);
//
//        return personMapper.toResponseFromRequest(personRequestDto);
//    }
}