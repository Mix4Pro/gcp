package gcp.demo.dto.request;

import gcp.demo.dto.PersonGender;
import gcp.demo.dto.PersonDocumentType;
import gcp.demo.dto.PersonResidency;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;


import java.time.LocalDate;

public record PersonRequestDto (
    @NotBlank (message = "Имя не может быть пустым")
    @Pattern(regexp = "^[A-Za-z]+$", message = "Имя должно содержать только латинские буквы")
    @Size(max = 100 , message = "Имя должно быть от 1 до 100 символов")
    String fio,

    @NotBlank (message = "Адресс не может быть пустым")
    @Size(min = 4 , max = 200 , message = "Адресс должен состоять из 1 до 200 символов")
    String address,

    @NotBlank (message = "Номер телефона не может быть пустым")
    @Pattern(regexp = "\\d{12}" , message = "Номер телефона должен состоять из 12 цифр")
    String phoneNumber,

    @NotBlank(message = "Email не может быть пустым")
    @Email(message = "Некорректный формат почты")
    @Size(max = 100 , message = "Email не может быть больше 100 символов")
    String email,

    @NotBlank(message = "ПИНФ не может быть пустым")
    @Pattern(regexp = "\\d{14}", message = "ПИНФ должен состоять из 14 цифр")
    String pinfl,

    @NotBlank(message = "Возраст не может быть пустым")
    @Min(value = 1, message = "Возраст не может быть меньше 1 года")
    @Max(value = 200 , message = "Возраст не может быть больше 200 лет")
    int age,

    @NotBlank(message = "Гендр не может быть пустым")
    PersonGender gender,

    @NotBlank (message = "Тип документа не может быть пустым")
    PersonDocumentType documentType,

    @NotBlank(message = "Ссылка на фото не может быть пустой")
    @Size(max = 400 , message = "Ссылка не может состоять из более чем 400 символов")
    String photoUrl,

    @NotBlank(message = "Дата выдачи не может быть пустой")
    @Past(message = "Дата выдачи не валидна")
    LocalDate documentGivenDate,

    @NotBlank(message = "Гражданство не может быть пустым")
    PersonResidency residency
) {

}
