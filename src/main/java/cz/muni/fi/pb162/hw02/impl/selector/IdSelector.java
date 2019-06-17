package cz.muni.fi.pb162.hw02.impl.selector;

import cz.muni.fi.pb162.hw02.Attribute;
import cz.muni.fi.pb162.hw02.Element;
import cz.muni.fi.pb162.hw02.Selector;

import java.util.HashSet;
import java.util.Set;

/**
 * class represents id selector
 */
public class IdSelector implements Selector {
    private String idName;

    /**
     * setting id name
     * @param id selected id name
     */
    public IdSelector(String id){
        this.idName = id;
    }

    /**
     * search through the elements set trying to find the same value of id and the name must be "id"
     * @param elements - the input set of elements
     * @return elements with same value of id
     */
    @Override
    public Set<Element> apply(Set<Element> elements) {
            Set<Element> idElements = new HashSet<>();

        for (Element element : elements){
            for (Attribute attribute : element.getAttributes())
                if (attribute.getName().equals("id") && attribute.getValue().equals(idName)){
                    idElements.add(element);
                }
            }
        return idElements;
        }

    }

