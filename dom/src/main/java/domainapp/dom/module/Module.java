package domainapp.dom.module;

import com.google.common.collect.ComparisonChain;
import domainapp.dom.ColumnAllowsNull;
import org.apache.isis.applib.annotation.*;

import javax.annotation.Nonnull;
import javax.jdo.annotations.Column;
import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.Unique;

/**
 * Created by C.R.C on 12/29/2016.
 * Module
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
public class Module implements Comparable<Module> {

    //region name
    public static final String NAME_LABEL = "Name";

    @Column(allowsNull = ColumnAllowsNull.FALSE)
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

    @Override
    public int compareTo(@Nonnull Module other) {
        return ComparisonChain.start().compare(this.getName(), other.getName()).result();
    }

    @Override
    public String toString() {
        return name;
    }
}
