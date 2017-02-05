package domainapp.dom.viewmodel.studentletter;

import com.querydsl.core.Tuple;
import com.querydsl.jdo.JDOQuery;
import domainapp.dom.letter.DLetterD;
import domainapp.dom.student.DStudentD;
import org.apache.isis.applib.annotation.DomainService;
import org.apache.isis.applib.annotation.NatureOfService;
import org.apache.isis.applib.services.jdosupport.IsisJdoSupport;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by C.R.C on 2/5/2017.
 * Student letter repository
 */
@DomainService(
        nature = NatureOfService.DOMAIN,
        repositoryFor = StudentLetter.class)
public class StudentLetterRepository {

    @javax.inject.Inject
    IsisJdoSupport isisJdoSupport;

    public Collection<StudentLetter> getStudentLetters() {

        DStudentD qdStudent = DStudentD.student;
        DLetterD qdLetter = DLetterD.letter;

        JDOQuery<StudentLetter> query = new JDOQuery<>(isisJdoSupport.getJdoPersistenceManager());
        List<Tuple> studentLetterProjectionRecords = query
                .from(qdStudent, qdLetter)
                .select(qdStudent.fullName, qdStudent.address, qdStudent.tel, qdStudent.email, qdLetter.content)
                .where(qdLetter.student().eq(qdStudent))
                .fetch();
        query.close();

        List<StudentLetter> studentLetters = new LinkedList<>();
        for (Tuple studentLetterProjectionRecord : studentLetterProjectionRecords) {
            String studentFullName = studentLetterProjectionRecord.get(qdStudent.fullName);
            String studentAddress = studentLetterProjectionRecord.get(qdStudent.address);
            String studentTel = studentLetterProjectionRecord.get(qdStudent.tel);
            String studentEmail = studentLetterProjectionRecord.get(qdStudent.fullName);
            String letterContent = studentLetterProjectionRecord.get(qdLetter.content);
            StudentLetter studentLetter = new StudentLetter(studentFullName, studentAddress, studentTel, studentEmail, letterContent);
            studentLetters.add(studentLetter);
        }
        return studentLetters;
    }
}
