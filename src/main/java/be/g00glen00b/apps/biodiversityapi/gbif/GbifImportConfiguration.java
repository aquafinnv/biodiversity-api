package be.g00glen00b.apps.biodiversityapi.gbif;

import be.g00glen00b.apps.biodiversityapi.api.APIProperties;
import be.g00glen00b.apps.biodiversityapi.occurence.BiodiversityOccurence;
import be.g00glen00b.apps.biodiversityapi.occurence.BiodiversityOccurenceLogger;
import be.g00glen00b.apps.biodiversityapi.occurence.BiodiversityOccurenceWriter;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GbifImportConfiguration {

	@Bean
	public Job gbifImporterJob(JobBuilderFactory factory, Step gbifImporterStep, BiodiversityOccurenceLogger logger) {
		return factory.get("gbifImporterJob")
			.listener(logger)
			.incrementer(new RunIdIncrementer())
			.flow(gbifImporterStep)
			.end().build();
	}

	@Bean
	public Step gbifImporterStep(StepBuilderFactory factory, APIProperties properties,
								 GbifReader reader, GbifProcessor processor, BiodiversityOccurenceWriter writer) {
		return factory.get("gbifImporterStep")
			.<GbifRecord, BiodiversityOccurence>chunk(properties.getChunkSize())
			.reader(reader)
			.processor(processor)
			.writer(writer)
			.build();
	}
}
