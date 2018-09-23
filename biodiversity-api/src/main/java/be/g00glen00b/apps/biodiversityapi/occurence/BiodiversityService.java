package be.g00glen00b.apps.biodiversityapi.occurence;

import be.g00glen00b.apps.biodiversityapi.media.MediaService;
import be.g00glen00b.apps.biodiversityapi.specie.SpeciesDTO;
import be.g00glen00b.apps.biodiversityapi.specie.SpeciesKingdom;
import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.Point;
import lombok.AllArgsConstructor;
import org.geotools.geometry.jts.JTS;
import org.geotools.referencing.CRS;
import org.geotools.referencing.crs.DefaultGeographicCRS;
import org.opengis.referencing.FactoryException;
import org.opengis.referencing.crs.CoordinateReferenceSystem;
import org.opengis.referencing.operation.MathTransform;
import org.opengis.referencing.operation.TransformException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class BiodiversityService {
    private GeometryFactory geometryFactory;
    private MediaService mediaService;
    private BiodiversityOccurenceRepository repository;

    public BiodiversityOccurenceDTOWrapper findAll(double latitude, double longitude, int bufferZoneMeters, boolean localName, int page, int limit) throws FactoryException, TransformException {
        Point point = geometryFactory.createPoint(new Coordinate(longitude, latitude));
        Page<BiodiversityOccurence> result = findNearby(getBuffer(point, bufferZoneMeters), localName, PageRequest.of(page, limit));
        return new BiodiversityOccurenceDTOWrapper(findDTOs(result.getContent()), page, limit, result.getTotalElements());
    }

    public BiodiversityOccurenceDTOWrapper findAll(double latitude, double longitude, int bufferZoneMeters, boolean localName, SpeciesKingdom kingdom, int page, int limit) throws FactoryException, TransformException {
        Point point = geometryFactory.createPoint(new Coordinate(longitude, latitude));
        Page<BiodiversityOccurence> result = findNearby(getBuffer(point, bufferZoneMeters), kingdom, localName, PageRequest.of(page, limit));
        return new BiodiversityOccurenceDTOWrapper(findDTOs(result.getContent()), page, limit, result.getTotalElements());
    }

    public BiodiversityOccurenceDTO findOne(Long id) {
        BiodiversityOccurence entity = repository.findById(id).orElseThrow(BiodiversityNotFoundException::new);
        Map<String, Optional<String>> media = mediaService.getMedia(Collections.singletonList(entity.getSpecies()));
        return findDTO(entity, media);
    }

    private Page<BiodiversityOccurence> findNearby(Geometry buffer, boolean localName, PageRequest pageRequest) {
        if (localName) {
            return repository.findNearbyWithVernacularName(buffer, pageRequest);
        } else {
            return repository.findNearby(buffer, pageRequest);
        }
    }

    private Page<BiodiversityOccurence> findNearby(Geometry buffer, SpeciesKingdom kingdom, boolean localName, PageRequest pageRequest) {
        if (localName) {
            return repository.findNearbyWithVernacularName(buffer, kingdom.getScientificName(), pageRequest);
        } else {
            return repository.findNearby(buffer, kingdom.getScientificName(), pageRequest);
        }
    }

    private Geometry getBuffer(Point point, int bufferZone) throws FactoryException, TransformException {
        CoordinateReferenceSystem reference = CRS.decode("AUTO:42001," + point.getX() + "," + point.getY());
        MathTransform toTransform = CRS.findMathTransform(DefaultGeographicCRS.WGS84, reference);
        MathTransform fromTransform = CRS.findMathTransform(reference, DefaultGeographicCRS.WGS84);
        Geometry center = JTS.transform(point, toTransform);
        Geometry buffer = center.buffer(bufferZone);
        return JTS.transform(buffer, fromTransform);
    }

    private List<BiodiversityOccurenceDTO> findDTOs(List<BiodiversityOccurence> entities) {
        Map<String, Optional<String>> media = mediaService.getMedia(entities.stream().map(BiodiversityOccurence::getSpecies).collect(Collectors.toList()));
        return entities.stream().map(entity -> findDTO(entity, media)).collect(Collectors.toList());
    }

    private BiodiversityOccurenceDTO findDTO(BiodiversityOccurence entity, Map<String, Optional<String>> media) {
        return new BiodiversityOccurenceDTO(
            entity.getId(),
            entity.getCenter().getX(),
            entity.getCenter().getY(),
            new SpeciesDTO(
                entity.getSpecies().getScientificName(),
                entity.getSpecies().getVernacularName(),
                entity.getSpecies().getKingdom(),
                entity.getSpecies().getPhylum(),
                entity.getSpecies().getSpeciesClass(),
                entity.getSpecies().getSpeciesOrder(),
                entity.getSpecies().getFamily(),
                entity.getSpecies().getGenus(),
                entity.getSpecies().getSubgenus(),
                media.getOrDefault(entity.getSpecies().getVernacularName(), Optional.empty()).orElse(null)));
    }



}
