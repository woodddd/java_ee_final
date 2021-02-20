package friend.action;

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

public class FriendManager extends JFrame implements ActionListener,ListSelectionListener{
   private JLabel infoL, analyL, listL, nameL, telL, genderL, hobL, spaceL, space2L;
   private JTextField nameT, tel2T, tel3T;
   private JComboBox<String> tel1;
   private JTextArea analyT;
   private JList<FriendDTO> listT;
   private DefaultListModel<FriendDTO> model;
   private JButton regiBtn, modiBtn, trashBtn, deleBtn;
   private JRadioButton man, woman;
   private JCheckBox read, movie, music, game, shopping;
   
   
   public FriendManager() {
	   //JLabel 생성
      infoL = new JLabel("개인정보입력");
      analyL = new JLabel("개인정보분석");
      listL = new JLabel("전체목록");
      nameL = new JLabel("이        름 :");
      telL = new JLabel("전화번호 : ");
      genderL = new JLabel("성        별 :");
      hobL = new JLabel("취        미 :");
      spaceL = new JLabel(" - ");
      space2L = new JLabel(" - ");
      
      //JTextField 생성
      nameT = new JTextField(7);
      tel2T = new JTextField(3);
      tel3T = new JTextField(3);
      
      String[] number = {"010","02","031","032","033"};
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
      
      //모든 레코드를 꺼내서 JList에 뿌리기 (콘솔 종류 후 재실행 하여도 이전 데이터가 보여야함.)
      FriendDAO dao = FriendDAO.getInstance();
      List<FriendDTO> dtolist = dao.getFriendList();
      for(FriendDTO dto: dtolist) { //데이터베이스에 저장되어 있는데이터를 가져와서 다시 모델에 담아준다.
    	  model.addElement(dto);
      }
   }//FriendManager();
   
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
		   
		   insertarticle();
		  
	   }else if(e.getSource() == modiBtn) {
		   updateArticle();
	   }else if(e.getSource() == deleBtn) {
		   deleteArticle();
	   }else if(e.getSource() == trashBtn) {
		   clear();
	   }
	   
   }
   

	private void updateArticle() {
		
		String name = nameT.getText();
		   
		String tel1 = (String) this.tel1.getSelectedItem();
	   
	   String tel2 = (String) this.tel2T.getText();
	   
	   String tel3 = (String) this.tel3T.getText();
	   int gender = 0;
	   if(man.isSelected()) gender = 0;
	   else if(woman.isSelected()) gender = 1;
	   
	   
	   //read, movie, music, game, shopping;
	   int read = this.read.isSelected() ? 1 : 0;
	   int movie = this.movie.isSelected() ? 1 : 0;
	   int music = this.music.isSelected() ? 1 : 0;
	   int game = this.game.isSelected() ? 1 : 0;
	   int shopping = this.shopping.isSelected() ? 1 : 0;
	   
	   FriendDTO dto = listT.getSelectedValue();//현재 선택된 dto 객체를 가지고온다.
	   
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
	   FriendDAO dao = FriendDAO.getInstance(); //싱글톤으로 생성
	   
	   int su = dao.updateArticle(dto);//dao 의 dinsertAticle 메소드를 사용.
		   
	   analyT.setText("\n\t\t" + su + "개의 행이 만들어졌습니다");
	   
	}

	private void deleteArticle() {
		
		FriendDTO dto = listT.getSelectedValue();
		
		FriendDAO dao = FriendDAO.getInstance();
		
		int su = dao.deleteArticle(dto);
		
		model.remove(listT.getSelectedIndex());
		
		clear();
	    analyT.setText("\n\t\t"+su+"개의 행이 삭제되었습니다");
		
		
				
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

	public void insertarticle() {
		 //데이터
	   String name = nameT.getText();
	   
	   String tel1 = (String) this.tel1.getSelectedItem();
	   
	   String tel2 = (String) this.tel2T.getText();
	   
	   String tel3 = (String) this.tel3T.getText();
	   int gender = 0;
	   if(man.isSelected()) gender = 0;
	   else if(woman.isSelected()) gender = 1;
	   
	   
	   //read, movie, music, game, shopping;
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
	   FriendDAO dao = FriendDAO.getInstance(); //싱글톤으로 생성
	   int seq = dao.getSeq();//시퀀스 객체로부터 숫자 받아오기
	   
	   dto.setSeq(seq);//dao 클래스에서 받아온 Seq를 dto의 seq에 저장한다.(객체별 seq번호 저장)
	   int su = dao.insertArticle(dto);//dao 의 dinsertAticle 메소드를 사용.
		   
	   //응답
	   clear();//입력이 끝났으면 값을 리스트에 넣어주고 해당 텍스트필드를 초기화시켜줌.
	   analyT.setText("\n\t\t" + su + "개의 행이 만들어졌습니다");
	   
	   model.addElement(dto);
	   
	   
   }

	@Override
	public void valueChanged(ListSelectionEvent e) {
		
		System.out.println("seq = " + listT.getSelectedIndex());//값이 두번씩 찍힘. 마우스 프레스시 한번, 릴리즈시 한번
		FriendDTO dto = listT.getSelectedValue();//getSelectedValue()는Frienddto타입을 반환하여 주소값을 dto에게 넘겨주게된다.
		//만약 listT에서 model.remove가 실행이 되면, listT.getSelectedValue() 가 가르키는 값은
		//삭제된 값을 가리키므로 -1 로 반환값이 바뀌게된다. 그래서 결국 dto 에는 아무것도 안가르키고 있기 때문에 에러가 발생된다.
		
		
		if(e.getValueIsAdjusting()) {//중복행 제거

		nameT.setText(dto.getName()); 
		tel1.setSelectedItem(dto.getTel1());
		tel2T.setText(dto.getTel2());
		tel3T.setText(dto.getTel3());
		if(dto.getGender() == 0) {
			man.setSelected(true);
		}else if(dto.getGender() == 1) {
			woman.setSelected(true);
		}
		read.setSelected(dto.getRead() == 1 ? true : false);
		movie.setSelected(dto.getMovie() == 1 ? true : false);
		music.setSelected(dto.getMusic() == 1 ? true : false);
		game.setSelected(dto.getGame() == 1 ? true : false);
		shopping.setSelected(dto.getShopping() == 1 ? true : false);
		   
		analyT.setText("");
		
		regiBtn.setEnabled(false);
		modiBtn.setEnabled(true);
		deleBtn.setEnabled(true);
		trashBtn.setEnabled(true);
		}//if
	}
   
   public static void main(String[] args) {
      new FriendManager().event();
   }


}
