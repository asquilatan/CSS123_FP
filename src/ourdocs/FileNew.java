/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ourdocs;

import java.io.File;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

/**
 *
 * @author allen
 */
public class FileNew {
    public static File createNew(JFrame parent, JTextArea editor, File currentFile) {
        String text = editor.getText();
        if (text != null && !text.isEmpty()) {
            int choice = JOptionPane.showConfirmDialog(parent,
                    "Save changes to the current document?",
                    "Save changes",
                    JOptionPane.YES_NO_CANCEL_OPTION,
                    JOptionPane.QUESTION_MESSAGE);

            if (choice == JOptionPane.CANCEL_OPTION || choice == JOptionPane.CLOSED_OPTION) {
                return currentFile;
            } else if (choice == JOptionPane.YES_OPTION) {
                File saved = FileSave.save(parent, editor, currentFile);
                if (saved == null) {
                    return currentFile;
                }
            }
        }

        editor.setText("");
        return null;
    }
}
