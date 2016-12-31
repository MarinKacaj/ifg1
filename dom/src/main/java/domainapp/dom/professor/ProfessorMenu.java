package domainapp.dom.professor;

import org.apache.isis.applib.annotation.*;

import javax.annotation.Nullable;
import java.util.Collection;

/**
 * Created by C.R.C on 12/29/2016.
 * Professor menu
 */
@DomainService(
        nature = NatureOfService.VIEW_MENU_ONLY,
        repositoryFor = Professor.class)
@DomainServiceLayout(
        named = "Professor",
        menuOrder = "10")
public class ProfessorMenu {

    @javax.inject.Inject
    ProfessorRepository professorRepository;

    @Action(semantics = SemanticsOf.SAFE)
    @MemberOrder(sequence = "1")
    public Collection<Professor> listAll() {
        return professorRepository.listAll();
    }

    @Action(semantics = SemanticsOf.NON_IDEMPOTENT)
    @MemberOrder(sequence = "2")
    public Professor create(@ParameterLayout(named = Professor.FULL_NAME_LABEL) final String fullName,
                            @ParameterLayout(named = Professor.AFFILIATION_LABEL) final String affiliation,
                            @ParameterLayout(named = Professor.EMAIL_LABEL) final String email,
                            @ParameterLayout(named = Professor.TEL_NUM_LABEL) final String telNum,
                            @ParameterLayout(named = Professor.TEACHING_START_YEAR_LABEL) final Integer teachingStartYear,
                            @ParameterLayout(named = Professor.TEACHING_END_YEAR_LABEL) @Nullable final Integer teachingEndYear) {
        return professorRepository.create(fullName, affiliation, email, telNum, teachingStartYear, teachingEndYear);
    }
}
