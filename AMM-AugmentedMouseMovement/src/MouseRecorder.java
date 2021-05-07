import java.awt.MouseInfo;
import java.awt.Point;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class MouseRecorder implements Runnable{
	private FileWriter file;
	private int wait_time;
	private boolean goal_reached = false;
	
	
	public MouseRecorder(String filename, int wait_time) throws IOException{
		this.file = new FileWriter(filename);
		this.wait_time = wait_time;
	}
	
	public void run() {
		ArrayList<int[]> path = new ArrayList<int[]>(); 
		Point start_pos = MouseInfo.getPointerInfo().getLocation();
		Point prev_pos = start_pos;
		
		path.add(new int[]{0, 0, this.wait_time}); // start location
		try {
			Thread.sleep(wait_time); // Sleep until getting new pos
		} catch (InterruptedException e) {}
		
		while(true){
			
			Point pos = MouseInfo.getPointerInfo().getLocation();
			
			path.add(new int[]{(pos.x-prev_pos.x), (pos.y-prev_pos.y), this.wait_time}); // calc position deltas
			
			if(this.goal_reached){ // If goal was reached we want to write the path.
				try {
				    String sb = "[";
					for(int[] i : path){
						sb += "("+i[0]+","+i[1]+","+i[2]+")";
						sb += ",";
					}
					
					sb += "("+(pos.x-start_pos.x)+","+(pos.y-start_pos.y)+","+this.wait_time*path.size()+")";
					sb += "]\n";
					this.file.write(sb);
				} catch (IOException e) {}
				this.goal_reached = false;
				path.clear();
				start_pos = pos;
				path.add(new int[]{0, 0, this.wait_time});
			}

			prev_pos = pos;
			
			try {
				Thread.sleep(wait_time); // Sleep until getting new pos
			} catch (InterruptedException e) {}
		}
	}
	
	
	public void goalReached(){
		this.goal_reached = true;
	}

}
