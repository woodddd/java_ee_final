package imageboard.service;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.control.CommandProcess;

import imageboard.dao.ImageboardDAO;

public class ImageboardDeleteService implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		//데이터
		String[] seq = request.getParameterValues("check");
		
		ImageboardDAO imageboardDAO = ImageboardDAO.getInstance();
		
//		for(int i = 0; i < seq.length ; i++) {
//			imageboardDAO.imageboardDelete(seq[i]);
//		}
		imageboardDAO.imageboardDelete(seq);
		
		request.setAttribute("display", "/imageboard/imageboardDelete.jsp");
		return "/main/index.jsp";
	}

}
