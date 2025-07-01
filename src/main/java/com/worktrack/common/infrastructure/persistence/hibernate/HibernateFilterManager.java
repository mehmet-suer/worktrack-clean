package com.worktrack.common.infrastructure.persistence.hibernate;

import com.worktrack.common.infrastructure.persistence.base.Status;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.hibernate.Filter;
import org.hibernate.Session;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class HibernateFilterManager {

    @PersistenceContext
    private EntityManager entityManager;

    public void enableNotDeletedFilter() {
        enableFilterWithParams("notDeleted", Map.of("deletedStatus", Status.DELETED.name()));
    }

    public void enableFilterWithParams(String filterName, Map<String, Object> params) {
        Session session = entityManager.unwrap(Session.class);
        Filter filter = session.enableFilter(filterName);
        params.forEach(filter::setParameter);
    }

}
