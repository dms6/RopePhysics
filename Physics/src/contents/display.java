package contents;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.WindowConstants;



public class display extends Canvas implements Runnable{

	private static final long serialVersionUID = 1L;

	private Thread thread;
	private JFrame frame;
	private static String title = "String Physics";
	public static final int WIDTH = 800;
	public static final int HEIGHT = 800;
	private static boolean running = false;
	public static boolean start = false;
	
	public Simulate sim = new Simulate();
	private Mouse mouse;
	
	public display() {
		this.frame = new JFrame();
		this.mouse = new Mouse();
		this.addMouseListener(this.mouse);
		this.addKeyListener(this.mouse);
		
		Dimension size = new Dimension(WIDTH, HEIGHT);
		this.setPreferredSize(size);
	}

	public static void main(String[] args) {
		display display = new display();
		
		display.frame.setTitle(title);
		display.frame.add(display);
		display.frame.pack();
		display.frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		display.frame.setLocationRelativeTo(null);
		display.frame.setResizable(false);
		display.frame.setVisible(true);
		display.start();
	}

	public synchronized void start() {
		running = true;
		this.thread = new Thread(this, "Display");
		this.thread.start();
	}

	public synchronized void stop() {
		try {
			this.thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		long lastTime = System.nanoTime();
		long timer = System.currentTimeMillis();
		final double ns = 1000000000.0/ 500;
		double delta = 0;
		int frames = 0;

		while(running) {
			long now = System.nanoTime();
			delta += (now - lastTime)/ns;
			lastTime = now;
			while(delta >= 1) {
				update();
				delta--;
				render();
				frames++;
			}

			if(System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				this.frame.setTitle(title + " | " + frames + " fps");
				frames = 0;
			}
		}
		stop();
	}

	private void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null) {
			this.createBufferStrategy(3);
			return;
		}

		Graphics g = bs.getDrawGraphics();
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, WIDTH, HEIGHT);

		sim.render(g);

		g.dispose();
		bs.show();
	}
	
	private void update() {
		if(start)
			sim.update();
	}

}
