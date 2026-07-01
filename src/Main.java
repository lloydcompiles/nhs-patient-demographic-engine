import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("NHS Patient Demographic Engine");
        System.out.println("Application starting...");

        PatientRegistry registry = new PatientRegistry();

        CsvPatientLoader loadPatients = new CsvPatientLoader("data/patients.csv");
        List<Patient> savedPatients = loadPatients.loadPatients();
        for (Patient patient : savedPatients){
            registry.addPatient(patient);
        }

        CliMenu menu = new CliMenu(registry);
        menu.start();
    }
}