package domainapp.dom.professor;

import com.google.common.collect.ComparisonChain;
import domainapp.dom.ColumnAllowsNull;
import org.apache.isis.applib.annotation.*;

import javax.annotation.Nonnull;
import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.Unique;

/**
 * Created by C.R.C on 12/28/2016.
 * Professor
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
public class Professor implements Comparable<Professor> {

    private static final String FULL_NAME_AFFILIATION_UNIQUE_CONSTRAINT_NAME = "full_name_affiliation";

    public static final String FULL_NAME_LABEL = "Full Name";
    public static final String AFFILIATION_LABEL = "Affiliation";
    public static final String EMAIL_LABEL = "Email";
    public static final String TEL_NUM_LABEL = "Telephone number";
    public static final String TEACHING_START_YEAR_LABEL = "Teaching start year";
    public static final String TEACHING_END_YEAR_LABEL = "Teaching last year";

    //region fullName
    @javax.jdo.annotations.Column(allowsNull = ColumnAllowsNull.FALSE)
    @Unique(name = FULL_NAME_AFFILIATION_UNIQUE_CONSTRAINT_NAME, members = {"fullName", "affiliation"})
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
    @javax.jdo.annotations.Column(allowsNull = ColumnAllowsNull.FALSE)
    @Unique(name = FULL_NAME_AFFILIATION_UNIQUE_CONSTRAINT_NAME, members = {"fullName", "affiliation"})
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

    //region compareTo
    @Override
    public int compareTo(@Nonnull Professor other) {
        return ComparisonChain.start().compare(this.getFullName(), other.getFullName()).result();
    }
    //endregion

    //region toString
    @Override
    public String toString() {
        return fullName + ", " + affiliation;
    }
    //endregion
}
