package com.chokealot.konyiguitar.guitar;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GuitarRepository extends JpaRepository<GutiarEntity, Long> {

    GutiarEntity findGutiarEntitiesByName(String name);
}
