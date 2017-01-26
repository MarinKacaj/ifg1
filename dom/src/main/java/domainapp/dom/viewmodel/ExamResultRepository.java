package domainapp.dom.viewmodel;

import com.mysema.query.Tuple;
import com.mysema.query.jdo.sql.JDOSQLQuery;
import com.mysema.query.sql.Configuration;
import com.mysema.query.sql.HSQLDBTemplates;
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

        Configuration configuration = new Configuration(new HSQLDBTemplates());
        configuration.registerSchemaOverride("", "simple");
        configuration.registerTableOverride("professor", "Professor");

        JDOSQLQuery query = new JDOSQLQuery(isisJdoSupport.getJdoPersistenceManager(), configuration);
        DProfessorD qdProfessor = DProfessorD.professor;
        DAcademicYearD qdAcademicYear = DAcademicYearD.academicYear;
        DExamD qdExam = DExamD.exam;
        DSubjectD qdSubject = DSubjectD.subject;
        DStudentD qdStudent = DStudentD.student;
        DModuleD qdModule = DModuleD.module;

        List<Tuple> resultTuples = query.from(qdProfessor).innerJoin(qdExam).on(qdProfessor.eq(qdExam.professor()))
                .innerJoin(qdAcademicYear).on(qdAcademicYear.eq(qdExam.academicYear()))
                .innerJoin(qdSubject).on(qdSubject.eq(qdExam.subject()))
                .innerJoin(qdStudent).on(qdStudent.eq(qdExam.student()))
                .innerJoin(qdModule).on(qdModule.eq(qdSubject.module()))
                .innerJoin(qdAcademicYear).on(qdAcademicYear.eq(qdStudent.year()))
                .where(qdAcademicYear.startYear.eq(academicYearStartYear)
                        .and(qdStudent.employmentStatus.eq(employmentStatus)))
                .list(qdSubject.name, qdModule.name, qdExam.mark, qdProfessor.fullName, qdStudent.fullName);
        List<ExamResult> examResults = new LinkedList<>();
        for (Tuple tuple : resultTuples) {
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
