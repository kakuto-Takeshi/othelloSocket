package othelloSocket;

import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class MsgEncoder implements Encoder.Text<MsgObj>{
	ObjectMapper mapper;

	@Override
	public void destroy() {}
	@Override
	public void init(EndpointConfig config) {mapper = new ObjectMapper();}

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
