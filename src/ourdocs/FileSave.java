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
public class FileSave {
    public static File save(JFrame parent, JTextArea editor, File currentFile) {
        if (currentFile == null) {
            return saveAs(parent, editor);
        }
        try {
            Files.writeString(currentFile.toPath(), editor.getText(), StandardCharsets.UTF_8);
            return currentFile;
        } catch (IOException ex) {
            int retry = JOptionPane.showConfirmDialog(parent,
                    "Failed to save file:\n" + ex.getMessage() + "\n\nTry Save As?",
                    "Save Error",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.ERROR_MESSAGE);
            if (retry == JOptionPane.YES_OPTION) {
                return saveAs(parent, editor);
            } else {
                return null;
            }
        }
    }

    public static File saveAs(JFrame parent, JTextArea editor) {
        JFileChooser chooser = new JFileChooser();
        chooser.setFileFilter(new FileNameExtensionFilter("Markdown files", "md"));
        int result = chooser.showSaveDialog(parent);
        if (result != JFileChooser.APPROVE_OPTION) {
            return null;
        }

        File chosen = chooser.getSelectedFile();
        // Ensure the file has .md extension
        if (!chosen.getName().toLowerCase().endsWith(".md")) {
            chosen = new File(chosen.getParentFile(), chosen.getName() + ".md");
        }
        
        try {
            Files.writeString(chosen.toPath(), editor.getText(), StandardCharsets.UTF_8);
            return chosen;
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(parent,
                    "Failed to save file:\n" + ex.getMessage(),
                    "Save Error",
                    JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }
}
