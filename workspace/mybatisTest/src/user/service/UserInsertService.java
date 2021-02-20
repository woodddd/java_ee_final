package user.service;

import java.util.Scanner;

import user.bean.UserDTO;
import user.dao.UserDAO;

public class UserInsertService implements UserService {

   @Override
   public void execute() {
      //데이터
      Scanner scan = new Scanner(System.in);
      System.out.print("이름 입력 : ");
      String name = scan.next();
      System.out.print("아이디 입력 : ");
      String id = scan.next();
      System.out.print("비밀번호 입력 : ");
      String pwd = scan.next();
      
      UserDTO userDTO = new UserDTO();
      userDTO.setName(name);
      userDTO.setId(id);
      userDTO.setPwd(pwd);
      
      //DB
      UserDAO userDAO = UserDAO.getInstance();
      int su = userDAO.write(userDTO);
      
      //응답
      System.out.println(su + "개의 행이 만들어졌습니다.");
      System.out.println();
   }

}