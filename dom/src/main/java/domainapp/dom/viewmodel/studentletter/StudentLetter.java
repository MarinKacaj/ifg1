package domainapp.dom.viewmodel.studentletter;

import com.querydsl.core.annotations.QueryProjection;
import org.apache.isis.applib.annotation.DomainObject;
import org.apache.isis.applib.annotation.Editing;
import org.apache.isis.applib.annotation.Property;
import org.apache.isis.applib.annotation.ViewModel;

/**
 * Created by C.R.C on 2/5/2017.
 * Student letter
 */
@ViewModel
@DomainObject(editing = Editing.DISABLED)
public class StudentLetter {

    @Property
    private String studentFullName;

    @Property
    private String studentAddress;

    @Property
    private String studentTel;

    @Property
    private String studentEmail;

    @Property
    private String letterContent;

    public StudentLetter() {
    }

    @QueryProjection
    public StudentLetter(String studentFullName, String studentAddress, String studentTel, String studentEmail, String letterContent) {
        this.studentFullName = studentFullName;
        this.studentAddress = studentAddress;
        this.studentTel = studentTel;
        this.studentEmail = studentEmail;
        this.letterContent = letterContent;
    }

    public String getStudentFullName() {
        return studentFullName;
    }

    public String getStudentAddress() {
        return studentAddress;
    }

    public String getStudentTel() {
        return studentTel;
    }

    public String getStudentEmail() {
        return studentEmail;
    }

    public String getLetterContent() {
        return letterContent;
    }

    @Override
    public String toString() {
        return "To " + studentFullName + ", " + studentAddress;
    }
}
