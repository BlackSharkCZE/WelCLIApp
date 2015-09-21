package com.gem.jse.dao.commons.jpa;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Basic DAO Operation
 * User: blackshark
 * Date: 5.10.13
 * Time: 19:46
 */
public interface DaoInterface<E> {

	E save(E entity);

	QueryResult<E> findAll(int offset, int limit);

	List<E> findAll();

	boolean update(E entity);

	boolean delete(E entity);

	Optional<E> find(Object id);

	List<E> findByNamedQuery(String queryName, int offset, int limit, Map<String, Object> params);

	List<E> findByNamedQuery(String queryName, int offset, int limit);

	Optional<E> findFirstByNamedQuery(final String queryName, Map<String, Object> params);

	Long findCountByNamedQuery(String queryName, Map<String, Object> params);

	int updateByNamedQuery(final String namedQuery, Map<String, Object> params);

}
