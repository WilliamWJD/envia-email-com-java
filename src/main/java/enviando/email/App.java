package enviando.email;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) {
		ObjetoEnviaEmail enviaEmail = new ObjetoEnviaEmail("william.jose.wjd@gmail.com", "William Dias - Dev Backend",
				"Teste", "Esse e-mail é um teste");

		enviaEmail.enviarEmail(true);
	}
}
