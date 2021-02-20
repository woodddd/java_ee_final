package imageboard.dao;

import java.io.IOException;
import java.io.Reader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import imageboard.bean.ImageboardDTO;

public class ImageboardDAO {
	private static ImageboardDAO instance;
	private SqlSessionFactory sqlSessionFactory;
	
	public ImageboardDAO() {
		
		try {
			Reader reader = Resources.getResourceAsReader("mybatis-config.xml");
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static ImageboardDAO getInstance() {
		if(instance == null) {
			synchronized(ImageboardDAO.class) {
				instance = new ImageboardDAO();
			}
		}
		return instance;
	}

	public void imageboardWrite(ImageboardDTO imageboardDTO) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		sqlSession.insert("imageboardSQL.imageboardWrite",imageboardDTO);
		sqlSession.commit();
		sqlSession.close();
		
	}

	public List<ImageboardDTO> imageboardList(int startNum, int endNum) {
		
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("startNum", startNum);
		map.put("endNum",endNum);
		
		
		SqlSession sqlSession = sqlSessionFactory.openSession();
		List<ImageboardDTO> list = sqlSession.selectList("imageboardSQL.imageboardList", map);
		sqlSession.close();
		
		return list;
	}

	public int getTotalA() {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		int totalA = sqlSession.selectOne("imageboardSQL.getTotalA");
		sqlSession.close();
		
		return totalA;
	}


	public ImageboardDTO imageboardView(int seq) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		ImageboardDTO imageboardDTO = sqlSession.selectOne("imageboardSQL.imageboardView",seq);
		sqlSession.close();
		
		return imageboardDTO;
	}
	
	public void imageboardDelete(String[] seq) {
		//매퍼에서 배열이 안들어가서  Map 에 담아서 보낸거
		Map<String, String[]> map = new HashMap<String,String[]>();
		map.put("array", seq);
		
		SqlSession sqlSession = sqlSessionFactory.openSession();
		sqlSession.delete("imageboardSQL.imageboardDelete", seq);
		
		sqlSession.commit();
		sqlSession.close();
		
	}
	
}
