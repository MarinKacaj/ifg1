package domainapp.dom.initialformation;

import com.google.common.collect.ComparisonChain;
import domainapp.dom.ColumnAllowsNull;
import domainapp.dom.student.Student;
import org.apache.isis.applib.annotation.*;

import javax.annotation.Nonnull;
import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.Persistent;
import java.util.List;

/**
 * Created by C.R.C on 12/25/2016.
 * Initial formation
 */
@javax.jdo.annotations.PersistenceCapable(
        identityType = IdentityType.DATASTORE,
        schema = "simple")
@javax.jdo.annotations.DatastoreIdentity(
        strategy = IdGeneratorStrategy.IDENTITY,
        column = InitialFormation.ID)
@DomainObject(
        publishing = Publishing.ENABLED,
        auditing = Auditing.ENABLED) // TODO - autocomplete
public class InitialFormation implements Comparable<InitialFormation> {

    public static final String ID = "id";

    //region name
    @javax.jdo.annotations.Column(allowsNull = ColumnAllowsNull.FALSE)
    private String name;

    @Property(
            editing = Editing.ENABLED,
            publishing = Publishing.ENABLED)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    //endregion

    //region students
    @javax.jdo.annotations.Column(allowsNull = ColumnAllowsNull.FALSE)
    @Persistent(mappedBy = Student.INITIAL_FORMATION)
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

    //region compareTo
    @Override
    public int compareTo(@Nonnull InitialFormation other) {
        return ComparisonChain.start().compare(this.getName(), other.getName()).result();
    }
    //endregion

    //region toString
    @Override
    public String toString() {
        return getName();
    }
    //endregion
}
