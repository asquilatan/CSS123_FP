package ourdocs;
 
/**
*
* @author benjamin
*/
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.regex.*;
 
/**
* Simple find/replace for a JTextArea
*/
public class SearchReplaceDialog extends JDialog {
    private final JTextArea textArea;
    private final JTextField searchField = new JTextField(20);
    private final JTextField replaceField = new JTextField(20);
    private final JCheckBox caseBox = new JCheckBox("Match case");
    private final JCheckBox regexBox = new JCheckBox("Regex");
    private final JButton findNextBtn = new JButton("Find Next");
    private final JButton replaceBtn = new JButton("Replace");
    private final JButton replaceAllBtn = new JButton("Replace All");
    private final JButton closeBtn = new JButton("Close");
 
    public SearchReplaceDialog(Frame owner, JTextArea textArea) {
        super(owner, "Find / Replace", false);
        this.textArea = textArea;
        initUI();
        pack();
        setResizable(false);
        setLocationRelativeTo(owner);
    }
 
    private void initUI() {
        JPanel p = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(6, 6, 6, 6);
        c.gridx = 0; c.gridy = 0; c.anchor = GridBagConstraints.EAST;
        p.add(new JLabel("Find:"), c);
        c.gridx = 1; c.anchor = GridBagConstraints.WEST;
        p.add(searchField, c);
 
        c.gridx = 0; c.gridy = 1; c.anchor = GridBagConstraints.EAST;
        p.add(new JLabel("Replace:"), c);
        c.gridx = 1; c.anchor = GridBagConstraints.WEST;
        p.add(replaceField, c);
 
        c.gridx = 0; c.gridy = 2; c.gridwidth = 2;
        JPanel options = new JPanel(new FlowLayout(FlowLayout.LEFT, 6, 0));
        options.add(caseBox);
        options.add(regexBox);
        p.add(options, c);
 
        c.gridy = 3; c.gridwidth = 2;
        JPanel buttons = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttons.add(findNextBtn);
        buttons.add(replaceBtn);
        buttons.add(replaceAllBtn);
        buttons.add(closeBtn);
        p.add(buttons, c);
 
        getContentPane().add(p);
 
        // Actions
        findNextBtn.addActionListener(e -> findNext());
        replaceBtn.addActionListener(e -> replace());
        replaceAllBtn.addActionListener(e -> replaceAll());
        closeBtn.addActionListener(e -> setVisible(false));
 
        // Enter triggers Find
        searchField.addActionListener(e -> findNext());
 
        // ESC closes
        getRootPane().registerKeyboardAction(ev -> setVisible(false),
            KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0),
            JComponent.WHEN_IN_FOCUSED_WINDOW);
    }
 
    private void findNext() {
        String pattern = searchField.getText();
        if (pattern.isEmpty()) return;
 
        String text = textArea.getText();
        int startPos = Math.max(0, textArea.getSelectionEnd());
 
        if (regexBox.isSelected()) {
            int flags = caseBox.isSelected() ? 0 : Pattern.CASE_INSENSITIVE;
            try {
                Pattern p = Pattern.compile(pattern, flags | Pattern.UNICODE_CASE);
                Matcher m = p.matcher(text);
                if (m.find(startPos)) {
                    selectRange(m.start(), m.end());
                } else if (m.find(0)) { // wrap
                    selectRange(m.start(), m.end());
                } else {
                    JOptionPane.showMessageDialog(this, "Not found");
                }
            } catch (PatternSyntaxException ex) {
                JOptionPane.showMessageDialog(this, "Invalid regex: " + ex.getDescription());
            }
        } else {
            String hay = caseBox.isSelected() ? text : text.toLowerCase();
            String needle = caseBox.isSelected() ? pattern : pattern.toLowerCase();
            int idx = hay.indexOf(needle, startPos);
            if (idx < 0) idx = hay.indexOf(needle, 0); // wrap
            if (idx >= 0) selectRange(idx, idx + pattern.length());
            else JOptionPane.showMessageDialog(this, "Not found");
        }
    }
 
    private void replace() {
        int selStart = textArea.getSelectionStart();
        int selEnd = textArea.getSelectionEnd();
        if (selStart == selEnd) {
            // No selection -> find next then replace selection
            findNext();
            selStart = textArea.getSelectionStart();
            selEnd = textArea.getSelectionEnd();
            if (selStart == selEnd) return; // nothing to replace
        }
 
        String replacement = replaceField.getText();
        textArea.replaceRange(replacement, selStart, selEnd);
        textArea.select(selStart, selStart + replacement.length());
    }
 
    private void replaceAll() {
        String search = searchField.getText();
        if (search.isEmpty()) return;
 
        String text = textArea.getText();
        if (regexBox.isSelected()) {
            int flags = caseBox.isSelected() ? 0 : Pattern.CASE_INSENSITIVE;
            try {
                Pattern p = Pattern.compile(search, flags | Pattern.UNICODE_CASE);
                Matcher m = p.matcher(text);
                String repl = replaceField.getText();
                String result = m.replaceAll(repl);
                textArea.setText(result);
            } catch (PatternSyntaxException ex) {
                JOptionPane.showMessageDialog(this, "Invalid regex: " + ex.getDescription());
            }
        } else {
            if (caseBox.isSelected()) {
                textArea.setText(text.replace(search, replaceField.getText()));
            } else {
                // case-insensitive literal replace
                String quoted = Pattern.quote(search);
                Pattern p = Pattern.compile(quoted, Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE);
                Matcher m = p.matcher(text);
                String result = m.replaceAll(Matcher.quoteReplacement(replaceField.getText()));
                textArea.setText(result);
            }
        }
    }
 
    private void selectRange(int start, int end) {
        textArea.requestFocusInWindow();
        textArea.select(start, end);
        try {
            Rectangle r = textArea.modelToView(start);
            if (r != null) textArea.scrollRectToVisible(r);
        } catch (Exception ignored) {}
    }
}