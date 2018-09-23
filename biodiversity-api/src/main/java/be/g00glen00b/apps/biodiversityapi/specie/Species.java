package be.g00glen00b.apps.biodiversityapi.specie;

import be.g00glen00b.apps.biodiversityapi.occurence.BiodiversityOccurence;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "SPECIES")
@ToString(exclude = "occurrences")
public class Species {
    @Id
    @Column(name = "SCIENTIFIC_NAME")
    private String scientificName;
    @Column(name = "VERNACULAR_NAME")
    private String vernacularName;
    @JsonIgnore
    @Column(name = "KINGDOM")
    private String kingdom;
    @JsonIgnore
    @Column(name = "PHYLUM")
    private String phylum;
    @JsonIgnore
    @Column(name = "SPECIES_CLASS")
    private String speciesClass;
    @JsonIgnore
    @Column(name = "SPECIES_ORDER")
    private String speciesOrder;
    @JsonIgnore
    @Column(name = "FAMILY")
    private String family;
    @JsonIgnore
    @Column(name = "GENUS")
    private String genus;
    @JsonIgnore
    @Column(name = "SUBGENUS")
    private String subgenus;
    @JsonIgnore
    @OneToMany(mappedBy = "species")
    private List<BiodiversityOccurence> occurrences;

    public Taxonomy getTaxonomy() {
        return new Taxonomy(
            getKingdom(),
            getPhylum(),
            getSpeciesClass(),
            getSpeciesOrder(),
            getFamily(),
            getGenus(),
            getSubgenus());
    }
}
