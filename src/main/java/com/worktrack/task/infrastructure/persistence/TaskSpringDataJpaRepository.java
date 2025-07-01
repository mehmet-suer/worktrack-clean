package com.worktrack.task.infrastructure.persistence;


import com.worktrack.common.infrastructure.persistence.base.Status;
import com.worktrack.task.infrastructure.persistence.entity.TaskJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface TaskSpringDataJpaRepository extends JpaRepository<TaskJpaEntity, Long> {
    List<TaskJpaEntity> findAllByProjectIdAndStatusNot(Long projectId, Status status);

    @Query("SELECT t FROM TaskJpaEntity t WHERE t.id = :taskId AND t.project.id = :projectId")
    Optional<TaskJpaEntity> findByIdAndProjectId(@Param("taskId") Long taskId, @Param("projectId") Long projectId);


    @Query("SELECT t FROM TaskJpaEntity t WHERE t.project.id = :projectId")
    List<TaskJpaEntity> listByProjectId(@Param("projectId") Long projectId);
}
