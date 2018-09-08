package be.g00glen00b.apps.biodiversityapi.occurence;

import be.g00glen00b.apps.biodiversityapi.specie.SpeciesDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BiodiversityOccurenceDTO {
    private Long id;
    private Double longitude;
    private Double latitude;
    private SpeciesDTO species;
}
