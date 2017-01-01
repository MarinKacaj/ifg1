package domainapp.dom.professor;

import com.google.common.collect.ComparisonChain;
import domainapp.dom.ColumnAllowsNull;
import domainapp.dom.exam.Exam;
import org.apache.isis.applib.annotation.*;

import javax.annotation.Nonnull;
import javax.jdo.annotations.*;

/**
 * Created by C.R.C on 12/28/2016.
 * Professor
 */
@javax.jdo.annotations.PersistenceCapable(
        identityType = IdentityType.DATASTORE,
        schema = "simple")
@javax.jdo.annotations.DatastoreIdentity(
        strategy = IdGeneratorStrategy.IDENTITY,
        column = Professor.ID)
@DomainObject(
        publishing = Publishing.ENABLED,
        auditing = Auditing.ENABLED,
        autoCompleteRepository = ProfessorRepository.class,
        autoCompleteAction = "findByNameSequence")
@Unique(name = Professor.FULL_NAME_AFFILIATION_UNIQUE_CONSTRAINT_NAME, members = {"fullName", "affiliation"})
@Queries({
        @Query(
                name = Professor.FIND_BY_FULL_NAME_OR_AFFILIATION_QUERY,
                value = "SELECT FROM domainapp.dom.professor.Professor WHERE " +
                        Professor.FULL_NAME + ".indexOf(:" + Professor.FULL_NAME + ") >= 0 || " +
                        Professor.AFFILIATION + ".indexOf(:" + Professor.AFFILIATION + ") >= 0")
})
public class Professor implements Comparable<Professor> {

    public static final String ID = "id";

    public static final String FULL_NAME_AFFILIATION_UNIQUE_CONSTRAINT_NAME = "full_name_affiliation";
    public static final String FIND_BY_FULL_NAME_OR_AFFILIATION_QUERY = "findProfessorByFullName";

    public static final String FULL_NAME_LABEL = "Full Name";
    public static final String AFFILIATION_LABEL = "Affiliation";
    public static final String EMAIL_LABEL = "Email";
    public static final String TEL_NUM_LABEL = "Telephone number";
    public static final String TEACHING_START_YEAR_LABEL = "Teaching start year";
    public static final String TEACHING_END_YEAR_LABEL = "Teaching last year";

    //region fullName
    public static final String FULL_NAME = "fullName";

    @javax.jdo.annotations.Column(allowsNull = ColumnAllowsNull.FALSE)
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

    //region affiliation
    public static final String AFFILIATION = "affiliation";

    @javax.jdo.annotations.Column(allowsNull = ColumnAllowsNull.FALSE, name = AFFILIATION)
    private String affiliation;

    @Property(
            editing = Editing.ENABLED,
            publishing = Publishing.ENABLED)
    @PropertyLayout(named = AFFILIATION_LABEL)
    public String getAffiliation() {
        return affiliation;
    }

    public void setAffiliation(String affiliation) {
        this.affiliation = affiliation;
    }
    //endregion

    //region email
    @javax.jdo.annotations.Column(allowsNull = ColumnAllowsNull.FALSE)
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

    //region telNum
    @javax.jdo.annotations.Column(allowsNull = ColumnAllowsNull.FALSE)
    private String telNum;

    @Property(
            editing = Editing.ENABLED,
            publishing = Publishing.ENABLED)
    @PropertyLayout(named = TEL_NUM_LABEL)
    public String getTelNum() {
        return telNum;
    }

    public void setTelNum(String telNum) {
        this.telNum = telNum;
    }
    //endregion

    //region teachingStartYear
    @javax.jdo.annotations.Column(allowsNull = ColumnAllowsNull.FALSE)
    private Integer teachingStartYear;

    @Property(
            editing = Editing.ENABLED,
            publishing = Publishing.ENABLED)
    @PropertyLayout(named = TEACHING_START_YEAR_LABEL)
    public Integer getTeachingStartYear() {
        return teachingStartYear;
    }

    public void setTeachingStartYear(Integer teachingStartYear) {
        this.teachingStartYear = teachingStartYear;
    }
    //endregion

    //region teachingEndYear
    @javax.jdo.annotations.Column(allowsNull = ColumnAllowsNull.TRUE)
    private Integer teachingEndYear;

    @Property(
            editing = Editing.ENABLED,
            publishing = Publishing.ENABLED)
    @PropertyLayout(named = TEACHING_END_YEAR_LABEL)
    public Integer getTeachingEndYear() {
        return teachingEndYear;
    }

    public void setTeachingEndYear(Integer teachingEndYear) {
        this.teachingEndYear = teachingEndYear;
    }
    //endregion

    //region exams
    @Persistent(mappedBy = Exam.PROFESSOR)
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
    public int compareTo(@Nonnull Professor other) {
        return ComparisonChain.start().compare(this.getFullName(), other.getFullName()).result();
    }

    @Override
    public String toString() {
        return fullName + ", " + affiliation;
    }
    //endregion
}
