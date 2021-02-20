package board.dao;

import java.io.IOException;
import java.io.Reader;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import board.bean.BoardDTO;


public class BoardDAO {
	private static BoardDAO instance;
	private SqlSessionFactory sqlSessionFactory;
	
	public BoardDAO() {
		try {
			//InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml"); //바이트단위
			Reader reader = Resources.getResourceAsReader("mybatis-config.xml");//char 단위
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);  //build는 내가 사용하고자 하는 원하는 매개변수의 값을 매칭시켜줄 수 있음
		} catch (IOException e) {
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
		SqlSession sqlSession = sqlSessionFactory.openSession();
		sqlSession.insert("boardSQL.boardWrite", map);
		sqlSession.commit();
		sqlSession.close();
		
	}//boardwrite
	
	
	public BoardDTO boardView(int seq) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		BoardDTO boardDTO = sqlSession.selectOne("boardSQL.boardView",seq);
		sqlSession.close();
		
		return boardDTO;
		
	}
	
	
	
	public int getTotalA() {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		int totalA = sqlSession.selectOne("boardSQL.getTotalA");
		sqlSession.close();
		
		return totalA;
	}
	
	
	public List<BoardDTO> boardList(int startNum,int endNum){
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("startNum", startNum);
		map.put("endNum", endNum);
		
		SqlSession sqlSession = sqlSessionFactory.openSession();
		List<BoardDTO> list = sqlSession.selectList("boardSQL.boardList", map);
		sqlSession.close();
		
		return list;
		
	}
	
	
	public void hitUpdate(int seq) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		
		sqlSession.update("boardSQL.hitUpdate",seq);
		
		sqlSession.commit();
		sqlSession.close();
		
	}
	
	public void boardModify(Map<String, Object> map) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		
		sqlSession.update("boardSQL.boardModify", map);
		
		sqlSession.commit();
		sqlSession.close();
		
	}//boardModify
	
public void boardDelete(int seq) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		
//		sqlSession.update("boardSQL.sql1",seq);
//		sqlSession.commit();
//		
//		sqlSession.update("boardSQL.sql2",seq);
//		sqlSession.commit();
		
		sqlSession.delete("boardSQL.boardDelete",seq);
		sqlSession.commit();
		sqlSession.close();
		
	}
	
	


	public void boardReply(Map<String, String> map) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		
		BoardDTO pDTO = boardView(Integer.parseInt(map.get("pseq")));
		
		sqlSession.update("boardSQL.sql4",pDTO);
		
//		Map<String,Object> replymap = new HashMap<String,Object>();
//		replymap.put("id", map.get("id"));
//		replymap.put("name", map.get("name"));
//		replymap.put("email", map.get("email"));
//		replymap.put("subject", map.get("subject"));
//		replymap.put("content", map.get("content"));
		map.put("ref", pDTO.getRef()+"");
		map.put("lev", pDTO.getLev()+1+"");
		map.put("step", pDTO.getStep()+1+"");
//		replymap.put("pseq", Integer.parseInt(map.get("pseq")));
		
		sqlSession.insert("boardSQL.sql5", map);
		
		sqlSession.update("boardSQL.sql6", pDTO.getSeq());
		sqlSession.commit();
		sqlSession.close();
	      
	}


	public List<BoardDTO> boardSearch(Map<String, Object> map) {
		
		SqlSession sqlSession = sqlSessionFactory.openSession();
		List<BoardDTO> list = sqlSession.selectList("boardSQL.boardSearch",map);
		sqlSession.close();
		
		return list;
	}


	public int getSearchTotalA(Map<String, Object> map) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		int totalA = sqlSession.selectOne("boardSQL.getSearchTotalA",map);
		sqlSession.close();
		
		return totalA;
	}

}
