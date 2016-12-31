package domainapp.dom.exam;

import domainapp.dom.ColumnAllowsNull;
import domainapp.dom.academicyear.AcademicYear;
import domainapp.dom.academicyear.AcademicYearRepository;
import org.apache.isis.applib.annotation.*;
import org.apache.isis.applib.util.ObjectContracts;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;

/**
 * Created by C.R.C on 12/18/2016.
 * Exam
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
public class Exam implements Comparable<Exam> {

    //region academicYear
    public static final String ACADEMIC_YEAR_ID = "academicYear";

    @javax.inject.Inject
    private AcademicYearRepository academicYearRepository;

    @Property(
            editing = Editing.ENABLED,
            publishing = Publishing.ENABLED)
    @PropertyLayout(named = "Academic Year")
    public AcademicYear getAcademicYear() {
        return academicYear;
    }

    public void setAcademicYear(AcademicYear academicYear) {
        this.academicYear = academicYear;
    }

    @ActionLayout(hidden = Where.NOWHERE)
    public java.util.Collection<AcademicYear> autoCompleteAcademicYear(final String startYearDigitSequence) {
        return academicYearRepository.findByStartYearDigitSequence(startYearDigitSequence);
    }
    //endregion

    //region mark
    @javax.jdo.annotations.Column(allowsNull = ColumnAllowsNull.FALSE)
    private Integer mark;
    @javax.jdo.annotations.Column(allowsNull = ColumnAllowsNull.FALSE, target = "id", name = ACADEMIC_YEAR_ID)
    private AcademicYear academicYear;

    @Property(
            editing = Editing.ENABLED,
            publishing = Publishing.ENABLED)
    @PropertyLayout(named = "Mark")
    public Integer getMark() {
        return mark;
    }

    public void setMark(Integer mark) {
        this.mark = mark;
    }
    //endregion

    @Override
    public int compareTo(Exam other) {
        return ObjectContracts.compare(this, other);
    }

    @Override
    public String toString() {
        return mark + " for " + academicYear;
    }
}
