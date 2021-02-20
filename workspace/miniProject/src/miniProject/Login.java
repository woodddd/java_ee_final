package miniProject;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class Login extends JFrame implements ActionListener{
   private JLabel idL, pwL;
   private JTextField idT;
   private JButton joinB, loginB;
   private Image image, btI;
   private JPasswordField pwT;
   
   //private LoginResult result;
   private List<ClientDTO> list;
   
   public Login() {
	   super("로그인");
	   
	   
      //JLabel
      idL = new JLabel("ID       : ");
      idL.setOpaque(false);
      idL.setFont(new Font("휴먼나무T", Font.BOLD, 20));
      idL.setForeground(new Color(200,200,200));
      pwL = new JLabel("PASSWORD :");
      pwL.setOpaque(false);
      pwL.setFont(new Font("휴먼나무T", Font.BOLD, 20));
      pwL.setForeground(new Color(200,200,200));
      //JTextField
      idT = new JTextField(10);
      idT.setOpaque(false);
      pwT = new JPasswordField(10);
      pwT.setOpaque(false);
      setUndecorated(true);
      
      //JButton
      joinB = new JButton("회원가입");
      loginB = new JButton( "로그인");
      
      //Image
      image = Toolkit.getDefaultToolkit().getImage("pinkW.jpg");
      btI = Toolkit.getDefaultToolkit().getImage("07.png");
     
      idT.setForeground(Color.WHITE);
      pwT.setForeground(Color.WHITE);
      idT.setFont(new Font("휴먼나무T", Font.BOLD, 20));
      pwT.setFont(new Font("휴먼나무T", Font.BOLD, 20));
      idT.setOpaque(false);
      pwL.setOpaque(false);
      pwT.setOpaque(false);
      
      
      //투명 반투명 오파크
      JPanel idP = new JPanel();
      idP.add(idL);
      idP.add(idT);
      idP.setOpaque(false);
      
      
      JPanel pwP = new JPanel();
      pwP.add(pwL);
      pwP.add(pwT);
      pwP.setOpaque(false);
      pwT.setEchoChar('●');
      
      JPanel insertP = new JPanel();
      
      insertP.add(idP);
      insertP.add(pwP);
      
      
  
      
      JPanel btg = new JPanel();
      btg.add(joinB);
      btg.add(loginB);
      
      image = Toolkit.getDefaultToolkit().getImage("lib/9LnX.gif");
      
      JPanel c = new JPanel() {
    	  public void paintComponent(Graphics g) {
      	       g.drawImage(image, 0, 0, this);
      	       setOpaque(false);
      	       super.paintComponent(g);
      	   } };
      	   insertP.setOpaque(false);
      	   btg.setOpaque(false);
      c.setLayout(new BorderLayout());
      c.add("Center", insertP);
      c.add("South", btg);
     
      add(c);
      
      setVisible(true);
      setBounds(650,315,600,600);
      
      joinB.addActionListener(this);
      loginB.addActionListener(this);
      
      
   }//login
   
   public void idCheck() {
      ClientDAO dao = ClientDAO.getInstance();
      dao.getConnection();
      
      list = dao.getClientList();
      int count = 0;
      for(ClientDTO dto : list) {
         if(dto.getId().equals(idT.getText())&&
            dto.getPw().equals(pwT.getText()))
         {
            JOptionPane.showMessageDialog(this, "로그인 성공!");
            count = 1;
            System.out.println(JOptionPane.YES_OPTION);
            if(JOptionPane.YES_OPTION == 0) //new WormSelect();
            setVisible(false);   
            break;
            
         }//if
            
         
      }//for
      if(count == 0) {
         JOptionPane.showMessageDialog(this,    "아이디 또는 비밀번호가 일치하지 않습니다");
      }
   }//idCheck
   
   
   @Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		super.paint(g);
	}
   
   @Override
   public void actionPerformed(ActionEvent e) {
      if(e.getSource() == joinB) {
         new SignUp();
                  
      }
      if(e.getSource() == loginB) {
         idCheck();
         
      }
   }
   
}//