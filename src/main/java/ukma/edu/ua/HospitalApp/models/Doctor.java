package ukma.edu.ua.HospitalApp.models;
enum DoctorType {
    CARDIOLOGIST,
    THERAPIST,
    ORTHOPEDIST,
    DENTIST,
    COSMETOLOGIST
} 
public class Doctor {
    String firstName;
    String lastName;
    String email;
    int age;
    DoctorType doctorType;
}