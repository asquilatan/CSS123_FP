![Glyph](glyph.png)
# Glyph - Markdown Editor

Glyph is a feature-rich Markdown editor application built in Java Swing. It provides a user-friendly interface for creating and editing Markdown documents with live preview functionality.

## Features

### Core Functionality
- **Live Markdown Preview**: See your Markdown content rendered in real-time as you type
- **File Operations**: Create new documents, open existing files, save, and save as
- **Auto-save**: Automatic saving functionality to prevent data loss (saves every minute when a file is open)
- **Dark/Light Theme**: Toggle between dark and light modes for comfortable editing
- **Word Count**: Real-time word count display in the status bar
- **Undo/Redo**: Full undo and redo support for editing operations

### Text Formatting
The application provides a comprehensive toolbar with the following formatting options:
- **Bold** (`**text**`)
- **Italic** (`*text*`)
- **Strikethrough** (`~~text~~`)
- **Inline Code** (`` `code` ``)
- **Blockquotes** (`> quote`)
- **Bullet Lists** (`- item`)
- **Code Blocks** (` ``` code ``` `)
- **Headings** (H1 to H6)
- **Search and Replace**: Find and replace text with support for regex and case sensitivity options

### Inactivity Reminder
- An inactivity timer reminds the user to continue editing if no activity is detected for 5 minutes

## Technical Implementation

### Mouse Events
- **Toolbar Buttons**: Each formatting button (bold, italic, headings, etc.) responds to mouse release events to apply the appropriate Markdown syntax
- **Search Button**: Triggers the SearchReplaceDialog when mouse released
- **Dark Mode Toggle**: Toggles theme when the dark mode button is released
- **Mouse Movement Tracking**: Resets the inactivity timer when the user moves the mouse in the main window

### Menu System
- **File Menu**: New, Open, Save, and Save As operations
- **Edit Menu**: Undo, Redo, Cut, Copy, and Paste functionality
- **Help Menu**: About dialog with application information

### Timer Events
- **Auto-save Timer**: Automatically saves the current document every minute when a file is associated
- **Inactivity Timer**: Shows a reminder dialog after 5 minutes of inactivity, asking if the user is still editing

## Project Structure
`src/ourdocs/`: Main source code files
- **Markdown formatting**: `ApplyBlockquote.java`, `ApplyBold.java`, `ApplyBulletList.java`, `ApplyCodeBlock.java`, `ApplyHeading.java`, `ApplyInlineCode.java`, `ApplyItalic.java`, `ApplyStrikethrough.java`, `MarkdownConverter.java`, `MarkdownHelper.java`
- **Theme management**: `ThemeManager.java`
- **Auto-save functionality**: `AutoSave.java`
- **Search and replace operations**: `SearchReplaceDialog.java`
- **File handling**: `FileNew.java`, `FileOpen.java`, `FileSave.java`
- **Text operations**: `CopyAction.java`, `CutAction.java`, `PasteAction.java`, `UndoAction.java`, `RedoAction.java`, `TextHelper.java`, `LineHelper.java`
- **Word count**: `WordCount.java`

`src/icons/`: Button icons


## Usage Tips
- Use the toolbar buttons for quick formatting
- Toggle between light and dark mode based on your preference
- The live preview shows exactly how your Markdown will render
- The application automatically saves your work to prevent data loss
- Use Ctrl+Z for undo and Ctrl+Y for redo operations
