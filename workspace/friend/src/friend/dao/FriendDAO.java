package friend.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import friend.bean.FriendDTO;

public class FriendDAO {
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	private String driver = "oracle.jdbc.driver.OracleDriver";
	private String url = "jdbc:oracle:thin:@localhost:1521:xe";
	private String username = "c##java";
	private String password = "bit";
	
	private static FriendDAO instance;
	
	public FriendDAO() {
		try {
			Class.forName(driver);
			System.out.println("드라이버 로딩 성공");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}//생성자
	
	public void getConnection() {
		try {
			conn = DriverManager.getConnection(url,username,password);
			System.out.println("접속 성공");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static FriendDAO getInstance() { //스태틱이 널인 경우는 가장 처음 한 번 뿐.
		if(instance == null) { //getInstance 가 가장 처음 실행될 때만 null 값을 가지고 있음!
			synchronized(FriendDAO.class) {
				instance = new FriendDAO();//처음 실행할 때 딱 1번만 실행된다 - 동기화(synchronized)
			}
		}
		return instance;
	}

	public int insertArticle(FriendDTO dto) {
		int su = 0;
		String sql = "insert into friend values(?,?,?,?,?,?,?,?,?,?,?)";
		getConnection();
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, dto.getSeq());
			pstmt.setString(2, dto.getName());
			pstmt.setString(3, dto.getTel1());
			pstmt.setString(4, dto.getTel2());
			pstmt.setString(5, dto.getTel3());
			pstmt.setInt(6, dto.getGender());
			pstmt.setInt(7, dto.getRead());
			pstmt.setInt(8, dto.getMovie());
			pstmt.setInt(9, dto.getMusic());
			pstmt.setInt(10, dto.getGame());
			pstmt.setInt(11, dto.getShopping());
			
			su = pstmt.executeUpdate();//실행한 행의 갯수 반환
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
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
		return su;
	}//메소드 닫ㄷ는거

	public int getSeq() { //db로 부터 Seq 넘버를 받아오기위해 사용
		int seq = 0;
		String sql = "select seq_friend.nextval from dual";
		getConnection();
		
		try {
			pstmt = conn.prepareStatement(sql);//생성
			rs = pstmt.executeQuery();//실행
			
			rs.next(); //가상의 테이블(dual) 에 입력된 값은 하나밖에 없기 때문에 while문을 돌리지 않아도 된다.
			//rs.next()를 실행하게 되면 1번째 레코드 부터 시작하여 값이 존재하면 true/ 없으면 false 를 하게 된다.
			seq = rs.getInt(1);// 현재 컬럼명으로 nextval 이 나오게 되는데, 컬럼명을 사용하기 애매하니까 1번째 필드의 값을 가져오라고 말한 것임.
			//현재 가상의 테이블 dual 에 는 1개의 필드와 1개의 레코드만이 존재한다. 그래서 next가 가리키는 값을 가져오게 하면 seq값을 가져올 수 있는 것이다.
			
			
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
		
		System.out.println("seq  = " + seq);
		return seq;
	}//getSeq();

	public List<FriendDTO> getFriendList() {
		List<FriendDTO> dtoList = new ArrayList<FriendDTO>();//List는 인터페이스 이기 때문에 new할 수 없음 
		String sql = "select * from friend order by seq";
		getConnection();
		
		try {
			pstmt = conn.prepareStatement(sql);//생성
			rs = pstmt.executeQuery();//실행
			
			while(rs.next()) {
				FriendDTO dto = new FriendDTO();
				
				dto.setSeq(rs.getInt("seq"));
				dto.setName(rs.getString("name"));
				dto.setTel1(rs.getString("tel1"));
				dto.setTel2(rs.getString("tel2"));
				dto.setTel3(rs.getString("tel3"));
				dto.setGender(rs.getInt("gender"));
				dto.setRead(rs.getInt("read"));
				dto.setMovie(rs.getInt("movie"));
				dto.setMusic(rs.getInt("music"));
				dto.setGame(rs.getInt("game"));
				dto.setShopping(rs.getInt("shopping"));
				
				dtoList.add(dto);
							
			}
		} catch (SQLException e) {
			
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
		
		
		return dtoList;
	}

	
	public int updateArticle(FriendDTO dto) {
		int su = 0;
		String sql = "update friend set seq=?, name=?, tel1=?,tel2=?, tel3=?, gender=?, read=?, movie=?, music=?, game=?, shopping=? where seq =  " + dto.getSeq() ;
		getConnection();
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, dto.getSeq());
			pstmt.setString(2, dto.getName());
			pstmt.setString(3, dto.getTel1());
			pstmt.setString(4, dto.getTel2());
			pstmt.setString(5, dto.getTel3());
			pstmt.setInt(6, dto.getGender());
			pstmt.setInt(7, dto.getRead());
			pstmt.setInt(8, dto.getMovie());
			pstmt.setInt(9, dto.getMusic());
			pstmt.setInt(10, dto.getGame());
			pstmt.setInt(11, dto.getShopping());
			
			su = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
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
		return su;
	}

	public int deleteArticle(FriendDTO dto) {
		int su = 0;
		String sql = "delete friend where seq = " + dto.getSeq();
		getConnection();
		
		try {
			pstmt = conn.prepareStatement(sql);
			su = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
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
		return su;
	}
	

}
