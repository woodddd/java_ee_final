package user.dao;

import java.io.IOException;
import java.io.Reader;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import user.bean.UserDTO;

public class UserDAO {
   private static UserDAO instance;
   private SqlSessionFactory sqlSessionFactory;
   
   public static UserDAO getInstance() {
      if(instance==null) {
         synchronized (UserDAO.class) {
            instance = new UserDAO();
         }
      }
      return instance;
   }
   
   public UserDAO(){
	   //mybatis-config.xml의 내용 읽어오기
	  
	   try {
		//InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml"); //바이트단위
		Reader reader = Resources.getResourceAsReader("mybatis-config.xml");//char 단위
		sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);  //build는 내가 사용하고자 하는 원하는 매개변수의 값을 매칭시켜줄 수 있음
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} 
   }

	public int write(UserDTO userDTO) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		int su = sqlSession.insert("userSQL.write", userDTO); // mapper.xml에서 <insert> 태그 찾아가라.
		
		sqlSession.commit();
		sqlSession.close();
		
		return su;
	}

	public List<UserDTO> getUserList() {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		List<UserDTO> list = sqlSession.selectList("userSQL.getUserList");
		sqlSession.close();
		
		//결과(rs) 1개 - selectOne()
		//결과(rs) 2개 이상 - selectList()
		return list;
	}
	
	public UserDTO getUser(String id) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		UserDTO userDTO = sqlSession.selectOne("userSQL.getUser",id);
		sqlSession.close();
		
		return userDTO;
	}

	public void modify(Map<String, String> map) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		
		int su = sqlSession.update("userSQL.modify",map);
		
		sqlSession.commit();
		sqlSession.close();
		
	}
	

	public void delete(String id) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		
		sqlSession.delete("userSQL.delete",id);
		
		sqlSession.commit();
		sqlSession.close();
		
	}


//	내가한거
//	public List<UserDTO> search(UserDTO userDTO) {
//		SqlSession sqlSession = sqlSessionFactory.openSession();
//		List<UserDTO> list = null;
//		
//		list = sqlSession.selectList("userSQL.search", userDTO);
//		
//		sqlSession.close();
//		
//		return list;
//	}
	
	//강사님이한거
	public List<UserDTO> search(Map<String, String> map) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		List<UserDTO> list = sqlSession.selectList("userSQL.search",map);
		sqlSession.close();
		return list;
	}
	
}