package game;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;

import javax.swing.*;

import com.sun.source.doctree.AttributeTree.ValueKind;

public class GamePanel extends JPanel implements ActionListener {
static final int SCREEN_WIDTH=600;
static final int SCREEN_HEIGHT=600;	
static final int UNIT_SIZE=25;
static final int GAME_UNITS=(SCREEN_WIDTH*SCREEN_HEIGHT)/(UNIT_SIZE*UNIT_SIZE);
static final int DELAY=50;
final int x[]=new int[GAME_UNITS];
final int y[]=new int[GAME_UNITS];
int bodyParts=6;
int applesEaten;
int appleX;
int appleY;
char direction='R';
boolean running=false;
Timer timer;
Random random;
GamePanel(){
	random = new Random();
	  this.setPreferredSize(new Dimension(SCREEN_WIDTH,SCREEN_HEIGHT));
	 this.setOpaque(true);
	  this.setBackground(Color.black);
	  this.setFocusable(true);
	  this.addKeyListener(new MyKeys());
	  Start();
}
public void Start() {
newApple();
running=true;
Timer t=new Timer(DELAY,this);
t.start();
}

public void paintComponent(Graphics g) {
super.paintComponent(g);
  draw(g);
}
public void draw(Graphics g) {
	if(running) {
	g.setColor(Color.red);
	g.fillOval(appleX,appleY, UNIT_SIZE, UNIT_SIZE);
for(int i=0; i<bodyParts; i++) {
if(i==0) {
g.setColor(Color.green);
g.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE);
}
else {
	g.setColor(new Color(45,180,0));
	g.fillRect(x[i], y[i], UNIT_SIZE,UNIT_SIZE);
}

}


	}
}
public void newApple() {
	Random r=new Random();
	appleX=r.nextInt((int)(SCREEN_WIDTH/UNIT_SIZE))*UNIT_SIZE;
	appleY=r.nextInt((int)(SCREEN_WIDTH/UNIT_SIZE))*UNIT_SIZE;


}
public void Move() {
	for(int i=bodyParts;i>0; i--) {
x[i]=x[i-1];
y[i]=y[i-1];	
}
switch (direction) {
case 'U' : y[0]=y[0]-UNIT_SIZE; 
break;
case 'D' : y[0]=y[0]+UNIT_SIZE;	
break;
case 'L' : x[0]=x[0]-UNIT_SIZE;
break;
case 'R' : x[0]=x[0]+UNIT_SIZE;
break;

}	
	

}
public void CheckApple(){
	if((x[0]==appleX)&&(y[0]==appleY)) {
	bodyParts++;
	applesEaten++;
		newApple();
	}
}
public void Collisions() {
 for(int i=bodyParts; i>0; i--) {
  if((x[0]==x[i])&&(y[0]==y[i])) {
 running=false;
  }
  }
 if(x[0]<0) {running=false;
 }
 if(x[0]>SCREEN_WIDTH) {
	 running=false;
 }
 if(y[0]<0) {
 running=false;
 }
 if(y[0]>SCREEN_HEIGHT) {running=false;
 }
if(!running) {timer.stop();}
}
public void GameOver(Graphics g) {
	
}
public void actionPerformed(ActionEvent arg0) {
		if(running) {
	    
		Move();
		CheckApple();
		Collisions();
		}
	repaint();
}
public class MyKeys extends KeyAdapter{

@Override
public void keyPressed(KeyEvent e) {
	switch(e.getKeyCode()) {
	case KeyEvent.VK_LEFT:
		if(direction!='R') {
		direction='L';
		}
	break;
	case KeyEvent.VK_RIGHT:
		if(direction!='L') {
		direction='R';
		}
	break;
	case KeyEvent.VK_UP:
		if(direction!='D') {
		direction='U';
		}
	break;
	case KeyEvent.VK_DOWN:
		if(direction!='U') {
		direction='D';
		}
	break;
	
	}
	
	
	}


}
}
