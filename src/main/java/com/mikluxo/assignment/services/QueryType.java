package com.mikluxo.assignment.services;

public enum QueryType {
    SELECT("SELECT"),
    INSERT("INSERT"),
    UPDATE("UPDATE"),
    DELETE("DELETE");

    private final String operation;

    QueryType(String operation) {
        this.operation = operation;
    }

    public String getOperation() {
        return operation;
    }
}
