#Manna SMS Project

sid:
AC7228b63ff3596902315d2966ddb3d1ad
pass:
1f3837b4f3c4c8c4c9f5fb376d448fb7

twilio number
215-703-9793

postman password:
Glqh4fWfDZ5e30

(https://api.twilio.com/2010-04-01/Accounts/AC7228b63ff3596902315d2966ddb3d1ad/Messages)[hi]

H2 Console url: [http://localhost:8080/h2-console] (note password is blank)

mvn spring-boot:run/ctrl+c
ngrok http 8080/ctrl+c

##Activity Log:

8/25/18 
Trying to get caught up on what I did last time. Last time I deployed locally and then used ngrok to give my app an endpoint on the internet. Note: ngrok http 8080 and I use the forwarding url that ngrok then provides. I needed this endpoint for the twilio api to use as a webhook for replying to incoming messages. This is the twilio page to change the webhook url: https://www.twilio.com/console/phone-numbers/PN15d9ee4fff6e321556fbd94ac73bb5b2 

Added a method in Outgoing.java, sendBlast() which pulls all the contacts into an Iterable object and then sends a message to each of them.

8/26/18
Figured out why my webhook was sending the reply message in full xml. For whatever reason the method in the controller was producing text/plain as the content-type and as that type it sent the full xml. When I switched it to text/xml, I think twilio then knew not to include the tags (e.g. <Response><Message>) 

I made the switch using the annotation @RequestMapping(value = “incoming”, produces = “text/xml;UT8-Charset”)

 9/08/18
/Users/sgould3/dev/workspace is the dir my project resides in


3/3/19
	Started working on logic for how to respond to incoming texts. A problem was that every time a user messaged us a new contact was added to the database, resulting in endless duplicates. The logic will be complex enough that migrated it to a helper class. A check will have to be done on whether the number exists in the db or not and then the response will be tailored based on that fact. In the helper class there will be method like:
String responseMethod(boolean exists, String phoneNumber, String incomingMessage) which will return the appropriate response message.
	My thinking has also changed in that it doesn’t make sense to me to have a subscribed boolean column. I think to respect people’s privacy it is just better to add or delete the contact in the db as the mechanism of opting in or out. 
	I also added a constant class for the response messages. 