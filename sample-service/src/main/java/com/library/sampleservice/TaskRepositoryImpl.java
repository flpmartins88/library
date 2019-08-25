package com.library.sampleservice;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

/**
 * Por falta de um nome melhor
 */
@Repository
public class TaskRepositoryImpl implements TaskRepositoryCustom {

    // Autowires a entitymanager from spring jpa environment
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Task> findQualquerCoisa(Task.Category category) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Task> criteria = builder.createQuery(Task.class);

        Root<Task> root = criteria.from(Task.class);

        TypedQuery<Task> query = entityManager.createQuery(criteria);
        return query.getResultList();
    }

}
