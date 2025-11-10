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
public class ApplyCodeBlock {
    public static void apply(JTextArea editor) {
        int start = editor.getSelectionStart();
        int end = editor.getSelectionEnd();
        String selected = editor.getSelectedText();
        
        if (selected != null && !selected.isEmpty()) {
            // Wrap selection with code fences
            String newText = "```\n" + selected + "\n```";
            editor.replaceSelection(newText);
            editor.setSelectionStart(start);
            editor.setSelectionEnd(start + newText.length());
        } else {
            // Insert empty code block
            int pos = editor.getCaretPosition();
            editor.insert("```\n\n```", pos);
            editor.setCaretPosition(pos + 4); // Position inside fences
        }
    }
}
