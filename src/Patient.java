import java.time.LocalDate;

/**
 * Represents a patient record mapped to the FHIR R4 Patient resource.
 * Holds core demographic data including name, contact details,
 * gender, and date of birth.
 */
public class Patient {

    private HumanName humanName;
    private ContactPoint contactPoint;
    private AdministrativeGender administrativeGender;
    private LocalDate dateOfBirth;

    public Patient(HumanName humanName, ContactPoint contactPoint, AdministrativeGender administrativeGender, LocalDate dateOfBirth) {
        this.humanName = humanName;
        this.contactPoint = contactPoint;
        this.administrativeGender = (administrativeGender != null) ? administrativeGender : AdministrativeGender.UNKNOWN;
        this.dateOfBirth = dateOfBirth;
    }

    public HumanName getHumanName() {
        return humanName;
    }

    public ContactPoint getContactPoint() {
        return contactPoint;
    }

    public AdministrativeGender getAdministrativeGender() {
        return administrativeGender;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }
}
