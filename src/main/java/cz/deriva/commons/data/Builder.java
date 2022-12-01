package cz.deriva.commons.data;

/**
 * Definuje instanci builder pattern.
 *
 * @author Jiri Nemec
 */
public interface Builder<T> {

    T build();

    void validate();

}
