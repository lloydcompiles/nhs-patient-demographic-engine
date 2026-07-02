import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

/**
 * Provides the command-line interface for the NHS Patient Demographic Engine.
 * Handles the main menu loop and drives patient registration and search,
 * delegating storage and retrieval to PatientRegistry.
 */
public class CliMenu {

    private PatientRegistry patientRegistry;
    private Scanner scanner;

    public CliMenu(PatientRegistry patientRegistry) {
        this.patientRegistry = patientRegistry;
        this.scanner = new Scanner(System.in);
    }

    public void start() {

        boolean running = true;

        while (running) {
            System.out.println("\n--- NHS Patient Demographic Engine ---");
            System.out.println("1. Register new patient");
            System.out.println("2. Search for patient");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");

            String menuChoice = scanner.nextLine();

            switch (menuChoice) {
                case "1":
                    registerPatient();
                    break;
                case "2":
                    searchPatient();
                    break;
                case "3":
                    running = false;
                    System.out.println("Goodbye.");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void registerPatient() {
        boolean registering = true;

        while (registering) {
            System.out.println("\n--- Register New Patient ---");

            System.out.print("\nEnter NHS Number (10 digits): ");
            String nhsInput = scanner.nextLine();

            try {
                NhsNumber nhsNumber = new NhsNumber(nhsInput);


                System.out.print("Enter Surname: ");
                String family = scanner.nextLine();

                System.out.print("Enter First Name: ");
                String given = scanner.nextLine();

                System.out.print("Enter prefix (title e.g. Mr, Mrs, Dr): ");
                String prefix = scanner.nextLine();

                HumanName humanName = new HumanName(family, given, prefix);


                ContactSystem selectedSystem = null;
                String contactValue;
                String systemChoice;

                do {
                    System.out.println("\nPlease select only ONE method of contact");
                    System.out.println("1. Email");
                    System.out.println("2. Phone Number");
                    System.out.print("\nEnter your choice: ");

                    systemChoice = scanner.nextLine();

                    switch (systemChoice) {
                        case "1":
                            selectedSystem = ContactSystem.EMAIL;
                            break;
                        case "2":
                            selectedSystem = ContactSystem.PHONE;
                            break;
                        default:
                            System.out.println("Invalid choice. Please try again.\n");
                            break;
                    }
                } while (!systemChoice.equals("1") && !systemChoice.equals("2"));

                if (selectedSystem == ContactSystem.EMAIL) {
                    System.out.println("Enter email address: ");
                    contactValue = scanner.nextLine();
                } else {
                    System.out.println("Enter phone number: ");
                    contactValue = scanner.nextLine();
                }

                ContactPoint contactPoint = new ContactPoint(selectedSystem, contactValue);


                AdministrativeGender selectedGender = AdministrativeGender.UNKNOWN;
                String genderChoice;

                do {
                    System.out.println("Please select gender below");
                    System.out.println("1. Male");
                    System.out.println("2. Female");
                    System.out.println("3. Other");
                    System.out.println("4. Unknown");
                    System.out.print("\nEnter your choice: ");

                    genderChoice = scanner.nextLine();

                    switch (genderChoice) {
                        case "1":
                            selectedGender = AdministrativeGender.MALE;
                            break;
                        case "2":
                            selectedGender = AdministrativeGender.FEMALE;
                            break;
                        case "3":
                            selectedGender = AdministrativeGender.OTHER;
                            break;
                        case "4":
                            selectedGender = AdministrativeGender.UNKNOWN;
                            break;
                        default:
                            System.out.println("Invalid choice. Please try again.\n");
                            break;
                    }
                } while (!genderChoice.equals("1") && !genderChoice.equals("2") && !genderChoice.equals("3") && !genderChoice.equals("4"));


                LocalDate dateOfBirth = null;
                boolean isValidDate = false;

                while (!isValidDate) {
                    System.out.print("Enter Date of Birth (YYYY-MM-DD): ");
                    String dobInput = scanner.nextLine();
                    try {
                        dateOfBirth = LocalDate.parse(dobInput);
                        isValidDate = true;
                    } catch (DateTimeParseException e) {
                        System.out.println("Invalid date format. Please use YYYY-MM-DD.\n");
                    }
                }


                Patient patient = new Patient(nhsNumber, humanName, contactPoint, selectedGender, dateOfBirth);
                patientRegistry.addPatient(patient);
                System.out.println("Patient registered successfully.");

            } catch (InvalidNHSNumberException e) {
                System.out.println("Error: " + e.getMessage());
            }

            System.out.print("Register another patient? (Y/N): ");
            String again = scanner.nextLine();
            if (!again.equalsIgnoreCase("Y")) {
                registering = false;
            }
        }
    }

    private void searchPatient() {
        boolean searching = true;

        while (searching) {
            System.out.println("\n--- Search for Patient ---");

            System.out.print("\nEnter NHS Number (10 digits): ");
            String nhsInput = scanner.nextLine();

            try {
                NhsNumber nhsNumber = new NhsNumber(nhsInput);
                Patient foundPatient = patientRegistry.findByNhsNumber(nhsNumber);
                if (foundPatient != null){
                    System.out.println(foundPatient);
                    PatientFhirJsonBuilder patientJson = new PatientFhirJsonBuilder(foundPatient);
                    System.out.println(patientJson.buildPatientFhirJson());
                } else {
                    System.out.println("Patient not found.");
                }

            } catch (InvalidNHSNumberException e) {
                System.out.println("Error: " + e.getMessage());
            }

            System.out.print("Search for another patient? (Y/N): ");
            String again = scanner.nextLine();
            if (!again.equalsIgnoreCase("Y")) {
                searching = false;
            }
        }
    }
}
