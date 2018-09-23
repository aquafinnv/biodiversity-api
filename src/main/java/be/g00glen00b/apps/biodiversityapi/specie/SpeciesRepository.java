package be.g00glen00b.apps.biodiversityapi.specie;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SpeciesRepository extends JpaRepository<Species, String> {
}
