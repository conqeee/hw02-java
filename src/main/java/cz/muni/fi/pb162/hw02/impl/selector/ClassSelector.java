package cz.muni.fi.pb162.hw02.impl.selector;

import cz.muni.fi.pb162.hw02.Element;
import cz.muni.fi.pb162.hw02.Selector;

import java.util.HashSet;
import java.util.Set;

/**
 * class represents class slector
 */
public class ClassSelector implements Selector {
    private String className;

    /**
     * constructor of classselector setting the selected class name
     * @param className selected class name
     */
    public ClassSelector(String className){
        this.className = className;
    }

    /**
     * search through the elements and trying to find same class name as the defined in constructor
     * @param elements - the input set of elements
     * @return set of correct classes
     */
    @Override
    public Set<Element> apply(Set<Element> elements) {
        Set<Element> classes = new HashSet<>();
        for(Element element : elements){
           if (element.containsClass(className)){
                classes.add(element);
            }
        }
        return classes;
    }
}
