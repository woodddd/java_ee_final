package imageboard.service;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.control.CommandProcess;
import com.oreilly.servlet.MultipartRequest;

import imageboard.bean.ImageboardDTO;
import imageboard.dao.ImageboardDAO;

public class ImageboardWriteService implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {

		//실제폴더
		String realFolder = request.getServletContext().getRealPath("/storage");
		System.out.println("실제폴더 = " + realFolder);
		
		//업로드 !! 매우중요
		MultipartRequest multi = new MultipartRequest(request
													, realFolder
													, 5*1024*1024
													, "UTF-8");
		//request로 넘어온 애들을 실제경로 측에 업로드 하게 되는 것( 멀티파트리퀘스트에 실제경로를 지정해 줬으므로 
		//실제경로로 데이터가 담겨있음. 그러면 Tomcat(Server)이 켜져있는 동안은 실제 경로에 데이터다 감겨 있는 상태이므로
		//언제든지 다른 폴더에서도 실제경로에 저장된 데이터들을 불러다 쓸 수 있다.
		//데이터
		String imageId = multi.getParameter("imageId");
		String imageName = multi.getParameter("imageName");
		int imagePrice = Integer.parseInt(multi.getParameter("imagePrice"));
		int imageQty = Integer.parseInt(multi.getParameter("imageQty"));
		String imageContent = multi.getParameter("imageContent");
		String image1 = multi.getOriginalFileName("image1");//이름 얻어오기.
		
		//맵을 사용한 방식
//		Map<String,Object> map = new HashMap<String,Object>();
//		map.put("imageId",imageId);
//		map.put("imageName",imageName);
//		map.put("imagePrice",imagePrice);
//		map.put("imageQty", imageQty);
//		map.put("imageContent",imageContent);
//		map.put("image1", image1);
		
		ImageboardDTO imageboardDTO = new ImageboardDTO();
		imageboardDTO.setImageId(imageId);
		imageboardDTO.setImageName(imageName);
		imageboardDTO.setImagePrice(imagePrice);
		imageboardDTO.setImageQty(imageQty);
		imageboardDTO.setImageContent(imageContent);
		imageboardDTO.setImage1(image1);
		
		
		//DB
		ImageboardDAO imageboardDAO = ImageboardDAO.getInstance();
//		imageboardDAO.imageboardWrite(map);
		imageboardDAO.imageboardWrite(imageboardDTO);
		
		//응답
		request.setAttribute("display", "/imageboard/imageboardWrite.jsp");
		return "/main/index.jsp";
	}

}
