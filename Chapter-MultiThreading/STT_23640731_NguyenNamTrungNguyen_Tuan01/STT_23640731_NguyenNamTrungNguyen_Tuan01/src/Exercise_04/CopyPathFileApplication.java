package Exercise_04;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.SwingUtilities;

public class CopyPathFileApplication extends JFrame {

    private JTextField txtFrom;
    private JTextField txtTo;
    private JButton btnCopy;
    private JProgressBar progressBar;

    private File sourceFile;
    private File destFile;

    public CopyPathFileApplication() {
        super("Copy File");
        setSize(450, 260);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(5, 1, 5, 5));

        panel.add(new JLabel("From (Vui long Click vao vung nhap de chon File can copy)"));
        txtFrom = new JTextField();
        panel.add(txtFrom);

        panel.add(new JLabel("To (Vui long Click vao vung nhap de chon noi Path File)"));
        txtTo = new JTextField();
        panel.add(txtTo);

        progressBar = new JProgressBar(0, 100);
        progressBar.setStringPainted(true);

        add(panel, BorderLayout.CENTER);
        add(progressBar, BorderLayout.SOUTH);
        JPanel buttonPanel = new JPanel();
        btnCopy = new JButton("Copy");
        btnCopy.setPreferredSize(new Dimension(80, 30));

        buttonPanel.add(btnCopy);
        panel.add(buttonPanel);
        txtFrom.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JFileChooser fc = new JFileChooser();
                if (fc.showOpenDialog(CopyPathFileApplication.this) == JFileChooser.APPROVE_OPTION) {
                    sourceFile = fc.getSelectedFile();
                    txtFrom.setText(sourceFile.getAbsolutePath());
                }
            }
        });

        txtTo.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JFileChooser fc = new JFileChooser();
                fc.setDialogTitle("Save As");
                if (fc.showSaveDialog(CopyPathFileApplication.this) == JFileChooser.APPROVE_OPTION) {
                    destFile = fc.getSelectedFile();
                    txtTo.setText(destFile.getAbsolutePath());
                }
            }
        });

        // Copy
        btnCopy.addActionListener(e -> startCopy());

        setVisible(true);
    }

    private void startCopy() {
        if (sourceFile == null || destFile == null) {
            JOptionPane.showMessageDialog(this, "Vui long chon File can Copy va noi Path!");
            return;
        }

        progressBar.setValue(0);

        CopyUsingSW task = new CopyUsingSW(sourceFile, destFile);
        task.addPropertyChangeListener(evt -> {
            if ("progress".equals(evt.getPropertyName())) {
                progressBar.setValue((int) evt.getNewValue());
            }
        });
        task.execute();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new CopyPathFileApplication());
    }
}
