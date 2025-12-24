package gcp.demo.repository;

import gcp.demo.entity.PersonEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

public interface PeopleRepository extends JpaRepository<PersonEntity,Long> {
    Optional<PersonEntity> findByPinfl (String pinfl);
}
