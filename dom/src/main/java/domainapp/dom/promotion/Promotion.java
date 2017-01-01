package domainapp.dom.promotion;

import domainapp.dom.ColumnAllowsNull;
import domainapp.dom.student.Student;
import org.apache.isis.applib.annotation.*;

import javax.annotation.Nonnull;
import javax.jdo.annotations.*;

/**
 * Created by C.R.C on 12/24/2016.
 * Promotion
 */
@javax.jdo.annotations.PersistenceCapable(
        identityType = IdentityType.DATASTORE,
        schema = "simple")
@javax.jdo.annotations.DatastoreIdentity(
        strategy = IdGeneratorStrategy.IDENTITY,
        column = Promotion.ID)
@DomainObject(
        publishing = Publishing.ENABLED,
        auditing = Auditing.ENABLED,
        autoCompleteRepository = PromotionRepository.class,
        autoCompleteAction = "findByYearDigitSequence")
@Queries({
        @Query(
                name = Promotion.FIND_BY_YEAR_QUERY,
                value = "SELECT FROM domainapp.dom.promotion.Promotion WHERE " +
                        Promotion.YEAR + " >= :" + Promotion.YEAR)
})
public class Promotion implements Comparable<Promotion> {

    public static final String ID = "id";

    public static final String FIND_BY_YEAR_QUERY = "findByYear";

    //region year
    public static final String YEAR = "year";

    @javax.jdo.annotations.Column(allowsNull = ColumnAllowsNull.TRUE, name = YEAR)
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
    //endregion

    //region students
    @Persistent(mappedBy = Student.PROMOTION)
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
    public int compareTo(@Nonnull Promotion other) {
        return (int) Math.signum(this.getYear() - other.getYear());
    }

    @Override
    public String toString() {
        return year + " Promotion";
    }
    //endregion
}
