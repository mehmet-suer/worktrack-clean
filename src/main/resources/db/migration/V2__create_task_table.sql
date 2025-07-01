CREATE TABLE task (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    description VARCHAR(255),
    task_status VARCHAR(50) NOT NULL,
    project_id BIGINT,
    assigned_to_user_id BIGINT,
    version INTEGER DEFAULT 0,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NOT NULL,
    created_by VARCHAR(50),
    updated_by VARCHAR(50),
    status VARCHAR(50) NOT NULL,
    CONSTRAINT fk_task_project_id FOREIGN KEY (project_id) REFERENCES project(id)
);

CREATE INDEX idx_task_project_id ON task(project_id);
CREATE INDEX idx_task_status ON task(status);