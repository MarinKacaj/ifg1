package domainapp.dom.viewmodel.studentfinalmark;

import com.querydsl.core.annotations.QueryProjection;
import org.apache.isis.applib.annotation.DomainObject;
import org.apache.isis.applib.annotation.Editing;
import org.apache.isis.applib.annotation.Property;
import org.apache.isis.applib.annotation.ViewModel;

/**
 * Created by C.R.C on 2/5/2017.
 * Student final mark
 */
@ViewModel
@DomainObject(editing = Editing.DISABLED)
public class StudentResult {

    @Property
    private String studentFullName;

    @Property
    private String subjectName;

    @Property
    private Integer examMark;

    @Property
    private String professorFullName;

    @Property
    private Integer academicYearStartYear;

    @Property
    private Float moduleCoefficient;

    @Property
    private String moduleName;

    @Property
    private Float moduleGPA;

    public StudentResult() {
    }

    @QueryProjection
    public StudentResult(String studentFullName, String subjectName, Integer examMark, String professorFullName,
                         Integer academicYearStartYear, Float moduleCoefficient, String moduleName, Float moduleGPA) {
        this.studentFullName = studentFullName;
        this.subjectName = subjectName;
        this.examMark = examMark;
        this.professorFullName = professorFullName;
        this.academicYearStartYear = academicYearStartYear;
        this.moduleCoefficient = moduleCoefficient;
        this.moduleName = moduleName;
        this.moduleGPA = moduleGPA;
    }

    public String getStudentFullName() {
        return studentFullName;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public Integer getExamMark() {
        return examMark;
    }

    public String getProfessorFullName() {
        return professorFullName;
    }

    public Integer getAcademicYearStartYear() {
        return academicYearStartYear;
    }

    public Float getModuleCoefficient() {
        return moduleCoefficient;
    }

    public String getModuleName() {
        return moduleName;
    }

    public Float getModuleGPA() {
        return moduleGPA;
    }

    @Override
    public String toString() {
        return studentFullName + " @" + subjectName + ": " + examMark;
    }
}
