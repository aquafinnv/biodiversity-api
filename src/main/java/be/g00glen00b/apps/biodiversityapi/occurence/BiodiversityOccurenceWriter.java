package be.g00glen00b.apps.biodiversityapi.occurence;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

import static java.util.stream.Collectors.toList;

@Component
public class BiodiversityOccurenceWriter implements ItemWriter<BiodiversityOccurence> {
	private static final String OCCURRENCES_KEY = "occurrences";
	private final Logger logger = LoggerFactory.getLogger(getClass());
	private BiodiversityOccurenceRepository repository;
	private ExecutionContext context;

	public BiodiversityOccurenceWriter(BiodiversityOccurenceRepository repository) {
		this.repository = repository;
	}

	@Override
	public void write(List<? extends BiodiversityOccurence> occurences) {
		updateOccurrences(occurences);
		repository.saveAll(occurences.stream().filter(Objects::nonNull).collect(toList()));
	}

	private int updateOccurrences(List<? extends BiodiversityOccurence> occurences) {
		int oldSize = context.getInt(OCCURRENCES_KEY);
		int newSize = oldSize + occurences.size();
		context.put(OCCURRENCES_KEY, newSize);
		logger.info("Imported {} biodiversity occurrences", newSize);
		return newSize;
	}

	@BeforeStep
	public void initialize(StepExecution execution) {
		this.context = execution.getExecutionContext();
		this.context.putInt(OCCURRENCES_KEY, 0);
	}
}
