package ca.jrvs.apps.twitter.dao;

public interface CrdDao<T, ID> {

  /**
   * Creates a tweet
   * @param entity generic entity that will be passed in
   * @return
   */
  T create(T entity);

  /**
   * Find tweet by its id
   * @param id id of the entity
   * @return Tweet entity
   */

  T findById(ID id);

  /**
   * Delete a tweet by its id
   * @param id
   * @return
   */

  T deleteById(ID id);
}
