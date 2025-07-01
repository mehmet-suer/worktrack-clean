package com.worktrack.task.infrastructure.persistence.entity;

import com.worktrack.common.infrastructure.persistence.base.StatusAwareBaseEntity;
import com.worktrack.project.infrastructure.persistence.entity.ProjectJpaEntity;
import com.worktrack.task.infrastructure.persistence.TaskJpaStatus;
import jakarta.persistence.*;

@Entity
@Table(name = "task")
public class TaskJpaEntity extends StatusAwareBaseEntity {

    @Column(nullable = false)
    private String title;

    private String description;

    @Enumerated(EnumType.STRING)
    @Column(name = "task_status", nullable = false)
    private TaskJpaStatus taskJpaStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id")
    private ProjectJpaEntity project;

    @Column(name = "assigned_to_user_id")
    private Long assignedTo;

    public TaskJpaEntity(String title, String description, ProjectJpaEntity projectJpaEntity){
        this.title = title;
        this.description = description;
        this.project = projectJpaEntity;
    }

    public TaskJpaEntity(String title, String description, ProjectJpaEntity projectJpaEntity, Long assignedTo) {
        this.title = title;
        this.description = description;
        this.project = projectJpaEntity;
        this.assignedTo = assignedTo;
    }

    public TaskJpaEntity() {
    }

    @PrePersist
    protected void onCreateTask() {
        if (this.taskJpaStatus == null) {
            this.taskJpaStatus = TaskJpaStatus.TODO;
        }
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public TaskJpaStatus getTaskStatus() {
        return taskJpaStatus;
    }

    public void setTaskStatus(TaskJpaStatus taskJpaStatus) {
        this.taskJpaStatus = taskJpaStatus;
    }

    public ProjectJpaEntity getProject() {
        return project;
    }

    public void setProject(ProjectJpaEntity projectJpaEntity) {
        this.project = projectJpaEntity;
    }

    public Long getAssignedTo() {
        return assignedTo;
    }

    public void setAssignedTo(Long assignedTo) {
        this.assignedTo = assignedTo;
    }


}
