package domainapp.dom.viewmodel;

import com.mysema.query.Tuple;
import com.mysema.query.jdo.JDOQuery;
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
        JDOQuery query = new JDOQuery(isisJdoSupport.getJdoPersistenceManager());
        List<Tuple> resultTuples = query.from(DSubjectD.subject, DModuleD.module, DExamD.exam, DProfessorD.professor,
                DStudentD.student, DAcademicYearD.academicYear)
                .where(DAcademicYearD.academicYear.startYear.eq(academicYearStartYear)
                        .and(DStudentD.student.employmentStatus.eq(employmentStatus))).list();
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
