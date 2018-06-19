package be.g00glen00b.apps.biodiversityapi.gbif;

import be.g00glen00b.apps.biodiversityapi.occurence.BiodiversityOccurence;
import be.g00glen00b.apps.biodiversityapi.taxonomy.Species;
import be.g00glen00b.apps.biodiversityapi.taxonomy.SpeciesRepository;
import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.GeometryFactory;
import lombok.AllArgsConstructor;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
@AllArgsConstructor
public class GbifProcessor implements ItemProcessor<GbifRecord, BiodiversityOccurence> {
    private GeometryFactory geometryFactory;
    private SpeciesRepository repository;

    @Override
    public BiodiversityOccurence process(GbifRecord gbifRecord) {
        return new BiodiversityOccurence(
            gbifRecord.getGbifID(),
            gbifRecord.getDatasetName(),
            geometryFactory.createPoint(new Coordinate(
                gbifRecord.getDecimalLongitude(),
                gbifRecord.getDecimalLatitude()
            )),
            getSpecies(gbifRecord));
    }

    private Species getSpecies(GbifRecord gbifRecord) {
        return repository.findById(gbifRecord.getScientificName())
            .orElseGet(() -> repository.saveAndFlush(new Species(
                gbifRecord.getScientificName(),
                gbifRecord.getVernacularName(),
                gbifRecord.getKingdom(),
                gbifRecord.getPhylum(),
                gbifRecord.getSpeciesClass(),
                gbifRecord.getOrder(),
                gbifRecord.getFamily(),
                gbifRecord.getGenus(),
                gbifRecord.getSubgenus(),
                new ArrayList<>())));
    }
}
