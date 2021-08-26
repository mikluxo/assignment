package com.mikluxo.assignment.services;

import lombok.Builder;
import lombok.NonNull;

@Builder
public class QueryBuilder {
    @NonNull
    private final QueryType queryType;
    private final String[] fieldNames;
    @NonNull
    private final String tableName;
    private final String where;
    private final String[] fieldValues;

    public String getQuery() {
        StringBuilder sb = new StringBuilder();
        sb.append(queryType.getOperation());
        sb.append(" ");

        switch(queryType) {
            case SELECT:
                getSelect(sb);
                break;
            case UPDATE:
                getUpdate(sb);
                break;
            case INSERT:
                getInsert(sb);
                break;
            case DELETE:
                getDelete(sb);
                break;
            default:
                throw new UnsupportedOperationException("Operation not supported");
        }
        return sb.toString();
    }

    private void getDelete(StringBuilder sb) {
//        delete from tableName;
//        delete from tableName where
        sb.append(" from ").append(tableName);
        addWhereIfRequired(sb.append(" "));
    }

    private void getInsert(StringBuilder sb) {
//    insert into test(id, name, surname) values ('id', 'name', 'surname');
//    insert into test values('id', 'name', 'surname');
        sb.append(" into ").append(tableName);
        if (fieldNames != null && fieldNames.length > 0) {
            sb.append("(").append(String.join(", ", fieldNames)).append(")");
        }

        sb.append(" VALUES(");

        for(int i = 0; i < fieldValues.length; ++i) {
            sb.append("'").append(fieldValues[i]).append("'");
            if(i < fieldValues.length -1) {
                sb.append(",");
            }
        }

        sb.append(")");
    }

    private void getUpdate(StringBuilder sb) {
        // update tableName set id='id1' value='value1'
        // update tableName set id='id1' value='value1' where ....
        sb.append(tableName).append(" set ");

        for(int i = 0; i < fieldNames.length; ++i) {
            sb.append(fieldNames[i]).append("=").append("'").append(fieldValues[i]).append("'");
            if (i < fieldNames.length - 1) {
                sb.append(",");
            }
        }
        sb.append(" ");

        addWhereIfRequired(sb);
    }

    private void getSelect(StringBuilder sb) {
//        select * from tableName
//        select * from tableName where ...
//        select columnNames from tableName
//        select columnNames from tableName where ...
        if (fieldNames == null || fieldNames.length == 0) {
            sb.append(" * ");
        } else {
            sb.append(String.join(", ", fieldNames));
        }

        sb.append(" from ").append(tableName).append(" ");

        addWhereIfRequired(sb);
    }

    private void addWhereIfRequired(StringBuilder sb) {
        if(where != null && !where.isEmpty()) {
            sb.append(" WHERE ").append(where);
        }
    }
}
