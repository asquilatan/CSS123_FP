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
public class LineHelper {
    
    /**
     * Gets the start position of the line containing the given position
     * 
     * @param editor The JTextArea editor
     * @param position The position in the text
     * @return The start offset of the line containing the position
     * @throws Exception If the position is invalid
     */
    public static int getLineStart(JTextArea editor, int position) throws Exception {
        return editor.getLineStartOffset(editor.getLineOfOffset(position));
    }

    /**
     * Gets the end position of the line containing the given position
     * 
     * @param editor The JTextArea editor
     * @param position The position in the text
     * @return The end offset of the line containing the position
     * @throws Exception If the position is invalid
     */
    public static int getLineEnd(JTextArea editor, int position) throws Exception {
        return editor.getLineEndOffset(editor.getLineOfOffset(position));
    }

    /**
     * Toggles a prefix on all selected lines. adds prefix to lines that don't have it,
     * removes prefix from lines that do have it.
     * 
     * @param editor The JTextArea editor
     * @param prefix The prefix to toggle on/off
     * @return true if prefixes were removed, false if prefixes were added
     */
    public static boolean toggleLinePrefix(JTextArea editor, String prefix) {
        try {
//             Get the line numbers of the selection start and end
            int startLine = editor.getLineOfOffset(editor.getSelectionStart());
            int endLine = editor.getLineOfOffset(editor.getSelectionEnd());
            boolean allHavePrefix = true;
            
//             First pass: Check if all selected lines already have the prefix
            for (int line = startLine; line <= endLine; line++) {
                int lineStart = getLineStart(editor, editor.getLineStartOffset(line));
//                 Get the text of the current line and trim whitespace
                String lineText = editor.getText(lineStart, 
                    getLineEnd(editor, lineStart) - lineStart).trim();
                
//                 If any line doesn't start with the prefix, not all lines have it
                if (!lineText.startsWith(prefix)) {
                    allHavePrefix = false;
                    break;
                }
            }
            
//             Second pass: Apply the appropriate action to each line
            for (int line = startLine; line <= endLine; line++) {
                int lineStart = getLineStart(editor, editor.getLineStartOffset(line));
                int lineEnd = getLineEnd(editor, lineStart);
                String lineText = editor.getText(lineStart, lineEnd - lineStart).trim();
                
                if (allHavePrefix) {
//                     Remove prefix from lines that have it
                    if (lineText.startsWith(prefix)) {
                        editor.replaceRange(lineText.substring(prefix.length()), 
                            lineStart, lineStart + lineText.length());
                    }
                } else {
//                     Add prefix to lines that don't have it
                    if (!lineText.startsWith(prefix)) {
                        editor.insert(prefix, lineStart);
                    }
                }
            }
            
//             Return true if prefixes were removed, false if prefixes were added
            return allHavePrefix;
        } catch (Exception err) {
//             Return false if any error occurs during the operation
            return false;
        }
    }
}
