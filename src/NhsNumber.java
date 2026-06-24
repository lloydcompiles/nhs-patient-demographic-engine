/**
 * Represents a validated NHS Number mapped to the FHIR R4 identifier.
 * Accepts only a 10-digit numeric string, otherwise throws InvalidNHSNumberException.
 */
public class NhsNumber {

    private String value;

    public NhsNumber(String value) throws InvalidNHSNumberException {
        if (!value.matches("\\d{10}")) {
            throw new InvalidNHSNumberException("Invalid NHS Number as it must be exactly 10 digits. You entered: " + value);
        }
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
