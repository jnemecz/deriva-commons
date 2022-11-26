package cz.deriva.commons.data.repository;

/**
 * <p>Slouzi pro generovani unikatnich ID v ramci distribuovaneho systemu.<p/>
 *
 * @author Jiri Nemec
 */
public interface IdGenerationStrategy {

  /**
   * <p>Vraci nasledujici hodnotu id.</p>
   *
   * @return nova unikatni hodnota
   */
  Long nextValue();

  /**
   * Vraci unikatni integer hodnotu
   *
   * @return
   */
  Integer nextIntValue();

}
