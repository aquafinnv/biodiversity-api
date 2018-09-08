package be.g00glen00b.apps.biodiversityapi.occurence;

import be.g00glen00b.apps.biodiversityapi.api.APIConfiguration;
import be.g00glen00b.apps.biodiversityapi.specie.SpeciesRepository;
import org.apache.commons.io.IOUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.io.File;
import java.io.FileWriter;
import java.io.Writer;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
@Import(APIConfiguration.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class BiodiversityOccurenceApiTest {
	@Autowired
	private MockMvc mockMvc;
	@MockBean
	private BiodiversityService service;

	@Test
	public void swaggerJsonExists() throws Exception {
		String contentAsString = mockMvc
			.perform(get("/v2/api-docs")
				.accept(MediaType.APPLICATION_JSON)
				.param("group", APIConfiguration.GROUP_NAME))
			.andExpect(status().isOk())
			.andReturn()
			.getResponse().getContentAsString();
		try (Writer writer = new FileWriter(new File("target/generated-sources/swagger.json"))) {
			IOUtils.write(contentAsString, writer);
		}
	}
}
