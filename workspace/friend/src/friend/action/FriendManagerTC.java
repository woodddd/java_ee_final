package friend.action;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import friend.bean.FriendDTO;
import friend.dao.FriendDAO;
import friend.dao.FriendDAOTC;
//강사님 파일이라 전부다 기존 클래스명에 TC 붙여줘야함!!!!!!!!!!!!!!!!!
public class FriendManagerTC extends JFrame implements ActionListener, ListSelectionListener {
   private JLabel infoL, analyL, listL, nameL, telL, genderL, hobL, spaceL, space2L;
   private JTextField nameT, tel2T, tel3T;
   private JComboBox<String> tel1;
   private JTextArea analyT;
   private JList<FriendDTO> listT;
   private DefaultListModel<FriendDTO> model;
   private JButton regiBtn, modiBtn, trashBtn, deleBtn;
   private JRadioButton man, woman;
   private JCheckBox read, movie, music, game, shopping;
   
   public FriendManagerTC() {
      infoL = new JLabel("개인정보입력");
      analyL = new JLabel("개인정보분석");
      listL = new JLabel("전체목록");
      nameL = new JLabel("이        름 :");
      telL = new JLabel("전화번호 : ");
      genderL = new JLabel("성        별 :");
      hobL = new JLabel("취        미 :");
      spaceL = new JLabel(" - ");
      space2L = new JLabel(" - ");
      
      nameT = new JTextField(7);
      tel2T = new JTextField(3);
      tel3T = new JTextField(3);
      
      String[] number = {"010","011"};
      tel1 = new JComboBox<String>(number);
      
      analyT = new JTextArea();
      
      model = new DefaultListModel<FriendDTO>();
      listT = new JList<FriendDTO>(model);
      
      regiBtn = new JButton("등록");
      modiBtn = new JButton("수정");
      deleBtn = new JButton("삭제");
      trashBtn = new JButton("지우기");
      
      regiBtn.setEnabled(true);
      modiBtn.setEnabled(false);
      trashBtn.setEnabled(false);
      deleBtn.setEnabled(false);
      
      man = new JRadioButton("남성",true);
      man.setBackground(new Color(250, 100, 100));
      woman = new JRadioButton("여성");
      woman.setBackground(new Color(250, 100, 100));
      
      ButtonGroup group = new ButtonGroup();
      group.add(man);
      group.add(woman);
      
      read = new JCheckBox("독서");
      movie = new JCheckBox("영화");
      music = new JCheckBox("음악");
      game = new JCheckBox("게임");
      shopping = new JCheckBox("쇼핑");
      read.setBackground(new Color(250, 100, 100));
      movie.setBackground(new Color(250, 100, 100));
      music.setBackground(new Color(250, 100, 100));
      game.setBackground(new Color(250, 100, 100));
      shopping.setBackground(new Color(250, 100, 100));
      
      infoL.setBounds(150, 10, 200, 20);
      listL.setBounds(450, 10, 200, 20);
      analyL.setBounds(260, 320, 190, 20);
      
      setBackground(new Color(128, 128, 192));

      
      JPanel p1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
      p1.add(nameL);
      p1.add(nameT);
      p1.setBounds(10, 50, 200, 25);
      p1.setBackground(new Color(250, 100, 100));

      JPanel p2 = new JPanel(new FlowLayout(FlowLayout.LEFT));
      p2.add(telL);
      p2.add(tel1);
      p2.add(spaceL);
      p2.add(tel2T);
      p2.add(space2L);
      p2.add(tel3T);
      p2.setBounds(10, 90, 300, 30);
      p2.setBackground(new Color(250, 100, 100));
      
      JPanel p3 = new JPanel(new FlowLayout(FlowLayout.LEFT));
      p3.add(genderL);
      p3.add(man);
      p3.add(woman);
      p3.setBounds(10, 130, 200, 30);
      p3.setBackground(new Color(250, 100, 100));
      
      JPanel p4 = new JPanel(new FlowLayout(FlowLayout.LEFT));
      p4.add(hobL);
      p4.add(read);
      p4.add(movie);
      p4.add(music);
      p4.add(game);
      p4.add(shopping);
      p4.setBounds(10, 170, 350, 30);
      p4.setBackground(new Color(250, 100, 100));
      
      JPanel p5 = new JPanel(new FlowLayout(FlowLayout.CENTER));
      p5.add(regiBtn);
      p5.add(modiBtn);
      p5.add(deleBtn);
      p5.add(trashBtn);
      p5.setBounds(10, 210, 300, 50);
      p5.setBackground(new Color(250, 100, 100));
      
      analyT.setBounds(20, 350, 600, 110);
      listT.setBounds(380, 50, 230, 250);
      
      Container c = this.getContentPane();
      c.setBackground(new Color(250, 100, 100));
      c.add(infoL);
      c.add(listL);
      c.add(analyL);
      c.add(p1);
      c.add(p2);
      c.add(p3);
      c.add(p4);
      c.add(p5);
      c.add(analyT);
      c.add(listT);
      
      //2.프레임 생성
      setLayout(null);
      setBounds(700, 300, 650, 530);
      setVisible(true);
      setResizable(false);
      setDefaultCloseOperation(EXIT_ON_CLOSE);
      
      //모든 레코드를 꺼내서 JList에 뿌리기
      FriendDAO dao = FriendDAO.getInstance();
      List<FriendDTO> dtoList = dao.getFriendList();
      for(FriendDTO dto : dtoList) {
         model.addElement(dto);
      }//for
      
   }//FriendManager()
   
   public void event() {
      regiBtn.addActionListener(this);
      modiBtn.addActionListener(this);
      deleBtn.addActionListener(this);
      trashBtn.addActionListener(this);
      
      listT.addListSelectionListener(this);
   }
   
   @Override
   public void actionPerformed(ActionEvent e) {
      if(e.getSource() == regiBtn) {
         insertArticle();
         
      }else if(e.getSource() == modiBtn) {
         updateArticle();
         
      }else if(e.getSource() == deleBtn) {
         deleteArticle();
         
      }else if(e.getSource() == trashBtn) {
         clear();
      }
   }
   
   private void deleteArticle() {
      //데이터
      int seq = listT.getSelectedValue().getSeq();
      
      //DB
      FriendDAOTC dao = FriendDAOTC.getInstance();
      dao.deleteArticle(seq);
      
      //응답
      clear();
      analyT.setText("\n\t\t삭제 하였습니다");
      model.remove(listT.getSelectedIndex());
   }

   private void updateArticle() {
      //데이터
      int seq = listT.getSelectedValue().getSeq();
      String name = nameT.getText();
      String tel1 = (String) this.tel1.getSelectedItem();
      String tel2 = tel2T.getText();
      String tel3 = tel3T.getText();
      int gender=0;
      if(man.isSelected()) gender=0;
      else if(woman.isSelected()) gender=1;
      int read = this.read.isSelected() ? 1 : 0;
      int movie = this.movie.isSelected() ? 1 : 0;
      int music = this.music.isSelected() ? 1 : 0;
      int game = this.game.isSelected() ? 1 : 0;
      int shopping = this.shopping.isSelected() ? 1 : 0;
      
      //FriendDTO
      FriendDTO dto = new FriendDTO();
      dto.setSeq(seq);
      dto.setName(name);
      dto.setTel1(tel1);
      dto.setTel2(tel2);
      dto.setTel3(tel3);
      dto.setGender(gender);
      dto.setRead(read);
      dto.setMovie(movie);
      dto.setMusic(music);
      dto.setGame(game);
      dto.setShopping(shopping);
      
      //DB
      FriendDAO dao = FriendDAO.getInstance();
      dao.updateArticle(dto);
      
      analyT.setText("\n\t\t수정 하였습니다");
      model.set(listT.getSelectedIndex(), dto);      
   }//updateArticle()

   private void insertArticle() {
      //데이터
      String name = nameT.getText();
      String tel1 = (String) this.tel1.getSelectedItem();
      String tel2 = tel2T.getText();
      String tel3 = tel3T.getText();
      int gender=0;
      if(man.isSelected()) gender=0;
      else if(woman.isSelected()) gender=1;
      int read = this.read.isSelected() ? 1 : 0;
      int movie = this.movie.isSelected() ? 1 : 0;
      int music = this.music.isSelected() ? 1 : 0;
      int game = this.game.isSelected() ? 1 : 0;
      int shopping = this.shopping.isSelected() ? 1 : 0;
      
      //FriendDTO
      FriendDTO dto = new FriendDTO();
      dto.setName(name);
      dto.setTel1(tel1);
      dto.setTel2(tel2);
      dto.setTel3(tel3);
      dto.setGender(gender);
      dto.setRead(read);
      dto.setMovie(movie);
      dto.setMusic(music);
      dto.setGame(game);
      dto.setShopping(shopping);
      
      //DB
      FriendDAO dao = FriendDAO.getInstance();
      int seq = dao.getSeq(); //시퀀스 객체로부터 숫자 받아오기
      
      dto.setSeq(seq);
      int su = dao.insertArticle(dto);
            
      //응답
      clear(); //초기화
      analyT.setText("\n\t\t"+su+"개의 행이 만들어졌습니다");
      model.addElement(dto);
   }

   private void clear() {
      nameT.setText("");
      tel1.setSelectedItem("010");
      tel2T.setText("");
      tel3T.setText("");
      man.setSelected(true);
      woman.setSelected(false);
      read.setSelected(false);
      movie.setSelected(false);
      music.setSelected(false);
      game.setSelected(false);
      shopping.setSelected(false);
      analyT.setText("");
      
      regiBtn.setEnabled(true);
      modiBtn.setEnabled(false);
      deleBtn.setEnabled(false);
      trashBtn.setEnabled(false);
      
   }
   
   @Override
   public void valueChanged(ListSelectionEvent e) {
      System.out.println("seq = " + listT.getSelectedIndex());
      if(listT.getSelectedIndex() == -1 ) return;
      
      FriendDTO dto = listT.getSelectedValue();
      nameT.setText(dto.getName());
      tel1.setSelectedItem(dto.getTel1());
      tel2T.setText(dto.getTel2());
      tel3T.setText(dto.getTel3());
      if(dto.getGender()==0)
         man.setSelected(true);
      else if(dto.getGender()==1)
         woman.setSelected(true);
      
      read.setSelected( dto.getRead()==1 ? true : false );
      movie.setSelected( dto.getMovie()==1 ? true : false );
      music.setSelected( dto.getMusic()==1 ? true : false );
      game.setSelected( dto.getGame()==1 ? true : false );
      shopping.setSelected( dto.getShopping()==1 ? true : false );
      
      analyT.setText("");
      
      regiBtn.setEnabled(false);
      modiBtn.setEnabled(true);
      deleBtn.setEnabled(true);
      trashBtn.setEnabled(true);
   }

   public static void main(String[] args) {
      new FriendManager().event();
   }
}
