package cz.deriva.commons.exceptions;

import cz.deriva.commons.data.domain.RecordId;

/**
 * Created by jirka on 8/10/21.
 *
 * @author Jiri Nemec
 */
public class RecordNotFoundException extends RuntimeException {

  private final Class clazz;
  private final RecordId id;

  public <T extends RecordId> RecordNotFoundException(Class clazz, T id) {
    this.clazz = clazz;
    this.id = id;
  }

  public Class getClazz() {
    return clazz;
  }

  public RecordId getId() {
    return id;
  }

}
