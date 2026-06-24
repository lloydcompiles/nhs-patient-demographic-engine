/**
 * Represents a patient's name mapped to the FHIR R4 HumanName resource.
 * Contains family name (surname), given name (first name),
 * and prefix (title e.g. Mr, Mrs, Dr).
 */
public class HumanName {
    private String family;
    private String given;
    // prefix represents a name prefix like Mr/Mrs/Dr
    private String prefix;

    public HumanName(String family, String given, String prefix) {
        this.family = family;
        this.given = given;
        this.prefix = prefix;
    }

    public String getFamily() {
        return family;
    }

    public String getGiven() {
        return given;
    }

    public String getPrefix() {
        return prefix;
    }
}
