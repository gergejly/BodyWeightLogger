package bwa.repository;

import bwa.model.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface SubjectRepository extends JpaRepository<Subject, Long> {

    @Query(value = "SELECT * FROM subject  WHERE date_from >=?1 And date_to<=?1", nativeQuery = true)
    List<Subject> findByDate(LocalDate date);
}
