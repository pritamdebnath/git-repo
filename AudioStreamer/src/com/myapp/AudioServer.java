package com.myapp;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.UnsupportedAudioFileException;

public class AudioServer {

	public static void main(String[] args) throws IOException {

		ServerSocket ss = new ServerSocket(9090);
		ExecutorService executorService = Executors.newCachedThreadPool();

		while (true) {
			Socket s = ss.accept();
			// new Thread(()->processData(ss)).start();try later implementing
			// with lambda
			executorService.submit(new Callable<String>() {
				@Override
				public String call() throws Exception {
					processData(s);
					return null;
				}
			});
		}

	}

	private static void processData(Socket s) throws IOException {

		try (FileInputStream fis = new FileInputStream(new File("D:\\song.wav"));
				OutputStream os = s.getOutputStream();) {
			byte buffer[] = new byte[2048];
			int count;
			while ((count = fis.read(buffer)) != -1) {
				os.write(buffer, 0, count);
				System.out.println("Streaming audio to client-->" + buffer[0]
						+ "--" + buffer[1]);
			}

			System.out.println("Streaming Completed");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
