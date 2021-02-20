package board.bean;

import lombok.Data;

@Data
public class BoardPaging {
	private int currentPage;//현재페이지
	private int pageBlock;//[이전][1][2][3][다음]
	private int pageSize;//1페이지당5개씩
	private int totalA;//총글수
	private StringBuffer pagingHTML; //StringBuffer 타입은 수정이 가능하다.
	
	public void makePagingHTML() {
		pagingHTML = new StringBuffer();
		//총페이지수
		
		int totalP = (totalA + (pageSize-1)) / pageSize;//총 페이지수
		
		int startPage = (currentPage -1) / pageBlock * pageBlock + 1;//페이지 시작번호
		
		int endPage = startPage + pageBlock - 1;//페이지 끝번호
		if(endPage > totalP) {
			endPage = totalP;
		}
		
		if(startPage > pageBlock) {
			pagingHTML.append("[<a id='paging' href='boardList.jsp?pg=" + (startPage-1) + "'>이전</a>]");
		}
		
		for(int i=startPage; i<=endPage; i++) {
			if(i == currentPage) {
				pagingHTML.append("[<a id='currentPaging' href='boardList.jsp?pg="+i+"'>"+i+"</a>]");
			}else {
				pagingHTML.append("[<a id='paging' href='boardList.jsp?pg="+i+"'>"+i+"</a>]");
			}//if
		}//for
		
		if(endPage < totalP) {
			pagingHTML.append("[<a id='paging' href='boardList.jsp?pg="+(endPage+1)+"'>다음</a>]");
		}
			
			
	}

}
