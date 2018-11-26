import java.awt.Color;
import java.awt.Graphics;

public class Food {
	public int x = randInt(0, Board.N);
	public int y = randInt(0, Board.M);

	
	public void appear() {
		this.x = randInt(0, Board.N);
		this.y = randInt(0, Board.M);
	}
	public void paint(Graphics g) {
		g.setColor(Color.BLUE);
		g.fillRect(x*Board.square, y*Board.square, Board.square, Board.square);	
	}

	//base on: https://stackoverflow.com/questions/363681/how-to-generate-random-integers-within-a-specific-range-in-java
	public static int randInt(int min, int max) {
	    // nextInt is normally exclusive of the top value,
	    // so add 1 to make it inclusive
//	    return min + (int)(Math.random() * ((max - min) + 1));
		int hold= min + (int)(Math.random() * ((max - min)));// exclusive
		return hold;
	}
}
