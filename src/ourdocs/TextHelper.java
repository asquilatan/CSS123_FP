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
public class TextHelper {
    /**
     * Wraps selected text in JTextArea with a given prefix and suffix.
     * If no text is selected, it inserts the prefix and suffix at the caret position
     * and places the caret in between them.
     * 
     * 
     * @param editor The JTextArea editor
     * @param prefix The text to insert before the selection/caret
     * @param suffix The text to insert after the selection/caret
     */
    public static void wrapText(JTextArea editor, String prefix, String suffix) {
        
//        Get the start and end positions of selected text
        int start = editor.getSelectionStart();
        int end = editor.getSelectionEnd();
        String selected = editor.getSelectedText();
        
//         If text is selected
        if (selected != null && !selected.isEmpty()) {
//            Wrap the selected text with prefix and suffix
            String newText = prefix + selected + suffix;
            editor.replaceSelection(newText);
//            Select the newly wrapped text (including prefix and suffix)
            editor.setSelectionStart(start);
            editor.setSelectionEnd(start + newText.length());
        } else {
//            IF no text is selected, insert prefix and suffix at caret position
            int pos = editor.getCaretPosition();
            editor.insert(prefix + suffix, pos);
//            Place caret between prefix and suffix
            editor.setCaretPosition(pos + prefix.length());
        }
    }
    
    /**
     * Toggles text wrapping. adds wrapping if not present, removes if already wrapped.
     * 
     * @param editor The JTextArea editor
     * @param prefix The prefix to add/remove
     * @param suffix The suffix to add/remove
     * @return true if wrapping was removed, false if wrapping was added
     */
    public static boolean toggleWrap(JTextArea editor, String prefix, String suffix) {
        String selected = editor.getSelectedText();
        
//        If no text is selected, simply wrap at caret position
        if (selected == null || selected.isEmpty()) {
            wrapText(editor, prefix, suffix);
            return false;
        }
        
//        If selected text is already wrapped with prefix and suffix
        if (selected.startsWith(prefix) && selected.endsWith(suffix)) {
//            Remove the wrapping by getting the inner text
            String unwrapped = selected.substring(prefix.length(), selected.length() - suffix.length());
//            Select the unwrapped text
            editor.replaceSelection(unwrapped);
            editor.setSelectionStart(editor.getSelectionStart());
            editor.setSelectionEnd(editor.getSelectionEnd());
//            (wrapping was removed)
            return true;
        }
        
//        Text is selected but not wrapped. add wrapping
        wrapText(editor, prefix, suffix);
//        (wrapping was added)
        return false;
    }
    
    public static void insertAtLineStart(JTextArea editor, String prefix) {
    try {
        int caretPos = editor.getCaretPosition();
        int line = editor.getLineOfOffset(caretPos);
        int lineStart = editor.getLineStartOffset(line);
        
//          Check if line already starts with this prefix
        String lineText = editor.getText(lineStart, 
            Math.min(editor.getLineEndOffset(line) - lineStart, 100));
        
        if (lineText.startsWith(prefix)) {
//          Remove prefix
            editor.replaceRange("", lineStart, lineStart + prefix.length());
        } else {
//          Add prefix
            editor.insert(prefix, lineStart);
        }
    } catch (Exception ex) {
//          Fallback: append to end
        editor.append("\n" + prefix);
    }
    }
}

