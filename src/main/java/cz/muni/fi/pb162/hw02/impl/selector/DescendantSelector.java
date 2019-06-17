package cz.muni.fi.pb162.hw02.impl.selector;

import cz.muni.fi.pb162.hw02.Element;
import cz.muni.fi.pb162.hw02.Selector;

import java.util.HashSet;
import java.util.Set;

/**
 * class represents combinator, represents all descendants
 */
public class DescendantSelector implements Selector {

    /**
     * returns all descendants of all elements
     * @param elements - the input set of elements
     * @return all descendants of all elements
     */
    @Override
    public Set<Element> apply(Set<Element> elements) {
        Set<Element> descendantSet = new HashSet<>();

        for (Element element : elements) {
            descendantSet.addAll(element.getAllDescendants());
        }
        return descendantSet;
    }
}
