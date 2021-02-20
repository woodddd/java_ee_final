package miniProject;

public class ScoreDTO {
   private int score, x, y;
   private String win, lose;
   

   
   public ScoreDTO(int x, int y) {
	this.x= x;
	this.y= y;
}
   
   
   public int getScore() {
      return score;
   }
   
   public void setScore(int score) {
      this.score = score;
   }
   
   public String getWin() {
      return win;
   }
   
   public void setWin(String win) {
      this.win = win;
   }
   
   public String getLoose() {
      return lose;
   }
   
   public void setLose(String lose) {
      this.lose = lose;
   }
  
   
   //x, y 좌표=============================================
	public int getX() {
		return x;
	}
	
	public void setX(int x) {
		this.x = x;
	}
	
	public int getY() {
		return y;
	}
	
	public void setY(int y) {
		this.y = y;
	}

   
   
   
   


}
