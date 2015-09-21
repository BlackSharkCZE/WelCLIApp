package com.gem.jse.dao.commons.jpa;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.*;

/**
 * Base DAO Operation Implementation
 * User: blackshark
 * Date: 5.10.13
 * Time: 19:48
 */
public class DaoTemplate<E> implements DaoInterface<E> {

	private Logger logger = LoggerFactory.getLogger(getClass());

	@Inject
	protected EntityManager manager;

	protected final Class clazz;


	protected DaoTemplate(Class clazz) {
		this.clazz = clazz;
	}

	@Override
	@SuppressWarnings("unchecked")
	public int updateByNamedQuery(String namedQuery, Map<String, Object> params) {
		try {
			manager.getTransaction().begin();
			Query query = manager.createNamedQuery(namedQuery);
			if (params != null) {
				for (String k : params.keySet()) {
					query.setParameter(k, params.get(k));
				}
			}
			final int i = query.executeUpdate();
			manager.getTransaction().commit();
			return i;
		} catch (Exception e) {
			logger.error("Can not call update " + namedQuery, e);
			return -1;
		}
	}

	@Override
	@SuppressWarnings("unchecked")
	public Long findCountByNamedQuery(String queryName, Map<String, Object> params) {
		try {
			Query query = manager.createNamedQuery(queryName);
			if (params != null && !params.isEmpty()) {
				for (String key : params.keySet()) {
					query.setParameter(key, params.get(key));
				}
			}

			Object o = query.getSingleResult();

			if (o != null) {

				if (Number.class.isAssignableFrom(o.getClass())) {
					return ((Number) o).longValue();
				} else {
					if (o instanceof Long) {
						return (Long) query.getSingleResult();
					} else {
						if (o instanceof Integer) {
							return Long.valueOf((Integer) query.getSingleResult());
						}
					}
				}
			}

		} catch (NoResultException e) {
			logger.warn("Try read count on empty result set by query " + queryName + " and params: " + params);
			return 0L;

		} catch (Exception e) {
			logger.error("Can not read count by NamedQuery: " + queryName, e);

		}
		return 0L;
	}

	@Override
	@SuppressWarnings("unchecked")
	public E save(E entity) {
		try {
			manager.getTransaction().begin();
			manager.persist(entity);
			manager.getTransaction().commit();
			return entity;
		} catch (ConstraintViolationException e) {
			Set<ConstraintViolation<?>> constraintViolations = e.getConstraintViolations();
			StringBuilder sb = new StringBuilder(256);
			for (ConstraintViolation<?> cv : constraintViolations) {
				sb.append("Validation Error On: " + cv.getPropertyPath().toString() + " - " + cv.getConstraintDescriptor().getAnnotation().toString());
			}
			logger.error(sb.toString());
			return null;
		} catch (Exception e) {
			logger.error("Error save entity " + entity, e);
			return null;
		}
	}

	@Override
	@SuppressWarnings("unchecked")
	public QueryResult<E> findAll(int offset, int limit) {

		CriteriaBuilder cb = manager.getCriteriaBuilder();
		CriteriaQuery<E> q = cb.createQuery(clazz);
		Root<E> c = q.from(clazz);
		q.select(c);
		TypedQuery<E> query = manager.createQuery(q).setFirstResult(offset).setMaxResults(limit);

		QueryResult<E> result = new QueryResult<>();
		result.items = query.getResultList();


		CriteriaBuilder criteriaBuilder = manager.getCriteriaBuilder();
		CriteriaQuery q1 = criteriaBuilder.createQuery();
		q1.select(criteriaBuilder.count(c));

		TypedQuery<Long> tq = manager.createQuery(q1);
		result.count = tq.getSingleResult();

		return result;

	}

	@Override
	@SuppressWarnings("unchecked")
	public List<E> findAll() {
		CriteriaBuilder cb = manager.getCriteriaBuilder();
		CriteriaQuery<E> q = cb.createQuery(clazz);
		Root<E> c = q.from(clazz);
		q.select(c);
		TypedQuery<E> query = manager.createQuery(q);
		List<E> results = query.getResultList();
		return results;
	}

	@Override
	@SuppressWarnings("unchecked")
	public boolean update(E entity) {
		boolean res = false;
		try {
			manager.getTransaction().begin();
			entity = manager.merge(entity);
			manager.getTransaction().commit();
			res = true;
		} catch (Exception e) {
			logger.error("Can not merge entity " + entity, e);
		}
		return res;
	}

	@Override
	@SuppressWarnings("unchecked")
	public boolean delete(E entity) {
		boolean res = false;
		try {
			manager.getTransaction().begin();
			manager.remove(entity);
			manager.getTransaction().commit();
			res = true;
		} catch (Exception e) {
			logger.error("Can not delete " + entity, e);
		}
		return res;
	}

	@Override
	@SuppressWarnings("unchecked")
	public Optional<E> find(Object id) {
		E x = (E) manager.find(clazz, id);
		if (x == null) return Optional.empty();
		else return Optional.of(x);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<E> findByNamedQuery(String queryName, int offset, int limit, Map<String, Object> params) {
		try {
			Query query = manager.createNamedQuery(queryName).setFirstResult(offset > 0 ? offset : 0);
			if (limit > 0) query.setMaxResults(limit);
			if (params != null && !params.isEmpty()) {
				for (String key : params.keySet()) {
					query.setParameter(key, params.get(key));
				}
			}
			return query.getResultList();
		} catch (Exception e) {
			logger.error("Can not process query " + queryName + ".", e);
			return Collections.emptyList();
		}
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<E> findByNamedQuery(String queryName, int offset, int limit) {
		return findByNamedQuery(queryName, offset, limit, null);
	}

	@Override
	@SuppressWarnings("unchecked")
	public Optional<E> findFirstByNamedQuery(final String queryName, Map<String, Object> params) {
		List<E> res = findByNamedQuery(queryName, 0, 1, params);
		if (res != null && !res.isEmpty()) {
			return Optional.of(res.get(0));
		} else {
			return Optional.empty();
		}
	}

}
