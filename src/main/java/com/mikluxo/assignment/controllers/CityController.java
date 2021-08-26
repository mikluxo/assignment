package com.mikluxo.assignment.controllers;

import com.mikluxo.assignment.repository.QueryProvider;
import com.mikluxo.assignment.services.QueryBuilder;
import com.mikluxo.assignment.services.QueryType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/city")
public class CityController {
    private static final String tableName = "city";
    private final QueryProvider queryProvider;

    @Autowired
    public CityController(QueryProvider queryProvider) {
        this.queryProvider = queryProvider;
    }

    @RequestMapping(method = RequestMethod.GET, produces = {"application/json"})
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody String getCities(String[] fields, String where) {
        QueryBuilder build = QueryBuilder.builder().queryType(QueryType.SELECT)
                .fieldNames(fields).tableName(tableName).where(where).build();

        return queryProvider.query(build.getQuery());
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void insertCity(String [] fields, String[] values) {
        QueryBuilder build = QueryBuilder.builder().queryType(QueryType.INSERT)
                .fieldNames(fields).tableName(tableName).fieldValues(values).build();

        queryProvider.query(build.getQuery());
    }

    @RequestMapping(method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public void updateCity(String [] fields, String []values, String where) {
        QueryBuilder build = QueryBuilder.builder().queryType(QueryType.UPDATE)
                .fieldNames(fields).fieldValues(values).tableName(tableName).where(where).build();

        queryProvider.query(build.getQuery());
    }

    @RequestMapping(method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    public void deleteCity(String where) {
        QueryBuilder build = QueryBuilder.builder().queryType(QueryType.DELETE)
                .tableName(tableName).where(where).build();

        queryProvider.query(build.getQuery());
    }
}
