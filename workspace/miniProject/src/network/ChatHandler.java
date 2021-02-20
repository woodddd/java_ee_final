package network;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;

import miniProject.WormDTO;

public class ChatHandler extends Thread {
   private Socket socket;
   private List<ChatHandler> list;
   
   private ObjectInputStream wormois;
   private ObjectOutputStream wormoos;
   
   public ChatHandler(Socket socket, List<ChatHandler> list) throws IOException {
      this.socket = socket;
      this.list = list;
      
      //ois 보다 먼저 생성되어야 한다(안그러면 입장메세지가 안 뜬다)
      wormoos = new ObjectOutputStream(socket.getOutputStream());
      wormois = new ObjectInputStream(socket.getInputStream());
      
   }
   
   @Override
   public void run() {
      //클라이언트로 부터 받는 쪽
	   WormDTO dto = null;
      String nickName = null;
      
      while(true) {
         try {
            dto = (WormDTO)wormois.readObject();
            
            if(dto.getCommand() == WormForm.ID) {//동일 패키지로 옮기면 사용 가능.
               //만약 아이디만 넘어온다면, 준비화면에서 클라이언트의 각 화면의 지렁이 좌표를 받아와서 대기상태 화면 디스플레이 고려해볼 필요 있음.
               
            }else if(dto.getCommand() == WormForm.EXIT) {
               WormDTO sendDTO = new WormDTO();
               
               //quit를 보낸 클라이언트에게 quit를 보내기(나에게)
               sendDTO.setCommand(Info.EXIT);
               wormoos.writeObject(WormDTO);
               wormoos.flush();
               
               wormois.close();
               wormoos.close();
               socket.close();
               
               //남은 클라이언트에게즈  퇴장메세지 보내기
               list.remove(this);
               
               //클라이언트가 나가면 우리는 화면에서 현재클라이언트 지렁이를 삭제.
//               sendDTO.setCommand(Info.SEND);
//               sendDTO.setMessage(nickName+"님 퇴장하였습니다");
//               broadcast(sendDTO);
               
               break;
               
            }else if(dto.getCommand() == WormForm.DATA) {
               InfoDTO sendDTO = new InfoDTO();
               sendDTO.setCommand(Info.SEND);
               sendDTO.setMessage("["+nickName+"] "+dto.getMessage());
               broadcast(sendDTO);
            }
            
         } catch (ClassNotFoundException e) {
            e.printStackTrace();
         } catch (IOException e) {
            e.printStackTrace();
         }
      }//while
      
   }
   
   public void broadcast(WormDTO sendDTO) {
      for(ChatHandler handler : list) {
         try {
            handler.wormoos.writeObject(sendDTO);
            handler.wormoos.flush();
         } catch (IOException e) {
            e.printStackTrace();
         }
      }//for
   }
}
