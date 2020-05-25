package tester;

import java.awt.Dimension;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.io.InputStream;

import javax.swing.JFrame;

import processing.awt.PSurfaceAWT;
import processing.core.PApplet;
import javax.swing.*;
import sun.audio.*;
import java.io.*;

public class Main {

	public static void main(String args[]) {

		DrawingSurface drawing = new DrawingSurface();
		PApplet.runSketch(new String[] { "" }, drawing);
		PSurfaceAWT surf = (PSurfaceAWT) drawing.getSurface();
		PSurfaceAWT.SmoothCanvas canvas = (PSurfaceAWT.SmoothCanvas) surf.getNative();
		JFrame window = (JFrame) canvas.getFrame();
		window.setBounds(0, 0, 750, 750);
		window.setMinimumSize(new Dimension(700, 710));
		window.setMaximumSize(new Dimension(800, 810));
		window.setSize(752, 752);
		window.setPreferredSize(new Dimension(750, 750));
		window.setLocationRelativeTo(null);
		window.addComponentListener(new ComponentAdapter() {

			@Override
			public void componentResized(ComponentEvent e) {
				// System.out.println("true");
				double w = window.getSize().getWidth();
				double h = window.getSize().getHeight();
				System.out.println(w);
				if (w > 750 || h > 750) {
					System.out.println("true");
					window.setResizable(false);
					window.repaint();
					window.revalidate();
				}

				super.componentResized(e);
			}

		});
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setResizable(true);

		window.setVisible(true);
	}

}
