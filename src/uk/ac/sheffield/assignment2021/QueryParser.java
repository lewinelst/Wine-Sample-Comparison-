package uk.ac.sheffield.assignment2021;

/**
 * 
 * Class that puts queries into a format which is compatible with the system. 
 * 
 *
 * @author Lewin Elston 
 *
 */
import uk.ac.sheffield.assignment2021.codeprovided.AbstractQueryParser;
import uk.ac.sheffield.assignment2021.codeprovided.Query;
import uk.ac.sheffield.assignment2021.codeprovided.SubQuery;
import uk.ac.sheffield.assignment2021.codeprovided.WineProperty;
import uk.ac.sheffield.assignment2021.codeprovided.WineType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.*;

public class QueryParser extends AbstractQueryParser {
    @Override
    public List<Query> readQueries(List<String> queryTokens) throws IllegalArgumentException {

        List<Query> queryList = new ArrayList<Query>();
        List<SubQuery> listOfSubQueries = new ArrayList<SubQuery>();
        WineType queryWineType = WineType.ALL;
        String[] operators = {">",">=","=","!=","<","<="};
        String[] properties = {"f_acid", "v_acid", "c_acid", "r_sugar", "chlorid", "f_sulf", "t_sulf", "dens", "pH", "sulph", "alc", "qual"};

        for (int i = 0; i < queryTokens.size(); i++){
            if (queryTokens.get(i).equals("select")){ // Finds start of query 
                
                if(((queryTokens.get(i + 1).equals("red")) || (queryTokens.get(i + 1).equals("white"))) && (queryTokens.get(i + 2).equals("or"))){ // checks for wine type all
                    queryWineType = WineType.ALL;
                    String operator = "";
                    String property = "";
                    Double value = 0.0;
                    for (int j = i + 5; j < queryTokens.size(); j++){
                        if (queryTokens.get(j).equals("select")){ //Pushes to subquery list if the next query has been reached
                            WineProperty subQueryProperty = WineProperty.fromFileIdentifier(property);
                            SubQuery newSubQuery = new SubQuery(subQueryProperty, operator, value);
                            listOfSubQueries.add(newSubQuery);
                            break;
                        }
                        else if (queryTokens.get(j).equals("and")){ // pushes a subquery when it reaches the next subquery or the end
                            WineProperty subQueryProperty = WineProperty.fromFileIdentifier(property);
                            SubQuery newSubQuery = new SubQuery(subQueryProperty, operator, value);
                            listOfSubQueries.add(newSubQuery);
                        } else if (Arrays.stream(properties).anyMatch(queryTokens.get(j)::equals)){ //Checks if it found the property
                            property = queryTokens.get(j);
                        } else if (Arrays.stream(operators).anyMatch(queryTokens.get(j)::equals)){ //Checks if it found the operator
                            operator = queryTokens.get(j);
                        } else { //Only thing left should be the value
                            value = Double.parseDouble(queryTokens.get(j));
                        }

                        if ((j+1) == queryTokens.size()){ //pushes query to query list if it's at the last element in the queryTokens list     
                            WineProperty subQueryProperty = WineProperty.fromFileIdentifier(property);
                            SubQuery newSubQuery = new SubQuery(subQueryProperty, operator, value);
                            listOfSubQueries.add(newSubQuery);
                        }
                    }
                    
                } else if (queryTokens.get(i + 1).equals("red")){ // checks for wine type red
                    queryWineType = WineType.RED;
                    String operator = "";
                    String property = "";
                    Double value = 0.0;
                    for (int j = i + 3; j < queryTokens.size(); j++){
                        if (queryTokens.get(j).equals("select")){ //Pushes to subquery list if the next query has been reached
                            WineProperty subQueryProperty = WineProperty.fromFileIdentifier(property);
                            SubQuery newSubQuery = new SubQuery(subQueryProperty, operator, value);
                            listOfSubQueries.add(newSubQuery);
                            break;
                        }
                        else if (queryTokens.get(j).equals("and")){ // pushes a subquery when it reaches the next subquery or the end
                            WineProperty subQueryProperty = WineProperty.fromFileIdentifier(property);
                            SubQuery newSubQuery = new SubQuery(subQueryProperty, operator, value);
                            listOfSubQueries.add(newSubQuery);
                        } else if (Arrays.stream(properties).anyMatch(queryTokens.get(j)::equals)){ //Checks if it found the property
                            property = queryTokens.get(j);
                        } else if (Arrays.stream(operators).anyMatch(queryTokens.get(j)::equals)){ //Checks if it found the operator
                            operator = queryTokens.get(j);
                        } else { //Only thing left should be the value
                            value = Double.parseDouble(queryTokens.get(j));
                        }

                        if ((j+1) == queryTokens.size()){ //pushes query to query list if it's at the last element in the queryTokens list      
                            WineProperty subQueryProperty = WineProperty.fromFileIdentifier(property);
                            SubQuery newSubQuery = new SubQuery(subQueryProperty, operator, value);
                            listOfSubQueries.add(newSubQuery);
                        }
                    }
                    
                } else if (queryTokens.get(i + 1).equals("white")){ // checks for wine type white
                    queryWineType = WineType.WHITE;
                    String operator = "";
                    String property = "";
                    Double value = 0.0;
                    for (int j = i + 3; j < queryTokens.size(); j++){
                        if (queryTokens.get(j).equals("select")){ //Pushes to subquery list if the next query has been reached
                            WineProperty subQueryProperty = WineProperty.fromFileIdentifier(property);
                            SubQuery newSubQuery = new SubQuery(subQueryProperty, operator, value);
                            listOfSubQueries.add(newSubQuery);
                            break;
                        }
                        else if (queryTokens.get(j).equals("and")){ // pushes a subquery when it reaches the next subquery or the end
                            WineProperty subQueryProperty = WineProperty.fromFileIdentifier(property);
                            SubQuery newSubQuery = new SubQuery(subQueryProperty, operator, value);
                            listOfSubQueries.add(newSubQuery);
                        } else if (Arrays.stream(properties).anyMatch(queryTokens.get(j)::equals)){ //Checks if it found the property
                            property = queryTokens.get(j);
                        } else if (Arrays.stream(operators).anyMatch(queryTokens.get(j)::equals)){ //Checks if it found the operator
                            operator = queryTokens.get(j);
                        } else { //Only thing left should be the value
                            value = Double.parseDouble(queryTokens.get(j));
                        }

                        if ((j+1) == queryTokens.size()){ //pushes query to query list if it's at the last element in the queryTokens list
                            WineProperty subQueryProperty = WineProperty.fromFileIdentifier(property);
                            SubQuery newSubQuery = new SubQuery(subQueryProperty, operator, value);
                            listOfSubQueries.add(newSubQuery);
                        }
                    }
                    
                }

                
                Query newQuery = new Query(new ArrayList<SubQuery>(listOfSubQueries), queryWineType); // pushes query 
                queryList.add(newQuery);
                listOfSubQueries.clear();
            
            }
        }
        
        return queryList;
    }
}
