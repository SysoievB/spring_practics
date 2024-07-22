package com.named_entity_graph;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CharacteristicsRepository extends JpaRepository<Characteristic, Long> {

    @EntityGraph(attributePaths = {"item.name"}, type = EntityGraph.EntityGraphType.FETCH)
    Characteristic findByType(String type);
}
