package Util;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import java.util.Properties;

public class Email
{
    private static final String usuario = "joaopenteado@finchsolucoes.com.br";
    private static final String senha = "manda5595";
    private static final String smtp = "mail.smtp.host";

    public Session logar(){

        Properties prop = new Properties();

        prop = System.getProperties();
        prop.setProperty(smtp, "zimbra.finchsolucoes.com.br");
//       prop.setProperty("mail.user", usuario);
//       prop.setProperty("mail.user", senha);
        Properties props = System.getProperties();
        props.put("mail.smtp.host", "zimbra.finchsolucoes.com.br");
        props.put("mail.smtp.auth", "true");

        Authenticator auth = new SMTPAuthenticator();
        Session logar = Session. getDefaultInstance(prop, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication(){
                return new PasswordAuthentication(usuario, senha);}
        });
        return logar;
    }

    private class SMTPAuthenticator extends javax.mail.Authenticator {
        public PasswordAuthentication getPasswordAuthentication(){
            String user = usuario;
            String pass = senha;
            return new PasswordAuthentication(user, pass);
        }

    }
}
