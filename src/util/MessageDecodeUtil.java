package util;

import java.io.IOException;
import java.sql.Timestamp;

import com.fasterxml.jackson.databind.ObjectMapper;

import model.Message;

//解码工具类
public class MessageDecodeUtil {
	
	//解码
	public static Message decode(final String msg){
		try {
			ObjectMapper mapper = new ObjectMapper();
			Message message;
			message = mapper.readValue(msg, Message.class);
			
			//String time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Timestamp(System.currentTimeMillis()));
			message.setTime(new Timestamp(System.currentTimeMillis()));
			
			return message;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
