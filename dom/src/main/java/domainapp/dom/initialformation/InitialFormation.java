package domainapp.dom.initialformation;

import com.google.common.collect.ComparisonChain;
import domainapp.dom.ColumnAllowsNull;
import domainapp.dom.student.Student;
import org.apache.isis.applib.annotation.*;

import javax.annotation.Nonnull;
import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.Persistent;

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
        auditing = Auditing.ENABLED,
        autoCompleteRepository = InitialFormationRepository.class,
        autoCompleteAction = "findByName")
@javax.jdo.annotations.Queries({
        @javax.jdo.annotations.Query(
                name = InitialFormation.FIND_BY_NAME,
                value = "SELECT FROM domainapp.dom.initialformation.InitialFormation " +
                        "WHERE " + InitialFormation.NAME + ".indexOf(:" + InitialFormation.NAME + ") >= 0")
})
public class InitialFormation implements Comparable<InitialFormation> {

    public static final String ID = "id";

    public static final String FIND_BY_NAME = "findInitialFormationByName";

    //region name
    public static final String NAME = "name";

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
    @Persistent(mappedBy = Student.INITIAL_FORMATION)
    @Collection
    private java.util.Collection<Student> students;

    public java.util.Collection<Student> getStudents() {
        return students;
    }

    public void setStudents(java.util.Collection<Student> students) {
        this.students = students;
    }
    //endregion

    //region compareTo, toString
    @Override
    public int compareTo(@Nonnull InitialFormation other) {
        return ComparisonChain.start().compare(this.getName(), other.getName()).result();
    }

    @Override
    public String toString() {
        return getName();
    }
    //endregion
}
