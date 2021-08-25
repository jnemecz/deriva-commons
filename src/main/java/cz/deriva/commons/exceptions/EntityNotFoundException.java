package cz.deriva.commons.exceptions;

import cz.deriva.commons.data.domain.Entity;
import cz.deriva.commons.data.domain.RecordId;

/**
 * Identifikuje chybejici entitu (pouzivat prioritne oproti RecordNotFoundException)
 */
public class EntityNotFoundException extends RuntimeException {

  private final Class<? extends Entity> clazz;
  private final RecordId id;

  public EntityNotFoundException(final Class<? extends Entity> clazz, final RecordId id) {
    this.clazz = clazz;
    this.id = id;
  }

  public Class<? extends Entity> getClazz() {
    return clazz;
  }

  public RecordId getId() {
    return id;
  }

}
