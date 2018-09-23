package be.g00glen00b.apps.biodiversityapi.specie;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SpeciesDTO {
    private String scientificName;
    private String vernacularName;
    private String kingdom;
    private String phylum;
    private String speciesClass;
    private String speciesOrder;
    private String family;
    private String genus;
    private String subgenus;
    private String imageUrl;
}
