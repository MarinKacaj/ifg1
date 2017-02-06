package domainapp.dom.viewmodel.studentgpa;

import com.querydsl.core.annotations.QueryProjection;
import org.apache.isis.applib.annotation.DomainObject;
import org.apache.isis.applib.annotation.Editing;
import org.apache.isis.applib.annotation.Property;
import org.apache.isis.applib.annotation.ViewModel;

/**
 * Created by C.R.C on 2/5/2017.
 * Student GPA
 */
@ViewModel
@DomainObject(editing = Editing.DISABLED)
public class StudentGPA {

    @Property
    private String studentFullName;

    @Property
    private Float gpa;

    public StudentGPA() {
    }

    @QueryProjection
    public StudentGPA(String studentFullName, Float gpa) {
        this.studentFullName = studentFullName;
        this.gpa = gpa;
    }

    public String getStudentFullName() {
        return studentFullName;
    }

    public Float getGpa() {
        return gpa;
    }
}
