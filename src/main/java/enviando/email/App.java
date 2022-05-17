package enviando.email;

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
}
