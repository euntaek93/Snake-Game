

public class Snake {
	
	private int x = 150;
	private int y = 250;
//	private int preX = 0;
//	private int preY = 0;
	private int eatX = 260;
	private int eatY = 250;
	
	private int eatCount = 0;
	
	static final int mapMaxX = 500;
	static final int mapMaxY = 500;
	static final int mapMinX = -10;
	static final int mapMinY = -10;
	
	int getX() {
		return this.x;
	}
	
	int getY() {
		return this.y;
	}

	void setX(int x) {
		this.x = this.x + x;
	}
	void setY(int y) {
		this.y = this.y + y;
	}
	
	void setX(int x, boolean val){
		this.x = x;
	}
	
	void setY(int y, boolean val){
		this.y = y;
	}
	
	int getEatX() {
		return this.eatX;
	}
	
	int getEatY() {
		return this.eatY;
	}
	
	void setEatX(int x) {
		this.eatX = x;
	}
	
	void setEatY(int y) {
		this.eatY = y;
	}
	
	int getEatcount() {
		return this.eatCount;
	}
	
	void setEatCount(int eatCount) {
		this.eatCount += eatCount;
	}
	
	
//	int getPreX() {
//		return this.preX;
//	}
//	int getPreY() {
//		return this.preY;
//	}
	
	int getRandPoint() {
		
		int x = (int)(Math.random() * 500 + 1);
//		int prexxNumber = x;
//		int modNumber = x % 10;
//		int resultNumber = prexxNumber - modNumber;
		if (x % 10 == 0)
			return x;
		else if((x > 1) && (x < 9))
			return 10;
		else if((x > 11) && (x < 479)){
			int prexxNumber = x;
			int modNumber = x % 10;
			int resultNumber = prexxNumber - modNumber;
			
			return resultNumber;
		}
		return 250;
			
			
	}
	
	boolean checkPoint(){
		if((this.x == this.eatX) && (this.y == this.eatY))
			return true;
		else
			return false;
	}
	
	boolean checkMap() {
		if(((x > mapMinX) && (x < mapMaxX)) && ((y > mapMinY) && (y < mapMaxY)))
			return false; //¸Ê ³»ºÎ
		else
			return true; //¸Ê ÀÌÅ»
	}
	
	int repairPoint() {
		if(this.x == mapMinX){
			 return 0;
		}
		if(this.x == mapMaxX){
			return 490;
		}
		if(this.y == mapMaxY){
			return 490;
		}
		if(this.y == mapMinY){
			return 0;
		}
		return 0;
	}
}
