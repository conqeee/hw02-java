package cz.muni.fi.pb162.hw02.impl.dom;

import cz.muni.fi.pb162.hw02.Attribute;

/**
 * class represents the base attribute
 */
public class BaseAttribute implements Attribute{
    private String name;
    private String value;

    /**
     * constructor sets the name of BAm throws exception if name is null
     * @param name the name of AB
     */
    public BaseAttribute(String name){
        if (name == null){
            throw new IllegalArgumentException("Name cannot be null");
        }
        this.name = name;
    }

    /**
     * constructor sets the name of BAm throws exception if name is null
     * @param name name of AB
     * @param value values of AB
     */
    public BaseAttribute(String name, String value){
        if (name == null){
            throw new IllegalArgumentException("Name cannot be null");
        }
        this.name = name;
        this.value = value;
    }

    /**
     * gets a name
     * @return name
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * gets a value of ab
     * @return value
     */
    @Override
    public String getValue() {
        return value;
    }

    /**
     * equals method
     * @param o compared object
     * @return true or false when it is / isnot equals
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BaseAttribute that = (BaseAttribute) o;

        return name.equals(that.name);
    }

    /**
     * basic hashcode method
     * @return number - the hash
     */
    @Override
    public int hashCode() {
        return name.hashCode();
    }

    /**
     * tostring meth
     * @return name="value"
     */
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder(name);
        if (value!=null){
            sb.append("=\"").append(value).append("\"");
        }
        return sb.toString();
    }
}
