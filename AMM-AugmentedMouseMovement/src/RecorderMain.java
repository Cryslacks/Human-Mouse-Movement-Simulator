import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class RecorderMain {
	private int clicks = 0;
	private boolean first_click = true;
	private Thread thread_mr;
	private MouseRecorder mr;
	
	public RecorderMain(boolean draw, String filename) throws IOException{
		JFrame frame = new JFrame("Mouse movement recorder");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//frame.getContentPane().setBackground(Color.gray);
		//frame.setLocation(1920/2, 1080/2-800/2); // Location on screen
		if(draw){
			frame.setSize(780, 542); // Size
			JPanel canvas = new PathDrawer(filename);
			canvas.setSize(1920, 1080);
			
			// https://stackoverflow.com/questions/1064977/setting-background-images-in-jframe
			
			BufferedImage myImage = ImageIO.read(new File("osrs.png"));
			frame.setContentPane(new ImagePanel(myImage));
			
			//JButton b = new JButton("Next path");
			//b.setSize(300, 100);
			//b.setLocation(1920/2-300/2, 880);
			
			frame.addMouseListener(new MouseAdapter() {
			    public void mouseClicked(MouseEvent e){
			        System.out.println("Mouse was clicked on my frame!");
			        System.out.println(e.getPoint());
			    }
			});
			
			JLabel l = new JLabel("Path: 0");
			l.setFont(new Font("Serif", Font.PLAIN, 34));
			l.setSize(200, 60);
			l.setLocation(1920/2-200/2, 60);
			
			/*b.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					try {
						((PathDrawer)canvas).nextPath();
					} catch (FileNotFoundException e1) {}
					canvas.repaint();
				}
			});*/
			canvas.setOpaque(false);
			frame.add(l);
			frame.add(canvas);
			

			new Thread() {
				private int path_nr = 0;
				public void run(){
					try {			Thread.sleep(500);			} catch (InterruptedException e) {}
					while(true){
						try {
							path_nr = ((PathDrawer)canvas).nextPath();
							l.setText("Path: "+path_nr);
						} catch (FileNotFoundException e1) {}
						canvas.repaint();
						try {			Thread.sleep(500);			} catch (InterruptedException e) {}
					}
				}
			}.start();
		}else{
			frame.setExtendedState(JFrame.MAXIMIZED_BOTH); 
			Random rand = new Random();
			this.mr = new MouseRecorder("testalot.txt", 1);
			this.thread_mr = new Thread(mr);
			
			JLabel l = new JLabel("Clicks: "+this.clicks);
			l.setSize(100, 20);
			l.setLocation(0, 0);
			
			JButton b = new JButton("Start");
			b.setSize(300, 100);
			b.setLocation(1920/2-300/2, 880/2-100/2);
			
			b.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					if(first_click){
						b.setText("Click");
						b.setSize(300, 300);
						thread_mr.start();
						first_click = false;
					}else{
						clicks++;
						l.setText("Clicks: "+clicks);	
						mr.goalReached();
					}
					
					b.setLocation(rand.nextInt(frame.size().width-300), rand.nextInt(frame.size().height-300));
				}
			});
			
			frame.add(l);
			frame.add(b);
		}
		
		frame.setLayout(null);
		frame.setVisible(true);
	}
	
	public static void main(String[] args) throws IOException {
			new RecorderMain(false, "nn_inv_smash.txt");
			//new RecorderMain(true, "earlytest550.txt");
	}

}
