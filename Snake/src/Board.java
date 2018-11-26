/**
 * @Author Dat Nguyen 
 * @version of date: 11/25/18
 * 
 * Base on :http://www.edu4java.com/en/game/game2.html
 */



import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
//for keyboard input
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Arrays;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Board extends JPanel{
	public static int xOffset = 100;
	public static int yOffset = 100;
	public static int width = 1000;
	public static int height = 1000;
	public static int square = 20;
	public static int M = (int) ((double)height / (double)square);
	public static int N = (int) ((double)width / (double)square);
	Snake snake = new Snake();
	Food food = new Food(); 

	public Board() {
		addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {}
			@Override
			public void keyPressed(KeyEvent e) {
				snake.keyPressed(e);
			}

			@Override
			public void keyReleased(KeyEvent e) {
				snake.keyReleased(e);
			}
		});
		setFocusable(true);
	}
	
	private void update() {
		snake.move();
		if(snake.eatTail()) {
			System.out.println("Game over");
			System.exit(1);
		}
		if(eatFood()) {
			food.appear();
			snake.gainSize();
		}
	}
	public void start() throws InterruptedException {
		Board board = new Board();
		JFrame frame = new JFrame("snake");
		frame.add(board); 
		frame.setSize(width, height);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		while(true) {
			board.update();
			board.repaint();
			Thread.sleep(100);
		}
	}
	
	public void paint(Graphics g) {
		super.paint(g);// clean the screen
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		
		g2d.setColor(Color.BLACK);
		Font serif = new Font("Serif", Font.BOLD, 28);
		g2d.setFont(serif);
		g2d.drawString("Score "+snake.snakeSize, 40,40);
		g2d.setColor(Color.RED);
		snake.paint(g2d);
		food.paint(g2d);
	}
	
	public boolean eatFood() {
		if(snake.x == food.x && snake.y == food.y) {
			return true;
		}else
			return false;
	}

	public static void main(String args[])  throws InterruptedException{
		Board board = new Board();
		board.start();
	}

}
