package miniProject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClientDAO {
   private Connection conn;
   private PreparedStatement pstmt;
   private ResultSet rs;
   
   private String driver = "oracle.jdbc.driver.OracleDriver";
   private String url = "jdbc:oracle:thin:@localhost:1521:xe";
   
   private String username = "worm";
   private String password = "bit";
   
   private static ClientDAO instance;
   
   public ClientDAO() {
      try {
         Class.forName(driver);
         System.out.println("드라이버 로딩 성공");
      } catch (ClassNotFoundException e) {
         e.printStackTrace();
      }
      
   }//ClientDAO()
   
   public void getConnection() {
      
      try {
         conn = DriverManager.getConnection(url, username, password);
         System.out.println("접속 성공");
      } catch (SQLException e) {
         
         e.printStackTrace();
      }
   }//getConnection()
   
   public static ClientDAO getInstance() {
      if(instance == null) {
         synchronized(ClientDAO.class) {
            instance = new ClientDAO();
         }
      }
      return instance;
   }//ClientDAO getInstance()
   
   public int clinetInsertAticle(ClientDTO dto) {//회원가입 시 dto에 값을 저장한 후 db에 레코드 추가.
      int su = 0;
      String sql = "insert into client values(?,?,?,?,?,?,?)";
      getConnection();
      
      try {
         pstmt = conn.prepareStatement(sql);
         
         pstmt.setString(1, dto.getName());
         pstmt.setString(2, dto.getId());
         pstmt.setString(3, dto.getPw());
         pstmt.setString(4, dto.getEmail());
         pstmt.setString(5, dto.getDomain());
         pstmt.setInt(6, dto.getGender());
         pstmt.setInt(7, dto.getScore());
         
         su = pstmt.executeUpdate();
         
         
      } catch (SQLException e) {
         e.printStackTrace();
      } finally {
         try {
            if(pstmt != null) {
               pstmt.close();
            }
            if(conn != null) {
               conn.close();
            }
            
         } catch (SQLException e) {
            e.printStackTrace();
         }
      }
      return su;
   }//insertAticle(ClientDTO dto)
   
   public List<ClientDTO> getClientList(){//프로세스 종료 후 계정의 정보를 테이블에서 가져오는것
      List<ClientDTO> dtoList = new ArrayList<ClientDTO>();
      String sql = "select * from client";
      getConnection();
      
      try {
         pstmt = conn.prepareStatement(sql);
         rs = pstmt.executeQuery();
         
         while(rs.next()) {
            ClientDTO dto = new ClientDTO();
            
            dto.setName(rs.getString("name"));
            dto.setId(rs.getString("id"));
            dto.setPw(rs.getString("pw"));
            dto.setEmail(rs.getString("email"));
            dto.setDomain(rs.getString("domain"));
            dto.setGender(rs.getInt("gender"));
            
            dtoList.add(dto);
         }//while
      } catch (SQLException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      } finally {
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
      return dtoList;
   }//getClientList()
      
   public int updateArticle(ClientDTO dto) {
      int su = 0;
      String sql = "update client set name = ?, id= ?, pw = ?, email = ?, domain = ?, gender = ?, score = ?";
      getConnection();
      
      try {
         pstmt = conn.prepareStatement(sql);
         
         pstmt.setString(1, dto.getName());
         pstmt.setString(2, dto.getId());
         pstmt.setString(3, dto.getPw());
         pstmt.setString(4, dto.getEmail());
         pstmt.setString(5, dto.getDomain());
         pstmt.setInt(6, dto.getGender());
         pstmt.setInt(7,  dto.getScore());
         
         
         
         su = pstmt.executeUpdate();;
         
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
            
         } catch (SQLException e) {
            e.printStackTrace();
         }
      }
      return su;
   }//updateArticle(ClientDTO dto)
   
   public int deleteArticle(ClientDTO dto) {
      int su = 0;
      String sql = "delete client where id = " + dto.getId();
      getConnection();
      
      try {
         pstmt = conn.prepareStatement(sql);
         su = pstmt.executeUpdate();
      } catch (SQLException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      } finally {
         try {
            if(pstmt != null) {
               pstmt.close();
            }
            if(conn != null) {
               conn.close();
            }
            
         } catch (SQLException e) {
            e.printStackTrace();
         }
      }
      return su;
   }//deleteArticle(ClientDTO dto)
   
//   public void wormInsertArticle(WormDTO dto) {
//      int face = 0;
//      int color = 0;
//         if(dto.getFace() == WormForm.SMILE) face = 0;
//         if(dto.getFace() == WormForm.BEAUTY) face = 1;
//         if(dto.getFace() == WormForm.MAD) face = 2;
//         
//         if(dto.getColor() == WormForm.GREEN) color = 0;
//         if(dto.getColor() == WormForm.BLUE) color = 1;
//         if(dto.getColor() == WormForm.RED) color = 2;
//         if(dto.getColor() == WormForm.MAGENTA) color = 3;
//         
//            
//      
//      
//      String sql = "insert into worm values(?,?)";
//      getConnection();
//
//      try {
//         pstmt = conn.prepareStatement(sql);
//
//         pstmt.setInt(1, face);
//         pstmt.setInt(2,  color);
//
//         pstmt.executeUpdate();
//
//      } catch (SQLException e) {
//         e.printStackTrace();
//      } finally {
//         try {
//            if (pstmt != null) {
//               pstmt.close();
//            }
//            if (conn != null) {
//               conn.close();
//            }
//
//         } catch (SQLException e) {
//            e.printStackTrace();
//         }
//      }
//         
//   }

}