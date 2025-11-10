/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ourdocs;

/**
 * 
 * @author allen
 * 
 */

public class MarkdownConverter {
    /**
     * Converts a markdown string to HTML equivalent for previewPane
     * 
     * @param markdown Takes in a string of markdown text
     * @return Returns an HTML-equivalent string
     */
    public static String convert(String markdown) {
//        if there's nothing, have the string be an html body with no content
        if (markdown == null || markdown.isEmpty()) 
            return "<html><body style='margin:8px;font-family:sans-serif'></body></html>";
        
//         process Escape HTML special characters FIRST
        String safe = markdown
            .replace("&", "&amp;")
            .replace("<", "<")
            .replace(">", ">")
            .replace("\"", "&quot;")
            .replace("'", "&#x27;");
        
//         Apply conversions (complex first, so *** makes <b><i>)
        safe = safe.replaceAll("\\*\\*\\*(.*?)\\*\\*\\*", "<b><i>$1</i></b>");
        safe = safe.replaceAll("\\*\\*(.*?)\\*\\*", "<b>$1</b>");
        safe = safe.replaceAll("\\*(.*?)\\*", "<i>$1</i>");
        safe = safe.replaceAll("~~(.*?)~~", "<s>$1</s>");
        safe = safe.replaceAll("(?s)```(.*?)```", "<pre style='background:#f0f0f0;padding:10px;border-radius:5px'>$1</pre>");
        safe = safe.replaceAll("`(.*?)`", "<code style='background:#f0f0f0;padding:2px;border-radius:3px'>$1</code>");
        
//         Headers and Line-based elements
        safe = safe.replaceAll("(?m)^# (.*?)$", "<h1>$1</h1>");
        safe = safe.replaceAll("(?m)^## (.*?)$", "<h2>$1</h2>");
        safe = safe.replaceAll("(?m)^### (.*?)$", "<h3>$1</h3>");
        safe = safe.replaceAll("(?m)^#### (.*?)$", "<h4>$1</h4>");
        safe = safe.replaceAll("(?m)^##### (.*?)$", "<h5>$1</h5>");
        safe = safe.replaceAll("(?m)^###### (.*?)$", "<h6>$1</h6>");
        safe = safe.replaceAll("(?m)^> (.*)$", "<blockquote style='border-left:3px solid #ccc;padding-left:10px'>$1</blockquote>");
        safe = safe.replaceAll("(?m)^- (.*)$", "<ul style='padding-left:20px'><li>$1</li></ul>");
        
//         Final formatting
//         if it's a newline char, replace with br
        safe = safe.replace("\n", "<br>");
        
//        encapsulate with html body, and return as a string
        return "<html><body style='margin:8px;font-family:sans-serif;color:#333;line-height:1.5'>" 
               + safe + "</body></html>";
    }
}
