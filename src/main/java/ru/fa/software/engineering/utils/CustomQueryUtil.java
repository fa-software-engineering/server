package ru.fa.software.engineering.utils;

public class CustomQueryUtil {
    public static String getLikeQuery(String... fields) {
        StringBuilder searchQuery = new StringBuilder();

        for (String field : fields) {

            searchQuery
                    .append("lower(").append(field).append(")")
                    .append(" like ")
                    .append("'%'").append("||").append("lower(").append("?1").append(")").append("||").append("'%'")
                    .append(" or ");
        }
        searchQuery.delete(searchQuery.length() - 4, searchQuery.length() - 1);

        return searchQuery.toString();
    }
}
