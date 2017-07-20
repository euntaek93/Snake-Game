/*
 * 
 * Maked by coco
 * Update Info : Previously Add Queue by snake control.
 * Backup Date : 2017.01.19
 * 
 * */

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Vector;
import javax.swing.*;
import java.awt.Point;
import java.util.LinkedList;
import java.util.Queue;

public class SnakeGameFrame extends JFrame{
	
	Vector<TailSnake> vTs = new Vector<TailSnake>(); //n개의 꼬리를 그리는 벡터
	Vector<Snake> vS = new Vector<Snake>(); //머리 1개만 그림
	
	
	Queue<Integer> pX = new LinkedList<Integer>(); //앞 꼬리의 x좌표 
	Queue<Integer> pY = new LinkedList<Integer>(); //앞 꼬리의 y좌표
	
	
	Container cp = getContentPane();
	MyJpanel jp = new MyJpanel();
	Snake s = new Snake();
	TailSnake ts = new TailSnake();
	
	
	int eatCount = 0;
	int tailCount = 0;
	int Count = 0;
	
	
	public SnakeGameFrame() {
		setTitle("Snake Game Frame");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		cp.setLayout(null);
		
		cp.add(jp);
		jp.addKeyListener(new MyKeyListener(jp));
		
		setSize(500,500);
		setResizable(false); //프레임 크기 고정
		setVisible(true);
		setLocationRelativeTo(null); //프레임 위치 가운데
		jp.requestFocus();
	}
	
	class MyJpanel extends JPanel {
		MyJpanel(){
			setSize(500,500);
			setLayout(null);
			setBackground(Color.BLACK);
			vS.add(s);
		}
		public void paintComponent(Graphics g){
			super.paintComponent(g);
			
			
			if(s.getEatcount() == 0){
				g.setColor(Color.WHITE);
				g.fillRect(s.getX(), s.getY(), 10, 10);
				
				g.setColor(Color.RED);
				g.fillRect(s.getEatX(), s.getEatY(), 10, 10);
		}
			
			if(s.getEatcount() >= 1){
				for(int i = 0; i < vS.size() + vTs.size() -1; i++){
					g.setColor(Color.WHITE);
					g.fillRect(s.getX(), s.getY(), 10, 10);
					g.fillRect(vTs.elementAt(i).frontX, vTs.elementAt(i).frontY, 10, 10);
					System.out.println(vTs.elementAt(i).toString());
					
					g.setColor(Color.RED);
					g.fillRect(s.getEatX(), s.getEatY(), 10, 10);
				}
			}
	}
}

	class MyKeyListener extends KeyAdapter{
		MyJpanel jp;
		MyKeyListener(MyJpanel jp) {
			this.jp = jp;
		}
		
		@Override
		public void keyPressed(KeyEvent e) {
			int keyCode = e.getKeyCode();
			switch(keyCode) {
			case KeyEvent.VK_UP:
				s.setY(-10); 
				repaint();
				if(tailCount >= 1){
					for(int i = 0; i < vTs.size(); i++){
						try {
							Thread.sleep(10);
						vTs.elementAt(i).frontX = s.getX();
						vTs.elementAt(i).frontY = s.getY() + (i+1)*10;
						}catch (InterruptedException e1){
							System.out.println(e1);
						}
					}
				}
				if(s.checkMap() == true){
					int a = s.repairPoint();
					s.setY(a,true);
					repaint();
				}
				System.out.println("Snake : " + s.getX() +","+ s.getY());
				System.out.println("Eat : " + s.getEatX() + "," + s.getEatY());
				System.out.println("---------------------");
				if(s.checkPoint()){
					System.out.println(s.checkPoint());
					creatNewPoint();
					s.setEatCount(1);
					creatTail();
				}
				break;
				/////////////////////////////////////
			case KeyEvent.VK_DOWN:
				s.setY(10);
				repaint();
				if(tailCount >= 1){
					for(int i = 0; i < vTs.size(); i++){
						vTs.elementAt(i).frontX = s.getX();
						vTs.elementAt(i).frontY = s.getY() - (i+1)*10;
					}
				}
				if(s.checkMap() == true){
					int a = s.repairPoint();
					s.setY(a,true);
					repaint();
				}
				System.out.println("Snake : " + s.getX() +","+ s.getY());
				System.out.println("Eat : " + s.getEatX() + "," + s.getEatY());
				System.out.println("---------------------");
				if(s.checkPoint()){
					System.out.println(s.checkPoint());
					creatNewPoint();
					s.setEatCount(1);
					creatTail();
				}
				break;
				/////////////////////////////////////////
			case KeyEvent.VK_LEFT:
				s.setX(-10);
				repaint();
				if(tailCount >= 1){
					for(int i = 0; i < vTs.size(); i++){
						vTs.elementAt(i).frontX = s.getX() + (i+1)*10;
						vTs.elementAt(i).frontY = s.getY();
					}
				}
				if(s.checkMap() == true){
					int a = s.repairPoint();
					s.setX(a,true);
					repaint();
				}
				System.out.println("Snake : " + s.getX() +","+ s.getY());
				System.out.println("Eat : " + s.getEatX() + "," + s.getEatY());
				System.out.println("---------------------");
				if(s.checkPoint()){
					System.out.println(s.checkPoint());
					creatNewPoint();
					s.setEatCount(1);
					creatTail();
				}
				break;
				///////////////////////////////////////
			case KeyEvent.VK_RIGHT:
				s.setX(10);
				repaint();
				if(tailCount >= 1){
					for(int i = 0; i < vTs.size(); i++){
						vTs.elementAt(i).frontX = s.getX() - (i+1)*10;
						vTs.elementAt(i).frontY = s.getY();
					}
				}
				
				System.out.println(vTs.size());
				if(s.checkMap() == true){
					int a = s.repairPoint();
					s.setX(a,true);
					repaint();
				}
				System.out.println("Snake : " + s.getX() +","+ s.getY());
				System.out.println("Eat : " + s.getEatX() + "," + s.getEatY());
				System.out.println("---------------------");
				if(s.checkPoint()){ //먹었으면
					System.out.println(s.checkPoint());
					creatNewPoint(); 
					s.setEatCount(1);
					creatTail();
				}
				repaint();
				break;
				////////////////////////////////////////
			case KeyEvent.VK_ESCAPE:
				System.exit(0);
			}
		}
	}

	void creatNewPoint() {
		s.setEatX(s.getRandPoint());
		s.setEatY(s.getRandPoint());
//		System.out.println(s.getEatX());
//		System.out.println(s.getEatY());
	}
	void creatTail() {
		if(s.getEatcount() == 1){
			/*
			 * 1. 꼬리 1개 생성
			 * 2. 꼬리벡터에 저장
			 * 3. 머리 획득
			 * 4. 머리의 x,y좌표를 만든 꼬리 x,y에 대입
			 * 5. 꼬리갯수 1개 증가
			 * 
			 * */
			
			TailSnake tail = new TailSnake();
			vTs.add(tail);
			System.out.println("head : " + s.getX() + "," + s.getY());
			tail.frontX = s.getX();
			tail.frontY = s.getY();
			tailCount++;
			
			Count++;
			System.out.println("tailCount : " + "(" + Count + ")" +  ":" + tailCount);
			System.out.println(tail.toString());
		}
		else if(s.getEatcount() > 1){
			/*
			 * 1. 꼬리 1개 생성
			 * 2. 꼬리벡터에 저장
			 * 3. 이전꼬리 획득
			 * 4. 이전꼬리의 x,y좌표를 현재 꼬리 x,y에 대입
			 * 5. 꼬리 갯수 1개 증가
			 * 
			 * */
			TailSnake tail = new TailSnake();
			vTs.add(tail);
			System.out.println("head : " + s.getX() + "," + s.getY());
			TailSnake frontTail = new TailSnake();
			frontTail = vTs.elementAt(tailCount - 1);
			tail.frontX = frontTail.getFrontX();
			tail.frontY = frontTail.getFrontY();
			tailCount++;
			
			Count++;
			System.out.println("tailCount : " + "(" + Count + ")" +  ":" + tailCount);
			System.out.println("tailCount" + "(" + Count + ")" +  ":" + tail.frontX + 
					"tailCount" + "(" + Count + ")" +  ":" + tail.frontY);
		}
	}
	public static void main(String[] args) {
		new SnakeGameFrame();
	}
}
