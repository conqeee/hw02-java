package cz.muni.fi.pb162.hw02.impl.selector;

import cz.muni.fi.pb162.hw02.Element;
import cz.muni.fi.pb162.hw02.Selector;

import java.util.HashSet;
import java.util.Set;

/**
 * class represents element selector
 */
public class ElementSelector implements Selector {
    private String elementName;

    /**
     * setting the selected name
     * @param elementName selected name
     */
    public ElementSelector(String elementName){
        this.elementName=elementName;
    }

    /**
     * gets all elements from set, which name is equals to selected name
     * @param elements - the input set of elements
     * @return elements with same name
     */
    @Override
    public Set<Element> apply(Set<Element> elements) {
        Set<Element> correctElements = new HashSet<>();

        for(Element element : elements){
            if (element.getName().equals(this.elementName)){
                correctElements.add(element);
            }
        }
        return correctElements;
    }
}
