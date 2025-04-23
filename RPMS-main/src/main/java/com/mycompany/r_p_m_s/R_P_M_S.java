package com.mycompany.r_p_m_s;

import java.util.*;

interface Notifiable {
    void sendNotification(String message, String recipient);
}

public class R_P_M_S {

    private static final Scanner scanner = new Scanner(System.in);
    private static final EmailNotification emailNotification = new EmailNotification();
    private static final NotificationService notificationService = new NotificationService();
    private static final ReminderService reminderService = new ReminderService(emailNotification);
    private static final EmergencyAlert emergencyAlert = new EmergencyAlert(emailNotification);
    private static final PanicButton panicButton = new PanicButton(emailNotification);
    private static final ChatClient chatClient = new ChatClient(new ChatServer("19706603801"));

    public static void main(String[] args) {
        while (true) {
            showMenu();
            int choice = getUserChoice();

            switch (choice) {
                case 1 -> sendGeneralEmail();
                case 2 -> sendAppointmentReminder();
                case 3 -> sendMedicationReminder();
                case 4 -> checkVitalsAndAlert();
                case 5 -> triggerPanicAlert();
                case 6 -> startWhatsAppChat();
                case 7 -> startVideoCall();
                case 0 -> exitSystem();
                default -> System.out.println("❌ Invalid choice. Please try again.");
            }
        }
    }

    private static void showMenu() {
        System.out.println("\n======= Remote Patient Monitoring System =======");
        System.out.println("1. Send General Email Notification");
        System.out.println("2. Send Appointment Reminder");
        System.out.println("3. Send Medication Reminder");
        System.out.println("4. Check Vitals & Trigger Emergency Alert");
        System.out.println("5. Trigger Panic Button Alert");
        System.out.println("6. Start WhatsApp Chat");
        System.out.println("7. Start Video Call");
        System.out.println("0. Exit");
        System.out.print("Select an option: ");
    }

    private static int getUserChoice() {
        return Integer.parseInt(scanner.nextLine());
    }

    private static void sendGeneralEmail() {
        System.out.print("Enter recipient email: ");
        String email = scanner.nextLine();
        System.out.print("Enter message: ");
        String message = scanner.nextLine();
        emailNotification.sendNotification(message, email);
    }

    private static void sendAppointmentReminder() {
        System.out.print("Patient name: ");
        String name = scanner.nextLine();
        System.out.print("Appointment date/time: ");
        String appointment = scanner.nextLine();
        System.out.print("Recipient email: ");
        String email = scanner.nextLine();
        reminderService.sendAppointmentReminder(name, appointment, email);
    }

    private static void sendMedicationReminder() {
        System.out.print("Patient name: ");
        String name = scanner.nextLine();
        System.out.print("Medication details: ");
        String medication = scanner.nextLine();
        System.out.print("Recipient email: ");
        String email = scanner.nextLine();
        reminderService.sendMedicationReminder(name, medication, email);
    }

    private static void checkVitalsAndAlert() {
        System.out.print("Patient name: ");
        String name = scanner.nextLine();
        System.out.print("Heart Rate: ");
        int heartRate = Integer.parseInt(scanner.nextLine());
        System.out.print("Systolic BP: ");
        int systolic = Integer.parseInt(scanner.nextLine());
        System.out.print("Diastolic BP: ");
        int diastolic = Integer.parseInt(scanner.nextLine());
        System.out.print("Recipient email: ");
        String email = scanner.nextLine();

        Vitals vitals = new Vitals(heartRate, systolic, diastolic);
        emergencyAlert.checkVitalsAndAlert(name, vitals.getHeartRate(), vitals.getSystolicBP(), vitals.getDiastolicBP(), email);
    }

    private static void triggerPanicAlert() {
        System.out.print("Patient name: ");
        String name = scanner.nextLine();
        System.out.print("Patient ID: ");
        String id = scanner.nextLine();
        System.out.print("Recipient email: ");
        String email = scanner.nextLine();
        panicButton.triggerPanicAlert(name, id, email);
    }

    private static void startWhatsAppChat() {
        System.out.print("Message to send via WhatsApp: ");
        String message = scanner.nextLine();
        chatClient.startChat(message);
    }

    private static void startVideoCall() {
        System.out.print("Enter Google Meet or Zoom link: ");
        String link = scanner.nextLine();
        try {
            VideoCall videoCall = new VideoCall(link);
            videoCall.startCall();
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void exitSystem() {
        System.out.println("👋 Exiting RMPS. Stay safe!");
        scanner.close();
        System.exit(0);
    }
}
