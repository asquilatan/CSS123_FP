/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ourdocs;

import javax.swing.JTextArea;
import javax.swing.undo.UndoManager;
import javax.swing.undo.CannotRedoException;

/**
 *
 * @author sobre
 */
public class RedoAction {
    public static void apply(JTextArea editor, UndoManager undoManager) {
        try {
            if (undoManager.canRedo()) {
                undoManager.redo();
                // Update the menu state to reflect current undo/redo availability
                if (editor.getTopLevelAncestor() instanceof MainJFrame) {
                    MainJFrame frame = (MainJFrame) editor.getTopLevelAncestor();
                    frame.updateMenuState(); // This will update both undo and redo menu items
                }
            }
        } catch (CannotRedoException ex) {
            System.out.println("Nothing to redo.");
        }
    }
}
