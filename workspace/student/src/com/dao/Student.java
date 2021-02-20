package com.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Student {
	private Scanner scan = new Scanner(System.in);
	
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	private String driver = "oracle.jdbc.driver.OracleDriver";
	private String url = "jdbc:oracle:thin:@localhost:1521:xe";
	private String username = "c##java";
	private String password = "bit";
	
	private String name;
	private String value;
	private int code;
	

	public Student() {
		try {
			Class.forName(driver);
			
			System.out.println("드라이버 로딩 성공");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}//Student()
	
	public void menu() {
		
		int menuChoice = 0;
		
		while(true) {
			
			System.out.println("****************");
			System.out.println("\t관리");
			System.out.println("****************");
			System.out.println(" 1. 입력");
			System.out.println(" 2. 검색");
			System.out.println(" 3. 삭제");
			System.out.println(" 4. 종료");
			System.out.println("****************");
			System.out.print(" 번호 선택 : ");
			menuChoice = scan.nextInt();
			
			if(menuChoice == 1) {
				insertArticle();
			}else if(menuChoice == 2) {
				selectArticle();
			}else if(menuChoice == 3) {
				deleteArticle();
			}else if(menuChoice == 4) {
				break;
			}else {
				System.out.println("잘못 입력 하였습니다.");
			}
		}//while
	}//menu()
	
	public void insertArticle() {
		
		while(true) {
			System.out.println("****************");
			System.out.println(" 1. 학생");
			System.out.println(" 2. 교수");
			System.out.println(" 3. 관리자");
			System.out.println(" 4. 이전메뉴");
			System.out.println("****************");
			System.out.println(" 번호선택 : ");
			code = scan.nextInt();
			
			System.out.print("이름입력 : ");
			name = scan.next();
			
			if(code == 1) {
				
				System.out.print("학번입력 : ");
				value = scan.next();
				
			}else if(code == 2) {
				
				System.out.print("과목입력 : ");
				value = scan.next();
				
			}else if(code == 3) {
				
				System.out.print("부서입력 : ");
				value = scan.next();
				
			}else if(code == 4) {
				return;
			}else {
				System.out.println("잘못 입력 하였습니다.");
			}//if
			
			if(code == 1 || code == 2 || code == 3) {
				getConnection();	
			
				String sql = "insert into student(name,value,code) values(?,?,?)";
			
			
				try {
					pstmt = conn.prepareStatement(sql);
					pstmt.setString(1, name);
					pstmt.setString(2, value);
					pstmt.setInt(3, code);
					int su = pstmt.executeUpdate();
					
					System.out.println(su + "개의 행이 삽입되었습니다.");
					
					
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}finally {
					try {
						if(pstmt != null) {
							pstmt.close();
						}
						if(conn != null) {
							conn.close();
						}
					}catch(SQLException e) {
						e.printStackTrace();
					}
				}
			}//if
		}//whlie
	}//insertArticle()
	
	public void selectArticle() {
		
		while(true) {
			System.out.println("****************");
			System.out.println(" 1. 이름 검색");
			System.out.println(" 2. 전체 검색");
			System.out.println(" 3. 이전 메뉴");
			System.out.println("****************");
			System.out.println(" 번호선택 : ");
			int choice = scan.nextInt();
			
			if(choice == 1) {
				System.out.print("검색할 이름 입력: ");
				String searchname = scan.next();
				
				getConnection();
				String sql = "select * from student where name like ?";
				
				try {
					
					pstmt = conn.prepareStatement(sql);
					pstmt.setString(1,"%" + searchname + "%");
					rs = pstmt.executeQuery();
					
					while(rs.next()) {
						System.out.println(rs.getString("name") + "\t"
								+ rs.getString("value") + "\t"
								+ rs.getInt("code"));
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}finally {
					try {
						if(rs != null) {
							rs.close();
						}
						if(pstmt != null) {
							pstmt.close();
						}
						if(conn != null) {
							conn.close();
						}
					}catch(SQLException e) {
						e.printStackTrace();
					}
				}
				
				
			}else if(choice == 2) {
				getConnection();
				String sql = "select * from student";
				
				try {
					pstmt = conn.prepareStatement(sql);
					rs = pstmt.executeQuery();
					
					while(rs.next()) {
						System.out.println(rs.getString("name") + "\t"
								+ rs.getString("value") + "\t"
								+ rs.getInt("code"));
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}finally {
					try {
						if(rs != null) {
							rs.close();
						}
						if(pstmt != null) {
							pstmt.close();
						}
						if(conn != null) {
							conn.close();
						}
					}catch(SQLException e) {
						e.printStackTrace();
					}
				}
				
				
				
			}else if(choice == 3) {
				return;
			}else {
				System.out.println("잘못 입력하였습니다.");
			}
		}//while
	}
	
	public void deleteArticle() {//delete를 하면 commit 을 실행해 주기 때문에 db 에서 select만 해 주어도 수정된 결과가 나온다.
		System.out.print("삭제를 원하는 이름 입력: ");
		String deletename = scan.next();
		
		getConnection();
		
		String sql = "delete student where name = ?";
		System.out.println(sql);
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,deletename);
			int su = pstmt.executeUpdate();
			
			System.out.println(su + "개의 행이 삭제되었습니다.");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				if(pstmt != null) {
					pstmt.close();
				}
				if(conn != null) {
					conn.close();
				}
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	public void getConnection() {
		try {
			conn = DriverManager.getConnection(url,username,password);
			System.out.println("접속 성공");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	
	
	public static void main(String[] args) {
		Student st = new Student();
		st.menu();
		System.out.println("프로그램을 종료합니다.");
	}

}
