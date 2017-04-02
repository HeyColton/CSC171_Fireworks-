/*Name:Xiaoyu Zheng
 * Project number:3
 * Lab Section: Mon 12:30
 * Lab TAs: Camllo, Jack Venuti
 * I did not work with anyone on this assignment
 */
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Arc2D;
import java.awt.geom.Ellipse2D;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class GUI extends JFrame implements ChangeListener,ActionListener{
JTextField jtextfield1,jtextfield2,jtextfield3,jtextfield4;
JLabel jlabel1,jlabel2,jlabel3,jlabel4,sliderLabel;
JSlider jslider;
double value;
JPanel jpanel;
public int height, width;
int num,v,angle,color;
JButton jbutton,SpecialButton;
int child=1;
public GUI(){
    JPanel jpanel=new JPanel();
	add(jpanel);
	setSize(800,600);
	setDefaultCloseOperation(EXIT_ON_CLOSE);
	setLayout(new FlowLayout());
	jtextfield1=new JTextField(3);
	add(jtextfield1);
	jlabel1=new JLabel();
	add(jlabel1);
	jtextfield2=new JTextField(3);
	add(jtextfield2);
	jlabel2=new JLabel();
	add(jlabel2);
	jtextfield3=new JTextField(3);
	add(jtextfield3);
	jlabel3=new JLabel();
	add(jlabel3);
	jtextfield4=new JTextField(3);
	add(jtextfield4);
	jtextfield4.addActionListener(this);
	jlabel4=new JLabel("[1-5] 1.Red 2.Blue 3.Yellow 4.Green 5.Orange");
	add(jlabel4);
	jtextfield1.addActionListener(this);
	jtextfield2.addActionListener(this);
	jtextfield3.addActionListener(this);
	jtextfield4.addActionListener(this);
	jlabel1.setText("Explosion [1-5]");
	jtextfield1.setText("1");
	jlabel2.setText("Velocity");
	jtextfield2.setText("100");
	jlabel3.setText("Angle");
	jtextfield3.setText("60");
	//I add the space to make my program form look better
	JLabel space=new JLabel("                                                             ");
	add(space);
	//JSlider is time from fireworks launched to explosion.
	jslider=new JSlider();
	//set the initial position is left
	jslider.setValue(0);
	jslider.setMaximum(500);
	add(jslider);
	jslider.addChangeListener(this);
	sliderLabel=new JLabel();
	add(sliderLabel);
	sliderLabel.setText("Time");
	jbutton=new JButton("Repaint");
	add(jbutton);
	jbutton.addActionListener(this);
	SpecialButton=new JButton("SpecialButton");
	add(SpecialButton);
	SpecialButton.addActionListener(this);
}

public void explosion(int num, int v, int angle){
	Graphics g=getGraphics();
	if(color==1){
		g.setColor(Color.RED);
	}else if(color==2){
		g.setColor(Color.BLUE);
	}else if(color==3){
		g.setColor(Color.YELLOW);
	}else if(color==4){
		g.setColor(Color.GREEN);
	}else if(color==5){
		g.setColor(Color.ORANGE);
	}
	//the whole time is 6.00
	//if time is less than 4, it's at the trajectory part.
	if(value<=4){
		int x=(int) (Xdistance(v,angle,value))+200;
		int y=getHeight()-(int) (Ydistance(v,angle,value))-100;
		g.drawOval(x, y, 1, 1);//there are numerous dots making a trajectory arc.
	}else{
		//the explosion keeps for 1.00
		double t=value-4;
		//the position the explosion starts
		int initialX=(int) Xdistance(v,angle,4)+200;
		int initialY=getHeight()-(int) (Ydistance(v,angle,4))-100;
		//this is the first part of explosion, and there will be many circles of explosion waves.
		if(t<=0.5){
		g.drawOval(initialX-(int)(20*t)/2,initialY-(int)(20*t)/2,(int)(20*t),(int)(20*t));		
		}else if(t>0.5){
			
		switch(num){
		case 1:for(int rotate=10;rotate<=370;rotate+=10){	
				int finX=(int) (150*Math.random()*t*Math.cos(Math.toRadians(rotate)));
				int finY=(int) (150*Math.random()*t*Math.sin(Math.toRadians(rotate)));
		       //this is to set a random color
				g.setColor(new Color((int)(Math.random() * 0x1000000)));
				g.drawLine(initialX,initialY,initialX+finX,initialY+finY);
		       }
		break;
		case 2:g.setColor(new Color((int)(Math.random() * 0x1000000)));
			for(int rotate=0;rotate<=360;rotate+=5){	
				//change this to graphics2d, so I can rotate them. 
			Graphics2D g1=(Graphics2D) g.create();
			
		   Rectangle Rect=new Rectangle(initialX,initialY,(int)(100*t),10);
	      g1.rotate(Math.toRadians(rotate),initialX,initialY);
	      g1.draw(Rect);
		}
			
	     break;
	    case 3: g.setColor(new Color((int)(Math.random() * 0x1000000)));
	    	for(int rotate=0;rotate<=360;rotate+=20){	
				Graphics2D g1=(Graphics2D) g.create();	      
			      Ellipse2D ellipse=new Ellipse2D.Double(initialX-100*t, initialY,200*t,10);
			      g1.rotate(Math.toRadians(rotate),initialX,initialY);
			      g1.draw(ellipse);
	       }
	    break;
	    case 4:for(int i=1;i<=3;i++){
	    	 g.setColor(new Color((int)(Math.random() * 0x1000000)));
	    	 int radius=(int)(250*Math.random()*t);
              g.drawOval(initialX-radius/2, initialY-radius/2,radius,radius);
	    }
	    break;
	    case 5:
    	for(int rotate=0;rotate<=720;rotate+=5){	
			Graphics2D g1=(Graphics2D) g.create();	      
		      g1.rotate(Math.toRadians(rotate),initialX,initialY);
		      g.setColor(new Color((int)(Math.random() * 0x1000000)));
		      g1.drawOval((int)(initialX+Math.random()*100*t),(int)(initialY+Math.random()*100*t),1,1);
       }
	 
     }
		}
	}
		
}
	
public void SpecialExplosion(int v,int angle){
	Graphics g=getGraphics();
	if(color==1){
		g.setColor(Color.RED);
	}else if(color==2){
		g.setColor(Color.BLUE);
	}else if(color==3){
		g.setColor(Color.YELLOW);
	}else if(color==4){
		g.setColor(Color.GREEN);
	}else if(color==5){
		g.setColor(Color.ORANGE);
	}
	int x=0,y=0;
	//This is the trajectory of the first firework.
	for(double m=0;m<=4;m+=0.1){
		x=(int) (Xdistance(v,angle,m))+200;
		y=getHeight()-(int) (Ydistance(v,angle,m))-100;
		g.drawOval(x, y, 1, 1);
	}
	//It breaks into three parts
		for(int i=1;i<=3;i++){
			g.setColor(new Color((int)(Math.random() * 0x1000000)));
			angle=(int) (180*Math.random());
			v=v/2;
			//Every part has its own trajectory
			for(double n=0;n<=4.5;n+=0.1){
				int x1=x+(int) (Xdistance(v,angle,n));
				int y1=y-(int) (Ydistance(v,angle,n));
				g.drawOval(x1, y1, 1, 1);
			}
			//Every part will explode in 1 unit time.
			for(double n=4.5;n<=5.5;n+=0.05){
			for(int rotate=10;rotate<=370;rotate+=40){
				int initialX1=x+(int) Xdistance(v,angle,4.5);
				int initialY1=y-(int) (Ydistance(v,angle,4.5));
				int finX1=(int) (100*Math.random()*Math.cos(Math.toRadians(rotate)));
				int finY1=(int) (100*Math.random()*Math.sin(Math.toRadians(rotate)));
				g.setColor(new Color((int)(Math.random() * 0x1000000)));
				g.drawLine(initialX1,initialY1,initialX1+finX1,initialY1+finY1);
		       }
			}
		}
	}
	


public double Xdistance(int v, int angle, double t){
	double x=v*Math.cos(Math.toRadians(angle))*t;
	return x;
}

public double Ydistance(int v, int angle, double t){	
	double y=v*Math.sin(Math.toRadians(angle))*t-4.9*t*t;
	return y;
}

	
	@Override
	public void stateChanged(ChangeEvent e) {
		// TODO Auto-generated method stub
		sliderLabel.setText(String.valueOf(jslider.getValue()));
		//the maximum is 500, so I can get 500 points during the process.
		//But time cannot be 500, otherwise the math formula: v*t*sin-1/2*g*t^2 cannot be balanced.
		//So I make the value smaller, but still get 500 points
		value=(double)(jslider.getValue()/100.00);
		//trigger the explosion
		explosion(num,v,angle);
		
	}
public static void main(String[] args) {
		// TODO Auto-generated method stub
GUI gui=new GUI();
gui.setVisible(true);


	}

@Override
public void actionPerformed(ActionEvent e) {
	// TODO Auto-generated method stub
	
	if(e.getSource()==jtextfield1){
	num=Integer.parseInt(jtextfield1.getText());
	jlabel1.setText("Explosion: "+num);
	}else if(e.getSource()==jtextfield2){
	v=Integer.parseInt(jtextfield2.getText());
	jlabel2.setText("Velocity: "+v);
	}else if(e.getSource()==jtextfield3){
	angle=Integer.parseInt(jtextfield3.getText());
	jlabel3.setText("Angle: "+angle);
	}else if(e.getSource()==jtextfield4){
		color=Integer.parseInt(jtextfield4.getText());			
	}else if(e.getSource()==jbutton){
	repaint();
	jslider.setValue(0);
    }else if(e.getSource()==SpecialButton){
    	SpecialExplosion(v, angle);
    }
	}

}
//