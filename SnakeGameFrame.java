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
	
	Vector<TailSnake> vTs = new Vector<TailSnake>(); //n���� ������ �׸��� ����
	Vector<Snake> vS = new Vector<Snake>(); //�Ӹ� 1���� �׸�
	
	
	Queue<Integer> pX = new LinkedList<Integer>(); //�� ������ x��ǥ 
	Queue<Integer> pY = new LinkedList<Integer>(); //�� ������ y��ǥ
	
	
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
		setResizable(false); //������ ũ�� ����
		setVisible(true);
		setLocationRelativeTo(null); //������ ��ġ ���
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
				if(s.checkPoint()){ //�Ծ�����
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
			 * 1. ���� 1�� ����
			 * 2. �������Ϳ� ����
			 * 3. �Ӹ� ȹ��
			 * 4. �Ӹ��� x,y��ǥ�� ���� ���� x,y�� ����
			 * 5. �������� 1�� ����
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
			 * 1. ���� 1�� ����
			 * 2. �������Ϳ� ����
			 * 3. �������� ȹ��
			 * 4. ���������� x,y��ǥ�� ���� ���� x,y�� ����
			 * 5. ���� ���� 1�� ����
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
