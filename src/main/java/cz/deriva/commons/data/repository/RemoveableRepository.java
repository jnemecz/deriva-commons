package cz.deriva.commons.data.repository;

public interface RemoveableRepository<IdType> {

  void remove(final IdType id);

}
