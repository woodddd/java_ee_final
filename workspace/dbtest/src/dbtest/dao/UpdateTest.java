package dbtest.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class UpdateTest {
	private Connection conn;
	private PreparedStatement pstmt;
	
	private String driver = "oracle.jdbc.driver.OracleDriver";
	private String url = "jdbc:oracle:thin:@localhost:1521:xe"; 
	private String username = "c##java";
	private String password = "bit";
	
	
	public UpdateTest() {
		// 드라이버 로딩
		try {
			Class.forName(driver);
			System.out.println("드라이버 로딩 성공");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
	}
	
	public void getConnection() {
		//접속
		try {
			conn = DriverManager.getConnection(url,username,password);
			
			System.out.println("접속 성공");
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public void updateArticle() {
		//수정할 이름 입력 : 또(이름에 또가 들어간 레코드를 찾아서 나이를 1살 증가시키세요)
		
		//데이터
		Scanner scan = new Scanner(System.in);
		System.out.println("수정할 이름 입력 : ");
		String name = scan.next();
		
		
		//DB
		getConnection();
		//name 에 입력한 글자를 찾아서 age 를 1증가시킴
		//String sql = "update dbtest set age = age + 1 where name like '%" + name + "%'";
		//자바는 인덱스넘버가 0부터 시작하지만 오라클은 1부터 시작함.
		String sql = "update dbtest set age = age + 1 where name like ?";
		// ? 는 데이터가 들어갈 자리이다.
		
		//ex) String sql = "insert into dbtest values(?,?,? sysdate)";
		// 											  1,2,3 번째 인덱스가 된다.
		//pstmt.setString(1,name);
		//pstmt.setInt(2,age);
		//... // set뒤엔 ? 에 들어갈 타입을 적어줘야 한다.
		
		//그래서 업데이트 실행 전에 ? 를 사용했다면 그 후 ?에 데이터를 주입해 주어야 한다.
		
		try {
			
			pstmt = conn.prepareStatement(sql);//생성
			pstmt.setString(1, "%" + name + "%");//?에 데이터를 주입
			int su = pstmt.executeUpdate();// 업데이트 실행
			
			System.out.println(su + "개의 행이 수정되었습니다.");
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(pstmt != null) {
					pstmt.close();
				}
				if(conn != null) {
					conn.close();
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}//updateArticle()
	
	
	public static void main(String[] args) {
		UpdateTest ut = new UpdateTest();
		ut.updateArticle();
	}

}
