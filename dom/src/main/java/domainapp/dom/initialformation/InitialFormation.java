package domainapp.dom.initialformation;

import domainapp.dom.ColumnAllowsNull;
import org.apache.isis.applib.annotation.*;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;

/**
 * Created by C.R.C on 12/25/2016.
 * Initial formation
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
public class InitialFormation {

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
}
