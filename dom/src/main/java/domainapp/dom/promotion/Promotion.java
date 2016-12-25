package domainapp.dom.promotion;

import domainapp.dom.ColumnAllowsNull;
import org.apache.isis.applib.annotation.*;

import javax.annotation.Nonnull;
import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;

/**
 * Created by C.R.C on 12/24/2016.
 * Promotion
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
public class Promotion implements Comparable<Promotion> {

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

    @Override
    public int compareTo(@Nonnull Promotion other) {
        return (int) Math.signum(this.getYear() - other.getYear());
    }

    @Override
    public String toString() {
        return year + " Promotion";
    }
}
