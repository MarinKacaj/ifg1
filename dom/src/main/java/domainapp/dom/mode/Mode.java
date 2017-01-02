package domainapp.dom.mode;

import com.google.common.collect.ComparisonChain;
import domainapp.dom.ColumnAllowsNull;
import domainapp.dom.initialformation.InitialFormation;
import domainapp.dom.student.Student;
import org.apache.isis.applib.annotation.*;

import javax.annotation.Nonnull;
import javax.jdo.annotations.*;
import java.util.Collection;

/**
 * Created by C.R.C on 12/30/2016.
 * Mode
 */
@javax.jdo.annotations.PersistenceCapable(
        identityType = IdentityType.DATASTORE,
        schema = "simple")
@javax.jdo.annotations.DatastoreIdentity(
        strategy = IdGeneratorStrategy.IDENTITY,
        column = InitialFormation.ID)
@DomainObject(
        publishing = Publishing.ENABLED,
        auditing = Auditing.ENABLED,
        autoCompleteRepository = ModeRepository.class,
        autoCompleteAction = "findByName")
public class Mode implements Comparable<Mode> {

    public static final String ID = "id";

    //region name
    public static final String NAME = "name";
    public static final String NAME_LABEL = "Name";

    @Column(allowsNull = ColumnAllowsNull.TRUE, name = NAME)
    @Unique
    private String name;

    @Property(
            editing = Editing.ENABLED,
            publishing = Publishing.ENABLED)
    @PropertyLayout(named = NAME_LABEL)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    //endregion

    //region students
    @Persistent(mappedBy = Student.MODE)
    @org.apache.isis.applib.annotation.Collection
    private Collection<Student> students;

    public Collection<Student> getStudents() {
        return students;
    }

    public void setStudents(Collection<Student> students) {
        this.students = students;
    }
    //endregion

    //region compareTo, toString
    @Override
    public int compareTo(@Nonnull Mode other) {
        return ComparisonChain.start().compare(this.getName(), other.getName()).result();
    }

    @Override
    public String toString() {
        return name;
    }
    //endregion
}
