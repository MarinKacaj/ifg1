package domainapp.dom.module;

import com.google.common.collect.ComparisonChain;
import domainapp.dom.ColumnAllowsNull;
import domainapp.dom.subject.Subject;
import org.apache.isis.applib.annotation.*;

import javax.annotation.Nonnull;
import javax.jdo.annotations.*;
import java.util.Collection;

/**
 * Created by C.R.C on 12/29/2016.
 * Module
 */
@javax.jdo.annotations.PersistenceCapable(
        identityType = IdentityType.DATASTORE,
        schema = "simple")
@javax.jdo.annotations.DatastoreIdentity(
        strategy = IdGeneratorStrategy.IDENTITY,
        column = Module.ID)
@DomainObject(
        publishing = Publishing.ENABLED,
        auditing = Auditing.ENABLED,
        autoCompleteRepository = ModuleRepository.class,
        autoCompleteAction = "findByNameSequence")
@Queries({
        @Query(
                name = Module.FIND_BY_NAME_QUERY,
                value = "SELECT FROM domainapp.dom.module.Module WHERE " +
                        Module.NAME + ".indexOf(:" + Module.NAME + ") >= 0")
})
public class Module implements Comparable<Module> {

    public static final String ID = "id";

    public static final String FIND_BY_NAME_QUERY = "findModuleByName";

    //region name
    public static final String NAME = "name";
    public static final String NAME_LABEL = "Name";

    @Column(allowsNull = ColumnAllowsNull.FALSE, name = NAME)
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

    //region coefficient
    public static final String COEFFICIENT_LABEL = "Coefficient";

    @Column(allowsNull = ColumnAllowsNull.FALSE)
    private Float coefficient;

    @Property(
            editing = Editing.ENABLED,
            publishing = Publishing.ENABLED)
    @PropertyLayout(named = COEFFICIENT_LABEL)
    public Float getCoefficient() {
        return coefficient;
    }

    public void setCoefficient(Float coefficient) {
        this.coefficient = coefficient;
    }
    //endregion

    //region subjects
    @Persistent(mappedBy = Subject.MODULE)
    @org.apache.isis.applib.annotation.Collection
    private Collection<Subject> subjects;

    public Collection<Subject> getSubjects() {
        return subjects;
    }

    public void setStudents(Collection<Subject> subjects) {
        this.subjects = subjects;
    }
    //endregion

    //region compareTo, toString
    @Override
    public int compareTo(@Nonnull Module other) {
        return ComparisonChain.start().compare(this.getName(), other.getName()).result();
    }

    @Override
    public String toString() {
        return name;
    }
    //endregion
}
