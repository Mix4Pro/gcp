package gcp.demo.entity;

import lombok.Builder;

import java.time.LocalDate;

@Builder
public record PersonEntity (
    long id,
    String fio,
    String address,
    String phoneNumber,
    String email,
    String pinfl,
    int age,
    String gender,
    String documentType,
    String photoUrl,
    LocalDate documentGivenDate,
    String residency
) {
}
