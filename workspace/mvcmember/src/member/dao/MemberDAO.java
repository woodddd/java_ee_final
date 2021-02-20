package member.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;



import member.bean.MemberDTO;
import member.bean.ZipcodeDTO;

public class MemberDAO {
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	private DataSource ds;
	
	private static MemberDAO instance;
	

	public MemberDAO() {
		Context context;
		try {
			context = new InitialContext();
			ds = (DataSource)context.lookup("java:comp/env/jdbc/oracle"); //Tomcat의 경우
		
		} catch (NamingException e) {
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
		System.out.println("write실행");
		String sql = "insert into Member values(?,?,?,?,?,?,?,?,?,?,?,?,sysdate)";
		
		
		try {
			conn = ds.getConnection();
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
	
	public void modify(MemberDTO memberDTO) {
		String sql = "update member set name=?"
				                    + ",pwd=?"
				                    + ",gender=?"
				                    + ",email1=?"
				                    + ",email2=?"
				                    + ",tel1=?"
				                    + ",tel2=?"
				                    + ",tel3=?"
				                    + ",zipcode=?"
				                    + ",addr1=?"
				                    + ",addr2=?"
				                    + ",logtime=sysdate where id=?";
		
		
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setNString(1, memberDTO.getName());
			pstmt.setString(2, memberDTO.getPwd());
			pstmt.setString(3, memberDTO.getGender());
			pstmt.setString(4, memberDTO.getEmail1());
			pstmt.setString(5, memberDTO.getEmail2());
			pstmt.setString(6, memberDTO.getTel1());
			pstmt.setString(7, memberDTO.getTel2());
			pstmt.setString(8, memberDTO.getTel3());
			pstmt.setString(9, memberDTO.getZipcode());
			pstmt.setString(10, memberDTO.getAddr1());
			pstmt.setString(11, memberDTO.getAddr2());
			pstmt.setString(12, memberDTO.getId());
			
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public List<MemberDTO> read(){
		List<MemberDTO> dtoList = new ArrayList<MemberDTO>();
		String sql = "select * from Member";
		
		
		try {
			conn = ds.getConnection();
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
	
	
	
	
	
	

	public MemberDTO login(String loginid, String loginpwd) {
	      MemberDTO memberDTO = null;
	      
	      String sql = "select * from member where id=? and pwd=?";
	      
	      
	      try {
	    	 conn = ds.getConnection();
	         pstmt = conn.prepareStatement(sql);//생성
	         pstmt.setString(1, loginid);
	         pstmt.setString(2, loginpwd);
	         
	         rs = pstmt.executeQuery();//실행
	         
	         if(rs.next()) {
	            memberDTO = new MemberDTO();
	            
	            memberDTO.setName(rs.getString("name"));
				memberDTO.setId(rs.getString("id"));
				memberDTO.setPwd(rs.getString("pwd"));
				memberDTO.setGender(rs.getString("gender"));
				memberDTO.setEmail1(rs.getString("email1"));
				memberDTO.setEmail2(rs.getString("email2"));
				memberDTO.setTel1(rs.getString("tel1"));
				memberDTO.setTel2(rs.getString("tel2"));
				memberDTO.setTel3(rs.getString("tel3"));
				memberDTO.setZipcode(rs.getString("zipcode"));
				memberDTO.setAddr1(rs.getString("addr1"));
				memberDTO.setAddr1(rs.getString("addr2"));
	         }
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
	      
	      
	      return memberDTO;
	   }
	
	public List<ZipcodeDTO> getZipcodeList(String sido, String sigungu, String roadname){
		List<ZipcodeDTO> list = new ArrayList<ZipcodeDTO>();
		String sql = "select * from newzipcode "
				   + " where sido like ? and nvl(sigungu,'0') like ? and roadname like ?";
		
		
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%" + sido + "%");
			pstmt.setString(2, "%" + sigungu + "%");
			pstmt.setString(3, "%" + roadname + "%");
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				ZipcodeDTO zipcodeDTO = new ZipcodeDTO();
				zipcodeDTO.setZipcode(rs.getString("zipcode"));
				zipcodeDTO.setSido(rs.getString("sido"));
				zipcodeDTO.setSigungu(rs.getString("sigungu")==null ? "" : rs.getString("sigungu"));
				zipcodeDTO.setYubmyundong(rs.getString("yubmyundong"));
				zipcodeDTO.setRi(rs.getString("ri")==null ? "" : rs.getString("ri"));
				zipcodeDTO.setRoadname(rs.getString("roadname"));
				zipcodeDTO.setBuildingname(rs.getString("buildingname")==null ? "" : rs.getString("buildingname"));
				
				list.add(zipcodeDTO);			
				
			}//while
			
		} catch (SQLException e) {
			e.printStackTrace();
			list = null;
		} finally {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return list;
	}
	
	
	public boolean isExistId(String id) {
		boolean exist = false;
		String sql = "select * from member where id=?";
		
		
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, id);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				exist = true;
			}
			
			System.out.println(rs.next());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
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
		
		return exist;
	}
	
//	public String existId(String id) {
//		String checkId = null;
//		String sql = "select * from member where id=?";
//		getConnection();
//		
//		try {
//			pstmt = conn.prepareStatement(sql);
//			
//			pstmt.setString(1, id);
//			
//			rs = pstmt.executeQuery();
//			
//			if(rs.next()) {
//				checkId = rs.getString("id");
//			} else {
//				checkId = "0";
//			}
//			
//			
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} finally {
//			try {
//				if(rs != null) rs.close();
//				if(pstmt != null) pstmt.close();
//				if(conn != null)conn.close();
//			}catch(SQLException e) {
//				e.printStackTrace();
//			}	
//		}
//		
//		return checkId;
//	}
	
	public MemberDTO getMember(String id){
		MemberDTO memberDTO = null;
		String sql = "select * from member where id=?";
		
		
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				memberDTO = new MemberDTO();
				memberDTO.setName(rs.getString("name"));
                memberDTO.setId(rs.getString("id"));
                memberDTO.setPwd(rs.getString("pwd"));
                memberDTO.setGender(rs.getString("gender"));
                memberDTO.setEmail1(rs.getString("email1"));
                memberDTO.setEmail2(rs.getString("email2"));
                memberDTO.setTel1(rs.getString("tel1"));
                memberDTO.setTel2(rs.getString("tel2"));
                memberDTO.setTel3(rs.getString("tel3"));
                memberDTO.setZipcode(rs.getString("zipcode"));
                memberDTO.setAddr1(rs.getString("addr1"));
                memberDTO.setAddr2(rs.getString("addr2"));
			}//if
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return memberDTO;
	}
}
