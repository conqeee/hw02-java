package cz.muni.fi.pb162.hw02.impl;


import cz.muni.fi.pb162.hw02.Element;
import cz.muni.fi.pb162.hw02.QueryParser;
import cz.muni.fi.pb162.hw02.Selector;
import cz.muni.fi.pb162.hw02.impl.parser.InvalidQueryException;
import cz.muni.fi.pb162.hw02.impl.parser.QueryParserImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * final class represents query executor
 */
public final class QueryExecutor {
    /**
     * private constructor for not creating any instance
     */
    private QueryExecutor(){

    }

    /**
     * execute method for executing a query
     * @param query the main query
     * @param rootElement the root
     * @return set of elements which are permitted by query
     * @throws InvalidQueryException when the query is not ok
     */
    public static Set<Element> execute(String query, Element rootElement) throws InvalidQueryException{

        List<Selector> selectorList = new ArrayList<>();
        QueryParser parser = new QueryParserImpl(query);
        Set<Element> elements = rootElement.getAllDescendants();
        elements.add(rootElement);
            while (parser.hasNextSelector()){
            selectorList.add(parser.getNextSelector());
        }

        for (Selector selector : selectorList){
            elements = selector.apply(elements);
        }
            return elements;
    }

}
