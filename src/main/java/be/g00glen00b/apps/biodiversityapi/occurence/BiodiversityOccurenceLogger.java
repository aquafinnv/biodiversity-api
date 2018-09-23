package be.g00glen00b.apps.biodiversityapi.occurence;

import be.g00glen00b.apps.biodiversityapi.specie.SpeciesRepository;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class BiodiversityOccurenceLogger implements JobExecutionListener {
	private final Logger logger = LoggerFactory.getLogger(getClass());
	private BiodiversityOccurenceRepository repository;
	private SpeciesRepository speciesRepository;

	@Override
	public void beforeJob(JobExecution jobExecution) {

	}

	@Override
	public void afterJob(JobExecution jobExecution) {
		logger.info("Registered {} occurrences", repository.count());
		logger.info("Registered {} different species", speciesRepository.count());
	}
}
