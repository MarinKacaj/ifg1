package domainapp.dom.viewmodel.studentgpa;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.NumberExpression;
import com.querydsl.jdo.JDOQuery;
import domainapp.dom.academicyear.DAcademicYearD;
import domainapp.dom.exam.DExamD;
import domainapp.dom.module.DModuleD;
import domainapp.dom.professor.DProfessorD;
import domainapp.dom.student.DStudentD;
import domainapp.dom.student.Student;
import domainapp.dom.subject.DSubjectD;
import domainapp.dom.viewmodel.studentfinalmark.StudentResultCollection;
import org.apache.isis.applib.annotation.DomainService;
import org.apache.isis.applib.annotation.NatureOfService;
import org.apache.isis.applib.services.jdosupport.IsisJdoSupport;

import java.util.List;

/**
 * Created by C.R.C on 2/5/2017.
 * Student GPA repository
 */
@DomainService(
        nature = NatureOfService.DOMAIN,
        repositoryFor = StudentResultCollection.class)
public class StudentGPARepository {

    @javax.inject.Inject
    IsisJdoSupport isisJdoSupport;

    public StudentGPA getStudentGPA(final Student student) {

        DProfessorD qdProfessor = DProfessorD.professor;
        DAcademicYearD qdAcademicYear = DAcademicYearD.academicYear;
        DExamD qdExam = DExamD.exam;
        DSubjectD qdSubject = DSubjectD.subject;
        DStudentD qdStudent = DStudentD.student;
        DModuleD qdModule = DModuleD.module;

        JDOQuery<StudentGPA> query = new JDOQuery<>(isisJdoSupport.getJdoPersistenceManager());
        NumberExpression<Float> gpaFormula = qdModule.coefficient.multiply(qdExam.mark).sum()
                .divide(qdStudent.fullName.count());
        List<StudentGPA> studentGPAs = query
                .from(qdProfessor, qdAcademicYear, qdExam, qdSubject, qdStudent, qdModule)
                .select(Projections.constructor(StudentGPA.class,
                        qdStudent.fullName, gpaFormula))
                .where(qdStudent.eq(qdExam.student())
                        .and(qdProfessor.eq(qdExam.professor()))
                        .and(qdSubject.eq(qdExam.subject()))
                        .and(qdAcademicYear.eq(qdExam.academicYear()))
                        .and(qdModule.eq(qdSubject.module()))
                        .and(qdStudent.eq(student)))
                .groupBy(qdStudent.fullName, qdSubject.name, qdExam.mark, qdProfessor.fullName, qdAcademicYear.startYear,
                        qdModule.coefficient, qdModule.name)
                .fetch();
        query.close();
        return studentGPAs.get(0);
    }
}
