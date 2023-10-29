import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FolderLockeer extends JFrame {
    private JButton lockButton;
    private JButton unlockButton;
    private JTextField folderPathField;

    public FolderLockeer() {
        setTitle("Folder Locker");
        setSize(400, 150);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        folderPathField = new JTextField(30);
        lockButton = new JButton("Lock Folder");
        unlockButton = new JButton("Unlock Folder");

        lockButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                lockFolder(folderPathField.getText());
            }
        });

        unlockButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                unlockFolder(folderPathField.getText());
            }
        });

        add(new JLabel("Folder Path: "));
        add(folderPathField);
        add(lockButton);
        add(unlockButton);
    }

    public void lockFolder(String folderPath) {
        File folder = new File(folderPath);
        if (folder.exists()) {
            String lockedFolderPath = folderPath + ".locked";

            if (folder.renameTo(new File(lockedFolderPath))) {
                JOptionPane.showMessageDialog(this, "Folder locked successfully.");
            } else {
                JOptionPane.showMessageDialog(this, "Unable to lock the folder.");
            }
        } else {
            JOptionPane.showMessageDialog(this, "The specified folder does not exist.");
        }
    }

    public void unlockFolder(String lockedFolderPath) {
        File lockedFolder = new File(lockedFolderPath);
        if (lockedFolder.exists()) {
            String originalFolderPath = lockedFolderPath.replace(".locked", "");

            if (lockedFolder.renameTo(new File(originalFolderPath))) {
                JOptionPane.showMessageDialog(this, "Folder unlocked successfully.");
            } else {
                JOptionPane.showMessageDialog(this, "Unable to unlock the folder.");
            }
        } else {
            JOptionPane.showMessageDialog(this, "The specified locked folder does not exist.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new FolderLockeer().setVisible(true);
            }
        });
    }
}
