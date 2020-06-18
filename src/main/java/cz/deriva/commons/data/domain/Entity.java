package cz.deriva.commons.data.domain;

import cz.deriva.commons.utils.AssertUtils;

import java.time.LocalDateTime;

/**
 * An entity, as explained in the DDD book.
 */
public abstract class Entity<T extends Entity, ID extends RecordId> {

  private final ID id;
  private final LocalDateTime itime;

  protected Entity(final ID id, final LocalDateTime itime) {

    AssertUtils.notNull(id, "id");
    this.id = id;

    AssertUtils.notNull(itime, "itime");
    this.itime = itime;

  }

  /**
   * Entities compare by identity, not by attributes.
   *
   * @param other The other entity.
   * @return true if the identities are the same, regardless of other attributes.
   */
  public boolean sameIdentityAs(final T other) {
    AssertUtils.notNull(other, "other");
    return this.getId().sameAs(other.getId());
  }

  /**
   * Vraci ID dane entity.
   *
   * @return id entity
   */
  public ID getId() {
    return this.id;
  }

  public LocalDateTime getItime() {
    return itime;
  }

}
