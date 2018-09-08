package be.g00glen00b.apps.biodiversityapi.occurence;

import com.vividsolutions.jts.geom.Geometry;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BiodiversityOccurenceRepository extends JpaRepository<BiodiversityOccurence, Long> {
    @Query("select o from BiodiversityOccurence o where within(o.center, :buffer) = true")
    Page<BiodiversityOccurence> findNearby(@Param("buffer") Geometry buffer, Pageable pageable);

    @Query("select o from BiodiversityOccurence o inner join o.species s where within(o.center, :buffer) = true and s.vernacularName is not null and s.vernacularName <> ''")
    Page<BiodiversityOccurence> findNearbyWithVernacularName(@Param("buffer") Geometry buffer, Pageable pageable);

    @Query("select o from BiodiversityOccurence o inner join o.species s where within(o.center, :buffer) = true and s.kingdom = :kingdom")
    Page<BiodiversityOccurence> findNearby(@Param("buffer") Geometry buffer, @Param("kingdom") String kingdom, Pageable pageable);

    @Query("select o from BiodiversityOccurence o inner join o.species s where within(o.center, :buffer) = true and s.vernacularName is not null and s.vernacularName <> '' and s.kingdom = :kingdom")
    Page<BiodiversityOccurence> findNearbyWithVernacularName(@Param("buffer") Geometry buffer, @Param("kingdom") String kingdom, Pageable pageable);
}
