package com.explorer;
import javax.swing.*;
import java.awt.*;
import java.net.http.HttpResponse;
import java.util.HashMap;
public class ApiExplorerGUI extends JFrame {
    public ApiExplorerGUI() {
        setTitle("API Explorer");
        setSize(600, 700);
        setLayout(new BorderLayout());
        JTextField urlField = new JTextField("https://");
        JTextArea bodyArea = new JTextArea(10, 50);
        JTextArea responseArea = new JTextArea(20, 50);
        responseArea.setEditable(false);
        JComboBox<String> methodBox = new JComboBox<>(new String[]{"GET", "POST", "PUT", "DELETE", "PATCH"});
        JButton sendButton = new JButton("Enviar");
        sendButton.addActionListener(e -> {
            ApiRequestData data = new ApiRequestData();
            data.url = urlField.getText();
            data.method = methodBox.getSelectedItem().toString();
            data.body = bodyArea.getText();
            data.headers = new HashMap<>();
            try {
                HttpResponse<String> response = ApiRequestExecutor.execute(data);
                responseArea.setText("Status: " + response.statusCode() + "\n\n" + response.body());
                HistoryManager.salvar(data, response);
            } catch (Exception ex) {
                responseArea.setText("Erro: " + ex.getMessage());
            }
        });
        JPanel topPanel = new JPanel(new GridLayout(3, 1));
        topPanel.add(urlField);
        topPanel.add(methodBox);
        topPanel.add(sendButton);
        add(topPanel, BorderLayout.NORTH);
        add(new JScrollPane(bodyArea), BorderLayout.CENTER);
        add(new JScrollPane(responseArea), BorderLayout.SOUTH);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(ApiExplorerGUI::new);
    }
}
