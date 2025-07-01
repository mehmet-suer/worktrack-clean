package com.worktrack.project.application.port.in;

import com.worktrack.project.domain.Project;

public interface CreateProjectUseCase {
  Project createProject(Project domain);
 }
