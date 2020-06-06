package othelloSocket;

import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Encoders {

	private static abstract class BaseEncoder<T extends BaseObj> implements Encoder.Text<T>{
		ObjectMapper mapper;
		@Override
		public void init(EndpointConfig config) {mapper = new ObjectMapper();}
		@Override
		public void destroy() {}
	}

	public static class MsgEncoder extends BaseEncoder<MsgObj>{
		@Override
		public String encode(MsgObj obj) throws EncodeException {
			 mapper = new ObjectMapper();
			String json = "";
			try {
				json = mapper.writeValueAsString(obj);
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}
			return json;
		}
	}

	public static class IndexEncoder extends BaseEncoder<IndexObj>{
		@Override
		public String encode(IndexObj obj) throws EncodeException {
			 mapper = new ObjectMapper();
			String json = "";
			try {
				json = mapper.writeValueAsString(obj);
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}
			return json;
		}
	}

}
