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
public class ApplyStrikethrough {
    public static void apply(JTextArea editor) {
        TextHelper.toggleWrap(editor, "~~", "~~");
    }
}
