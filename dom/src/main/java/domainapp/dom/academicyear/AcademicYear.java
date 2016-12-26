package domainapp.dom.academicyear;

import domainapp.dom.ColumnAllowsNull;
import domainapp.dom.exam.Exam;
import domainapp.dom.student.Student;
import org.apache.isis.applib.annotation.*;

import javax.annotation.Nonnull;
import javax.jdo.annotations.Column;
import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.Persistent;
import java.util.List;

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
    private Integer startYear;

    @Property(
            editing = Editing.ENABLED,
            publishing = Publishing.ENABLED)
    public Integer getStartYear() {
        return startYear;
    }

    public void setStartYear(Integer startYear) {
        this.startYear = startYear;
    }
    //endregion

    //region exams
    @Column(allowsNull = ColumnAllowsNull.FALSE)
    @Persistent(mappedBy = Exam.ACADEMIC_YEAR_ID)
    private List<Exam> exams;

    @Property(
            editing = Editing.ENABLED,
            publishing = Publishing.ENABLED)
    public List<Exam> getExams() {
        return exams;
    }

    public void setExams(List<Exam> exams) {
        this.exams = exams;
    }
    //endregion

    //region students
    @Column(allowsNull = ColumnAllowsNull.FALSE)
    @Persistent(mappedBy = Student.ACADEMIC_YEAR)
    private List<Student> students;

    @Property(
            editing = Editing.ENABLED,
            publishing = Publishing.ENABLED)
    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
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
