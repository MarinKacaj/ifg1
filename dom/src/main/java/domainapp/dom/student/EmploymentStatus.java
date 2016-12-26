package domainapp.dom.student;

/**
 * Created by C.R.C on 12/25/2016.
 * Employment status
 */
public enum EmploymentStatus {
    EMPLOYED("D"), UNEMPLOYED("ND");

    private String abbreviation;

    EmploymentStatus(String abbreviation) {
        this.abbreviation = abbreviation;
    }

    public String getAbbreviation() {
        return abbreviation;
    }
}
