package cz.muni.fi.pb162.hw02.impl.parser;

/**
 * class represents the exception of invalid query
 */
public class InvalidQueryException extends Exception{
    /**
     * basic constructor
     */
    public InvalidQueryException() {
        super();
    }

    /**
     * constructor saying what is wrong
     * @param message wrong mess
     */
    public InvalidQueryException(String message) {
        super(message);
    }
}
