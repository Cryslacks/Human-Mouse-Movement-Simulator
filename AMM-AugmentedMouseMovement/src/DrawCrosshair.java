import java.awt.Color;
import java.awt.Graphics;

public class DrawCrosshair implements Runnable {
	//private Graphics g;
	private int[] pos;
	private boolean isRunning = false;
	
	public DrawCrosshair(int[] pos){
		//this.g = g;
		this.pos = pos;
	}
	
	public void run(){
		this.isRunning = true;
		while(true){
	        //g.setColor(Color.green);
	        //g.drawLine(this.pos[0], 0, this.pos[0], 542);
	        //g.drawLine(0, this.pos[1], 780, this.pos[1]);
		}
	}
	
	public void changePos(int[] pos){
		this.pos = pos;
	}

	public boolean isAlive() {
		return this.isRunning;
	}
}
