package com.mycompany.r_p_m_s;

/**
 * Handles emergency alerting based on patient vital signs.
 */
public class EmergencyAlert {

    private final Notifiable notifier;

    public EmergencyAlert(Notifiable notifier) {
        this.notifier = notifier;
    }

    /**
     * Checks vital signs and sends an alert if any readings are critical.
     * 
     * @param patientName Name of the patient
     * @param heartRate Patient's heart rate in BPM
     * @param systolicBP Systolic blood pressure
     * @param diastolicBP Diastolic blood pressure
     * @param email Email to send the alert to
     */
    public void checkVitalsAndAlert(String patientName, int heartRate, int systolicBP, int diastolicBP, String email) {
        StringBuilder alertBuilder = new StringBuilder();

        // Check for abnormal heart rate
        if (heartRate > 120) {
            alertBuilder.append("❗ Heart rate is critically high (")
                        .append(heartRate)
                        .append(" BPM). ");
        }

        // Check for abnormal blood pressure
        if (systolicBP > 180 || diastolicBP > 120) {
            alertBuilder.append("❗ Blood pressure is critically high (")
                        .append(systolicBP)
                        .append("/")
                        .append(diastolicBP)
                        .append(" mmHg). ");
        }

        // Send emergency alert if any critical readings are found
        if (alertBuilder.length() > 0) {
            String finalAlert = "🚨 Emergency Alert for " + patientName + ": " + alertBuilder;
            notifier.sendNotification(finalAlert, email);
        }
    }
}
