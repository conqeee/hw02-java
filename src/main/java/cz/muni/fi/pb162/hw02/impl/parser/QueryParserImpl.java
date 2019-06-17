package cz.muni.fi.pb162.hw02.impl.parser;

import cz.muni.fi.pb162.hw02.QueryParser;
import cz.muni.fi.pb162.hw02.Selector;
import cz.muni.fi.pb162.hw02.Utils;
import cz.muni.fi.pb162.hw02.impl.selector.ClassSelector;
import cz.muni.fi.pb162.hw02.impl.selector.DescendantSelector;
import cz.muni.fi.pb162.hw02.impl.selector.ElementSelector;
import cz.muni.fi.pb162.hw02.impl.selector.IdSelector;

/**
 * class represents query parser, methods for parsing
 */
public class QueryParserImpl implements QueryParser{
    private String query;

    /**
     * constructor setting the query which has to be parsed
     * more whitespaces are set to one whitespace and than trim deletes whitespace at start of query
     * @param query determined command
     */
    public QueryParserImpl(String query){
        if (query == null){
            throw new IllegalArgumentException("querry cannot be null");
        }
        this.query = query.replaceAll("\\s+", " ").trim();

                //.replaceAll("\\s+", " ").trim();
    }

    /**
     * get next selector returns the valid selector for determined piece of query
     * if there is not next selector returns null
     * than sets the main query
     * than sets the selector operator - decides if the selector operator is name char - if its not,
     * than you 'remove' first letter of query
     * sets the parameter - parameter of the selector
     * (while i is lower than query lenght and char at i position of query is not equals to character
     * which changes selector, if there is some changing selector command on i, you set i-1 and break the cycle
     * if char at i is not name char, you call exception
     * than append character on i position to parameter stringbuilder)
     * if == 0 (the query has one char or is empty) and the character is not name char throw exception
     * if selector operator is not ' ' whitespace you remove the number of chars in mainquery
     * then sets new query - as a cutted mainQuery
     * than just calls selector depended on what character is in selectorOperator
     * @return correct selector
     * @throws InvalidQueryException when the query is not executable
     */
    @Override
    public Selector getNextSelector() throws InvalidQueryException {
        if (!hasNextSelector()){
            return null;
        }
        StringBuilder mainQuery = new StringBuilder(query);
        StringBuilder selectorOperator = new StringBuilder("").append(mainQuery.charAt(0));
        if (!Utils.isNameChar(selectorOperator.charAt(0))){
            mainQuery = mainQuery.replace(0,1,"");
        }
        StringBuilder parameter = new StringBuilder("");
        int i = 0; while(i<(mainQuery.length()) && (mainQuery.charAt(i) != ' '
                || mainQuery.charAt(i) != '#' || mainQuery.charAt(i) != '.')){
            if ((mainQuery.charAt(i) == ' ' || mainQuery.charAt(i) == '#'
                    || mainQuery.charAt(i) == '.') && i!=mainQuery.length()){
                i--; break;
            }
            if (!Utils.isNameChar(mainQuery.charAt(i))){
                throw new InvalidQueryException("character is not valid");
            }
            parameter.append(mainQuery.charAt(i)); i++;
        }
        if (i==0 && !Utils.isNameChar(mainQuery.charAt(0))){
            throw new InvalidQueryException("bad query");
        }
        if (selectorOperator.charAt(0) != ' '){
            mainQuery.replace(0, i+1, "");
        }
        this.query = mainQuery.toString();
        return selectorFinder(selectorOperator.charAt(0),parameter);
    }

    /**
     * finding good selector depended on selectoOperator
     * @param character the selectorOperator
     * @param parameter part of query
     * @return selector
     */
    private Selector selectorFinder(Character character,StringBuilder parameter) {
        if (character == ' ') {
            return new DescendantSelector();
        } else if (character == '#') {
            return new IdSelector(parameter.toString());
        } else if (character == '.') {
            return new ClassSelector(parameter.toString());
        }
        return new ElementSelector(parameter.toString());
    }
    /**
     * it has next selctor when query is not null or isnt empty and first query char is equals to some operator or a
     * is name char
     * @return true or false
     */
    @Override
    public boolean hasNextSelector() {
        return  (query != null && !query.equals("") && (query.charAt(0) == '#' || query.charAt(0) == '.'
                || Utils.isNameChar(query.charAt(0)) || query.charAt(0) == ' '));
    }
}