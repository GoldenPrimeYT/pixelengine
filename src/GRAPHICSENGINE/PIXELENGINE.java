package GRAPHICSENGINE;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import javax.swing.JFrame;

import GRAPHICSENGINE.SHAPES.PIXELPOLYGON;
import GRAPHICSENGINE.point.PIXELPOINT;

public class PIXELENGINE extends Canvas implements Runnable {

	private static final long serialVersionUID = 1L;
	
	 private Thread thread;
	 private JFrame frame;
	 private static String title = "PixelEngine";
	 public final static int WIDTH = 1280;
	 public final static int HEIGHT = 720;
	 private static boolean running = false;
	 public PIXELENGINE() {
		 this.frame = new JFrame();
		 Dimension size = new Dimension(WIDTH, HEIGHT);
		 this.setPreferredSize(size);
	 }
	 public synchronized void start() {
		 running = true;
		 this.thread = new Thread(this, "Display");
		 this.thread.start();
	 }
	 public synchronized void stop() {
		 running = false;
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
		final double ns = 1000000000.0 / 60;
		double delta = 0;
		double frames = 0;
	    while(running) {
	    	long now= System.nanoTime();
	    	delta += (now - lastTime) / ns;
	    	lastTime = now;
	    	while(delta >= 1) {
	    		update();
	    		delta--;
	    	}
	    	render();
	    	frames++;
	    	
	    	if(System.currentTimeMillis() - timer > 1000) {
	    		timer +=1000;
	    		this.frame.setTitle(title + " running at  " + frames + "fps");
	    		frames = 0;
	    	}
		}
	    stop();
		
	}

	
	private void render() {
		BufferStrategy bs = this.getBufferStrategy();
		System.out.println("done1");
		if(bs == null) {
			this.createBufferStrategy(3);
			return;
		}
		Graphics g = bs.getDrawGraphics();
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, WIDTH * 2, HEIGHT * 2);
		
		PIXELPOLYGON poly = new PIXELPOLYGON(
				new PIXELPOINT(0,100,0),
				new PIXELPOINT(100,0,0),
				new PIXELPOINT(0,0,100));
		poly.render(g);
		g.dispose();
		bs.show();
	}
	
	private void update() {
		
	}
	public static void main(String[] args) {
		PIXELENGINE Engine = new PIXELENGINE();
		Engine.frame.setTitle(title);
		Engine.frame.add(Engine);
		Engine.frame.pack();
		Engine.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Engine.frame.setResizable(true);
		Engine.frame.setVisible(true);
		
		Engine.start();
	}
}