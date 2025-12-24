package gcp.demo.service;

import gcp.demo.dto.request.PersonRequestDto;
import gcp.demo.dto.response.PersonResponseDto;

import java.util.List;

public interface GcpService {
    List<PersonResponseDto> getAllUsersInfo ();
    PersonResponseDto getPersonById (long id);
    PersonResponseDto getPersonByPinfl (String pinfl);
//    PersonResponseDto addPersonToDB (PersonRequestDto personRequestDto);
}
