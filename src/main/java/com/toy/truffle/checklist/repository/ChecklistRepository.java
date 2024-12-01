package com.toy.truffle.checklist.repository;

import com.toy.truffle.checklist.entity.Checklist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.EntityGraph;

import java.util.List;

public interface ChecklistRepository extends JpaRepository<Checklist, Integer> {

    @EntityGraph(attributePaths = {"childChecklists"})
    List<Checklist> findByTravelSeqAndParentChecklistIsNull(Integer travelSeq);
}
