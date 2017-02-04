package domainapp.dom.student;

import com.querydsl.core.Tuple;
import com.querydsl.core.types.Expression;

/**
 * Created by C.R.C on 2/4/2017.
 * Utility for queries student.
 */
public class StudentQUtil {

    private static final DStudentD qdStudent = DStudentD.student;

    public static Expression<?>[] createStudentFullProjection() {
        return new Expression<?>[]{qdStudent.fullName, qdStudent.gender, qdStudent.birthYear, qdStudent.initialFormation,
                qdStudent.address, qdStudent.city, qdStudent.country, qdStudent.tel, qdStudent.email,
                qdStudent.employer, qdStudent.employmentStatus, qdStudent.promotion, qdStudent.year,
                qdStudent.mode};
    }

    public static Student convertToTupleDomainObject(Tuple unPromotedStudent) {
        Student student = new Student();
        student.setFullName(unPromotedStudent.get(qdStudent.fullName));
        student.setGender(unPromotedStudent.get(qdStudent.gender));
        student.setBirthYear(unPromotedStudent.get(qdStudent.birthYear));
        student.setInitialFormation(unPromotedStudent.get(qdStudent.initialFormation));
        student.setAddress(unPromotedStudent.get(qdStudent.address));
        student.setCity(unPromotedStudent.get(qdStudent.city));
        student.setCountry(unPromotedStudent.get(qdStudent.country));
        student.setTel(unPromotedStudent.get(qdStudent.tel));
        student.setEmail(unPromotedStudent.get(qdStudent.email));
        student.setEmploymentStatus(unPromotedStudent.get(qdStudent.employmentStatus));
        student.setPromotion(unPromotedStudent.get(qdStudent.promotion));
        student.setYear(unPromotedStudent.get(qdStudent.year));
        student.setMode(unPromotedStudent.get(qdStudent.mode));
        return student;
    }
}
