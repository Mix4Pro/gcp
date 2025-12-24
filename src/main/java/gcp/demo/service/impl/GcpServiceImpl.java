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

    public PersonResponseDto addPersonToDB (PersonRequestDto personRequestDto) {
        peopleRepository.findByPinfl(personRequestDto.pinfl())
            .ifPresent(p -> {
                throw new PersonExistsInTheDBException("Person with this pinfl already exists");
            });

        PersonEntity personEntity = personMapper.toEntityFromRequest(personRequestDto);

        PersonEntity savedPersonEntity = peopleRepository.save(personEntity);

        return personMapper.toResponseFromEntity(savedPersonEntity);
    }
}