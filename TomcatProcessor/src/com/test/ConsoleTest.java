package com.test;

import java.awt.BorderLayout;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

@SuppressWarnings("serial")
public class ConsoleTest extends JPanel {

	private JTextArea textArea = new JTextArea(15, 30);
	private TextAreaOutputStream taOutputStream = new TextAreaOutputStream(
			textArea, "EMA");

	public ConsoleTest() throws IOException {
		setLayout(new BorderLayout());
		add(new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER));
		System.setOut(new PrintStream(taOutputStream));

	}

	private static void createAndShowGui() throws IOException {
		JFrame frame = new JFrame("EMA TESTING APP CONSOLE");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(new ConsoleTest());
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

	public static void main(String[] args) {

		// Start GUI
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				try {
					createAndShowGui();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});

		// Start Tomcat
		try {
			// String[] command = {"CMD /C catalina.bat"};
			// ProcessBuilder probuilder = new ProcessBuilder(command);
			// probuilder.directory(new File("D:/"));
			// Process process = probuilder.start();

			Process process = Runtime.getRuntime().exec("cmd.exe /c dir");

			// Read out dir output
			InputStream is = process.getInputStream();
			InputStreamReader isr = new InputStreamReader(is);
			BufferedReader br = new BufferedReader(isr);
			String line;
			while ((line = br.readLine()) != null) {
				//Thread.sleep(2000);
				System.out.println(line);
			}

			// Wait to get exit value
			int exitValue = process.waitFor();
			System.out.println("\n\nExit Value is " + exitValue);

		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}

	}

}
