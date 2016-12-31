package domainapp.dom.exam;

import domainapp.dom.ColumnAllowsNull;
import domainapp.dom.academicyear.AcademicYear;
import domainapp.dom.academicyear.AcademicYearRepository;
import domainapp.dom.student.Student;
import domainapp.dom.student.StudentRepository;
import org.apache.isis.applib.annotation.*;
import org.apache.isis.applib.util.ObjectContracts;

import javax.annotation.Nonnull;
import javax.jdo.annotations.Column;
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
    public static final String ACADEMIC_YEAR_LABEL = "Academic Year";

    @javax.inject.Inject
    private AcademicYearRepository academicYearRepository;

    @Column(allowsNull = ColumnAllowsNull.FALSE, target = "id", name = ACADEMIC_YEAR_ID)
    private AcademicYear academicYear;

    @Property(
            editing = Editing.ENABLED,
            publishing = Publishing.ENABLED)
    @PropertyLayout(named = ACADEMIC_YEAR_LABEL)
    public AcademicYear getAcademicYear() {
        return academicYear;
    }

    public void setAcademicYear(AcademicYear academicYear) {
        this.academicYear = academicYear;
    }

    @Action
    public java.util.Collection<AcademicYear> autoCompleteAcademicYear(final String startYearDigitSequence) {
        return academicYearRepository.findByStartYearDigitSequence(startYearDigitSequence);
    }
    //endregion

    //region mark
    public static final String MARK_LABEL = "Mark";

    @Column(allowsNull = ColumnAllowsNull.FALSE)
    private Integer mark;

    @Property(
            editing = Editing.ENABLED,
            publishing = Publishing.ENABLED)
    @PropertyLayout(named = MARK_LABEL)
    public Integer getMark() {
        return mark;
    }

    public void setMark(Integer mark) {
        this.mark = mark;
    }
    //endregion

    //region student
    public static final String STUDENT = "student";
    public static final String STUDENT_LABEL = "Student";

    @javax.inject.Inject
    StudentRepository studentRepository;

    @Column(
            name = STUDENT,
            allowsNull = ColumnAllowsNull.FALSE,
            target = Student.ID)
    private Student student;

    @Property(
            editing = Editing.ENABLED,
            publishing = Publishing.ENABLED)
    @PropertyLayout(named = STUDENT_LABEL)
    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    @Action
    public java.util.Collection<Student> autoCompleteStudent(final String studentNameSequence) {
        return studentRepository.findByNameSequence(studentNameSequence);
    }
    //endregion

    //region compareTo, toString
    @Override
    public int compareTo(@Nonnull Exam other) {
        return ObjectContracts.compare(this, other);
    }

    @Override
    public String toString() {
        return mark + " for " + academicYear;
    }
    //endregion
}
