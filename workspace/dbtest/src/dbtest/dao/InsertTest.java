package dbtest.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class InsertTest {
	private Connection conn;
	private PreparedStatement pstmt; 
	// PreparedStatement 를 사용하는 이유!!
	//자바 안에서는 sql문을 사용할 수 없음. 그런데 자바에서 sql 을 사용하여 db로 넘겨줘야 하기 때문에
	// Statement 를 사용한다.
		
	private String driver = "oracle.jdbc.driver.OracleDriver"; 
	private String url = "jdbc:oracle:thin:@localhost:1521:xe"; // thin -> oracle 드라이버의 이름이다. 만약 oracle 이 아닌 것을 사용한다면 드라이버명은 바뀐다.
	//( jdbc:oracle) 은 jdbc를 oracle것을 사용한다는 말임:오라클 드라이버
	// xe : db명
	private String username = "c##java";
	private String password = "bit";
	
	public InsertTest() {//생성하려는 파일이 어떤 타입인지 모를 때, Class 타입으로 잡는다
		// 생성하려는 파일이 클래스인지 인터페이스인지 모르므로  new할 수 없다.
		
		try {
			Class.forName(driver);//( ) 안에는 풀쿼리 네임으로 써야 한다.(패키지명 까지 다 쓰라는말)
			//()안에 들어간 것을 new 하여 Class 타입으로 생성해라. return type - > static Class<?>
			System.out.println("드라이버 로딩 성공");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public void getConnection() {//getConnection 메소드는 static Connection 타입을 반환한다.
		try {
			conn = DriverManager.getConnection(url,username,password);// url,user,password 
			//getConnection() 을 사용하여 지정된 데이터베이스 URL에 대한 연결 설정 시도 
			//getConnection 은 static 타입 이므로 new 로 생성해 줄 필요 없이 사용 가능.
			
			System.out.println("접속 성공");
									//jdbc:oracle:thin:@localhost:1521:xe
		} catch (SQLException e) {// jdbc:oracle:thin:@localhost 데이터가 들어오는곳이 나의 컴퓨터라면 내 IP를 사용해 주면 되지만, 모든 아이피에서 데이터가들어올 수 있게 하려면 localhost를 써주어야함.
			e.printStackTrace();
		}
	}
	
	public void InsertAticle() {
		getConnection(); //오라클에 접속
		
		try {
			String sql = "insert into dbtest values('우석', 27, 183.5, sysdate)";
			pstmt = conn.prepareStatement(sql);// Interface Connection 안에 있는 메소드
			//매개 변수화된 SQL 문을 데이터베이스로 보내기 위한 PreparedStatement 개체를 생성한다.
			int su = pstmt.executeUpdate(); // 업데이트 실행 // 레코드가 몇개가 생성되었는지 int 값으로 반환해줌.
			
			System.out.println(su + "개의 행이 만들어졌습니다.");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally { //무조건 실행해라. 필드에 생성된 순서의 반대로 close 해 주어야 한다.
			try {//만약 pstmt 실행 전에 에러가 발생하면 아무것도 실행되지 않았으므로 닫아주면 안된다.
				if(pstmt != null) {
					pstmt.close();
				}
				if(conn != null) {
					conn.close();
				}
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {
		
		InsertTest it = new InsertTest();
		it.InsertAticle();
	}
	
}
