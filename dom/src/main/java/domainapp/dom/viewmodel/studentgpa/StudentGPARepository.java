package domainapp.dom.viewmodel.studentgpa;

import domainapp.dom.student.Student;
import domainapp.dom.viewmodel.studentfinalmark.StudentResult;
import domainapp.dom.viewmodel.studentfinalmark.StudentResultCollectionRepository;
import org.apache.isis.applib.annotation.DomainService;
import org.apache.isis.applib.annotation.NatureOfService;

import java.util.Collection;

/**
 * Created by C.R.C on 2/5/2017.
 * Student GPA repository
 */
@DomainService(
        nature = NatureOfService.DOMAIN,
        repositoryFor = StudentGPA.class)
public class StudentGPARepository {

    @javax.inject.Inject
    StudentResultCollectionRepository studentResultCollectionRepository;

    public StudentGPA getStudentGPA(final Student student) {
        Collection<StudentResult> studentResults = studentResultCollectionRepository.getStudentFinalMarks(student);
        Float sumMarks = 0.0f;
        Integer numResults = studentResults.size();
        String studentFullName = null;
        for (StudentResult studentResult : studentResults) {
            sumMarks += studentResult.getExamMark() * studentResult.getModuleCoefficient();
            if (studentFullName == null) {
                studentFullName = studentResult.getStudentFullName();
            }
        }
        return new StudentGPA(studentFullName, (float) sumMarks / numResults);
    }
}
