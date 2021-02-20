package user.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import user.bean.UserDTO;
import user.dao.UserDAO;

public class UserSearchService implements UserService {

	@Override
	public void execute() {
		Scanner scan = new Scanner(System.in);
		String name = null;
		String id = null;
		String pwd = null;
		System.out.println();
		System.out.println("1. 이름 검색");
		System.out.println("2. 아이디 검색");
		System.out.print("번호 입력 : ");
		int num = scan.nextInt();
		
		String columnName = null;
		String value = null;
		if(num==1) {
			System.out.println("찾고자 하는 이름 입력 : ");
//			name = scan.next();
			value = scan.next();
			columnName = "name";
		}else if(num==2) {
			System.out.println("찾고자 하는 아이디 입력 : ");
//			id = scan.next();
			value = scan.next();
			columnName = "id";
		}
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("columnName",columnName);
		map.put("value",value);
		
		//DB
		UserDAO userDAO = UserDAO.getInstance();
//		UserDTO userDTO = new UserDTO();
//		userDTO.setName(name);
//		userDTO.setId(id);
//		List<UserDTO> list = userDAO.search(userDTO); /*UserDAO 에서 search()1개, userMapper.xml에서 <select id="search"> 1개*/
		List<UserDTO> list = userDAO.search(map);
		
		System.out.println("찾고자 하는 정보");
		System.out.println("이름\t아이디\t비밀번호");
		for(int i = 0 ; i < list.size(); i++) {
			System.out.println(list.get(i).getName()+"\t"+list.get(i).getId()+"\t"+list.get(i).getPwd());
		}//for
		if(list.size()==0) {
			System.out.println("\t검색결과 없음");
		}
		
		

	}

}
