package uk.ac.ucl;

import uk.ac.ucl.daos.PatientDao;
import uk.ac.ucl.entities.Patient;
import uk.ac.ucl.io.JsonFileHandler;
import uk.ac.ucl.io.ReadCSV;
import uk.ac.ucl.json.JSONFormatter;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

/**
 * • Has a readFile method that uses the ReadCSV class to read an input file and generate the
 * Patient objects.
 * • Holds the list of Patient objects once they have been generated by the ReadCSV class.
 * • Provides getter methods to access the patient data, for example, get a single JSON
 * structure for all patients, get a JSON structure for a single patient, get the names
 * or ids for all patients, and so on. Add these getter methods as you need them,
 * including others that you find you need. Don’t try to write them all first, only when
 * you actually need a method!
 */
public class Model {

    private ReadCSV readCSV;
    private JSONFormatter jsonFormatter;
    private List<Patient> patients;
    private JsonFileHandler jsonFileHandler;
    private PatientDao patientDao;

    public Model() {
        readCSV = new ReadCSV();
        jsonFormatter = new JSONFormatter();
        patients = Collections.emptyList();
        jsonFileHandler = new JsonFileHandler();
        patientDao = new PatientDao();
    }

    public List<Patient> readFile(File file) throws IOException {
        patients = readCSV.getPatients(file);
        return patients;
    }

    public List<Patient> filterPatients(String name, String address) {
        return patients.stream()
                .filter(patient -> patient.getPatientName().toString().toLowerCase().contains(name.toLowerCase()))
                .filter(patient -> patient.getAddress().toString().toLowerCase().contains(address.toLowerCase()))
                .collect(Collectors.toList());
    }

    public String getPatients() {
        return jsonFormatter.serializePatients(patients);
    }

    public String getPatient(UUID uuid) {
        Optional<Patient> optionalPatient = patients.stream()
                .filter(patient -> patient.getId().equals(uuid))
                .findFirst();

        return jsonFormatter.serializePatient(optionalPatient.get());
    }

    public String savePatientsJSON(String filename) throws IOException {
        return jsonFileHandler.write(patients, filename);
    }

    public List<Patient> loadPatientsJSON(File file) throws IOException {
        patients = jsonFileHandler.read(file);
        return patients;
    }

    public void savePatientsDB() throws SQLException, ClassNotFoundException {
        patientDao.savePatients(patients);
    }

    public List<Patient> loadPatientsFromDB() throws SQLException, ClassNotFoundException {
        patients = patientDao.getPatients();
        return patients;
    }

    public int getAverageAge() {
        int ageTotal = 0;
        for(Patient patient: patients) {
            ageTotal++;
        }
        return ageTotal/patients.size();
    }

    public String getCommonBirthDate() {
        Map<LocalDate, Integer> map = new HashMap<>();
        for(Patient patient : patients) {
            map.computeIfPresent(patient.getBirthDate(), (key, value) -> value++);
            map.putIfAbsent(patient.getBirthDate(), 0);
        }

        Map.Entry<LocalDate, Integer> commonDate = getMostPopularEntry(map);
        return commonDate == null ? null : commonDate.getKey().toString();
    }

    public String getCommonCity() {
        Map<String, Integer> cityToPopularity = new HashMap<>();
        for(Patient patient : patients) {
            String city = patient.getAddress().getCity();
            cityToPopularity.computeIfPresent(city, (key, value) -> value++);
            cityToPopularity.putIfAbsent(city, 0);
        }

        Map.Entry<String, Integer> mostCommonCityEntry = getMostPopularEntry(cityToPopularity);
        return mostCommonCityEntry == null ? null : mostCommonCityEntry.getKey().toString();
    }

    private <T> Map.Entry<T, Integer> getMostPopularEntry(Map<T, Integer> map) {
        Map.Entry<T, Integer> mostCommonEntry = null;
        for (Map.Entry<T, Integer> entry : map.entrySet()) {
            if (mostCommonEntry != null && entry.getValue() > mostCommonEntry.getValue()) {
                mostCommonEntry = entry;
            } else {
                mostCommonEntry = entry;
            }
        }
        return mostCommonEntry;
    }

}
