package uk.ac.ucl.entities;

import uk.ac.ucl.enums.Gender;

import java.time.LocalDate;
import java.util.UUID;

public class Patient {

    private UUID id;
    private Gender gender;
    private LocalDate birthDate;
    private LocalDate deathDate;
    private PatientName patientName;
    private Identification identification;
    private PatientDetails patientDetails;
    private Address address;

    public Patient() {
    }

    public Patient(UUID id, Gender gender, LocalDate birthDate, LocalDate deathDate, PatientName patientName, Identification identification, PatientDetails patientDetails, Address address) {
        this.id = id;
        this.gender = gender;
        this.birthDate = birthDate;
        this.deathDate = deathDate;
        this.patientName = patientName;
        this.identification = identification;
        this.patientDetails = patientDetails;
        this.address = address;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public LocalDate getDeathDate() {
        return deathDate;
    }

    public void setDeathDate(LocalDate deathDate) {
        this.deathDate = deathDate;
    }

    public PatientName getPatientName() {
        return patientName;
    }

    public void setPatientName(PatientName patientName) {
        this.patientName = patientName;
    }

    public Identification getIdentification() {
        return identification;
    }

    public void setIdentification(Identification identification) {
        this.identification = identification;
    }

    public PatientDetails getPatientDetails() {
        return patientDetails;
    }

    public void setPatientDetails(PatientDetails patientDetails) {
        this.patientDetails = patientDetails;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return patientName.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Patient) {
           Patient other = (Patient)obj;
           return id.equals(other.id) && patientName.equals(other.patientName) && identification.equals(other.identification);
        } else {
            return false;
        }
    }
}
