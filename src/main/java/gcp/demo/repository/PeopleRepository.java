package gcp.demo.repository;
import gcp.demo.entity.PersonEntity;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Repository
@Getter
public class PeopleRepository {
    private final List<PersonEntity> peopleInfoList = new ArrayList<>();

    public PeopleRepository () {
        peopleInfoList.add(
            PersonEntity.builder().
                id(1L)
                .fio("James Lebron")
                .address("Los angeles , baker st , 23")
                .phoneNumber("+998 223 23 23")
                .email("lebron@gmail.com")
                .pinfl("23232323232323")
                .age(40)
                .gender("MALE")
                .documentType("PASSPORT")
                .photoUrl("photo-of-lebron.jpg")
                .documentGivenDate(LocalDate.of(2018,1,23))
                .residency("USA")
                .build()
        );
        peopleInfoList.add(
            PersonEntity.builder().
                id(2L)
                .fio("Giannis Antetokounmpo")
                .address("Milwaukee , baker st , 34")
                .phoneNumber("+998 334 34 34")
                .email("giannis@gmail.com")
                .pinfl("34343434343434")
                .age(31)
                .gender("MALE")
                .documentType("PASSPORT")
                .photoUrl("photo-of-giannis.jpg")
                .documentGivenDate(LocalDate.of(2021,1,31))
                .residency("GREEK")
                .build()
        );
    }

    public PersonEntity addPersonInfo (PersonEntity personEntity) {
        peopleInfoList.add(personEntity);
        return personEntity;
    }
}
