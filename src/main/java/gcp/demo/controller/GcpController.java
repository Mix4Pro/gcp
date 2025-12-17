package gcp.demo.controller;

import gcp.demo.dto.request.PersonRequestDto;
import gcp.demo.dto.response.PersonResponseDto;
import gcp.demo.service.GcpService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class GcpController {

    private final GcpService gcpService;

    @GetMapping("/api/gcp/users")
    public ResponseEntity<List<PersonResponseDto>> getAllUsersInfo () {
        return ResponseEntity.ok(gcpService.getAllUsersInfo());
    }

    @GetMapping("/api/gcp/users/by-id/{id}")
    public ResponseEntity<PersonResponseDto> getPersonById (@PathVariable long id) {
        return ResponseEntity.ok(gcpService.getPersonById(id));
    }

    @GetMapping("/api/gcp/users/by-pinfl/{pinfl}")
    public ResponseEntity<PersonResponseDto> getPersonByPinfl (@PathVariable String pinfl) {
        return ResponseEntity.ok(gcpService.getPersonByPinfl(pinfl));
    }

    @PostMapping("api/gcp/users")
    public ResponseEntity<PersonResponseDto> addPersonToDB (@RequestBody PersonRequestDto personRequestDto) {
        return ResponseEntity.ok(gcpService.addPersonToDB(personRequestDto));
    }
}
