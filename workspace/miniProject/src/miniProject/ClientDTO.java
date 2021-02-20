package miniProject;

public class ClientDTO { //worm Max ea : 3ea
   private String name, id, pw, email, domain;
   private int gender, score;
   
   
   public int getScore() {
	   return score;
   }
   public void setScore(int score) {
	   this.score = score;
	}
	public String getName() {
      return name;
   }
   public void setName(String name) {
      this.name = name;
   }
   public String getId() {
      return id;
   }
   public void setId(String id) {
      this.id = id;
   }
   public String getPw() {
      return pw;
   }
   public void setPw(String pw) {
      this.pw = pw;
   }
   public String getEmail() {
      return email;
   }
   public void setEmail(String email) {
      this.email = email;
   }
   public int getGender() {
      return gender;
   }
   public void setGender(int gender) {
      this.gender = gender;
   }
   public String getDomain() {
	   return domain;
   }
   public void setDomain(String domain) {
	   this.domain = domain;
   }
   
   
}