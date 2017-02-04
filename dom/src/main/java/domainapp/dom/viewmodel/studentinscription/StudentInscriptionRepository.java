package domainapp.dom.viewmodel.studentinscription;

import com.querydsl.core.Tuple;
import com.querydsl.jdo.JDOQuery;
import domainapp.dom.academicyear.DAcademicYearD;
import domainapp.dom.initialformation.DInitialFormationD;
import domainapp.dom.promotion.DPromotionD;
import domainapp.dom.student.DStudentD;
import domainapp.dom.student.EmploymentStatus;
import org.apache.isis.applib.annotation.DomainService;
import org.apache.isis.applib.annotation.NatureOfService;
import org.apache.isis.applib.services.jdosupport.IsisJdoSupport;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by C.R.C on 2/4/2017.
 * Student inscription repository
 */
@DomainService(
        nature = NatureOfService.DOMAIN,
        repositoryFor = StudentInscription.class)
public class StudentInscriptionRepository {

    @javax.inject.Inject
    IsisJdoSupport isisJdoSupport;

    public Collection<StudentInscription> getStudentInscriptions() {

        DStudentD qdStudent = DStudentD.student;
        DInitialFormationD qdInitialFormation = DInitialFormationD.initialFormation;
        DPromotionD qdPromotion = DPromotionD.promotion;
        DAcademicYearD qdAcademicYear = DAcademicYearD.academicYear;

        JDOQuery<StudentInscription> query = new JDOQuery<>(isisJdoSupport.getJdoPersistenceManager());
        List<Tuple> studentInscriptionProjectedRecords = query
                .from(qdStudent, qdInitialFormation, qdPromotion, qdAcademicYear)
                .select(qdStudent.fullName, qdStudent.birthYear, qdStudent.city, qdStudent.country,
                        qdStudent.employmentStatus, qdStudent.email, qdInitialFormation.name, qdPromotion.year,
                        qdAcademicYear.startYear)
                .where(qdInitialFormation.eq(qdStudent.initialFormation())
                        .and(qdPromotion.eq(qdStudent.promotion()))
                        .and(qdAcademicYear.eq(qdStudent.year())))
                .orderBy(qdStudent.fullName.asc())
                .fetch();
        query.close();

        List<StudentInscription> studentInscriptions = new LinkedList<>();
        for (Tuple studentInscriptionProjectedRecord : studentInscriptionProjectedRecords) {
            String studentFullName = studentInscriptionProjectedRecord.get(qdStudent.fullName);
            Integer studentBirthYear = studentInscriptionProjectedRecord.get(qdStudent.birthYear);
            String studentCity = studentInscriptionProjectedRecord.get(qdStudent.city);
            String studentCountry = studentInscriptionProjectedRecord.get(qdStudent.country);
            EmploymentStatus studentEmploymentStatus = studentInscriptionProjectedRecord.get(qdStudent.employmentStatus);
            String studentEmail = studentInscriptionProjectedRecord.get(qdStudent.email);
            String initialFormationName = studentInscriptionProjectedRecord.get(qdInitialFormation.name);
            Integer promotionYear = studentInscriptionProjectedRecord.get(qdPromotion.year);
            Integer academicYearStartYear = studentInscriptionProjectedRecord.get(qdAcademicYear.startYear);
            StudentInscription studentInscription = new StudentInscription(studentFullName, studentBirthYear,
                    studentCity, studentCountry, studentEmploymentStatus, studentEmail, initialFormationName,
                    promotionYear, academicYearStartYear);
            studentInscriptions.add(studentInscription);
        }

        return studentInscriptions;
    }
}
