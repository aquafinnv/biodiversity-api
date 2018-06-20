package be.g00glen00b.apps.biodiversityapi.occurence;

import be.g00glen00b.apps.biodiversityapi.api.APIError;
import be.g00glen00b.apps.biodiversityapi.taxonomy.Species;
import be.g00glen00b.apps.biodiversityapi.taxonomy.SpeciesRepository;
import com.google.common.collect.Lists;
import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.Point;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.ResponseHeader;
import lombok.AllArgsConstructor;
import org.opengis.referencing.FactoryException;
import org.opengis.referencing.operation.TransformException;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/biodiversity")
@AllArgsConstructor
@Validated
public class BiodiversityOccurenceController {
    private GeometryFactory geometryFactory;
    private SpeciesRepository repository;

    @GetMapping
    @Cacheable("taxonomy")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Success", response = Species[].class, responseHeaders = {
            @ResponseHeader(name = "X-Page-Total-Results", description = "Total number of species matching the given search criteria", response = Long.class),
            @ResponseHeader(name = "X-Page-Nr", description = "The page number used to fetch the results", response = Integer.class),
            @ResponseHeader(name = "X-Page-Limit", description = "The page limit used to fetch the results", response = Integer.class)
        }),
        @ApiResponse(code = 400, message = "Bad request", response = APIError[].class),
        @ApiResponse(code = 500, message = "Internal Server Error", response = APIError[].class)
    })
    @ResponseHeader(name = "X-Page-Total-Results", description = "Total number of results that match the given search criteria", response = Long.class)
    public ResponseEntity<List<Species>> findAll(
        @ApiParam("Latitude of the position to search for species")
        @RequestParam(required = false, defaultValue = "0") Double latitude,
        @ApiParam("Longitude of the position to search for species")
        @RequestParam(required = false, defaultValue = "0") Double longitude,
        @Valid @Positive(message = "{biodiversity.bufferZoneMeters.positive}")
        @ApiParam(value = "Bufferzone added to the position to search for species", allowableValues = "range[1, infinity]")
        @RequestParam(required = false, defaultValue = "1") int bufferZoneMeters,
        @ApiParam(value = "Enkel resultaten terug krijgen met lokale naam")
        @RequestParam(required = false, defaultValue = "true") boolean localName,
        @ApiParam(value = "Page number of the results, one-based", allowableValues = "range[1, infinity]")
        @Valid @Positive(message = "{biodiversity.page.positive}")
        @RequestParam(required = false, defaultValue = "1") int page,
        @ApiParam(value = "Page limit of the results", allowableValues = "range[0, infinity]")
        @Valid @PositiveOrZero(message = "{biodiversity.limit.positive}")
        @RequestParam(required = false, defaultValue = "10") int limit
    ) {
        Point point = geometryFactory.createPoint(new Coordinate(longitude, latitude));
        Page<Species> result = findNearby(point.buffer(bufferZoneMeters), localName, PageRequest.of(page, limit));
        HttpHeaders headers = new HttpHeaders();
        headers.add("X-Page-Total-Results", String.valueOf(result.getTotalElements()));
        headers.add("X-Page-Nr", String.valueOf(page));
        headers.add("X-Page-Limit", String.valueOf(limit));
        return new ResponseEntity<>(result.getContent(), headers, HttpStatus.OK);
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler({FactoryException.class, TransformException.class})
    public List<APIError> handleBufferException(Exception ex) {
        return Lists.newArrayList(new APIError(new String[] {ex.getClass().getName()}, ex.getMessage()));
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ConstraintViolationException.class)
    public List<APIError> handleValidationException(ConstraintViolationException ex) {
        return ex.getConstraintViolations().stream()
            .map(violation -> new APIError(
                new String[] {violation.getPropertyPath().toString()},
                violation.getMessage()))
            .collect(Collectors.toList());
    }

    private Page<Species> findNearby(Geometry buffer, boolean localName, PageRequest pageRequest) {
        if (localName) {
            return repository.findNearby(buffer, pageRequest);
        } else {
            return repository.findNearbyWithVernacularName(buffer, pageRequest);
        }
    }
}
