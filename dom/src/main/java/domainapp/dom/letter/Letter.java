package domainapp.dom.letter;

import com.google.common.base.MoreObjects;
import com.google.common.collect.ComparisonChain;
import domainapp.dom.ColumnAllowsNull;
import org.apache.isis.applib.annotation.*;

import javax.annotation.Nonnull;
import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;

/**
 * Created by C.R.C on 12/29/2016.
 * Letter
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
public class Letter implements Comparable<Letter> {

    //region content
    public static final String CONTENT_LABEL = "Content";

    @javax.jdo.annotations.Column(allowsNull = ColumnAllowsNull.FALSE)
    private String content;

    @Property(
            editing = Editing.ENABLED,
            publishing = Publishing.ENABLED)
    @PropertyLayout(named = CONTENT_LABEL)
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
    //endregion

    @Override
    public int compareTo(@Nonnull Letter other) {
        return ComparisonChain.start().compare(this.getContent(), other.getContent()).result();
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("content", content)
                .toString();
    }
}
