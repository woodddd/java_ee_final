package dbtest.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SelectTest {
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;//select 한 모든 결과를 담기위한 그릇. select의 모든 결과를 담아줌
	
	private String driver = "oracle.jdbc.driver.OracleDriver";
	private String url = "jdbc:oracle:thin:@localhost:1521:xe";
	private String username = "c##java";
	private String password = "bit";
	
	
	public SelectTest() {
		try {
			Class.forName(driver);
			
			System.out.println("드라이버 로딩 성공");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void getConnection() {
		try {
			conn = DriverManager.getConnection(url,username,password);
			System.out.println("접속 성공");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void selectArticle() {
		//DB
		getConnection();
		String sql = "select * from dbtest";
		
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery(); //select 는 검색을 하기 때문에 executeUpdate 를 사용하지 않음
			//rs 는 크기를 구하지 못한다. 있다 없다만 판별을 해준다.
			//rs.next() 역할 -	1. 현재 위치에 레코드 유(true) 무(false)
			//					2. 다음 레코드로 이동
			
			while(rs.next()) {//orcle 의 테이블 첫행은 1행, 첫열은 1열 부터 시작한다. --자바는 0행,0열부터시작
				//rs.getString("name") -> 레코드중 네임을 꺼내온다.
				//rs.getString(1) 1번컬럼의 값을 꺼내와라
				//rs.getInt("age")
				//rs.getInt(2) 2번컬럼의 값을 꺼내와라
				//rs.getDouble("height")
				//rs.getDate("logtime") - 날짜를 날자 형식으로 가져온다.
				//rs.getString("logtime") - 날짜를 문자열 형식으로 가져온다.
				
				System.out.println(rs.getString("name") + "\t"
						+ rs.getInt("age") + "\t"
						+ rs.getDouble("height") + "\t"
						+ rs.getString("logtime"));
			}//while
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if(rs != null) {
					rs.close();
				}
				if(pstmt != null) {
					pstmt.close();
				}
				if(conn != null) {
					conn.close();
				}
			}catch(SQLException e) {
				e.printStackTrace();
			}	
		}
	}
	
	public static void main(String[] args) {
		SelectTest st = new SelectTest();
		st.selectArticle();
	}

}
