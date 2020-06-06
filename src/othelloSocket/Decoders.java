package othelloSocket;

import javax.websocket.DecodeException;
import javax.websocket.Decoder;
import javax.websocket.EndpointConfig;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Decoders {

	private static abstract class BaseDecoder<T extends BaseObj> implements Decoder.Text<T>{
		ObjectMapper mapper;
		@Override
		public void init(EndpointConfig config) {mapper = new ObjectMapper();}
		@Override
		public void destroy() {}
	}

	public static class MsgDecoder extends BaseDecoder<BaseObj>{
		@Override
		public boolean willDecode(String text) {
			JsonNode node = null;
			try {
				node = mapper.readTree(text);
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}
			boolean isDecode = (node.get("type") != null && node.get("msg") != null);
			return isDecode;
		}

		@Override
		public MsgObj decode(String text) throws DecodeException {
			MsgObj obj = null;
			try {
				obj = mapper.readValue(text, MsgObj.class);
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}
			return obj;
		}
	}

	public static class IndexDecoder extends BaseDecoder<BaseObj>{
		@Override
		public boolean willDecode(String text) {
			JsonNode node = null;
			try {
				node = mapper.readTree(text);
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}
			boolean isDecode = (node.get("type") != null && node.get("index") != null);
			return isDecode;
		}

		@Override
		public IndexObj decode(String text) throws DecodeException {
			IndexObj obj = null;
			try {
				obj = mapper.readValue(text, IndexObj.class);
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}
			return obj;
		}
	}
}
