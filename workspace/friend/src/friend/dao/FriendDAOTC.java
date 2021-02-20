package friend.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import friend.bean.FriendDTO;

public class FriendDAOTC {
   private Connection conn;
   private PreparedStatement pstmt;
   private ResultSet rs;
   
   private String driver = "oracle.jdbc.driver.OracleDriver";
   private String url = "jdbc:oracle:thin:@localhost:1521:xe";
   private String username = "c##java";
   private String password = "bit";
   
   private static FriendDAOTC instance;
   
   public FriendDAOTC() {
      try {
         Class.forName(driver);
      } catch (ClassNotFoundException e) {
         e.printStackTrace();
      }
   }
   
   public void getConnection() {
      try {
         conn = DriverManager.getConnection(url, username, password);
      } catch (SQLException e) {
         e.printStackTrace();
      }
   }

   public static FriendDAOTC getInstance() {
      if(instance==null) {
         synchronized (FriendDAOTC.class) {
            instance = new FriendDAOTC();
         }
      }
      return instance;
   }

   public int insertArticle(FriendDTO dto) {
      int su=0;
      String sql = "insert into friend values(?,?,?,?,?,?,?,?,?,?,?)";
      getConnection();
      
      try {
         pstmt = conn.prepareStatement(sql);
         pstmt.setInt(1, dto.getSeq());
         pstmt.setString(2, dto.getName());
         pstmt.setString(3, dto.getTel1());
         pstmt.setString(4, dto.getTel2());
         pstmt.setString(5, dto.getTel3());
         pstmt.setInt(6, dto.getGender());
         pstmt.setInt(7, dto.getRead());
         pstmt.setInt(8, dto.getMovie());
         pstmt.setInt(9, dto.getMusic());
         pstmt.setInt(10, dto.getGame());
         pstmt.setInt(11, dto.getShopping());
         
         su = pstmt.executeUpdate();//실행      
         
      } catch (SQLException e) {
         e.printStackTrace();
      } finally {
         try {
            if(pstmt != null) pstmt.close();
            if(conn != null) conn.close();
         } catch (SQLException e) {
            e.printStackTrace();
         }
      }
      
      return su;
   }

   public int getSeq() {
      int seq=0;
      String sql = "select seq_friend.nextval from dual";
      getConnection();
      
      try {
         pstmt = conn.prepareStatement(sql);//생성
         rs = pstmt.executeQuery();//실행
         
         rs.next();
         seq = rs.getInt(1);
         
      } catch (SQLException e) {
         e.printStackTrace();
      } finally {
         try {
            if(rs != null) rs.close();
            if(pstmt != null) pstmt.close();
            if(conn != null) conn.close();
         } catch (SQLException e) {
            e.printStackTrace();
         }
      }
      
      System.out.println("seq = "+seq);
      return seq;
   }

   public List<FriendDTO> getFriendList() {
      List<FriendDTO> dtoList = new ArrayList<FriendDTO>();
      String sql = "select * from friend order by seq";
      getConnection();
      
      try {
         pstmt = conn.prepareStatement(sql);//생성
         rs = pstmt.executeQuery();//실행
         
         while(rs.next()) {
            FriendDTO dto = new FriendDTO();
            dto.setSeq(rs.getInt("seq"));
            dto.setName(rs.getString("name"));
            dto.setTel1(rs.getString("tel1"));
            dto.setTel2(rs.getString("tel2"));
            dto.setTel3(rs.getString("tel3"));
            dto.setGender(rs.getInt("gender"));
            dto.setRead(rs.getInt("read"));
            dto.setMovie(rs.getInt("movie"));
            dto.setMusic(rs.getInt("music"));
            dto.setGame(rs.getInt("game"));
            dto.setShopping(rs.getInt("shopping"));
            
            dtoList.add(dto);         
            
         }//while
         
      } catch (SQLException e) {
         e.printStackTrace();
      } finally {
         try {
            if(rs != null) rs.close();
            if(pstmt != null) pstmt.close();
            if(conn != null) conn.close();
         } catch (SQLException e) {
            e.printStackTrace();
         }
      }
      
      return dtoList;
   }

   public void updateArticle(FriendDTO dto) {
      String sql = "update friend set name=?"
                           + ",tel1=?"
                           + ",tel2=?"
                           + ",tel3=?"
                           + ",gender=?"
                           + ",read=?"
                           + ",movie=?"
                           + ",music=?"
                           + ",game=?"
                           + ",shopping=? where seq=?";
      getConnection();
      try {
         pstmt = conn.prepareStatement(sql);
         pstmt.setString(1, dto.getName());
         pstmt.setString(2, dto.getTel1());
         pstmt.setString(3, dto.getTel2());
         pstmt.setString(4, dto.getTel3());
         pstmt.setInt(5, dto.getGender());
         pstmt.setInt(6, dto.getRead());
         pstmt.setInt(7, dto.getMovie());
         pstmt.setInt(8, dto.getMusic());
         pstmt.setInt(9, dto.getGame());
         pstmt.setInt(10, dto.getShopping());
         pstmt.setInt(11, dto.getSeq());
         
         pstmt.executeUpdate();
         
      } catch (SQLException e) {
         e.printStackTrace();
      } finally {
         try {
            if(rs != null) rs.close();
            if(pstmt != null) pstmt.close();
            if(conn != null) conn.close();
         } catch (SQLException e) {
            e.printStackTrace();
         }
      }      
   }

   public void deleteArticle(int seq) {
      String sql = "delete friend where seq=?";
      getConnection();
      try {
         pstmt = conn.prepareStatement(sql);
         pstmt.setInt(1, seq);
         pstmt.executeUpdate();//실행
         
      } catch (SQLException e) {
         e.printStackTrace();
      } finally {
         try {
            if(rs != null) rs.close();
            if(pstmt != null) pstmt.close();
            if(conn != null) conn.close();
         } catch (SQLException e) {
            e.printStackTrace();
         }
      }
   }
}