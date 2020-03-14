package brave.wave.text.messager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

@Controller
public class Outgoing {
	public static final String ACCOUNT_SID = "AC7228b63ff3596902315d2966ddb3d1ad";
	  public static final String AUTH_TOKEN = "1f3837b4f3c4c8c4c9f5fb376d448fb7";
	  
	  @Autowired
	  ContactRepository repository;

	  
	  @RequestMapping("outgoing")
	  @ResponseBody
	  public String sendMessage(@RequestParam("PhoneNumber")String phoneNumber, @RequestParam("Text") String text) {
	      Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
	   
	      Message message = Message.creator(new PhoneNumber(phoneNumber),
				  new PhoneNumber("+12157039793" + 
				  		""), text).create();
		  return message.getBody();

	  }
	  
	  @RequestMapping("textblast")
	  @ResponseBody
	  public String sendBlast(@RequestParam("Text") String text) {
	      Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
	      Message message;
	      
	      try {
              
              Iterable<Contact> subscribers = repository.findBySubscribed(false);
              for (Contact contact : subscribers) {
            	  message = Message.creator(new PhoneNumber(contact.getPhoneNumber()),
        				  new PhoneNumber("+12157039793" + 
        				  		""), text).create();
              }
          } catch (Exception exception) {
              return exception.getMessage();
          }
	      
		  return "success";

	  }

	  @RequestMapping("anything")
	  @ResponseBody
	  public int changeSubscription(@RequestParam("PhoneNumber")String phoneNumber, @RequestParam("Subscribed")boolean subscribed) {
		  phoneNumber = "+1" + phoneNumber;
		  return repository.setSubscribedForContact(true, phoneNumber);
	  }
	  
	  
}
