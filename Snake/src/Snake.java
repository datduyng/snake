import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Snake {
	public int x = 0;
	public int y = 0;
	int speed = 1;
	int dx = speed; 
	int dy = 0;
	public int snakeSize = 1;
//	static ArrayList<int[]> tail = new ArrayList<int[]>() {{add(new int[] {this.x,this.y});}};// first e in tail is the head
	public ArrayList<int[]> tail = new ArrayList<int[]>(Arrays.asList(new int[] {this.x,this.y}));
	
	public ArrayList<int[]> getTail(){
		return this.tail;
	}
	public Snake() {}
	
	public boolean eatTail() {
		for(int i=1;i<this.snakeSize;i++) { 
			if(Arrays.equals(this.tail.get(0), this.tail.get(i)))
				return true;
		}
		return false;
	}
	public void move() {
		if(x >= Board.M) this.x = 0; 
		if(x < 0) this.x = Board.M;
		if(y >= Board.N) this.y = 0; 
		if(y < 0) this.y = Board.N;
		x += dx;
		y += dy;
		
		int[] prev = tail.get(0).clone();
		int[] curr;
		if((this.snakeSize-tail.size())==1) {
			this.tail.add(tail.get(tail.size()-1).clone());
		}
		//update tail
		for(int i=1;i<this.snakeSize;i++) {
			curr = tail.get(i).clone();
			//tail.get(i)[0] = prev[0];tail.get(i)[1] = prev[1];
			tail.set(i, prev.clone());
			prev = curr.clone();
		}
		tail.get(0)[0] = this.x;tail.get(0)[1] = this.y;
	}
	public void gainSize() {
		this.snakeSize++;
	}
	
	public void paint(Graphics g) {
		for(int i=0;i<tail.size();i++) {
			g.fillRect(tail.get(i)[0]*Board.square, 
					   tail.get(i)[1]*Board.square, Board.square, Board.square);	
		}
	}
	
	public void keyReleased(KeyEvent e) {
	}

	public void keyPressed(KeyEvent e) {
		// if head is pointing left then cannot go right. 
		if (e.getKeyCode() == KeyEvent.VK_LEFT && dx <= 0) {
			dx = -speed;dy = 0;
		}else if (e.getKeyCode() == KeyEvent.VK_RIGHT&& dx >= 0) {
			dx = speed;dy = 0;
		}else if (e.getKeyCode() == KeyEvent.VK_UP && dy <= 0) {
			dy = -speed;dx = 0;
		}else if (e.getKeyCode() == KeyEvent.VK_DOWN && dy >= 0) {
			dy = speed;dx = 0;
		}
	}
	public void printArrayList() {
		for(int[] e: this.tail) {
			System.out.print("["+e[0]+","+e[1]+"]");
		}
		System.out.println();
	}
	

}
