package domainapp.dom.viewmodel.yearlystudentinscription;

import domainapp.dom.student.EmploymentStatus;
import org.apache.isis.applib.annotation.DomainObject;
import org.apache.isis.applib.annotation.Editing;
import org.apache.isis.applib.annotation.Property;
import org.apache.isis.applib.annotation.ViewModel;

/**
 * Created by C.R.C on 2/4/2017.
 * Yearly student inscription
 */
@ViewModel
@DomainObject(editing = Editing.DISABLED)
public class YearlyStudentInscription {

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
    private String studentEmployer;

    @Property
    private String initialFormationName;

    @Property
    private Integer promotionYear;

    public YearlyStudentInscription() {
    }

    public YearlyStudentInscription(String studentFullName, Integer studentBirthYear, String studentCity,
                                    String studentCountry, EmploymentStatus studentEmploymentStatus,
                                    String studentEmployer, String initialFormationName, Integer promotionYear) {
        this.studentFullName = studentFullName;
        this.studentBirthYear = studentBirthYear;
        this.studentCity = studentCity;
        this.studentCountry = studentCountry;
        this.studentEmploymentStatus = studentEmploymentStatus;
        this.studentEmployer = studentEmployer;
        this.initialFormationName = initialFormationName;
        this.promotionYear = promotionYear;
    }

    @Override
    public String toString() {
        return studentFullName;
    }
}
