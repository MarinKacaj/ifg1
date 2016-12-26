package domainapp.dom.student;

/**
 * Created by C.R.C on 12/25/2016.
 * Student gender
 */
public enum Gender {

    MALE("M"), FEMALE("F");

    private String abbreviation;

    Gender(String abbreviation) {
        this.abbreviation = abbreviation;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    @Override
    public String toString() {
        return abbreviation;
    }
}
