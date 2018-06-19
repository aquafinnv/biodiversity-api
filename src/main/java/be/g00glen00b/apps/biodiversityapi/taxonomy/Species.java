package be.g00glen00b.apps.biodiversityapi.taxonomy;

import be.g00glen00b.apps.biodiversityapi.occurence.BiodiversityOccurence;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.common.collect.Lists;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Arrays;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "TAXONOMY")
@ToString(exclude = "occurences")
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
    @OneToMany(mappedBy = "species")
    @JsonIgnore
    private List<BiodiversityOccurence> occurences;

    public List<String> getTaxonomy() {
        return Lists.newArrayList(
            getKingdom(),
            getPhylum(),
            getSpeciesClass(),
            getSpeciesOrder(),
            getFamily(),
            getGenus(),
            getSubgenus());
    }
}
