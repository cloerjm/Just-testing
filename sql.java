import java.sql.*;
import java.util.LinkedList;
import java.util.ArrayList;
import java.util.logging.Filter;

public class sql extends fiter {

    static ArrayList<fiter> fiters = new ArrayList<fiter>() {
    };

    static String[] listOne = {"maine", "colorado", "texas"};
    static String[] listTwo = {"Backstreet", "NYSNC", "One Direction"};
    static fiter one = new fiter("states", listOne);
    static fiter two = new fiter("Boy Bands", listTwo);

    public static void setFiters(ArrayList<fiter> f) {
        f.add(one);
        f.add(two);
    }

    static String fullQuery = "";

    static String filterQuery = "";

    static String startQuery = ""
            +  "SELECT airports.name, \n"
            +  "       airports.municipality, \n"
            +  "       region.name AS region, \n"
            +  "       country.name AS country, \n"
            +  "       continents.name AS continent, \n"
            +  "       airports.id, \n"
            +  "       airports.type, \n"
            +  "       airports.latitude, \n"
            +  "       airports.longitude, \n"
            +  "       airports.elevation, \n"
            +  "       airports.wikipedia_link \n"
            +  "FROM continents \n"
            +  "INNER JOIN country ON continents.id = country.continent \n"
            +  "INNER JOIN region ON country.id = region.iso_country \n"
            +  "INNER JOIN airports ON region.id = airports.iso_region \n"
            +  "WHERE \n";

    static String endQuery = ""
            +  "(country.name LIKE ? \n"
            +  "OR region.name LIKE ? \n"
            +  "OR airports.name LIKE ? \n"
            +  "OR airports.municipality LIKE ?) \n"
            +  "ORDER BY continents.name, \n"
            +  "         country.name, \n"
            +  "         region.name, \n"
            +  "         airports.municipality, \n"
            +  "         airports.name ASC \n"
            +  "LIMIT 20; \n";


    public static void createFilterQuery() {
        if (fiters != null) {
            filterQuery += "";
            for (int i = 0; i < fiters.size(); i++) {
                for (int j = 0; j < fiters.get(i).values.length; j++) {
                    filterQuery += ("(" + fiters.get(i).attributes + " LIKE " + fiters.get(i).values[j] + ")" + " AND \n");
                }
            }
        }
        else filterQuery = "(";
    }

    public static void createQuery() {
        setFiters(fiters);
        createFilterQuery();
        fullQuery = startQuery + filterQuery + endQuery;
    }

    public static void main(String[] args) {
        createQuery();
//        System.out.println(startQuery);
        System.out.println("filterQuery:" + filterQuery);
        System.out.println(fullQuery);
    }
}