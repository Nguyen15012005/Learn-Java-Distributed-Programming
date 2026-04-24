package Exercise_05;

import java.awt.BorderLayout;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.concurrent.ExecutionException;
import java.util.stream.Stream;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;

import org.w3c.dom.events.EventTarget;

public class OpenEditSaveFileApplication extends JFrame {
	private JProgressBar progressBar;
	private JTextArea textArea;

	public OpenEditSaveFileApplication() {
		super("My Notepad");
		setSize(800, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);

//        Tao thanh menu voi nut open, save, exit
		JMenuBar menuBar = new JMenuBar();
		JMenu fileMenu = new JMenu("File");
		JMenuItem createMenuItem = new JMenuItem("Tao Tap Tin (Chua Hoan Thanh)");
		JMenuItem openMenuItem = new JMenuItem("Mo Tap Tin");
		JMenuItem saveMenuItem = new JMenuItem("Luu Tap Tin");
		JMenuItem updateMenuItem = new JMenuItem("Sua Tap Tin");
		JMenuItem printMenuItem = new JMenuItem("In Tap Tin (Chua Hoan Thanh)");
		JMenuItem exitMenuItem = new JMenuItem("Thoat (Chua Hoan Thanh)");
		fileMenu.add(createMenuItem);
		fileMenu.add(openMenuItem);
		fileMenu.add(saveMenuItem);
		fileMenu.add(updateMenuItem);
		fileMenu.add(printMenuItem);
		fileMenu.add(exitMenuItem);
		
		JMenu fileMenu1 = new JMenu("Edit");
		JMenuItem openMenuItem1 = new JMenuItem("Open (Chua Hoan Thanh)");
		JMenuItem saveMenuItem2 = new JMenuItem("Save (Chua Hoan Thanh)");
		JMenuItem exitMenuItem3= new JMenuItem("Exit (Chua Hoan Thanh)");
		fileMenu1.add(openMenuItem1);
		fileMenu1.add(saveMenuItem2);
		fileMenu1.add(exitMenuItem3);

		
		JMenu fileMenu2 = new JMenu("Help");
		JMenuItem openMenuItem4 = new JMenuItem("Open (Chua Hoan Thanh)");
		JMenuItem saveMenuItem5 = new JMenuItem("Save(Chua Hoan Thanh)");
		JMenuItem exitMenuItem6= new JMenuItem("Exit(Chua Hoan Thanh)");
		fileMenu2.add(openMenuItem4);
		fileMenu2.add(saveMenuItem5);
		fileMenu2.add(exitMenuItem6);
		
		
		menuBar.add(fileMenu);
		menuBar.add(fileMenu1);
		menuBar.add(fileMenu2);
		setJMenuBar(menuBar);

		textArea = new JTextArea();
		JScrollPane scrollPane = new JScrollPane(textArea);
		add(scrollPane);

		add(progressBar = new JProgressBar(), BorderLayout.SOUTH);
		progressBar.setStringPainted(true);

		openMenuItem.addActionListener(e -> {
			JFileChooser fileChooser = new JFileChooser();
			int value = fileChooser.showOpenDialog(this);
			if (value == JFileChooser.APPROVE_OPTION) {
				String fileName = fileChooser.getSelectedFile().getAbsolutePath();
//				1. Without thread
//				loadingWithhoutThread(textArea, fileName);
//				2. With thread
//				Runnable task = new LoadingWithThread(textArea, fileName);
//				Thread thread = new Thread(task);
//				thread.start();
//				3. Using SwingWorker
				LoadingUsingSW task3 = new LoadingUsingSW(textArea, fileName);
				task3.execute();
				task3.addPropertyChangeListener(evt -> {
					if(evt.getPropertyName().equals("progress"))
						progressBar.setValue((int) evt.getNewValue());
				});
			}
		});
		
		
	}

	private void loadingWithhoutThread(JTextArea textArea, String fileName) {
//		try - catch thì try không có ngoặc tròn , còn try with resources thì try có ngoăc đơn
		try (
//				Những đối tượng nào được dẫn xuất từ AutoCloseiable mới được bỏ vào dấu mở ngoặc tròn
				FileReader reader = new FileReader(fileName); 
				BufferedReader in = new BufferedReader(reader);
			) {
			while (in.ready()) {
				String line = in.readLine();
				textArea.append(line + "\n");
			}
		} catch (IOException ex) {
			ex.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {

		}
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> new OpenEditSaveFileApplication());
	}
}
//3. Using SwingWork, Tính tổng số line trong 1 file
class LoadingUsingSW extends SwingWorker<Long, Void>{
	private JTextArea textArea;
	private String fileName;
	public LoadingUsingSW(JTextArea textArea, String fileName) {
		super();
		this.textArea = textArea;
		this.fileName = fileName;
	}
	@Override
	protected Long doInBackground() throws Exception {
		long lines = 0L, i =0L;
		try(Stream<String> stream = Files.lines(Path.of(fileName));){
			lines = stream.count();
		}
		try (
//				Những đối tượng nào được dẫn xuất từ AutoCloseiable mới được bỏ vào dấu mở ngoặc tròn
				FileReader reader = new FileReader(fileName); 
				BufferedReader in = new BufferedReader(reader);
			) {
			while (in.ready()) {
				String line = in.readLine();
				textArea.append(line + "\n");
				i++;
				setProgress((int)(i *100 /lines));
			}
		} catch (IOException ex) {
			ex.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return lines;
	}
	
	@Override
	protected void done() {
		try {
			long lines = get();
			JOptionPane.showMessageDialog(null, "Completed, lines: " + lines);
		} catch (InterruptedException | ExecutionException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	
}

//2. With thread
class LoadingWithThread implements Runnable{
	
	private JTextArea textArea;
	private String fileName;
	
	public LoadingWithThread(JTextArea textArea, String fileName) {
		this.textArea = textArea;
		this.fileName = fileName;
	}
	@Override
	public void run() {
		try (
//				Những đối tượng nào được dẫn xuất từ AutoCloseiable mới được bỏ vào dấu mở ngoặc tròn
				FileReader reader = new FileReader(fileName); 
				BufferedReader in = new BufferedReader(reader);
			) {
			while (in.ready()) {
				String line = in.readLine();
				textArea.append(line + "\n");
			}
		} catch (IOException ex) {
			ex.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {

		}
	}
	
}














