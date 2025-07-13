import java.util.ArrayList;
import java.util.Scanner;

// Model class for storing patient data
class Patient {
    int id;
    String name;
    int age;
    String gender;
    String disease;

    public Patient(int id, String name, int age, String gender, String disease) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.disease = disease;
    }

    public void display() {
        System.out.println("ID: " + id + ", Name: " + name + ", Age: " + age +
                ", Gender: " + gender + ", Disease: " + disease);
    }
}

// Appointment model
class Appointment {
    int patientId;
    String doctorName;
    String appointmentDate;

    public Appointment(int patientId, String doctorName, String appointmentDate) {
        this.patientId = patientId;
        this.doctorName = doctorName;
        this.appointmentDate = appointmentDate;
    }

    public void display() {
        System.out.println("Patient ID: " + patientId + ", Doctor: " + doctorName + ", Date: " + appointmentDate);
    }
}

// Main class
public class HospitalManagementSystem {
    static Scanner sc = new Scanner(System.in);
    static ArrayList<Patient> patients = new ArrayList<>();
    static ArrayList<Appointment> appointments = new ArrayList<>();

    public static void main(String[] args) {
        if (loginReceptionist()) {
            int choice;
            do {
                System.out.println("\n=== Hospital Management Menu ===");
                System.out.println("1. Add Patient Record");
                System.out.println("2. View All Patients");
                System.out.println("3. Book Appointment");
                System.out.println("4. View Appointments");
                System.out.println("5. Exit");
                System.out.print("Enter choice: ");
                choice = sc.nextInt();
                sc.nextLine(); // consume newline

                switch (choice) {
                    case 1 -> addPatient();
                    case 2 -> viewPatients();
                    case 3 -> bookAppointment();
                    case 4 -> viewAppointments();
                    case 5 -> System.out.println("Exiting system. Goodbye!");
                    default -> System.out.println("❌ Invalid choice. Try again.");
                }
            } while (choice != 5);
        }
        sc.close();
    }

    static boolean loginReceptionist() {
        String correctUsername = "reception";
        String correctPassword = "admin123";
        System.out.println("=== Receptionist Login ===");
        System.out.print("Username: ");
        String user = sc.nextLine();
        System.out.print("Password: ");
        String pass = sc.nextLine();

        if (user.equals(correctUsername) && pass.equals(correctPassword)) {
            System.out.println("✅ Login successful!\n");
            return true;
        } else {
            System.out.println("❌ Login failed. Exiting.");
            return false;
        }
    }

    static void addPatient() {
        System.out.print("Enter Patient ID: ");
        int id = sc.nextInt();
        sc.nextLine();

        System.out.print("Enter Name: ");
        String name = sc.nextLine();

        System.out.print("Enter Age: ");
        int age = sc.nextInt();
        sc.nextLine();

        System.out.print("Enter Gender: ");
        String gender = sc.nextLine();

        System.out.print("Enter Disease: ");
        String disease = sc.nextLine();

        patients.add(new Patient(id, name, age, gender, disease));
        System.out.println("✅ Patient added successfully!");
    }

    static void viewPatients() {
        System.out.println("\n--- Patient Records ---");
        if (patients.isEmpty()) {
            System.out.println("No patient records available.");
        } else {
            for (Patient p : patients) {
                p.display();
            }
        }
    }

    static void bookAppointment() {
        System.out.print("Enter Patient ID: ");
        int pid = sc.nextInt();
        sc.nextLine();

        boolean exists = false;
        for (Patient p : patients) {
            if (p.id == pid) {
                exists = true;
                break;
            }
        }

        if (!exists) {
            System.out.println("❌ Patient ID not found. Please add patient first.");
            return;
        }

        System.out.print("Enter Doctor Name: ");
        String doctor = sc.nextLine();

        System.out.print("Enter Appointment Date (e.g., 2025-07-14): ");
        String date = sc.nextLine();

        appointments.add(new Appointment(pid, doctor, date));
        System.out.println("✅ Appointment booked successfully!");
    }

    static void viewAppointments() {
        System.out.println("\n--- Appointment Records ---");
        if (appointments.isEmpty()) {
            System.out.println("No appointments found.");
        } else {
            for (Appointment appt : appointments) {
                appt.display();
            }
        }
    }
}
