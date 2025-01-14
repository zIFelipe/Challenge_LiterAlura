package Alura_Cursos.LiterAlura_Challenge;

import Alura_Cursos.LiterAlura_Challenge.clasePrincipal.Principal;
import Alura_Cursos.LiterAlura_Challenge.repository.libroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LiterAluraChallengeApplication  implements CommandLineRunner {

	@Autowired
	private libroRepository repositoryL;
	public static void main(String[] args) {
		SpringApplication.run(LiterAluraChallengeApplication.class, args);
	}
	@Override
	public void run(String... args) throws Exception {
		Principal principal = new Principal(repositoryL);
		principal.muestraElMenu();
	}

}
