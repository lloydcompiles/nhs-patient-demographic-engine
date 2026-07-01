import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CsvPatientLoader {

    private String filePath;

    public CsvPatientLoader(String filePath) {
        this.filePath = filePath;
    }

    public List<Patient> loadPatients() {

        ArrayList<Patient> patients = new ArrayList<>();
        String line = "";
        String csvSplitBy = ",";


        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {

            br.readLine();

            while ((line = br.readLine()) != null) {
                String[] data = line.split(csvSplitBy);

                if (data.length >= 8) {

                    String nhsNumber = data[0].trim();
                    String familyName = data[1].trim();
                    String givenName = data[2].trim();
                    String prefix = data[3].trim();
                    String contactSystem = data[4].trim();
                    String contactValue = data[5].trim();
                    String gender = data[6].trim();
                    String dateOfBirth = data[7].trim();

                    try {
                        NhsNumber nhsNumberValidated = new NhsNumber(nhsNumber);

                        HumanName humanNameValidated = new HumanName(familyName, givenName, prefix);
                        ContactPoint contactPointValidated = new ContactPoint(ContactSystem.valueOf(contactSystem),contactValue);
                        LocalDate dateOfBirthValidated = LocalDate.parse(dateOfBirth);

                        // Create the object and add it to your list
                        Patient patientCsvLoaded = new Patient(nhsNumberValidated,humanNameValidated,contactPointValidated,AdministrativeGender.valueOf(gender),dateOfBirthValidated);
                        patients.add(patientCsvLoaded);

                    } catch (InvalidNHSNumberException e) {
                        System.err.println("Skipping invalid NHS Number: " + nhsNumber + "\n" + e.getMessage());
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return patients;
    }
}
