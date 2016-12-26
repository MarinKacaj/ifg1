package domainapp.dom.student;

import domainapp.dom.ColumnAllowsNull;
import domainapp.dom.academicyear.AcademicYear;
import domainapp.dom.initialformation.InitialFormation;
import domainapp.dom.promotion.Promotion;
import org.apache.isis.applib.annotation.*;

import javax.jdo.annotations.Column;
import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;

/**
 * Created by C.R.C on 12/25/2016.
 * Student
 */
@javax.jdo.annotations.PersistenceCapable(
        identityType = IdentityType.DATASTORE,
        schema = "simple")
@javax.jdo.annotations.DatastoreIdentity(
        strategy = IdGeneratorStrategy.IDENTITY,
        column = "id")
@DomainObject(
        publishing = Publishing.ENABLED,
        auditing = Auditing.ENABLED)
public class Student {

    //region fullName
    @Column(allowsNull = ColumnAllowsNull.FALSE)
    private String fullName;

    @Property(
            editing = Editing.ENABLED,
            publishing = Publishing.ENABLED)
    @PropertyLayout(named = "Full Name")
    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
    //endregion

    //region gender
    @Column(allowsNull = ColumnAllowsNull.FALSE)
    private Gender gender;

    @Property(
            editing = Editing.ENABLED,
            publishing = Publishing.ENABLED)
    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }
    //endregion

    //region birthYear
    @Column(allowsNull = ColumnAllowsNull.FALSE)
    private Integer birthYear;

    @Property(
            editing = Editing.ENABLED,
            publishing = Publishing.ENABLED)
    public Integer getBirthYear() {
        return birthYear;
    }

    public void setBirthYear(Integer birthYear) {
        this.birthYear = birthYear;
    }
    //endregion

    //region initialFormation
    public static final String INITIAL_FORMATION = "initialFormation";

    @Column(
            name = INITIAL_FORMATION,
            allowsNull = ColumnAllowsNull.FALSE,
            target = InitialFormation.ID)
    private InitialFormation initialFormation;

    @Property(
            editing = Editing.ENABLED,
            publishing = Publishing.ENABLED)
    @PropertyLayout(named = "Initial Formation")
    public InitialFormation getInitialFormation() {
        return initialFormation;
    }

    public void setInitialFormation(InitialFormation initialFormation) {
        this.initialFormation = initialFormation;
    }
    //endregion

    //region address
    @Column(allowsNull = ColumnAllowsNull.TRUE)
    private String address;

    @Property(
            editing = Editing.ENABLED,
            publishing = Publishing.ENABLED)
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    //endregion

    //region city
    @Column(allowsNull = ColumnAllowsNull.FALSE)
    private String city;

    @Property(
            editing = Editing.ENABLED,
            publishing = Publishing.ENABLED)
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
    //endregion

    //region country
    @Column(allowsNull = ColumnAllowsNull.FALSE)
    private String country;

    @Property(
            editing = Editing.ENABLED,
            publishing = Publishing.ENABLED)
    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
    //endregion

    //region tel
    @Column(allowsNull = ColumnAllowsNull.TRUE)
    private String tel;

    @Property(
            editing = Editing.ENABLED,
            publishing = Publishing.ENABLED)
    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }
    //endregion

    //region email
    @Column(allowsNull = ColumnAllowsNull.TRUE)
    private String email;

    @Property(
            editing = Editing.ENABLED,
            publishing = Publishing.ENABLED)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    //endregion

    //region employer
    @Column(allowsNull = ColumnAllowsNull.TRUE)
    private String employer;

    @Property(
            editing = Editing.ENABLED,
            publishing = Publishing.ENABLED)
    @PropertyLayout(named = "Employer Name")
    public String getEmployer() {
        return employer;
    }

    public void setEmployer(String employer) {
        this.employer = employer;
    }
    //endregion

    //region employmentStatus
    @Column(allowsNull = ColumnAllowsNull.FALSE)
    private EmploymentStatus employmentStatus;

    @Property(
            editing = Editing.ENABLED,
            publishing = Publishing.ENABLED)
    @PropertyLayout(named = "Employment Status")
    public EmploymentStatus getEmploymentStatus() {
        return employmentStatus;
    }

    public void setEmploymentStatus(EmploymentStatus employmentStatus) {
        this.employmentStatus = employmentStatus;
    }
    //endregion

    //region promotion
    public static final String PROMOTION = "promotion";

    @Column(
            name = PROMOTION,
            allowsNull = ColumnAllowsNull.FALSE,
            target = Promotion.ID)
    private Promotion promotion;

    @Property(
            editing = Editing.ENABLED,
            publishing = Publishing.ENABLED)
    @PropertyLayout(named = "Promotion Year/Status")
    public Promotion getPromotion() {
        return promotion;
    }

    public void setPromotion(Promotion promotion) {
        this.promotion = promotion;
    }
    //endregion

    //region year
    public static final String ACADEMIC_YEAR = "year";

    @Column(
            name = ACADEMIC_YEAR,
            allowsNull = ColumnAllowsNull.FALSE,
            target = AcademicYear.ID)
    private AcademicYear year;

    @Property(
            editing = Editing.ENABLED,
            publishing = Publishing.ENABLED)
    @PropertyLayout(named = "Year")
    public AcademicYear getYear() {
        return year;
    }

    public void setYear(AcademicYear year) {
        this.year = year;
    }
    //endregion
}
