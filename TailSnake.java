public class TailSnake {

	int frontX;
	int frontY;
	
	void setFrontX(int x){
		this.frontX = x;
	}
	
	void setFrontY(int y){
		this.frontY = y;
	}
	int getFrontX(){
		return this.frontX;
	}
	
	int getFrontY(){
		return this.frontY;
	}

	@Override
	public String toString() {
		return "TailSnake [frontX=" + frontX + ", frontY=" + frontY + "]";
	}
	
	
}
