package com.myapp;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.UnsupportedAudioFileException;

public class AudioClient {

	public static void main(String[] args) throws IOException {

		try {
			Socket socket = new Socket(args[0], 9090);
			System.out.println("server connected...");
			socket.getOutputStream().write(args[1].getBytes());
			AudioInputStream ais = AudioSystem
					.getAudioInputStream(new BufferedInputStream(socket
							.getInputStream()));
			AudioFormat format = ais.getFormat();
			DataLine.Info info = new DataLine.Info(SourceDataLine.class, format);
			final SourceDataLine line = (SourceDataLine) AudioSystem
					.getLine(info);
			line.open(format);
			line.start();

			int bufferSize = format.getFrameSize()
					* (int) format.getSampleRate();
			byte[] buffer = new byte[bufferSize];
			int count = 0;

			while ((count = ais.read(buffer)) > 0) {
				if (count > 0) {
					line.write(buffer, 0, count);
					System.out.println("getting audio from server-->"
							+ buffer[0] + "---" + buffer[1]);
				}
			}
			line.drain();
			line.close();
		} catch (UnknownHostException e) {
			System.out.println("Unknown host: localhost");
		} catch (IOException e) {
			System.out.println("No I/O");
		} catch (LineUnavailableException e) {
			e.printStackTrace();
		} catch (UnsupportedAudioFileException e) {
			e.printStackTrace();
		}
	}
}
