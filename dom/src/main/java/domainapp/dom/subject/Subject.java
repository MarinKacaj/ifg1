package domainapp.dom.subject;

import com.google.common.collect.ComparisonChain;
import domainapp.dom.ColumnAllowsNull;
import domainapp.dom.module.Module;
import domainapp.dom.module.ModuleRepository;
import org.apache.isis.applib.annotation.*;

import javax.annotation.Nonnull;
import javax.jdo.annotations.*;

/**
 * Created by C.R.C on 12/29/2016.
 * Subject
 */
@javax.jdo.annotations.PersistenceCapable(
        identityType = IdentityType.DATASTORE,
        schema = "simple")
@javax.jdo.annotations.DatastoreIdentity(
        strategy = IdGeneratorStrategy.IDENTITY,
        column = Subject.ID)
@DomainObject(
        publishing = Publishing.ENABLED,
        auditing = Auditing.ENABLED)
@Queries({
        @Query(
                name = Subject.FIND_BY_NAME_QUERY,
                value = "SELECT FROM domainapp.dom.subject.Subject WHERE " +
                        Subject.NAME + ".indexOf(:" + Subject.NAME + ") >= 0")
})
public class Subject implements Comparable<Subject> {

    public static final String ID = "id";

    public static final String FIND_BY_NAME_QUERY = "findSubjectByName";

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

    //region module
    public static final String MODULE = "module";
    public static final String MODULE_LABEL = "Module";

    @javax.inject.Inject
    ModuleRepository moduleRepository;

    @Column(
            name = MODULE,
            allowsNull = ColumnAllowsNull.FALSE,
            target = Module.ID)
    private Module module;

    @Property(
            editing = Editing.ENABLED,
            publishing = Publishing.ENABLED)
    @PropertyLayout(named = MODULE_LABEL)
    public Module getModule() {
        return module;
    }

    public void setModule(Module module) {
        this.module = module;
    }

    @Action
    public java.util.Collection<Module> autoCompleteModule(final String nameSequence) {
        return moduleRepository.findByNameSequence(nameSequence);
    }
    //endregion

    @Override
    public int compareTo(@Nonnull Subject other) {
        return ComparisonChain.start().compare(this.getName(), other.getName()).result();
    }

    @Override
    public String toString() {
        return name;
    }
}
