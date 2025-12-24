package gcp.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Table(name = "people_info")
@Entity
@Getter
@Setter
public class PersonEntity{
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    @Column(name = "full_name")
    String fio;

    @Column(name = "address")
    String address;

    @Column(name = "phone_number")
    String phoneNumber;

    @Column(name = "email")
    String email;

    @Column(name = "pinfl")
    String pinfl;

    @Column(name = "age")
    int age;

    @Column(name = "gender")
    String gender;

    @Column(name = "document_type")
    String documentType;

    @Column(name = "photo_url")
    String photoUrl;

    @Column(name = "document_given_date")
    LocalDate documentGivenDate;

    @Column(name = "residency")
    String residency;
}
