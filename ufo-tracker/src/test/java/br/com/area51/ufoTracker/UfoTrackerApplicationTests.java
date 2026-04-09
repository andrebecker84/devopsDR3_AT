package br.com.area51.ufoTracker;

import br.com.area51.ufoTracker.model.jpa.AvistamentoJPA;
import br.com.area51.ufoTracker.repository.jpa.AvistamentoJPARepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

@SpringBootTest
class UfoTrackerApplicationTests {
	@Autowired
	private AvistamentoJPARepository repository;
	@Test
	void testaAlgo() {
		//List<AvistamentoJPA> ufo = repository.findByCidade("UFO");
		List<AvistamentoJPA> niteroi = repository.findByCidadeAndConfiabilidadeGreaterThanEqual("Niterói", 3);
		niteroi.forEach(System.out::println);

	}
	@Test
	void testaAlgo2() {
		//List<AvistamentoJPA> ufo = repository.findByCidade("UFO");
		Optional<AvistamentoJPA> niteroi = repository.findFirstByCidadeOrderByConfiabilidadeDesc("Niterói");
		niteroi.ifPresent(System.out::println);

		long niterói = repository.countByCidade("Niterói");
		System.out.println(niterói);


	}

}
