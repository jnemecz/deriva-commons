package cz.deriva.commons.data.domain;

import java.io.Serializable;

/**
 * Created by jirka on 16.06.18.
 *
 * @author Jiri Nemec
 */
public interface ValueObject<TypeValueObject> extends Serializable {

  boolean sameAs(TypeValueObject other);

}
