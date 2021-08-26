package com.mikluxo.assignment.repository;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

@Service
public class QueryProviderImpl implements QueryProvider {
    @PersistenceContext
    private EntityManager manager;
    @Transactional
    @Override
    public String query(String queryString) {
        StringBuilder response = new StringBuilder();

        Query nativeQuery = manager.createNativeQuery(queryString);

        if (isWriteQuery(queryString)) {
            response.append(nativeQuery.executeUpdate());
        } else {
            List resultList = nativeQuery.getResultList();
            if (resultList.isEmpty()) {
                throw new NotFoundException();
            }
            for (int i = 0; i < resultList.size(); ++i) {
                Object[] strings;
                if (resultList.get(i) instanceof String) {
                    strings = new Object[]{resultList.get(i)};
                } else {
                    strings = (Object[]) resultList.get(i);
                }

                for (int j = 0; j < strings.length; ++j) {
                    response.append(" ").append(strings[j]);
                    if (i < resultList.size() -1 || j < strings.length -1) {
                        response.append(",");
                    }
                }
            }
        }

        return response.toString();
    }

    public boolean isWriteQuery(String queryString) {
        return !queryString.toLowerCase().startsWith("select");
    }
}
