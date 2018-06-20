package be.g00glen00b.apps.biodiversityapi.gbif;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GbifRecord {
    private Long gbifID;
    private String datasetName;
    private Double decimalLatitude;
    private Double decimalLongitude;
    private String scientificName;
    private String kingdom;
    private String phylum;
    private String order;
    private String family;
    private String speciesClass;
    private String genus;
    private String subgenus;
    private String vernacularName;
    private String verbatimSRS;
    private Integer individualCount;
    private String license;
    private String institutionCode;

    public boolean hasCoordinates() {
        return getDecimalLatitude() != null && getDecimalLongitude() != null;
    }
}
