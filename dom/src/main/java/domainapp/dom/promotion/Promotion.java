package domainapp.dom.promotion;

import domainapp.dom.ColumnAllowsNull;
import domainapp.dom.student.Student;
import org.apache.isis.applib.annotation.*;

import javax.annotation.Nonnull;
import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.Persistent;

/**
 * Created by C.R.C on 12/24/2016.
 * Promotion
 */
@javax.jdo.annotations.PersistenceCapable(
        identityType = IdentityType.DATASTORE,
        schema = "simple")
@javax.jdo.annotations.DatastoreIdentity(
        strategy = IdGeneratorStrategy.IDENTITY,
        column = Promotion.ID)
@DomainObject(
        publishing = Publishing.ENABLED,
        auditing = Auditing.ENABLED) // TODO - autocomplete
public class Promotion implements Comparable<Promotion> {

    public static final String ID = "id";

    //region year
    @javax.jdo.annotations.Column(allowsNull = ColumnAllowsNull.TRUE)
    private Integer year;

    @Property(
            editing = Editing.ENABLED,
            publishing = Publishing.ENABLED)
    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }
    //endregion

    //region students
    @Persistent(mappedBy = Student.PROMOTION)
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
    public int compareTo(@Nonnull Promotion other) {
        return (int) Math.signum(this.getYear() - other.getYear());
    }

    @Override
    public String toString() {
        return year + " Promotion";
    }
}
