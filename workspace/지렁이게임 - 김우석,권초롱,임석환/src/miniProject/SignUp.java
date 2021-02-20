package miniProject;

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class SignUp extends JFrame implements ActionListener {
   private JLabel nameL, idL, pwL, pwCkL, emailL, numberL;
   private JButton checkBtn, signUpBtn, cancelBtn, verifyBtn ,numberBtn;
   private JRadioButton maleBtn, femaleBtn;
   private JTextField nameT, idT, emailT, insertT, numberT;
   private JPasswordField pwT, pwCkT;
   private JComboBox<String> combo;
   private int gender;
   
   private List<ClientDTO> list;
   
   private String idCheck="";
   private String emailCheck="";
   private String domainCheck="";
   private int emailpw=0;

   public SignUp() {
      super("회원가입");
      
      list = new ArrayList<ClientDTO>();
      
      nameL = new JLabel("이름");
      nameT = new JTextField(5);

      idL = new JLabel("아이디");
      idT = new JTextField(5);

      pwL = new JLabel("비밀번호");
      pwT = new JPasswordField(5);

      pwCkL = new JLabel("재확인");
      pwCkT = new JPasswordField(5);

      emailL = new JLabel("메일 주소");
      emailT = new JTextField("", 15);
      String[] domain = { "@naver.com", "@daum.net", "@gmail.com", "직접 입력" };
      combo = new JComboBox<String>(domain);
      combo.setSelectedIndex(0);
      verifyBtn = new JButton("이메일 인증");
      insertT = new JTextField("@", 15);
      insertT.setEnabled(false);

      maleBtn = new JRadioButton("남", true);
      femaleBtn = new JRadioButton("여");

      checkBtn = new JButton("중복체크");

      signUpBtn = new JButton("회원가입");
      cancelBtn = new JButton("취소");
      
      
      numberL = new JLabel("인증번호 입력: ");
      numberT = new JTextField(10);
      numberBtn = new JButton("확인");

      nameL.setBounds(10, 5, 60, 30);
      nameT.setBounds(70, 5, 120, 30);

      ButtonGroup group = new ButtonGroup();
      group.add(maleBtn);
      group.add(femaleBtn);
      maleBtn.setBounds(200, 5, 50, 30);
      femaleBtn.setBounds(255, 5, 50, 30);

      idL.setBounds(10, 40, 60, 30);
      idT.setBounds(70, 40, 150, 30);
      checkBtn.setBounds(230, 40, 90, 30);

      pwL.setBounds(10, 75, 60, 30);
      pwT.setBounds(70, 75, 180, 30);
      pwT.setEchoChar('●');

      pwCkL.setBounds(10, 110, 60, 30);
      pwCkT.setBounds(70, 110, 180, 30);
      pwCkT.setEchoChar('●');

      emailL.setBounds(10, 150, 60, 30);
      emailT.setBounds(70, 150, 90, 30);
      combo.setBounds(165, 150, 120, 30);
      verifyBtn.setBounds(290, 150, 120, 30);
      insertT.setBounds(165, 185, 120, 30);
      
      numberL.setBounds(70, 220, 95, 30);
      numberT.setBounds(165, 220, 120, 30);
      numberBtn.setBounds(290, 220, 70, 30);

      JPanel p = new JPanel();
      p.setLayout(null);
      p.add(nameL);
      p.add(nameT);
      p.add(idL);
      p.add(idT);
      p.add(checkBtn);
      p.add(pwL);
      p.add(pwT);
      p.add(pwCkL);
      p.add(pwCkT);
      p.add(emailL);
      p.add(emailT);
      p.add(combo);
      p.add(verifyBtn);
      p.add(maleBtn);
      p.add(femaleBtn);
      p.add(insertT);
      p.add(numberL);
      p.add(numberT);
      p.add(numberBtn);

      JPanel p1 = new JPanel();
      p1.add(signUpBtn);
      p1.add(cancelBtn);

      Container c = this.getContentPane();
      c.add("Center", p);
      c.add("South", p1);

      setBounds(550, 200, 450, 350);
      setVisible(true);

      this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
      
      
      this.addWindowListener(new WindowAdapter() {
         @Override
         public void windowClosing(WindowEvent e) {
            setVisible(false);
           }
        });

      signUpBtn.addActionListener(this);
      cancelBtn.addActionListener(this);
      combo.addActionListener(this);
      checkBtn.addActionListener(this);
      verifyBtn.addActionListener(this);
      numberBtn.addActionListener(this);

      
      ClientDAO dao = ClientDAO.getInstance();
      List<ClientDTO> dtolist = dao.getClientList();
      for(ClientDTO dto: dtolist) {
    	  list.add(dto);
    	  System.out.println(dto.getId());
      }
      System.out.println(list.size());
      
   }

   @Override
   public void actionPerformed(ActionEvent e) {
//      System.out.println(combo.getSelectedIndex());

      if (combo.getSelectedIndex() == 3) {
         insertT.setEnabled(true);
      } else {
         insertT.setEnabled(false);
      }

      if (e.getSource() == signUpBtn) {
         if (nameT.getText().equals("") || nameT.getText() == null) {
            JOptionPane.showMessageDialog(this, "이름을 입력해주세요");
            return;
         }
         if (idT.getText().equals("") || idT.getText() == null) {
            JOptionPane.showMessageDialog(this, "아이디를 입력해주세요");
            return;
         }
         if (pwT.getText().equals("") || pwT.getText() == null) {
            JOptionPane.showMessageDialog(this, "비밀번호를 입력해주세요");
            return;
         }
         if(pwCkT.getText().equals("") || pwCkT.getText() == null) {
            JOptionPane.showMessageDialog(this, "비밀번호 재확인을 입력해주세요");
            return;
         }
         if(emailT.getText().equals("") || emailT.getText() == null) {
           JOptionPane.showMessageDialog(this, " 이메일 주소를 입력해주세요"); 
           return;
         }
         if(combo.getSelectedIndex() == -1 || (combo.getSelectedIndex() == 3 && insertT.getText().equals("") || insertT.getText() == null)) {
            JOptionPane.showMessageDialog(this, "도메인 주소를 입력/선택 해주세요");
            return;
         }
         if(numberT.getText().equals("") || numberT.getText() == null) { //확인 버튼을 누르면  입력된 인증번호와 메일에 도착한 인증번호를 비교하여 회원가입의 한 조건으로 봄.
            JOptionPane.showMessageDialog(this,"인증번호를 입력하세요."); // 로그인을 하기 위한 조건 : 아이디중복x 인증번호 같음 o 
            return;
         }
         if(! pwT.getText().equals(pwCkT.getText())) {
            JOptionPane.showMessageDialog (this,"비밀번호가 일치하지 않습니다");
            return;
         }
         if(!idCheck.equals(idT.getText())) {
        	 JOptionPane.showMessageDialog (this,"중복체크를 확인하시기 바랍니다");
        	 return;
         }
         
         if(emailCheck.equals(emailT.getText()) && (domainCheck.equals(insertT.getText()) || domainCheck.equals(combo.getItemAt(combo.getSelectedIndex())))) {
        	 insertArticle();
        	 idCheck = "";
        	 emailCheck = "";
        	 System.out.println("db로 데이터 전송");
        	 setVisible(false);
         }else {
        	 JOptionPane.showMessageDialog (this,"이메일 인증을 진행하세요.");
         }
         
      }else if(e.getSource() == cancelBtn){//signUpBtn
    	  nameT.setText("");
          idT.setText("");
          pwT.setText("");
          pwCkT.setText("");
          emailT.setText("");
          numberT.setText("");
          maleBtn.setSelected(true);
      }else if(e.getSource() == checkBtn) {
    	  
    	  System.out.println(idCheck);
    	  System.out.println("list = " + list.size());
    	  if(list.size() == 0) {
    		  JOptionPane.showMessageDialog(this,"사용할 수 있는 아이디 입니다."); 
			  
			  //idCheck = idT.getText();
    	  }
    	  
    	  for(int i = 0; i < list.size(); i++) {
    		  if(idT.getText().equals(list.get(i).getId())){
    			  JOptionPane.showMessageDialog(this,"아이디가 중복되었습니다.");
    			  return;
    		  }
    	  }//for
    	  JOptionPane.showMessageDialog(this,"사용할 수 있는 아이디 입니다.");
    	  idCheck = idT.getText();      
      
      
      
      
      }else if(e.getSource() == verifyBtn) {
    	  numberBtn.setEnabled(true);
    	  ClientDTO dto = new ClientDTO();
    	  
    	  
    	  //이메일을 입력한 후 인증확인을 누르므로 
    	  
          dto.setEmail(emailT.getText());
          if (combo.getSelectedIndex() == 3) {
       	  dto.setDomain(insertT.getText());
          }else{                                  // 절대 combo 상자를 -1 로 만들지 말 것,
       	  dto.setDomain(combo.getItemAt(combo.getSelectedIndex()));
          }
          
    	  
    	  SendEmail se  = new SendEmail(dto);
    	  
    	  emailpw = se.getCheck();//numberT
    	  System.out.println(emailpw);
    	
    	  //이메일 인증번호 확인
    	  
    	  
      }else if(e.getSource() == numberBtn) {
    	  if(numberT.getText() == null || numberT.getText().length() == 0||numberT.getText() == ""){
              JOptionPane.showMessageDialog(this,"인증번호  불일치.");
           }else if(Integer.parseInt(numberT.getText()) == emailpw) {
              
              emailCheck = emailT.getText();
              
              if (combo.getSelectedIndex() == 3) {
                    domainCheck = insertT.getText();
                    
              }else{
                domainCheck = combo.getItemAt(combo.getSelectedIndex());
              }

              JOptionPane.showMessageDialog(this,"인증번호 일치.");
              numberBtn.setEnabled(false);
              
           }
      }

      
      
     

   }//actionPerformed
   
   
   
   public void insertArticle() {
    //insertArticle()로 
       ClientDTO dto = new ClientDTO();
       //name, id, pw, email,domain, gender --- 사용자 정보
      //Name, Size, Length, Face,Color, Score --지렁이에 대한 정
       dto.setName(nameT.getText());
       dto.setId(idT.getText());
       dto.setPw(pwT.getText());
       dto.setEmail(emailT.getText());
       if (combo.getSelectedIndex() == 3) {
    	   dto.setDomain(insertT.getText());
       }else if(combo.getSelectedIndex() != -1 ){
    	   dto.setDomain(combo.getItemAt(combo.getSelectedIndex()));
       }
       if(maleBtn.isSelected()) {
          dto.setGender(1);
       }else if(femaleBtn.isSelected()) {
          dto.setGender(0);
       }
       
       ClientDAO dao = ClientDAO.getInstance();
       int su = dao.clinetInsertAticle(dto);
       
      System.out.println("이름은 : " + nameT.getText()); //메소드 실행되는지 확인하는 부분임, 지우기
      System.out.println(su + "개의 행이 만들어졌습니다.");
      
      list.add(dto);
   }
   
}