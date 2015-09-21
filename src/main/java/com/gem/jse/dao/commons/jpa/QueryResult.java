package com.gem.jse.dao.commons.jpa;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

/**
 * Vysledek selectu na kolekci z DB. Kolekce + celkovy pocet zaznamu (Strankovani)
 * User: blackshark
 * Date: 5.10.13
 * Time: 19:43
 */
public class QueryResult<E> implements Serializable {

	public List<E> items;
	public long count;

	public QueryResult(List<E> items, long count) {
		this.items = items;
		this.count = count;
	}

	public QueryResult() {
		this(Collections.<E>emptyList(), 0L);
	}
}
