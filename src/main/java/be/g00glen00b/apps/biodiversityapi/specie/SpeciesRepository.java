package be.g00glen00b.apps.biodiversityapi.specie;

import com.vividsolutions.jts.geom.Geometry;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface SpeciesRepository extends JpaRepository<Species, String> {
    @Query("select distinct t from Species t " +
        "inner join t.occurrences o " +
        "where within(o.center, :buffer) = true")
    Page<Species> findNearby(@Param("buffer") Geometry buffer, Pageable pageable);

    @Query("select distinct t from Species t " +
        "inner join t.occurrences o " +
        "where within(o.center, :buffer) = true and t.vernacularName is not null and t.vernacularName <> ''")
    Page<Species> findNearbyWithVernacularName(@Param("buffer") Geometry buffer, Pageable pageable);

    @Query("select distinct t from Species t " +
        "inner join t.occurrences o " +
        "where within(o.center, :buffer) = true and t.kingdom = :kingdom")
    Page<Species> findNearby(@Param("buffer") Geometry buffer, @Param("kingdom") String kingdom, Pageable pageable);

    @Query("select distinct t from Species t " +
        "inner join t.occurrences o " +
        "where within(o.center, :buffer) = true and t.vernacularName is not null and t.vernacularName <> '' and t.kingdom = :kingdom")
    Page<Species> findNearbyWithVernacularName(@Param("buffer") Geometry buffer, @Param("kingdom") String kingdom, Pageable pageable);
}