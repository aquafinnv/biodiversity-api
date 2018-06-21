package be.g00glen00b.apps.biodiversityapi.specie;

public enum SpeciesKingdom {
	ANIMAL("Animalia"), PLANT("Plantae");

	private final String scientificName;

	private SpeciesKingdom(String scientificName) {
		this.scientificName = scientificName;
	}

	public String getScientificName() {
		return scientificName;
	}
}
