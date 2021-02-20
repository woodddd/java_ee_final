package network;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;


public class ChatClient extends JFrame implements ActionListener, Runnable{
	private JTextField input;
	private JButton send;
	private JTextArea output;
	
	private Socket socket;
	
	private ObjectInputStream msgois;
	private ObjectOutputStream msgoos;
	
	private ObjectInputStream wormois;
	private ObjectOutputStream wormoos;
	
	public ChatClient() {
		InfoDTO dto = new InfoDTO();
		
		output = new JTextArea();
		JScrollPane scroll = new JScrollPane(output);
		scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		output.setEnabled(false);
		
		input = new JTextField();
		
		send = new JButton("보내기");
		
		JPanel p = new JPanel();
		p.setLayout(new BorderLayout());
		p.add("Center", input);
		p.add("East", send);
		
		Container con = this.getContentPane();
		con.add("Center",scroll);
		con.add("South",p);
		
		setBounds(700,200,300,300);
		setVisible(true);
		
		addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e) {
				if(msgois == null || msgoos == null ) {
					System.exit(0);
				}
				
				InfoDTO dto = new InfoDTO();
				dto.setCommand(Info.EXIT);
				
				try {
					msgoos.writeObject(dto);
					msgoos.flush();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
	}//ChatClient()
	
	public void service() {
		String serverIP = JOptionPane.showInputDialog(null,"서버IP를 입력하세요.");
		if(serverIP == null || serverIP.length() == 0) {
			System.out.println("서버IP가 입력되지 않았습니다.");
			System.exit(0);
		}
		
		String nickName = JOptionPane.showInputDialog(null,"닉네임을 입력하세요","닉네임",JOptionPane.INFORMATION_MESSAGE);
		if(nickName == null || nickName.length() == 0) {
			nickName = "guest";
		}
		
		try {
			socket = new Socket(serverIP, 9500);
			
			msgois = new ObjectInputStream(socket.getInputStream());
			msgoos = new ObjectOutputStream(socket.getOutputStream());
			
			wormois = new ObjectInputStream(socket.getInputStream());
			wormoos = new ObjectOutputStream(socket.getOutputStream());
			
		} catch (UnknownHostException e) {
			System.out.println("서버를 찾을 수 없습니다");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("서버를 찾을 수 없습니다.");
			e.printStackTrace();
		}
		
		InfoDTO dto = new InfoDTO();
		dto.setCommand(Info.JOIN);
		dto.setNickName(nickName);
		try {
			msgoos.writeObject(dto);
			msgoos.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		Thread t = new Thread(this);
		
		t.start();
		
		send.addActionListener(this);
		input.addActionListener(this);
	}//service()
	
	@Override
	public void run() {
	
		InfoDTO dto = null;
		
		while(true) {
			try {
				dto = (InfoDTO)msgois.readObject();
				
				 if(dto.getCommand() == Info.EXIT) {
					 msgois.close();
					 msgoos.close();
					 socket.close();
					 
					 System.exit(0);
				 }else if(dto.getCommand() == Info.SEND) {
					 output.append(dto.getMessage() + "\n");
					 
					 int pos = output.getText().length();
					 output.setCaretPosition(pos);//한줄이 초과되면 다음줄러 넘김
				 }
				
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}//while
	}//run()
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String msg = input.getText();
		
		InfoDTO dto = new InfoDTO();
		
		if(msg.toLowerCase().equals("quit")) {
			dto.setCommand(Info.EXIT);
		}else {
			dto.setCommand(Info.SEND);
			dto.setMessage(msg);
		}
		
		try {
			msgoos.writeObject(dto);
			msgoos.flush();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		input.setText("");
		
		
	}
	
	
	
	public static void main(String[] args) {
		new ChatClient().service();

	}

	
}
