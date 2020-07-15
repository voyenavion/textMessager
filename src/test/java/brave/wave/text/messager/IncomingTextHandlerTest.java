package brave.wave.text.messager;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;


@SpringBootTest
public class IncomingTextHandlerTest {

	@Autowired
	IncomingTextHandler incomingTextHandler;
	@Autowired
	ContactRepository contactRepository;


	@BeforeEach
	public void setUp() {

	}

	@Test
	public void shouldEnterPhoneNumberInDb(){
		String phoneNumber = "2673746841";
		incomingTextHandler.receiveMessage(phoneNumber, "wasssssssup add me");
		List<Contact> contactList = contactRepository.findByPhonenumber(phoneNumber);
		assertTrue(contactList.get(0).getPhonenumber()==phoneNumber);
	}


}
