package domainapp.dom.mode;

import com.google.common.collect.ComparisonChain;
import domainapp.dom.ColumnAllowsNull;
import org.apache.isis.applib.annotation.Editing;
import org.apache.isis.applib.annotation.Property;
import org.apache.isis.applib.annotation.PropertyLayout;
import org.apache.isis.applib.annotation.Publishing;

import javax.annotation.Nonnull;
import javax.jdo.annotations.Column;
import javax.jdo.annotations.Unique;

/**
 * Created by C.R.C on 12/30/2016.
 * Mode
 */
public class Mode implements Comparable<Mode> {

    //region name
    public static final String NAME_LABEL = "Name";

    @Column(allowsNull = ColumnAllowsNull.TRUE)
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

    @Override
    public int compareTo(@Nonnull Mode other) {
        return ComparisonChain.start().compare(this.getName(), other.getName()).result();
    }

    @Override
    public String toString() {
        return name;
    }
}
