/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ourdocs;

import javax.swing.JTextArea;
import javax.swing.undo.UndoManager;
import javax.swing.undo.CannotUndoException;

/**
 *
 * @author sobre
 */
public class UndoAction {
     public static void apply(JTextArea editor, UndoManager undoManager) {
        try {
            if (undoManager.canUndo()) {
                undoManager.undo();
            }
        } catch (CannotUndoException ex) {
            System.out.println("Nothing to undo.");
        }
    }
}
