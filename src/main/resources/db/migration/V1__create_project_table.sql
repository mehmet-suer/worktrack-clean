CREATE TABLE project (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description VARCHAR(255),
    owner_id BIGINT,
    version INTEGER DEFAULT 0,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NOT NULL,
    created_by VARCHAR(50),
    updated_by VARCHAR(50),
    status VARCHAR(50) NOT NULL
);

CREATE INDEX idx_project_owner_id ON project(owner_id);
CREATE INDEX idx_project_status ON project(status);