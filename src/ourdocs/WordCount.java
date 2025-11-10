package ourdocs;
 
/**
*
* @author benjamin
*/
import java.util.regex.*;
 
public class WordCount {
 
    /**
     * This returns the number of words in the given markdown text,
     * while ignoring markdown syntax.
     *
     * @param markdownText the raw markdown text
     * @return the count of actual words
     */
    public static int countWords(String markdownText) {
        if (markdownText == null || markdownText.isEmpty()) {
            return 0;
        }
 
        // remove code blocks 
        String cleaned = markdownText.replaceAll("```[\\s\\S]*?```", " ");
 
        // remove inline code 
        cleaned = cleaned.replaceAll("`[^`]*`", " ");
 
        // remove markdown symbols 
        cleaned = cleaned.replaceAll("[#*>~`\\-\\*\\_]+", " ");
 
        // remove markdown link/image syntax 
        cleaned = cleaned.replaceAll("\\!?\\[[^\\]]*\\]\\([^\\)]*\\)", " ");
 
        // remove extra whitespace
        cleaned = cleaned.replaceAll("\\s+", " ").trim();
 
        if (cleaned.isEmpty()) {
            return 0;
        }
 
        // count words (sequences of letters/numbers)
        Matcher matcher = Pattern.compile("\\b[\\p{L}\\p{N}]+\\b").matcher(cleaned);
        int count = 0;
        while (matcher.find()) {
            count++;
        }
 
        return count;
    }
}