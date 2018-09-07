package be.g00glen00b.apps.biodiversityapi.gbif;

import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;

public class GbifTokenizer extends DelimitedLineTokenizer {
	public GbifTokenizer() {
	}

	public GbifTokenizer(String delimiter) {
		super(delimiter);
	}

	@Override
	protected boolean isQuoteCharacter(char c) {
		return false;
	}
}
