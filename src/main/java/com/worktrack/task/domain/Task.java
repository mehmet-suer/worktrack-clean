package com.worktrack.task.domain;

import com.worktrack.project.domain.ProjectId;
import com.worktrack.user.domain.UserId;

import java.util.Objects;

public class Task {
    private final TaskId id;
    private String title;
    private String description;
    private TaskStatus taskStatus;
    private ProjectId projectId;
    private UserId assignedTo;

    public Task(TaskId id,
                String title,
                String description,
                TaskStatus taskStatus,
                ProjectId projectId,
                UserId assignedTo) {

        this.id = id;
        this.title = Objects.requireNonNull(title);
        this.description = description;
        this.taskStatus = Objects.requireNonNull(taskStatus);
        this.projectId = Objects.requireNonNull(projectId);
        this.assignedTo = assignedTo;
    }

    public TaskId getId() {
        return id;
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

    public TaskStatus getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(TaskStatus taskStatus) {
        this.taskStatus = taskStatus;
    }

    public ProjectId getProjectId() {
        return projectId;
    }

    public void setProjectId(ProjectId projectId) {
        this.projectId = projectId;
    }

    public UserId getAssignedTo() {
        return assignedTo;
    }

    public void setAssignedTo(UserId assignedTo) {
        this.assignedTo = assignedTo;
    }

    public void assignTo(UserId userId) {
        if (this.taskStatus == TaskStatus.DONE) {
            throw new IllegalStateException("Completed task cannot be reassigned");
        }

        if (this.assignedTo != null && this.assignedTo.equals(userId)) {
            throw new IllegalArgumentException("User is already assigned to this task");
        }

        this.assignedTo = userId;
    }
}