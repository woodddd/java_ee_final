package user.main;

import java.util.Scanner;

import user.service.UserDeleteService;
import user.service.UserInsertService;
import user.service.UserSearchService;
import user.service.UserSelectService;
import user.service.UserService;
import user.service.UserUpdateService;

public class UserMain {
	
	public void menu() {
		Scanner scan = new Scanner(System.in);
		UserService userService = null;
		int choice = 0;
		
		while(true) {
			
			System.out.println("1. 입력");
			
			System.out.println("2. 출력");
			
			System.out.println("3. 수정");
			
			System.out.println("4. 삭제");
			
			System.out.println("5. 검색");
			
			System.out.println("6. 끝");
			
			System.out.println("번호입력 : ");
			choice = scan.nextInt();
			
			if(choice == 1) {
				userService = new UserInsertService(); //부모 = new 자식
			}else if(choice == 2) {
				userService = new UserSelectService();
			}else if(choice == 3) {
				userService = new UserUpdateService();
			}else if(choice == 4) {
				userService = new UserDeleteService();
			}else if(choice == 5) {
				userService = new UserSearchService();
			}else if(choice == 6) {
				break;
			}
			userService.execute();//호출. 누가 들어왔던 오버라이딩한 execute 메소드를 이용하므로 이렇게 써도 상관이 없음.
			
		}//while
		
	}
	
	public static void main(String[] args) {
		UserMain userMain = new UserMain();
		userMain.menu();
		System.out.println("프로그램 종료");
	}

}


