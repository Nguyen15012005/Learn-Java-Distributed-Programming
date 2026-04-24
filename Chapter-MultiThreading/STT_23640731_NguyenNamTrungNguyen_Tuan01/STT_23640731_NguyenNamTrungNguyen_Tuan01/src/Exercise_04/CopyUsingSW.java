package Exercise_04;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import javax.swing.JOptionPane;
import javax.swing.SwingWorker;

class CopyUsingSW extends SwingWorker<Long, Void> {

    private File source;
    private File dest;

    public CopyUsingSW(File source, File dest) {
        this.source = source;
        this.dest = dest;
    }

    @Override
    protected Long doInBackground() throws Exception {
        long totalBytes = source.length();
        long copied = 0;

        try (
            InputStream in = new FileInputStream(source);
            OutputStream out = new FileOutputStream(dest)
        ) {
            byte[] buffer = new byte[4096];
            int bytesRead;

            while ((bytesRead = in.read(buffer)) != -1) {
                out.write(buffer, 0, bytesRead);
                copied += bytesRead;
                setProgress((int) (copied * 100 / totalBytes));
            }
        }
        return copied;
    }

    @Override
    protected void done() {
        try {
            get();
            JOptionPane.showMessageDialog(null, "Copy completed!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
