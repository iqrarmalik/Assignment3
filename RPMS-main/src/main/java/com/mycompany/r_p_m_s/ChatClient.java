package com.mycompany.r_p_m_s;

import java.awt.Desktop;
import java.net.URI;

/**
 * Handles initiating WhatsApp chats via web links.
 */
public class ChatClient {

    private final ChatServer chatServer;

    public ChatClient(ChatServer chatServer) {
        this.chatServer = chatServer;
    }

    /**
     * Opens the WhatsApp chat link in the default browser.
     * 
     * @param message The message to send via WhatsApp.
     */
    public void startChat(String message) {
        String chatLink = chatServer.generateWhatsAppLink(message);
        System.out.println("🔗 WhatsApp Chat Link: " + chatLink);

        if (Desktop.isDesktopSupported()) {
            try {
                Desktop.getDesktop().browse(new URI(chatLink));
                System.out.println("🌐 Opening WhatsApp in your browser...");
            } catch (Exception e) {
                System.out.println("❌ Failed to open browser. Please use the link manually.");
                e.printStackTrace();
            }
        } else {
            System.out.println("⚠️ Desktop browsing not supported on this system. Please open the link manually.");
        }
    }
}

