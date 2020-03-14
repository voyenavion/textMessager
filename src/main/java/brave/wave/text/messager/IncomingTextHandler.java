package brave.wave.text.messager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.twilio.sdk.verbs.Message;
import com.twilio.sdk.verbs.TwiMLException;
import com.twilio.sdk.verbs.TwiMLResponse;

@Component
public class IncomingTextHandler {
	
	@Autowired
	ContactRepository contactRepository;
	
	public String receiveMessage(String fromPhoneNumber, String messageText) {
		boolean contactExists = contactRepository.existsByPhonenumber(fromPhoneNumber);
		TwiMLResponse twiml = new TwiMLResponse();
	    Message message;
	    Contact contact = new Contact(fromPhoneNumber);
	    
	    if(messageText.toLowerCase().contains("add")){
	    		message = new Message("You are successfully subscribed to messages from Manna on Main Street!");
	    		contact.setSubscribed(true);
	    } else {
	    		message = new Message("Hello! You've reached Manna on Main Street :) You are not currently subscribed. "
	    				+ "Text the word \"add\" to subcribe to our messages.");
	    }
	    
	    try {
	        twiml.append(message);
	    } catch (TwiMLException e) {
	        e.printStackTrace();
	    }

	    contactRepository.save(contact);
	    
	    return twiml.toXML();
	  }
	
	protected String selectAppropriateMessageForUser(String fromPhoneNumber, String messageText) {
		return null;
	}

}

