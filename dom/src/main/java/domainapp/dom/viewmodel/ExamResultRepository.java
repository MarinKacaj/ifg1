package domainapp.dom.viewmodel;

import com.querydsl.core.Tuple;
import com.querydsl.jdo.JDOQuery;
import domainapp.dom.academicyear.DAcademicYearD;
import domainapp.dom.exam.DExamD;
import domainapp.dom.module.DModuleD;
import domainapp.dom.professor.DProfessorD;
import domainapp.dom.student.DStudentD;
import domainapp.dom.student.EmploymentStatus;
import domainapp.dom.subject.DSubjectD;
import org.apache.isis.applib.annotation.DomainService;
import org.apache.isis.applib.annotation.NatureOfService;
import org.apache.isis.applib.services.jdosupport.IsisJdoSupport;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by C.R.C on 1/25/2017.
 * Exam result repository
 */
@DomainService(
        nature = NatureOfService.DOMAIN,
        repositoryFor = ExamResult.class)
public class ExamResultRepository {

    @javax.inject.Inject
    IsisJdoSupport isisJdoSupport;

    public List<ExamResult> getExamResultsByAcademicYearAndEmploymentStatus(Integer academicYearStartYear,
                                                                            EmploymentStatus employmentStatus) {

        DProfessorD qdProfessor = DProfessorD.professor;
        DAcademicYearD qdAcademicYear = DAcademicYearD.academicYear;
        DExamD qdExam = DExamD.exam;
        DSubjectD qdSubject = DSubjectD.subject;
        DStudentD qdStudent = DStudentD.student;
        DModuleD qdModule = DModuleD.module;

        JDOQuery<ExamResult> query = new JDOQuery<>(isisJdoSupport.getJdoPersistenceManager());
        List<Tuple> examResultTuples = query
                .from(qdProfessor, qdAcademicYear, qdExam, qdSubject, qdStudent, qdModule)
                .select(qdSubject.name, qdModule.name, qdExam.mark, qdProfessor.fullName, qdStudent.fullName)
                .where(qdProfessor.eq(qdExam.professor())
                        .and(qdAcademicYear.eq(qdExam.academicYear()))
                        .and(qdSubject.eq(qdExam.subject()))
                        .and(qdStudent.eq(qdExam.student()))
                        .and(qdModule.eq(qdSubject.module()))
                        .and(qdAcademicYear.eq(qdStudent.year()))
                        .and(qdAcademicYear.startYear.eq(academicYearStartYear))
                        .and(qdStudent.employmentStatus.eq(employmentStatus))
                )
                .fetch();

        List<ExamResult> examResults = new LinkedList<>();
        for (Tuple tuple : examResultTuples) {
            String subjectName = tuple.get(DSubjectD.subject.name);
            String moduleName = tuple.get(DModuleD.module.name);
            Integer mark = tuple.get(DExamD.exam.mark);
            String professorFullName = tuple.get(DProfessorD.professor.fullName);
            String studentFullName = tuple.get(DStudentD.student.fullName);
            ExamResult examResult = new ExamResult(subjectName, moduleName, mark, professorFullName, studentFullName);
            examResults.add(examResult);
        }
        return examResults;
    }
}
