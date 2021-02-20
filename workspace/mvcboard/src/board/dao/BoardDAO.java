package board.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import board.bean.BoardDTO;


public class BoardDAO {
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	private DataSource ds;
	
	private static BoardDAO instance;
	
	public BoardDAO() {
		
		Context context;
		try {
			context = new InitialContext();
			ds = (DataSource) context.lookup("java:comp/env/jdbc/oracle");//톱캣인 경우 접두사를 붙여주어야 한다. java:comp/env  
			//jdbc/oracle - context.xml 에 설정한 name
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	public static BoardDAO getInstance() {
		if(instance == null) {
			synchronized(BoardDAO.class) {
				instance = new BoardDAO();
			}
		}
		return instance;
	}
	
	public void boardWrite(Map<String, String> map) {
		String sql = "insert into board values(seq_board.nextval,?,?,?,?,?,seq_board.currval,0,0,0,0,0,sysdate)";
		
		
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, map.get("id"));
			pstmt.setString(2, map.get("name"));
			pstmt.setString(3, map.get("email"));
			pstmt.setString(4, map.get("subject"));
			pstmt.setString(5, map.get("content"));
	
			
			pstmt.executeUpdate();
			
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
	}//write
	
	
	public List<BoardDTO> listRead(){
		List<BoardDTO> boardList = new ArrayList<BoardDTO>();
		String sql = "select seq,id,name,email,subject,content,"
				+ "ref,lev,step,pseq,reply,hit,"
				+"to_char(logtime,'YYYY.MM.DD') as logtime  from board order by 1 desc"; 
		//order by 1 desc 오더바이절은 필수가아님 select 해서 그냥 가져오게 된다면 레코드 행의 순서 없이 그냥 가져오기 때문에 내가 정렬을 시켜서 데이터를 담아준 것임.
		
		
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				BoardDTO boardDTO = new BoardDTO();
				
				boardDTO.setSeq(rs.getInt("seq"));
				boardDTO.setId(rs.getString("id"));
				boardDTO.setName(rs.getString("name"));
				boardDTO.setEmail(rs.getString("email"));
				boardDTO.setSubject(rs.getString("subject"));
				boardDTO.setContent(rs.getString("content"));
				boardDTO.setRef(rs.getInt("ref"));
				boardDTO.setLev(rs.getInt("lev"));
				boardDTO.setStep(rs.getInt("step"));
				boardDTO.setPseq(rs.getInt("pseq"));
				boardDTO.setReply(rs.getInt("reply"));
				boardDTO.setHit(rs.getInt("hit"));
				boardDTO.setLogtime(rs.getString("logtime"));
				
				boardList.add(boardDTO);
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
			boardList = null; // List<BoardDTO> boardList = new ArrayList<BoardDTO>(); 
			//리스트를 List<BoardDTO> boardList = null 이 아닌 생성자를 통한 생성을 하고 값을 넣게 되면 sql 문이 들어가서 에러가 발생하게 되었을 때,
			//null 값을 반환하지 않기 때문에 수신 쪽에서 많은 에러를 발생한다.
			//그래서 catch 문에서 에러가 발생하면 boardList 를 null 로 만들어 주는것이 중요한 포인트이다.
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
		return boardList;
	}
	
	public BoardDTO boardView(int seq) {
		BoardDTO boardDTO = null;
		String sql = "select * from board where seq=?";
		
		
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, seq);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				boardDTO = new BoardDTO();
				
				boardDTO.setSeq(rs.getInt("seq"));
				boardDTO.setId(rs.getString("id"));
				boardDTO.setName(rs.getString("name"));
				boardDTO.setEmail(rs.getString("email"));
				boardDTO.setSubject(rs.getString("subject"));
				boardDTO.setContent(rs.getString("content"));
				boardDTO.setRef(rs.getInt("ref"));
				boardDTO.setLev(rs.getInt("lev"));
				boardDTO.setStep(rs.getInt("step"));
				boardDTO.setPseq(rs.getInt("pseq"));
				boardDTO.setReply(rs.getInt("reply"));
				boardDTO.setHit(rs.getInt("hit"));
				boardDTO.setLogtime(rs.getString("logtime"));
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
		return boardDTO;
	}
	
	
	
	public int getTotalA() {
		int totalA = 0;
		String sql = "select count(*) from board";
		
		
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			
			
			if(rs.next()) {
				totalA = rs.getInt(1);	
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
		return totalA;
		
	}
	
	
	public List<BoardDTO> boardList(int startNum,int endNum){
		List<BoardDTO> list = new ArrayList<BoardDTO>();
		 String sql = "select * from"
	               + "(select rownum rn, tt.* from" 
	               + "(select seq, id, name, email, subject, content,"
	               + " ref, lev, step, pseq, reply, hit,"
	               + " to_char(logtime, 'YYYY.MM.DD') as logtime from board order by ref desc, step asc)tt"
	               + ")where rn>=? and rn<=?";

	
		
		
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, startNum);
			pstmt.setInt(2, endNum);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				BoardDTO boardDTO = new BoardDTO();
				
				boardDTO.setSeq(rs.getInt("seq"));
				boardDTO.setId(rs.getString("id"));
				boardDTO.setName(rs.getString("name"));
				boardDTO.setEmail(rs.getString("email"));
				boardDTO.setSubject(rs.getString("subject"));
				boardDTO.setContent(rs.getString("content"));
				boardDTO.setRef(rs.getInt("ref"));
				boardDTO.setLev(rs.getInt("lev"));
				boardDTO.setStep(rs.getInt("step"));
				boardDTO.setPseq(rs.getInt("pseq"));
				boardDTO.setReply(rs.getInt("reply"));
				boardDTO.setHit(rs.getInt("hit"));
				boardDTO.setLogtime(rs.getString("logtime"));
				
				list.add(boardDTO);
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
		}
		return list;
		
	}
	
	public void hitUpdate(int seq) {
		String sql = "update board set hit = hit+1 where seq=?";
		
		
		try {
			conn = ds.getConnection();
			pstmt= conn.prepareStatement(sql);
			
			pstmt.setInt(1, seq);
			
			pstmt.executeUpdate();
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
		
	}


	public void boardReply(Map<String, String> map) {
	      String step_sql = "update board set step=step+1 where ref=? and step>?";
	      String sql = "insert into board values(seq_board.nextval"
	                                 + ", ?" //아이디
	                                 + ", ?" //이름
	                                 + ", ?" //이메일
	                                 + ", ?" //제목
	                                 + ", ?" //내용
	                                 + ", ?" //원글ref
	                                 + ", ?" //원글lev + 1
	                                 + ", ?" //원글step + 1
	                                 + ", ?" //pseq
	                                 + ", 0" //답글수
	                                 + ", 0" //조회수
	                                 + ", sysdate)";
	      String reply_sql = "update board set reply=reply+1 where seq=?";
	      
	      //원글
	      BoardDTO pDTO = boardView(Integer.parseInt(map.get("pseq")));
	      
	      try {
	         conn = ds.getConnection();
	         
	         //step update
	         pstmt = conn.prepareStatement(step_sql);
	         pstmt.setInt(1, pDTO.getRef()); //원글ref
	         pstmt.setInt(2, pDTO.getStep()); //원글step
	         pstmt.executeUpdate();
	         pstmt.close();
	         
	         pstmt = conn.prepareStatement(sql);
	         pstmt.setString(1, map.get("id"));
	         pstmt.setString(2, map.get("name"));
	           pstmt.setString(3, map.get("email"));
	           pstmt.setString(4, map.get("subject"));
	           pstmt.setString(5, map.get("content"));
	           pstmt.setInt(6, pDTO.getRef());//원글ref
	           pstmt.setInt(7, pDTO.getLev()+1);//원글lev + 1
	           pstmt.setInt(8, pDTO.getStep()+1);//원글step + 1
	           pstmt.setInt(9, Integer.parseInt(map.get("pseq")));
	           pstmt.executeUpdate();
	         pstmt.close();
	         
	         pstmt = conn.prepareStatement(reply_sql);
	         pstmt.setInt(1, Integer.parseInt(map.get("pseq")));//원글번호
	         pstmt.executeUpdate();
	         
	      } catch (SQLException e) {
	         e.printStackTrace();
	      }finally {
	         try {
	            if (pstmt != null) pstmt.close();
	            if (conn != null) conn.close();
	         } catch (SQLException e) {
	            e.printStackTrace();
	         }
	      }
	      
	   }


	public void boardModify(Map<String, Object> map) {
		String sql = "update board set subject=?, content=?, logtime=sysdate where seq=?";
		
		try {
			conn = ds.getConnection();
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, (String) map.get("subject"));
			pstmt.setString(2, (String) map.get("content"));
			pstmt.setInt(3, (Integer) (map.get("seq")));
			
			pstmt.executeUpdate();
			
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
		
	}//boardModify


	public void boardDelete(int seq) {
		
		String sql ="";
		 
		try {
			conn = ds.getConnection();
			
			//원글을 찾아서 답글수(reply) 감소
			sql = "update board set reply = reply - 1 where seq = (select pseq from board where seq=?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, seq);
			pstmt.executeUpdate();
			pstmt.close();
			
			//답글을 찾아서 subject 를 수정;
			sql = "update board set subject=concat('[원글이 삭제된 답글]',subject) where pseq=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, seq);
			pstmt.executeUpdate();
			pstmt.close();
			
			//삭제
			sql = "delete from board where seq=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, seq);
			pstmt.executeUpdate();
			
			
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
		
	}

	
	
}
