package bwa.service;

import bwa.model.Subject;
import bwa.repository.SubjectRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class SubjectSeviceTest {

    @Mock
    private SubjectRepository subjectRepository;

    @InjectMocks
    private SubjectService subjectService;

    private int subjectId = 10;
    private LocalDate dateFrom = LocalDate.of(2022, 11, 11);
    private LocalDate dateTo = LocalDate.of(2022, 12, 12);
    private double bodyWeight = 71;


    @Test
    public void createSubject_shouldReturnSubject() {

        Subject subject = createSubject();

        when(subjectRepository.save(ArgumentMatchers.any(Subject.class))).thenReturn(subject);

        Subject created = subjectService.save(subject);

        assertThat(created.getSubjectId()).isSameAs(subject.getSubjectId());
        assertThat(created.getDateFrom()).isSameAs(subject.getDateFrom());
        assertThat(created.getWeight()).isEqualTo(subject.getWeight());
        assertThat(created.getDateTo()).isSameAs(subject.getDateTo());
        verify(subjectRepository).save(subject);
    }

    @Test
    public void shouldReturn_AllSubjects() {
        List<Subject> subjects = new ArrayList<>();
        subjects.add(createSubject());
        subjects.add(new Subject(11, LocalDate.of(2021, 12, 12), LocalDate.of(2011, 12, 21), 71));

        given(subjectRepository.findAll()).willReturn(subjects);

        List<Subject> expected = subjectService.findAll();

        assertEquals(expected, subjects);
        verify(subjectRepository).findAll();
    }

    @Test
    public void whenGivenId_shouldDeleteSubject_ifExists() {
        Subject subject = createSubject();
        subject.setId(1);
        subjectService.save(subject);

        subjectService.deleteSubject(subject.getId());
        assertThat(subjectService.findAll().size()).isZero();
        verify(subjectRepository).deleteById(subject.getId());
    }

    @Test
    public void whenGivenId_shouldUpdateSubject() {
        Subject subject = createSubject();
        subject.setId(19);

        subjectService.updateSubject(subject);
        assertThat(subject.getId()).isEqualTo(19);

        verify(subjectRepository).save(subject);
    }

    @Test
    public void whenGivenDate_shouldFindByDate() {
        LocalDate date = LocalDate.of(2021, 12, 13);
        Subject subject = createSubject();
        subjectService.save(subject);
        List<Subject> lookingFor = subjectService.findByDate(date);
        assertThat(lookingFor.size() > 0);

        verify(subjectRepository).findByDate(date);
    }

    @Test
    public void whenGivenInvalidDate_findNull() {
        LocalDate date = LocalDate.of(2020, 12, 13);
        Subject subject = createSubject();

        subjectService.save(subject);
        List<Subject> lookingFor = subjectService.findByDate(date);
        assertThat(lookingFor.size()).isZero();

        verify(subjectRepository).findByDate(date);
    }

    public Subject createSubject() {
        return new Subject(subjectId, dateFrom, dateTo, (int) bodyWeight);
    }
}
