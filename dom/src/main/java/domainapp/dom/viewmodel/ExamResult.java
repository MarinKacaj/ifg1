package domainapp.dom.viewmodel;

import com.querydsl.core.annotations.QueryProjection;
import org.apache.isis.applib.annotation.DomainObject;
import org.apache.isis.applib.annotation.Editing;
import org.apache.isis.applib.annotation.Property;
import org.apache.isis.applib.annotation.ViewModel;


@ViewModel
@DomainObject(editing = Editing.DISABLED)
public class ExamResult {

    @Property
    private String subjectName;

    @Property
    private String moduleName;

    @Property
    private Integer mark;

    @Property
    private String professorFullName;

    @Property
    private String studentFullName;

    public ExamResult() {
    }

    @QueryProjection
    public ExamResult(String subjectName, String moduleName, Integer mark, String professorFullName, String studentFullName) {
        this.subjectName = subjectName;
        this.moduleName = moduleName;
        this.mark = mark;
        this.professorFullName = professorFullName;
        this.studentFullName = studentFullName;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public String getModuleName() {
        return moduleName;
    }

    public Integer getMark() {
        return mark;
    }

    public String getProfessorFullName() {
        return professorFullName;
    }

    public String getStudentFullName() {
        return studentFullName;
    }

    @Override
    public String toString() {
        return studentFullName + "'s mark in " + subjectName + " by " + professorFullName + " is " + mark;
    }
}
