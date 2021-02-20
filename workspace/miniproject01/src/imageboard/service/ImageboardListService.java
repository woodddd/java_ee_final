package imageboard.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.control.CommandProcess;

import imageboard.bean.ImageboardDTO;
import imageboard.bean.ImageboardPaging;
import imageboard.dao.ImageboardDAO;

public class ImageboardListService implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		//데이터
		int pg = Integer.parseInt(request.getParameter("pg"));
		
		//1페이지당 3개씩 뿌릴거임.
		int endNum = pg*3;
		int startNum = endNum -2;
		
		//DB
		ImageboardDAO imageboardDAO = ImageboardDAO.getInstance();
		List<ImageboardDTO> list = imageboardDAO.imageboardList(startNum, endNum);
		
		//페이징 처리
		int totalA = imageboardDAO.getTotalA();
		
		ImageboardPaging imageboardPaging = new ImageboardPaging();
		imageboardPaging.setCurrentPage(pg);
		imageboardPaging.setPageBlock(3);
		imageboardPaging.setPageSize(3);
		imageboardPaging.setTotalA(totalA);
		
		imageboardPaging.makePagingHTML();
		
		
		request.setAttribute("pg", pg);
		request.setAttribute("list", list);
		request.setAttribute("imageboardPaging", imageboardPaging);
		request.setAttribute("display", "/imageboard/imageboardList.jsp");
		return "/main/index.jsp";
	}

}
