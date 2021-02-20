package member.dao;

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

import member.bean.MemberDTO;
import member.bean.ZipcodeDTO;

public class MemberDAO {
	private static MemberDAO instance;
	private SqlSessionFactory sqlSessionFactory;
	
	
	

	public MemberDAO() {
		try {
			//InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml"); //바이트단위
			Reader reader = Resources.getResourceAsReader("mybatis-config.xml");//char 단위
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);  //build는 내가 사용하고자 하는 원하는 매개변수의 값을 매칭시켜줄 수 있음
		} catch (IOException e) {
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
		SqlSession sqlSession = sqlSessionFactory.openSession();
		int su = sqlSession.insert("memberSQL.write", memberDTO);
		sqlSession.commit();
		sqlSession.close();
		
		return su;
	}
	
	public void modify(MemberDTO memberDTO) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		sqlSession.update("memberSQL.modify", memberDTO);
		sqlSession.commit();
		sqlSession.close();
		
	}
	
	public List<MemberDTO> read(){
		SqlSession sqlSession = sqlSessionFactory.openSession();
		List<MemberDTO> dtoList = sqlSession.selectList("memberSQL.read");
		sqlSession.close();
				
		
		return dtoList;
		
	}
	
	
	
	
	
	

	public MemberDTO login(String id, String pwd) {
		Map<String,String> map = new HashMap<String,String>();
		map.put("id",id);
		map.put("pwd",pwd);
		
		SqlSession sqlSession = sqlSessionFactory.openSession();
	    MemberDTO loginMemberDTO = sqlSession.selectOne("memberSQL.login",map);
	    sqlSession.close();
	    
	    return loginMemberDTO;
	    }
	
	
	public List<ZipcodeDTO> getZipcodeList(String sido, String sigungu, String roadname){
		Map<String,String> map = new HashMap<String,String>();
		map.put("sido",sido);
		map.put("sigungu",sigungu);
		map.put("roadname",roadname);
		
		SqlSession sqlSession = sqlSessionFactory.openSession();
		List<ZipcodeDTO> list = sqlSession.selectList("memberSQL.getZipcodeList",map);
		sqlSession.close();
		
		return list;
		
	}
	
	
	public boolean isExistId(String id) {
		
		boolean exist = false;
		SqlSession sqlSession = sqlSessionFactory.openSession();
		
		MemberDTO memberDTO = sqlSession.selectOne("memberSQL.isExistId", id);
		
		
		if(memberDTO != null) {
			exist = true;
		}
		sqlSession.close();
		
		return exist;
				
	}

	
	public MemberDTO getMember(String id){
		
		SqlSession sqlSession = sqlSessionFactory.openSession();
		MemberDTO memberDTO = sqlSession.selectOne("memberSQL.getMember", id);
		sqlSession.close();
		
		return memberDTO;
	}
}
