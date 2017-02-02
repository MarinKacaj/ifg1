package domainapp.dom.viewmodel.academicyearexamresult;

import com.querydsl.core.Tuple;
import com.querydsl.jdo.JDOQuery;
import domainapp.dom.academicyear.DAcademicYearD;
import domainapp.dom.exam.DExamD;
import domainapp.dom.module.DModuleD;
import domainapp.dom.professor.DProfessorD;
import domainapp.dom.student.DStudentD;
import domainapp.dom.subject.DSubjectD;
import org.apache.isis.applib.annotation.DomainService;
import org.apache.isis.applib.annotation.NatureOfService;
import org.apache.isis.applib.services.jdosupport.IsisJdoSupport;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by C.R.C on 1/30/2017.
 * Academic year exam result repository
 */
@DomainService(
        nature = NatureOfService.DOMAIN,
        repositoryFor = AcademicYearExamResult.class)
public class AcademicYearExamResultRepository {

    @javax.inject.Inject
    IsisJdoSupport isisJdoSupport;

    public List<AcademicYearExamResult> getExamResultsByAcademicYear(Integer academicYearStartYear) {

        DAcademicYearD qdAcademicYear = DAcademicYearD.academicYear;
        DExamD qdExam = DExamD.exam;
        DSubjectD qdSubject = DSubjectD.subject;
        DProfessorD qdProfessor = DProfessorD.professor;
        DStudentD qdStudent = DStudentD.student;
        DModuleD qdModule = DModuleD.module;

        JDOQuery<AcademicYearExamResult> query = new JDOQuery<>(isisJdoSupport.getJdoPersistenceManager());
        List<Tuple> academicYearExamResultPartials = query
                .from(qdAcademicYear, qdExam, qdSubject, qdProfessor, qdStudent, qdModule)
                .select(qdAcademicYear.startYear, qdExam.mark, qdSubject.name, qdProfessor.fullName, qdStudent.fullName, qdModule.name)
                .where(qdProfessor.eq(qdExam.professor())
                        .and(qdAcademicYear.eq(qdExam.academicYear()))
                        .and(qdSubject.eq(qdExam.subject()))
                        .and(qdStudent.eq(qdExam.student()))
                        .and(qdModule.eq(qdSubject.module()))
                        .and(qdAcademicYear.eq(qdStudent.year()))
                        .and(qdAcademicYear.startYear.eq(academicYearStartYear))
                )
                .fetch();
        query.close();

        List<AcademicYearExamResult> academicYearExamResults = new LinkedList<>();
        for (Tuple academicYearExamResultPartial : academicYearExamResultPartials) {
            Integer startYear = academicYearExamResultPartial.get(qdAcademicYear.startYear);
            Integer mark = academicYearExamResultPartial.get(qdExam.mark);
            String subjectName = academicYearExamResultPartial.get(qdSubject.name);
            String professorFullName = academicYearExamResultPartial.get(qdProfessor.fullName);
            String studentFullName = academicYearExamResultPartial.get(qdStudent.fullName);
            String moduleName = academicYearExamResultPartial.get(qdModule.name);
            AcademicYearExamResult academicYearExamResult = new AcademicYearExamResult(startYear, mark, subjectName, professorFullName, studentFullName, moduleName);
            academicYearExamResults.add(academicYearExamResult);
        }

        return academicYearExamResults;
    }
}
