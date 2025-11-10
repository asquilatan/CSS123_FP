/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ourdocs;

import javax.swing.JTextArea;

/**
 *
 * @author allen
 */
public class ApplyHeading {
    public static void apply(JTextArea editor, int level) {
        if (level < 1 || level > 6) return;
        String prefix = new String(new char[level]).replace('\0', '#') + " ";
        TextHelper.insertAtLineStart(editor, prefix);
    }
}
