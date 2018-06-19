package be.g00glen00b.apps.biodiversityapi.taxonomy;

import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.Point;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface SpeciesRepository extends JpaRepository<Species, String> {
    @Query("select distinct t from Species t " +
        "inner join t.occurences o " +
        "where within(o.center, :buffer) = true")
    Page<Species> findNearby(@Param("buffer") Geometry buffer, Pageable pageable);
}
