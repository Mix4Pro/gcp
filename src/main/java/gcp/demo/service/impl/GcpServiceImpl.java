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
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class GcpServiceImpl implements GcpService {
    private static final AtomicInteger nextId = new AtomicInteger(3);

    private PeopleRepository peopleRepository;
    private List<PersonEntity> listOfPeople;
    private PersonMapper personMapper;

    public GcpServiceImpl(PeopleRepository peopleRepository , PersonMapper personMapper) {
        this.peopleRepository = peopleRepository;
        this.listOfPeople = peopleRepository.getPeopleInfoList();
        this.personMapper = personMapper;
    }

    public List<PersonResponseDto> getAllUsersInfo () {
        List<PersonResponseDto> allUsers = new ArrayList<>();
        if(listOfPeople.isEmpty()) {
            return allUsers;
        }
        for(PersonEntity person_i : listOfPeople) {
            allUsers.add(
                personMapper.toResponseFromEntity(person_i)
            );
        }
        return allUsers;
    }


    public PersonResponseDto getPersonById(long id) {
        for(int i = 0; i < listOfPeople.size(); i++) {
            if(id == listOfPeople.get(i).id()) {
                 return personMapper.toResponseFromEntity(listOfPeople.get(i));
            }
        }

        throw new PersonNotFoundException("Could not find the person");

    }

    public PersonResponseDto getPersonByPinfl (String pinfl) {
        if(!pinfl.matches("\\d{14}")) {
            throw new PersonIncorrectPinflException("ПИНФЛ должен состоять из 14 цифр");
        }

        for(int i = 0; i < listOfPeople.size(); i++) {
            if(pinfl.equals(listOfPeople.get(i).pinfl())) {
                return personMapper.toResponseFromEntity(listOfPeople.get(i));
            }
        }
        throw new PersonNotFoundException("Could not find the person");
    }

    public PersonResponseDto addPersonToDB (PersonRequestDto personRequestDto) {
        for(PersonEntity person_i : listOfPeople) {
            if(person_i.pinfl().equals(personRequestDto.pinfl())) {
                throw new PersonExistsInTheDBException("Человек с такими данными уже зарегестрирован");
            }
        }

        PersonEntity entityWithoutId = personMapper.toEntity(personRequestDto);
        long uniqueId = nextId.getAndIncrement();
        PersonEntity personEntity = new PersonEntity(
            uniqueId,
            entityWithoutId.fio(),
            entityWithoutId.address(),
            entityWithoutId.phoneNumber(),
            entityWithoutId.email(),
            entityWithoutId.pinfl(),
            entityWithoutId.age(),
            entityWithoutId.gender(),
            entityWithoutId.documentType(),
            entityWithoutId.photoUrl(),
            entityWithoutId.documentGivenDate(),
            entityWithoutId.residency()
        );


        peopleRepository.addPersonInfo(personEntity);

        return personMapper.toResponseFromRequest(personRequestDto);
    }
}