import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ChatClientGUI {
    private JFrame frame = new JFrame("Chat Room");
    private JTextArea messageArea = new JTextArea(20, 50);
    private JTextField textField = new JTextField(40);
    private ChatClient client;

    public ChatClientGUI(String server, int port) {
        try {
            client = new ChatClient(server, port);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(frame, "Cannot connect to server", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        messageArea.setEditable(false);
        frame.getContentPane().add(new JScrollPane(messageArea), BorderLayout.CENTER);
        frame.getContentPane().add(textField, BorderLayout.SOUTH);
        frame.pack();

        textField.addActionListener(e -> {
            client.sendMessage(textField.getText());
            textField.setText("");
        });

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        new Thread(() -> {
            try {
                String message;
                while ((message = client.getReader().readLine()) != null) {
                    messageArea.append(message + "\n");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ChatClientGUI("localhost", 12345));
    }
}
