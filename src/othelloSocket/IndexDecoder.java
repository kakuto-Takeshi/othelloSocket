package othelloSocket;

import javax.websocket.DecodeException;
import javax.websocket.Decoder;
import javax.websocket.EndpointConfig;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class IndexDecoder implements Decoder.Text<BaseObj>{
	ObjectMapper mapper;

	@Override
	public void destroy() {	}
	@Override
	public void init(EndpointConfig config) {mapper = new ObjectMapper();}

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

}
