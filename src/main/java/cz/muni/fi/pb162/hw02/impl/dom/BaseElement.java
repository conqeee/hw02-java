package cz.muni.fi.pb162.hw02.impl.dom;

import cz.muni.fi.pb162.hw02.Attribute;
import cz.muni.fi.pb162.hw02.Element;

import java.util.Set;
import java.util.List;
import java.util.HashSet;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Class represents Base element
 */
public class BaseElement implements Element{
    private String name;
    private Set<Attribute> attributes = new HashSet<>();
    private List<Element> childElements = new ArrayList<>();

    /**
     * constructor setting the name of element
     * @param name of the element - cannot be nul
     */
    public BaseElement(String name){
        if (name == null){
            throw new IllegalArgumentException("Name cannot be null");
        }
        this.name = name;
    }

    /**
     * constructor setting the name of element its attributes and its children - all of params cannot be null
     * @param name of the BE
     * @param attributes set of its attributes
     * @param childElements list of its children
     */
    public BaseElement(String name, Set<Attribute> attributes, List<Element> childElements){
        if (name == null || attributes == null || childElements == null){
            throw new IllegalArgumentException("Parameter cannot be null");
        }
        this.name = name;
        this.attributes = attributes;
        this.childElements = childElements;
    }

    /**
     * name
     * @return name
     */
    @Override
    public String getName() {
        return name;
    }

        /**
     *
     * @param attribute - the attribute to be added
     * @return
     */
    @Override
    public boolean addAttribute(Attribute attribute) {
        if (attribute == null){
            throw new IllegalArgumentException("attribute cannot be null");
        }
        if (attributes.contains(attribute)){
            return false;
        }
        attributes.add(attribute);
        return true;
    }

    @Override
    public Attribute findAttribute(String name) {
        for (Attribute attribute : attributes) {
            if (attribute.getName().equals(name)){
                return attribute;
            }
        }
        return null;
    }

    @Override
    public boolean deleteAttribute(String name) {
        for (Attribute attribute : attributes){
            if (attribute.getName().equals(name)){
                attributes.remove(attribute);
                return true;
            }
        }
        return false;
    }

    @Override
    public Set<Attribute> getAttributes() {
        return attributes;
    }

    @Override
    public void appendChildElement(Element element) {
        if (element == null){
            throw new IllegalArgumentException("Element cannot be null");
        }
        childElements.add(element);
    }

    @Override
    public boolean deleteChildElement(Element element) {
        for (Element childElement : childElements){
            if (childElement.equals(element)){
                childElements.remove(childElement);
                return true;
            }
        }
        return false;
    }

    @Override
    public List<Element> getChildElements() {
        return childElements;
    }

    @Override
    public Set<String> getAllClasses() {
        Set<String> classes = new HashSet<>();
        for (Attribute attribute : attributes){
            if (attribute.getName().equals("class")){
                classes.addAll(Arrays.asList(attribute.getValue().split("\\s+")));
            }
        }
        return classes;
    }

    @Override
    public boolean containsClass(String clazz) {
        return getAllClasses().contains(clazz);
    }

    // descendant.add child
    @Override
    public Set<Element> getAllDescendants() {
        Set<Element> descendants = new HashSet<>();
        for (Element child : childElements){
            descendants.add(child);
            descendants.addAll(child.getAllDescendants());
        }
        return descendants;
    }
}
