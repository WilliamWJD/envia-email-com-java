package enviando.email;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) {
		StringBuilder stringBuilderTextoEmail = new StringBuilder();

		stringBuilderTextoEmail.append("<strong>Olá,</strong>");
		stringBuilderTextoEmail.append("<strong>Você está recebendo o acesso ao curso Java</strong>");
		stringBuilderTextoEmail.append("<p>Estámos muito feliz em tê-lo conosco, vai ser uma jornada e tanto.</p>");
		stringBuilderTextoEmail.append("<p>Para continuarmos basta clicar no botão abaixo.</p>");
		stringBuilderTextoEmail.append(
				"<a target=\"_blank\" href=\"https://projetojavaweb.com/certificado-aluno/plataforma-curso/aulaatual/470389405/idcurso/1/idvideoaula/428\" style=\"padding: 10px; background-color: #7159c1; color: #fff; border:none; text-decoration: none\">Vamos começar ?</a>");

		ObjetoEnviaEmail enviaEmail = new ObjetoEnviaEmail("william.jose.wjd@gmail.com", "William Dias - Dev Backend",
				"Teste", stringBuilderTextoEmail.toString());

		enviaEmail.enviarEmail(true);
	}
	
	// METODO SIMULA O PDF OU QUALQUER ARQUIVO QUE POSSA SER ENVIADO POR ANEXO NO E-MAIL
	private FileInputStream simuladorDePDF() throws Exception{
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
