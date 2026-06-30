import java.util.ArrayList;
import java.util.HashMap;

/**
 * Manages an in-memory collection of Patient records.
 * Stores patients in an ArrayList to preserve insertion order,
 * and in a HashMap keyed by NHS Number for fast lookup by identifier.
 */
public class PatientRegistry {

    private ArrayList<Patient> patientArrayList;
    private HashMap<String, Patient>  patientHashMap;

    public PatientRegistry() {
        this.patientArrayList = new ArrayList<>();
        this.patientHashMap = new HashMap<>();
    }

    public void addPatient(Patient patient) {
        patientArrayList.add(patient);
        patientHashMap.put(patient.getNhsNumber().getValue(), patient);
    }

    public Patient findByNhsNumber(NhsNumber nhsNumber) {
        return patientHashMap.get(nhsNumber.getValue());
    }

    public ArrayList<Patient> getAllPatients() {
        return  patientArrayList;
    }
}
