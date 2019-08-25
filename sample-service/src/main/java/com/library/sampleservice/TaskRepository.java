package com.library.sampleservice;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface TaskRepository extends JpaRepository<Task, Long>, TaskRepositoryCustom, JpaSpecificationExecutor<Task> {

}
