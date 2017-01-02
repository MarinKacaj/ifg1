package domainapp.dom.academicyear;

import domainapp.dom.ColumnAllowsNull;
import domainapp.dom.exam.Exam;
import domainapp.dom.student.Student;
import org.apache.isis.applib.annotation.*;

import javax.annotation.Nonnull;
import javax.jdo.annotations.*;

/**
 * Created by C.R.C on 12/17/2016.
 * AcademicYear
 */
@javax.jdo.annotations.PersistenceCapable(
        identityType = IdentityType.DATASTORE,
        schema = "simple")
@javax.jdo.annotations.DatastoreIdentity(
        strategy = IdGeneratorStrategy.IDENTITY,
        column = AcademicYear.ID)
@DomainObject(
        publishing = Publishing.ENABLED,
        auditing = Auditing.ENABLED,
        autoCompleteRepository = AcademicYearRepository.class,
        autoCompleteAction = "findByStartYearDigitSequence")
@javax.jdo.annotations.Queries({
        @javax.jdo.annotations.Query(
                name = AcademicYear.FIND_BY_START_YEAR_QUERY,
                value = "SELECT FROM domainapp.dom.academicyear.AcademicYear WHERE startYear >= :" + AcademicYear.START_YEAR)
})
public class AcademicYear implements Comparable<AcademicYear> {

    public static final String ID = "id";

    //region named queries
    public static final String FIND_BY_START_YEAR_QUERY = "findByStartYear";
    //endregion

    //region startYear
    public static final String START_YEAR = "startYear";

    @Column(allowsNull = ColumnAllowsNull.FALSE)
    @Unique
    private int startYear;

    @Property(
            editing = Editing.ENABLED,
            publishing = Publishing.ENABLED)
    public int getStartYear() {
        return startYear;
    }

    public void setStartYear(int startYear) {
        this.startYear = startYear;
    }
    //endregion

    //region exams
    @Persistent(mappedBy = Exam.ACADEMIC_YEAR_ID)
    @Collection
    private java.util.Collection<Exam> exams;

    public java.util.Collection<Exam> getExams() {
        return exams;
    }

    public void setExams(java.util.Collection<Exam> exams) {
        this.exams = exams;
    }
    //endregion

    //region students
    @Persistent(mappedBy = Student.ACADEMIC_YEAR)
    @Collection
    private java.util.Collection<Student> students;

    public java.util.Collection<Student> getStudents() {
        return students;
    }

    public void setStudents(java.util.Collection<Student> students) {
        this.students = students;
    }
    //endregion

    @Override
    public int compareTo(@Nonnull AcademicYear other) {
        return (int) Math.signum(this.getStartYear() - other.getStartYear());
    }

    @Override
    public String toString() {
        return getStartYear() + " - " + (getStartYear() + 1) + " academic year";
    }
}
