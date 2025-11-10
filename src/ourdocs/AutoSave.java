/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ourdocs;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.function.Supplier;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

/**
 *
 * @author allen
 */
public class AutoSave {
    
    public static Timer startAutoSave(JFrame parentFrame, JTextArea editor, Supplier<File> currentFileSupplier, JLabel statusLabel, int intervalMillis) {
        ActionListener task = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                File f = currentFileSupplier.get();
                if (f == null) {
                    return;
                }
                try {
                    Files.writeString(f.toPath(), editor.getText(), StandardCharsets.UTF_8);
                    
                    // Update status label to show last updated time and filename
                    String fileName = f.getName();
                    String currentTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
                    String statusText = "Last updated on " + currentTime + " (" + fileName + ")";
                    
                    SwingUtilities.invokeLater(() -> {
                        statusLabel.setText(statusText);
                    });
                } catch (IOException ex) {
                    SwingUtilities.invokeLater(() -> {
                        JOptionPane.showMessageDialog(parentFrame,
                                "Auto-save failed:\n" + ex.getMessage(),
                                "Auto-save Error",
                                JOptionPane.ERROR_MESSAGE);
                    });
                }
            }
        };

        Timer timer = new Timer(intervalMillis, task);
        timer.setCoalesce(true);
        timer.start();
        return timer;
    }
}
