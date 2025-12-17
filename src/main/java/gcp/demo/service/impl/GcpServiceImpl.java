package gcp.demo.service.impl;

import gcp.demo.dto.PersonDocumentType;
import gcp.demo.dto.PersonGender;
import gcp.demo.dto.PersonResidency;
import gcp.demo.dto.request.PersonRequestDto;
import gcp.demo.dto.response.PersonResponseDto;
import gcp.demo.entity.PersonEntity;
import gcp.demo.repository.PeopleRepository;
import gcp.demo.service.GcpService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GcpServiceImpl implements GcpService {

    private PeopleRepository peopleRepository;
    private List<PersonEntity> listOfPeople;

    public GcpServiceImpl(PeopleRepository peopleRepository) {
        this.peopleRepository = peopleRepository;
        this.listOfPeople = peopleRepository.getPeopleInfoList();
    }

    public List<PersonResponseDto> getAllUsersInfo () {
        List<PersonResponseDto> allUsers = new ArrayList<>();
        if(listOfPeople.isEmpty()) {
            return allUsers;
        }
        for(PersonEntity person_i : listOfPeople) {
            allUsers.add(
                PersonResponseDto.builder()
                    .fio(person_i.fio())
                    .address(person_i.address())
                    .phoneNumber(person_i.phoneNumber())
                    .email(person_i.email())
                    .pinfl(person_i.pinfl())
                    .age(person_i.age())
                    .gender(PersonGender.valueOf(person_i.gender()))
                    .documentType(PersonDocumentType.valueOf(person_i.documentType()))
                    .photoUrl(person_i.photoUrl())
                    .documentGivenDate(person_i.documentGivenDate())
                    .residency(PersonResidency.valueOf(person_i.residency()))
                    .build()
            );
        }
        return allUsers;
    }


    public PersonResponseDto getPersonById(long id) {
        for(int i = 0; i < listOfPeople.size(); i++) {
            if(id == listOfPeople.get(i).id()) {
                 return PersonResponseDto.builder()
                    .fio(listOfPeople.get(i).fio())
                    .address(listOfPeople.get(i).address())
                    .phoneNumber(listOfPeople.get(i).phoneNumber())
                    .email(listOfPeople.get(i).email())
                    .pinfl(listOfPeople.get(i).pinfl())
                    .age(listOfPeople.get(i).age())
                    .gender(PersonGender.valueOf(listOfPeople.get(i).gender()))
                    .documentType(PersonDocumentType.valueOf(listOfPeople.get(i).documentType()))
                    .photoUrl(listOfPeople.get(i).photoUrl())
                    .documentGivenDate(listOfPeople.get(i).documentGivenDate())
                    .residency(PersonResidency.valueOf(listOfPeople.get(i).residency()))
                    .build();
            }
        }

        throw new IllegalArgumentException("Could not find the person");


    }

    public PersonResponseDto getPersonByPinfl (String pinfl) {
        if(!pinfl.matches("\\d{14}")) {
            throw new IllegalArgumentException("ПИНФЛ должен состоять из 14 цифр");
        }

        for(int i = 0; i < listOfPeople.size(); i++) {
            if(pinfl.equals(listOfPeople.get(i).pinfl())) {
                return PersonResponseDto.builder()
                    .fio(listOfPeople.get(i).fio())
                    .address(listOfPeople.get(i).address())
                    .phoneNumber(listOfPeople.get(i).phoneNumber())
                    .email(listOfPeople.get(i).email())
                    .pinfl(listOfPeople.get(i).pinfl())
                    .age(listOfPeople.get(i).age())
                    .gender(PersonGender.valueOf(listOfPeople.get(i).gender()))
                    .documentType(PersonDocumentType.valueOf(listOfPeople.get(i).documentType()))
                    .photoUrl(listOfPeople.get(i).photoUrl())
                    .documentGivenDate(listOfPeople.get(i).documentGivenDate())
                    .residency(PersonResidency.valueOf(listOfPeople.get(i).residency()))
                    .build();
            }
        }

        throw new IllegalArgumentException("Такого челика нет в базе");
    }

    public PersonResponseDto addPersonToDB (PersonRequestDto personRequestDto) {
        
    }
}