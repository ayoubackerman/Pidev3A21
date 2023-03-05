package Api;



import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class JavaMail {
    public static void sendMail(String recepient) throws MessagingException {
        System.out.println("preparing to send email");
        Properties properties = new Properties();
        properties.put("mail.smtp.auth","true");
        properties.put("mail.smtp.starttls.enable","true");
        properties.put("mail.smtp.host","smtp.gmail.com");
        properties.put("mail.smtp.port","587");


        String myAccountEmail = "mohamed.rzem@esprit.tn";
        String password = "223AMT2630";



        Session session = Session.getInstance(properties,new Authenticator(){
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(myAccountEmail,password);
            }
        });

        Message message = prepareMessage(session,myAccountEmail,recepient);
        Transport.send(message);
        System.out.println("Message sent successfully");
    }

    private static Message prepareMessage(Session session, String myAccountEmail,String recepient) throws MessagingException {
        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(myAccountEmail));
        message.setRecipient(Message.RecipientType.TO,new InternetAddress(recepient));
        message.setSubject("URGENCE VROMVROM");
        String htmlCode = "<h1> salut , vouz avez recu un message d'urgence sur notre application vromvrom </h1> <br/> <h2><b> Veuillez rependre le plus tot possible </b></h2>";
        message.setContent(htmlCode,"text/html");
        return message;
    }
}
