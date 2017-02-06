package domainapp.dom.viewmodel.studentfinalmark;

import com.querydsl.core.types.Projections;
import com.querydsl.jdo.JDOQuery;
import domainapp.dom.academicyear.DAcademicYearD;
import domainapp.dom.exam.DExamD;
import domainapp.dom.module.DModuleD;
import domainapp.dom.professor.DProfessorD;
import domainapp.dom.student.DStudentD;
import domainapp.dom.student.Student;
import domainapp.dom.subject.DSubjectD;
import org.apache.isis.applib.annotation.DomainService;
import org.apache.isis.applib.annotation.NatureOfService;
import org.apache.isis.applib.services.jdosupport.IsisJdoSupport;

import java.util.Collection;
import java.util.List;

/**
 * Created by C.R.C on 2/5/2017.
 * Student final mark repository
 */
@DomainService(
        nature = NatureOfService.DOMAIN,
        repositoryFor = StudentResult.class)
public class StudentResultCollectionRepository {

    @javax.inject.Inject
    IsisJdoSupport isisJdoSupport;

    public Collection<StudentResult> getStudentFinalMarks(final Student student) {

        DProfessorD qdProfessor = DProfessorD.professor;
        DAcademicYearD qdAcademicYear = DAcademicYearD.academicYear;
        DExamD qdExam = DExamD.exam;
        DSubjectD qdSubject = DSubjectD.subject;
        DStudentD qdStudent = DStudentD.student;
        DModuleD qdModule = DModuleD.module;

        JDOQuery<StudentResult> query = new JDOQuery<>(isisJdoSupport.getJdoPersistenceManager());
        List<StudentResult> studentResults = query
                .from(qdProfessor, qdAcademicYear, qdExam, qdSubject, qdStudent, qdModule)
                .select(Projections.constructor(StudentResult.class,
                        qdStudent.fullName, qdSubject.name, qdExam.mark, qdProfessor.fullName,
                        qdAcademicYear.startYear, qdModule.coefficient, qdModule.name,
                        qdModule.coefficient.multiply(qdExam.mark)))
                .where(qdStudent.eq(qdExam.student())
                        .and(qdProfessor.eq(qdExam.professor()))
                        .and(qdSubject.eq(qdExam.subject()))
                        .and(qdAcademicYear.eq(qdExam.academicYear()))
                        .and(qdModule.eq(qdSubject.module()))
                        .and(qdStudent.eq(student)))
                .groupBy(qdStudent.fullName, qdSubject.name, qdExam.mark, qdProfessor.fullName, qdAcademicYear.startYear,
                        qdModule.coefficient, qdModule.name, qdModule.coefficient.multiply(qdExam.mark))
                .fetch();
        query.close();
        return studentResults;
    }
}
