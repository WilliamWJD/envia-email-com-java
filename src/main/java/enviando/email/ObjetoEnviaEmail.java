package enviando.email;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.util.ByteArrayDataSource;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

public class ObjetoEnviaEmail {

	private String userName = "trunks.hanibal@gmail.com";
	private String password = "trunks789456123";

	private String listaDestinatarios = "";
	private String nomeRemetente = "";
	private String assuntoEmail = "";
	private String textoEmail = "";

	public ObjetoEnviaEmail(String listaDestinatario, String nomeRemetente, String assuntoEmail, String textoEmail) {
		this.listaDestinatarios = listaDestinatario;
		this.nomeRemetente = nomeRemetente;
		this.assuntoEmail = assuntoEmail;
		this.textoEmail = textoEmail;
	}

	public void enviarEmail(boolean enviaHtml) {
		try {
			Properties properties = new Properties();

			// CASO SEJA NECESSÁRIO A UTILIZAÇÃO DE SSL
//			properties.put("mail.smtp.ssl.trust", "*");

			properties.put("mail.smtp.auth", "true");
			properties.put("mail.smtp.startls", "true");
			properties.put("mail.smtp.host", "smtp.gmail.com");
			properties.put("mail.smtp.port", "465");
			properties.put("mail.smtp.socketFactory.port", "465");
			properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");

			Session session = Session.getInstance(properties, new Authenticator() {

				@Override
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(userName, password);
				}
			});

			Address[] toUser = InternetAddress.parse(listaDestinatarios);

			Message message = new MimeMessage(session);

			message.setFrom(new InternetAddress(userName, nomeRemetente));
			message.setRecipients(Message.RecipientType.TO, toUser);
			message.setSubject(assuntoEmail);

			if (enviaHtml) {
				message.setContent(textoEmail, "text/html; charset=utf-8");
			} else {
				message.setText(textoEmail);
			}

			Transport.send(message);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void enviarEmailAnexo(boolean enviaHtml) {
		try {
			Properties properties = new Properties();

			// CASO SEJA NECESSÁRIO A UTILIZAÇÃO DE SSL
//			properties.put("mail.smtp.ssl.trust", "*");

			properties.put("mail.smtp.auth", "true");
			properties.put("mail.smtp.startls", "true");
			properties.put("mail.smtp.host", "smtp.gmail.com");
			properties.put("mail.smtp.port", "465");
			properties.put("mail.smtp.socketFactory.port", "465");
			properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");

			Session session = Session.getInstance(properties, new Authenticator() {

				@Override
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(userName, password);
				}
			});

			Address[] toUser = InternetAddress.parse(listaDestinatarios);

			Message message = new MimeMessage(session);

			message.setFrom(new InternetAddress(userName, nomeRemetente));
			message.setRecipients(Message.RecipientType.TO, toUser);
			message.setSubject(assuntoEmail);

			// parte 1 do e-mail que é o texto e a descrição do e-mail
			MimeBodyPart corpoEmail = new MimeBodyPart();

			if (enviaHtml) {
				corpoEmail.setContent(textoEmail, "text/html; charset=utf-8");
			} else {
				corpoEmail.setText(textoEmail);
			}

			// parte 2 do e-mail que são os anexo em pdf
			MimeBodyPart anexoEmail = new MimeBodyPart();
			anexoEmail.setDataHandler(new DataHandler(new ByteArrayDataSource(simuladorDePDF(), "application/pdf")));
			anexoEmail.setFileName("anexoemail.pdf");
			
			Multipart multipart = new MimeMultipart();
			multipart.addBodyPart(corpoEmail);
			multipart.addBodyPart(anexoEmail);
			
			message.setContent(multipart);

			Transport.send(message);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// METODO SIMULA O PDF OU QUALQUER ARQUIVO QUE POSSA SER ENVIADO POR ANEXO NO
	// E-MAIL
	private FileInputStream simuladorDePDF() throws Exception {
		Document document = new Document();

		File file = new File("anexo.pdf");
		file.createNewFile();

		PdfWriter.getInstance(document, new FileOutputStream(file));

		document.open();
		document.add(new Paragraph("Conteudo do PDF anexo com JavaMail"));
		document.close();

		return new FileInputStream(file);
	}
}
