package cz.deriva.commons.data.domain;

import cz.deriva.commons.utils.AssertUtils;

public abstract class RecordId<T extends RecordId> implements ValueObject<T> {

  private final Long longValue;

  protected RecordId(final Long idValue) {

    AssertUtils.notNull(idValue, "longValue");
    AssertUtils.isGreaterThanZero(idValue, "idValue");

    this.longValue = idValue;

  }

  public abstract boolean equals(Object o);

  @Override
  public boolean sameAs(T other) {
    return other != null && value().longValue() == other.value().longValue();
  }

  public Long value() {
    return longValue;
  }

  @Override
  public String toString() {
    return String.valueOf(this.longValue);
  }

  @Override
  public int hashCode() {
    return value().hashCode();
  }

}
