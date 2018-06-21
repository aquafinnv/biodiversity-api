package be.g00glen00b.apps.biodiversityapi.occurence;

import be.g00glen00b.apps.biodiversityapi.specie.Species;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.vividsolutions.jts.geom.Point;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "BIODIVERSITY_OCCURRENCE")
public class BiodiversityOccurence {
    @Id
    @Column(name = "ID")
    private Long id;
    @Column(name = "SOURCE")
    private String source;
    @Column(name = "CENTER")
    @JsonIgnore
    private Point center;
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "SCIENTIFIC_NAME")
    private Species species;
}
