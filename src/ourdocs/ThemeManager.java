package ourdocs;

/**
*
* @author benjamin
*/

import javax.swing.*;
import javax.swing.text.JTextComponent;
import javax.swing.text.html.HTMLEditorKit;
import java.awt.*;

public class ThemeManager {
    private boolean dark = false;
    public boolean isDark() { return dark; }
    public void setDark(boolean dark) { this.dark = dark; }

    public void toggle(Component root) {
        dark = !dark;
        apply(root);
    }

    public void apply(Component root) {
        Color bg = dark ? new Color(44,44,44) : new Color(242,242,242);
        Color paneBg = dark ? new Color(24,24,24) : new Color(251,251,251);
        Color fg = dark ? new Color(220,220,220) : new Color(30,30,30);
        applyRecursive(root, bg, paneBg, fg);
        
        // Update previewPane HTML content if it exists in the root
        updatePreviewPaneContent(root);
        
        if (root instanceof Window) ((Window) root).repaint();
    }

    // Method to update previewPane HTML content with theme-specific styles
    private void updatePreviewPaneContent(Component root) {
        if (root instanceof MainJFrame) {
            MainJFrame frame = (MainJFrame) root;
            // This will trigger a preview update with the new theme
            frame.updatePreview();
        }
    }

    private void applyRecursive(Component c, Color bg, Color paneBg, Color fg) {
        if (c == null) return;
        if (c instanceof JMenuBar || c instanceof JMenu || c instanceof JMenuItem) {
            // Skip theming menu components to use system defaults
        } else if (c instanceof JTextComponent) {
            c.setBackground(paneBg); c.setForeground(fg);
            ((JTextComponent)c).setCaretColor(fg);
        } else if (c instanceof JEditorPane) {
            // Special handling for JEditorPane (previewPane)
            c.setBackground(paneBg);
            c.setForeground(fg);
        } else if (c instanceof JComponent) {
            c.setBackground(bg); c.setForeground(fg);
        } else {
            c.setBackground(bg); c.setForeground(fg);
        }
        if (c instanceof Container) {
            for (Component child : ((Container)c).getComponents()) applyRecursive(child, bg, paneBg, fg);
        }
    }
}