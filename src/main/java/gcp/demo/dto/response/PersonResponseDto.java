package gcp.demo.dto.response;


import gcp.demo.dto.PersonDocumentType;
import gcp.demo.dto.PersonGender;
import gcp.demo.dto.PersonResidency;
import lombok.Builder;

import java.time.LocalDate;

@Builder
public record PersonResponseDto (
    String fio,
    String address,
    String phoneNumber,
    String email,
    String pinfl,
    int age,
    PersonGender gender,
    PersonDocumentType documentType,
    String photoUrl,
    LocalDate documentGivenDate,
    PersonResidency residency
) {
}
