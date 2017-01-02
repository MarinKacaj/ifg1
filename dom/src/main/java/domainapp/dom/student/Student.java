package domainapp.dom.student;

import com.google.common.collect.ComparisonChain;
import domainapp.dom.AutoCompleteConfig;
import domainapp.dom.ColumnAllowsNull;
import domainapp.dom.academicyear.AcademicYear;
import domainapp.dom.academicyear.AcademicYearRepository;
import domainapp.dom.exam.Exam;
import domainapp.dom.initialformation.InitialFormation;
import domainapp.dom.initialformation.InitialFormationRepository;
import domainapp.dom.letter.Letter;
import domainapp.dom.mode.Mode;
import domainapp.dom.promotion.Promotion;
import domainapp.dom.promotion.PromotionRepository;
import org.apache.isis.applib.annotation.*;

import javax.annotation.Nonnull;
import javax.jdo.annotations.*;

/**
 * Created by C.R.C on 12/25/2016.
 * Student
 */
@javax.jdo.annotations.PersistenceCapable(
        identityType = IdentityType.DATASTORE,
        schema = "simple")
@javax.jdo.annotations.DatastoreIdentity(
        strategy = IdGeneratorStrategy.IDENTITY,
        column = Student.ID)
@DomainObject(
        publishing = Publishing.ENABLED,
        auditing = Auditing.ENABLED,
        autoCompleteRepository = StudentRepository.class,
        autoCompleteAction = "findByNameSequence")
@Queries({
        @Query(
                name = Student.FIND_BY_FULL_NAME_QUERY,
                value = "SELECT FROM domainapp.dom.student.Student WHERE " +
                        Student.FULL_NAME + ".indexOf(:" + Student.FULL_NAME + ") >= 0")
})
public class Student implements Comparable<Student> {

    public static final String FIND_BY_FULL_NAME_QUERY = "findByFullName";

    //region > id
    public static final String ID = "id";
    //endregion

    //region fullName
    public static final String FULL_NAME = "fullName";
    public static final String FULL_NAME_LABEL = "Full Name";

    @Column(allowsNull = ColumnAllowsNull.FALSE, name = FULL_NAME)
    private String fullName;

    @Property(
            editing = Editing.ENABLED,
            publishing = Publishing.ENABLED)
    @PropertyLayout(named = FULL_NAME_LABEL)
    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
    //endregion

    //region gender
    public static final String GENDER_LABEL = "Gender";

    @Column(allowsNull = ColumnAllowsNull.FALSE)
    private Gender gender;

    @Property(
            editing = Editing.ENABLED,
            publishing = Publishing.ENABLED)
    @PropertyLayout(named = GENDER_LABEL)
    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }
    //endregion

    //region birthYear
    public static final String BIRTH_YEAR_LABEL = "Birth year";

    @Column(allowsNull = ColumnAllowsNull.FALSE, name = BIRTH_YEAR_LABEL)
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
    public static final String INITIAL_FORMATION_LABEL = "Initial Formation";

    @javax.inject.Inject
    InitialFormationRepository initialFormationRepository;

    @Column(
            name = INITIAL_FORMATION,
            allowsNull = ColumnAllowsNull.FALSE,
            target = InitialFormation.ID)
    private InitialFormation initialFormation;

    @Property(
            editing = Editing.ENABLED,
            publishing = Publishing.ENABLED)
    @PropertyLayout(named = INITIAL_FORMATION_LABEL)
    public InitialFormation getInitialFormation() {
        return initialFormation;
    }

    public void setInitialFormation(InitialFormation initialFormation) {
        this.initialFormation = initialFormation;
    }

    @Action
    public java.util.Collection<InitialFormation> autoCompleteInitialFormation(@MinLength(value = AutoCompleteConfig.MIN_LENGTH)
                                                                               final String professionNameSequence) {
        return initialFormationRepository.findByName(professionNameSequence);
    }
    //endregion

    //region address
    public static final String ADDRESS_LABEL = "Address";


    @Column(allowsNull = ColumnAllowsNull.TRUE)
    private String address;

    @Property(
            editing = Editing.ENABLED,
            publishing = Publishing.ENABLED)
    @PropertyLayout(named = ADDRESS_LABEL)
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    //endregion

    //region city
    public static final String CITY_LABEL = "City";

    @Column(allowsNull = ColumnAllowsNull.FALSE)
    private String city;

    @Property(
            editing = Editing.ENABLED,
            publishing = Publishing.ENABLED)
    @PropertyLayout(named = CITY_LABEL)
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
    //endregion

    //region country
    public static final String COUNTRY_LABEL = "Country";

    @Column(allowsNull = ColumnAllowsNull.FALSE)
    private String country;

    @Property(
            editing = Editing.ENABLED,
            publishing = Publishing.ENABLED)
    @PropertyLayout(named = COUNTRY_LABEL)
    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
    //endregion

    //region tel
    public static final String TEL_LABEL = "Telephone number";

    @Column(allowsNull = ColumnAllowsNull.TRUE)
    private String tel;

    @Property(
            editing = Editing.ENABLED,
            publishing = Publishing.ENABLED)
    @PropertyLayout(named = TEL_LABEL)
    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }
    //endregion

    //region email
    public static final String EMAIL_LABEL = "Email";

    @Column(allowsNull = ColumnAllowsNull.TRUE)
    private String email;

    @Property(
            editing = Editing.ENABLED,
            publishing = Publishing.ENABLED)
    @PropertyLayout(named = EMAIL_LABEL)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    //endregion

    //region employer
    public static final String EMPLOYER_LABEL = "Employer name";

    @Column(allowsNull = ColumnAllowsNull.TRUE)
    private String employer;

    @Property(
            editing = Editing.ENABLED,
            publishing = Publishing.ENABLED)
    @PropertyLayout(named = EMPLOYER_LABEL)
    public String getEmployer() {
        return employer;
    }

    public void setEmployer(String employer) {
        this.employer = employer;
    }
    //endregion

    //region employmentStatus
    public static final String EMPLOYMENT_STATUS_LABEL = "Employment status";

    @Column(allowsNull = ColumnAllowsNull.FALSE)
    private EmploymentStatus employmentStatus;

    @Property(
            editing = Editing.ENABLED,
            publishing = Publishing.ENABLED)
    @PropertyLayout(named = EMPLOYMENT_STATUS_LABEL)
    public EmploymentStatus getEmploymentStatus() {
        return employmentStatus;
    }

    public void setEmploymentStatus(EmploymentStatus employmentStatus) {
        this.employmentStatus = employmentStatus;
    }
    //endregion

    //region promotion
    public static final String PROMOTION = "promotion";
    public static final String PROMOTION_LABEL = "Promotion Year/Status";

    @javax.inject.Inject
    PromotionRepository promotionRepository;

    @Column(
            name = PROMOTION,
            allowsNull = ColumnAllowsNull.FALSE,
            target = Promotion.ID)
    private Promotion promotion;

    @Property(
            editing = Editing.ENABLED,
            publishing = Publishing.ENABLED)
    @PropertyLayout(named = PROMOTION_LABEL)
    public Promotion getPromotion() {
        return promotion;
    }

    public void setPromotion(Promotion promotion) {
        this.promotion = promotion;
    }

    @Action
    public java.util.Collection<Promotion> autoCompletePromotion(@MinLength(value = AutoCompleteConfig.MIN_LENGTH)
                                                                 final String yearDigitSequence) {
        return promotionRepository.findByYearDigitSequence(yearDigitSequence);
    }
    //endregion

    //region year
    public static final String ACADEMIC_YEAR = "year";
    public static final String ACADEMIC_YEAR_LABEL = "Year";

    @javax.inject.Inject
    AcademicYearRepository academicYearRepository;

    @Column(
            name = ACADEMIC_YEAR,
            allowsNull = ColumnAllowsNull.FALSE,
            target = AcademicYear.ID)
    private AcademicYear year;

    @Property(
            editing = Editing.ENABLED,
            publishing = Publishing.ENABLED)
    @PropertyLayout(named = ACADEMIC_YEAR_LABEL)
    public AcademicYear getYear() {
        return year;
    }

    public void setYear(AcademicYear year) {
        this.year = year;
    }

    @Action
    public java.util.Collection<AcademicYear> autoCompleteYear(@MinLength(value = AutoCompleteConfig.MIN_LENGTH)
                                                               final String startYearDigitSequence) {
        return academicYearRepository.findByStartYearDigitSequence(startYearDigitSequence);
    }
    //endregion

    //region mode
    public static final String MODE = "mode";
    public static final String MODE_LABEL = "Mode";

    @Column(
            name = MODE,
            allowsNull = ColumnAllowsNull.FALSE,
            target = Mode.ID)
    private Mode mode;

    @Property(
            editing = Editing.ENABLED,
            publishing = Publishing.ENABLED)
    @PropertyLayout(named = MODE_LABEL)
    public Mode getMode() {
        return mode;
    }

    public void setMode(Mode mode) {
        this.mode = mode;
    }
    //endregion

    //region letters
    @Persistent(mappedBy = Letter.STUDENT_ID)
    @Collection
    private java.util.Collection<Letter> letters;

    public java.util.Collection<Letter> getLetters() {
        return letters;
    }

    public void setLetters(java.util.Collection<Letter> letters) {
        this.letters = letters;
    }
    //endregion

    //region exams
    @Persistent(mappedBy = Exam.STUDENT)
    @Collection
    private java.util.Collection<Exam> exams;

    public java.util.Collection<Exam> getExams() {
        return exams;
    }

    public void setExams(java.util.Collection<Exam> exams) {
        this.exams = exams;
    }
    //endregion

    //region compareTo, toString
    @Override
    public int compareTo(@Nonnull Student other) {
        return ComparisonChain.start().compare(this.getFullName(), other.getFullName()).result();
    }

    @Override
    public String toString() {
        return fullName + ", " + year.toString();
    }
    //endregion
}
