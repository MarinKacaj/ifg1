package domainapp.dom.viewmodel.studentinscription;

import com.querydsl.core.annotations.QueryProjection;
import domainapp.dom.student.EmploymentStatus;
import org.apache.isis.applib.annotation.DomainObject;
import org.apache.isis.applib.annotation.Editing;
import org.apache.isis.applib.annotation.Property;
import org.apache.isis.applib.annotation.ViewModel;

/**
 * Created by C.R.C on 2/4/2017.
 * Student inscription
 */
@ViewModel
@DomainObject(editing = Editing.DISABLED)
public class StudentInscription {

    @Property
    private String studentFullName;

    @Property
    private Integer studentBirthYear;

    @Property
    private String studentCity;

    @Property
    private String studentCountry;

    @Property
    private EmploymentStatus studentEmploymentStatus;

    @Property
    private String email;

    @Property
    private String initialFormationName;

    @Property
    private Integer promotionYear;

    @Property
    private Integer academicYearStartYear;

    public StudentInscription() {
    }

    @QueryProjection
    public StudentInscription(String studentFullName, Integer studentBirthYear, String studentCity, String studentCountry, EmploymentStatus studentEmploymentStatus, String email, String initialFormationName, Integer promotionYear, Integer academicYearStartYear) {
        this.studentFullName = studentFullName;
        this.studentBirthYear = studentBirthYear;
        this.studentCity = studentCity;
        this.studentCountry = studentCountry;
        this.studentEmploymentStatus = studentEmploymentStatus;
        this.email = email;
        this.initialFormationName = initialFormationName;
        this.promotionYear = promotionYear;
        this.academicYearStartYear = academicYearStartYear;
    }

    public String getStudentFullName() {
        return studentFullName;
    }

    public Integer getStudentBirthYear() {
        return studentBirthYear;
    }

    public String getStudentCity() {
        return studentCity;
    }

    public String getStudentCountry() {
        return studentCountry;
    }

    public EmploymentStatus getStudentEmploymentStatus() {
        return studentEmploymentStatus;
    }

    public String getEmail() {
        return email;
    }

    public String getInitialFormationName() {
        return initialFormationName;
    }

    public Integer getPromotionYear() {
        return promotionYear;
    }

    public Integer getAcademicYearStartYear() {
        return academicYearStartYear;
    }

    @Override
    public String toString() {
        return studentFullName + " " + academicYearStartYear;
    }
}
