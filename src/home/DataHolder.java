package home;

import org.json.simple.JSONArray;

public class DataHolder {

    private static HttpRequestSender sender;
    private static JSONHandler handler;

    public static JSONArray diseases;
    public static JSONArray specializations;
    public static JSONArray doctors;
    public static JSONArray patients;
    public static JSONArray patientDiseases;
    public static JSONArray medicines;
    public static JSONArray insurances;
    public static JSONArray hypersensitivities;
    public static JSONArray prescriptions;
    public static JSONArray referrals;
    public static JSONArray surveys;
    public static JSONArray visits;


    public static void initializeData() {

        sender = new HttpRequestSender();
        handler = new JSONHandler();

        diseases = loadTable("Diseases");
        specializations = loadTable("Specializations");
        doctors = loadTable("Doctors");
        patients = loadTable("Patients");
        patientDiseases = loadTable("PatientDiseases");
        medicines = loadTable("Medicines");
        insurances = loadTable("Insurances");
        hypersensitivities = loadTable("Hypersensitivities");
        prescriptions = loadTable("Prescriptions");
        referrals = loadTable("Referrals");
        surveys = loadTable("Surveys");
        visits = loadTable("Visits");
    }


    public static JSONArray loadTable(String tableName) {

        return handler
                .getJSONArrayFrom(sender.getResponseFromServer(
                        "http://localhost:8083/medapp/get" + tableName
                ));
    }
}
