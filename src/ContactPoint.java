/**
 * Represents a contact detail mapped to the FHIR R4 ContactPoint resource.
 * Holds a contact system (PHONE or EMAIL) and its corresponding value.
 */
public class ContactPoint {
    private ContactSystem system;
    private String value;

    public ContactPoint(ContactSystem system, String value) {
        this.system = system;
        this.value = value;
    }

    public ContactSystem getSystem() {
        return system;
    }

    public String getValue() {
        return value;
    }
}
