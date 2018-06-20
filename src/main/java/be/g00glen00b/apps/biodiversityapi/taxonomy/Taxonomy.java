package be.g00glen00b.apps.biodiversityapi.taxonomy;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Taxonomy {
	private String kingdom;
	private String phylum;
	private String speciesClass;
	private String speciesOrder;
	private String family;
	private String genus;
	private String subgenus;
}
