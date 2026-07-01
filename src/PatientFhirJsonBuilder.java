/**
 * Builds a FHIR R4-shaped JSON representation of a Patient record.
 * Manually constructs the JSON string without external libraries,
 * mapping domain model fields to their FHIR R4 Patient resource equivalents.
 */
public class PatientFhirJsonBuilder {

    private Patient patient;

    public  PatientFhirJsonBuilder(Patient patient) {
        this.patient  = patient;
    }

    public String buildPatientFhirJson() {
        return """
        {
          "resourceType": "Patient",
          "id": "%s",
          "name": [{
            "family": "%s",
            "given": ["%s"],
            "prefix": ["%s"]
          }],
          "gender": "%s",
          "birthDate": "%s",
          "telecom": [{
            "system": "%s",
            "value": "%s"
          }]
        }
        """.formatted(patient.getNhsNumber().getValue(),
                patient.getHumanName().getFamily(),
                patient.getHumanName().getGiven(),
                patient.getHumanName().getPrefix(),
                patient.getAdministrativeGender().name().toLowerCase(),
                patient.getDateOfBirth().toString(),
                patient.getContactPoint().getSystem().name().toLowerCase(),
                patient.getContactPoint().getValue());
    }
}
