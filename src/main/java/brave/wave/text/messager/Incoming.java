package brave.wave.text.messager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.twilio.sdk.verbs.Message;
import com.twilio.sdk.verbs.TwiMLException;
import com.twilio.sdk.verbs.TwiMLResponse;

@Controller
public class Incoming {
	
	@Autowired
	ContactRepository contactRepository;
	
	@Autowired
	IncomingTextHandler incomingTextHandler; 
	
	@RequestMapping(value = "incoming", produces="text/xml; charset=utf-8" )
	@ResponseBody
	public String receiveMessage(@RequestParam("From")String fromPhoneNumber, @RequestParam("Body") String messageText) {
		return incomingTextHandler.receiveMessage(fromPhoneNumber, messageText);
	}
}
