/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ourdocs;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author allen
 */
public class FileOpen {
    public static File open(JFrame parent, JTextArea editor) {
        JFileChooser chooser = new JFileChooser();
        chooser.setFileFilter(new FileNameExtensionFilter("Text files",  "md"));
        int result = chooser.showOpenDialog(parent);
        if (result != JFileChooser.APPROVE_OPTION) {
            return null;
        }

        File chosen = chooser.getSelectedFile();
        try {
            String content = Files.readString(chosen.toPath(), StandardCharsets.UTF_8);
            editor.setText(content);
            editor.setCaretPosition(0);
            return chosen;
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(parent,
                    "Failed to open file:\n" + ex.getMessage(),
                    "Open Error",
                    JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }

}
