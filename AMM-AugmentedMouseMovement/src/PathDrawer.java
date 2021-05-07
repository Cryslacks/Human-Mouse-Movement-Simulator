import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JPanel;

public class PathDrawer extends JPanel{
	private Scanner file;
	private String filename;
	private ArrayList<int[]> current_path;
	private int[] current_pos;
	private int path_nr = 0;
	private Color[] cols;
	
	public PathDrawer(String filename) throws FileNotFoundException{
		this.filename = filename;
		this.file = new Scanner(new File(filename));
		this.current_path = new ArrayList<int[]>();
		this.cols = new Color[1];
		this.cols[0] = Color.white;
		//this.cols[1] = Color.green;
		//this.cols[2] = Color.blue;
		
		//this.ch_th = new DrawCrosshair(this.current_pos);
		//new Thread(this.ch_th).start();
		
		this.current_pos = new int[]{670-8, 257-31};
	}
	
	public void paint(Graphics g){
		if(this.current_path.size() == 0)
			return;
		g.setColor(this.cols[path_nr % this.cols.length]);
		
		int[] end = this.current_path.get(this.current_path.size()-1);
		
		this.current_path.remove(this.current_path.size()-1);
		
		g.fillOval(end[0]-6, end[1]-6, 12, 12);
		g.drawOval(this.current_pos[0]-3, this.current_pos[1]-3, 6, 6);
		
		int c = 0;
		for(int[] i : this.current_path){
	        g.drawLine(this.current_pos[0], this.current_pos[1], this.current_pos[0]+i[0], this.current_pos[1]+i[1]);
	        this.current_pos[0] += i[0];
	        this.current_pos[1] += i[1];
	        
	        //this.ch_th.changePos(this.current_pos);
	        
	        //try {
			//	Thread.sleep(i[2]);
			//} catch (InterruptedException e) {}
	        c++;
		}

		g.drawLine(this.current_pos[0], this.current_pos[1], end[0], end[1]);
        this.current_pos = end;
        
	}
	
	
	public int nextPath() throws FileNotFoundException{
		this.current_path.clear();
		if(this.file.hasNext()){
			this.path_nr++;
			String s = this.file.nextLine();
			s = s.replace("[", "").replace("]", "").replace(" ", "");
			for(String triplet : s.split("\\),")){
				String[] numbs = (triplet.split("\\(")[1]).split(",");
				this.current_path.add(new int[]{Integer.parseInt(numbs[0]), Integer.parseInt(numbs[1]), Integer.parseInt(numbs[2].split("\\)")[0])});
			}
		}else{
			this.file = new Scanner(new File(this.filename));
			this.path_nr = 0;
			this.nextPath();
		}
		
		return this.path_nr;
	}
}
