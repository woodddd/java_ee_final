package student.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import student.bean.StudentDTO;

public class StudentDAO {
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	private String driver = "oracle.jdbc.driver.OracleDriver";
	private String url = "jdbc:oracle:thin:@localhost:1521:xe";
	private String username = "c##java";
	private String password = "bit";
	
	private static StudentDAO instance;

	public StudentDAO() {
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public void getConnection() {
		try {
			conn = DriverManager.getConnection(url, username, password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static StudentDAO getInstance() {
		if(instance==null) {
			synchronized (StudentDAO.class) {
				instance = new StudentDAO();
			}
		}
		return instance;
	}
	
	public int insert(StudentDTO studentDTO) {
		int su = 0;
		String sql = "insert into studentprj values(?,?,?,?,?,?,?)";
		getConnection();
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1,studentDTO.getNum());
			pstmt.setString(2,studentDTO.getName());
			pstmt.setInt(3, studentDTO.getKor());
			pstmt.setInt(4, studentDTO.getEng());
			pstmt.setInt(5, studentDTO.getMath());
			pstmt.setInt(6, studentDTO.getHab());
			pstmt.setDouble(7, studentDTO.getAverage());
			
			su = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return su;
	}
	
	public List<StudentDTO> select() {
		List<StudentDTO> list = new ArrayList<StudentDTO>();
		String sql = "select * from studentprj order by num";
		getConnection();
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				StudentDTO studentDTO = new StudentDTO();
				
				studentDTO.setNum(rs.getString("num"));
				studentDTO.setName(rs.getString("name"));
				studentDTO.setKor(rs.getInt("kor"));
				studentDTO.setEng(rs.getInt("eng"));
				studentDTO.setMath(rs.getInt("math"));
				studentDTO.setHab(rs.getInt("hab"));
				studentDTO.setAverage(rs.getDouble("average"));
				
				list.add(studentDTO);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			list = null;
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
		}//finally
		return list;
	}
	
}
