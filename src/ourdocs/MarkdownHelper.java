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
class MarkdownHelper {

//    apparently, pushing bad code on gh is illegal or smth
//    - allen
    
//    Per Character features
    public static void applyBold(JTextArea editor) {
//        ApplyBold.apply(editor);
    }
    
    public static void applyItalic(JTextArea editor) {
//        ApplyItalic.apply(editor);
    }
    
    public static void applyStrikethrough(JTextArea editor) {
//        ApplyStrikethrough.apply(editor);
    }
    
    public static void applyInlineCode(JTextArea editor) {
//        ApplyInlineCode.apply(editor);
    }
    
//    Per Line features (like only 1 '>' is needed and the whole line changes)
    public static void applyBlockquote(JTextArea editor) {
//        ApplyBlockquote.apply(editor);
    }
    
    public static void applyBulletList(JTextArea editor) {
//        ApplyBulletList.apply(editor);
    }
    
    public static void applyCodeBlock(JTextArea editor) {
//        ApplyCodeBlock.apply(editor);
    }
    
//    Headings
    public static void applyHeading1(JTextArea editor) {
//        ApplyHeading.apply(editor, 1);
    }
    
    public static void applyHeading2(JTextArea editor) {
//        ApplyHeading.apply(editor, 2);
    }
    
    public static void applyHeading3(JTextArea editor) {
//        ApplyHeading.apply(editor, 3);
    }
    
    public static void applyHeading4(JTextArea editor) {
//        ApplyHeading.apply(editor, 4);
    }
    
    public static void applyHeading5(JTextArea editor) {
//        ApplyHeading.apply(editor, 5);
    }
    
    public static void applyHeading6(JTextArea editor) {
//        ApplyHeading.apply(editor, 6);
    }
    
//     Preview conversion
    public static String convertToHtml(String markdown) {
        return MarkdownConverter.convert(markdown);
    }
}
