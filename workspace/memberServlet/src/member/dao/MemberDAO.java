package member.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import member.bean.MemberDTO;

public class MemberDAO {
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	private String driver = "oracle.jdbc.driver.OracleDriver";
	private String url = "jdbc:oracle:thin:@localhost:1521:xe";
	private String username = "c##java";
	private String password = "bit";
	
	private static MemberDAO instance;
	

	public MemberDAO() {
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
	
	public static MemberDAO getInstance() { //스태틱이 널인 경우는 가장 처음 한 번 뿐.
		if(instance == null) { //getInstance 가 가장 처음 실행될 때만 null 값을 가지고 있음!
			synchronized(MemberDAO.class) {
				instance = new MemberDAO();//처음 실행할 때 딱 1번만 실행된다 - 동기화(synchronized)
			}
		}
		return instance;
	}
	
	public int write(MemberDTO memberDTO) {
		int su = 0;
		String sql = "insert into Member values(?,?,?,?,?,?,?,?,?,?,?,?,sysdate)";
		getConnection();
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1,memberDTO.getName());
			pstmt.setString(2,memberDTO.getId());
			pstmt.setString(3,memberDTO.getPwd());
			pstmt.setString(4,memberDTO.getGender());
			pstmt.setString(5,memberDTO.getEmail1());
			pstmt.setString(6,memberDTO.getEmail2());
			pstmt.setString(7,memberDTO.getTel1());
			pstmt.setString(8,memberDTO.getTel2());
			pstmt.setString(9,memberDTO.getTel3());
			pstmt.setString(10,memberDTO.getZipcode());
			pstmt.setString(11,memberDTO.getAddr1());
			pstmt.setString(12,memberDTO.getAddr2());
			
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
	
	public List<MemberDTO> read(){
		List<MemberDTO> dtoList = new ArrayList<MemberDTO>();
		String sql = "select * from Member";
		getConnection();
		
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				MemberDTO memberdto = new MemberDTO();
				
				memberdto.setName(rs.getString("name"));
				memberdto.setId(rs.getString("id"));
				memberdto.setPwd(rs.getString("pwd"));
				memberdto.setGender(rs.getString("gender"));
				memberdto.setEmail1(rs.getString("email1"));
				memberdto.setEmail2(rs.getString("email2"));
				memberdto.setTel1(rs.getString("tel1"));
				memberdto.setTel2(rs.getString("tel2"));
				memberdto.setTel3(rs.getString("tel3"));
				memberdto.setZipcode(rs.getString("zipcode"));
				memberdto.setAddr1(rs.getString("addr1"));
				memberdto.setAddr1(rs.getString("addr2"));
				
				dtoList.add(memberdto);
				
			}
			
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
		
		return dtoList;
		
	}

	public String login(String loginid, String loginpwd) {
		String name = null;
		String sql = "select * from member where id=? and pwd=?";
		getConnection();
		
		try {
			pstmt = conn.prepareStatement(sql);//생성
			pstmt.setString(1, loginid);
			pstmt.setString(2, loginpwd);
			
			rs = pstmt.executeQuery();//실행
			
			if(rs.next()) name = rs.getString("name");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn != null)conn.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}	
		}
		
		
		return name;
	}
	
	
}
