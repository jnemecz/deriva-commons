package cz.deriva.commons.data.repository;

import java.util.Optional;

public interface BaseRepository<Type, IdType> {

  /**
   * <p>Najde záznam podle ID</p>
   *
   * @param id id zamestnance
   * @return zamestnanecky ucet nebo empty
   */
  Optional<Type> findById(final IdType id);

  /**
   * <p>Vraci nasledujici unikatni identifikator pro dany domenovy model.</p>
   *
   * @return nasledujici unikatni identifikator
   */
  IdType nexIdentity();

  /**
   * @param domainModelObject
   */
  void save(final Type domainModelObject);

}
