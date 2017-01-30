package domainapp.dom.viewmodel.academicyearexamresult;

import com.querydsl.core.annotations.QueryProjection;
import org.apache.isis.applib.annotation.DomainObject;
import org.apache.isis.applib.annotation.Editing;
import org.apache.isis.applib.annotation.Property;
import org.apache.isis.applib.annotation.ViewModel;

/**
 * Created by C.R.C on 1/30/2017.
 * Academic year exam result
 */
@ViewModel
@DomainObject(editing = Editing.DISABLED)
public class AcademicYearExamResult {

    @Property
    private Integer academicYearStartYear;

    @Property
    private Integer mark;

    @Property
    private String subjectName;

    @Property
    private String professorFullName;

    @Property
    private String studentFullName;

    @Property
    private String moduleName;

    public AcademicYearExamResult() {
    }

    @QueryProjection
    public AcademicYearExamResult(Integer academicYearStartYear, Integer mark, String subjectName, String professorFullName, String studentFullName, String moduleName) {
        this.academicYearStartYear = academicYearStartYear;
        this.mark = mark;
        this.subjectName = subjectName;
        this.professorFullName = professorFullName;
        this.studentFullName = studentFullName;
        this.moduleName = moduleName;
    }

    public Integer getAcademicYearStartYear() {
        return academicYearStartYear;
    }

    public Integer getMark() {
        return mark;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public String getProfessorFullName() {
        return professorFullName;
    }

    public String getStudentFullName() {
        return studentFullName;
    }

    public String getModuleName() {
        return moduleName;
    }

    @Override
    public String toString() {
        return studentFullName + "'s mark in " + subjectName + " by " + professorFullName + " is " + mark;
    }
}
