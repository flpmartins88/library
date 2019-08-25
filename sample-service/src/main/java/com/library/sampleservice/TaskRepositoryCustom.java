package com.library.sampleservice;

import java.util.List;

/**
 * Implementação de um custom repository do spring data
 */
public interface TaskRepositoryCustom {

    List<Task> findQualquerCoisa(Task.Category category);

}
