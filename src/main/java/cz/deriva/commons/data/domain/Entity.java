package cz.deriva.commons.data.domain;

/**
 * An entity, as explained in the DDD book.
 */
public interface Entity<T, ID> {

  /**
   * Entities compare by identity, not by attributes.
   *
   * @param other The other entity.
   * @return true if the identities are the same, regardless of other attributes.
   */
  boolean sameIdentityAs(final T other);

  /**
   * Vraci ID dane entity.
   *
   * @return id entity
   */
  ID getId();

}
