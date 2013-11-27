package br.net.woodstock.sp.impl.persistencia;

import java.util.Map;

import br.net.woodstock.rockframework.domain.persistence.Page;
import br.net.woodstock.rockframework.domain.persistence.orm.ORMFilter;
import br.net.woodstock.rockframework.domain.persistence.orm.ORMFilterType;

public abstract class RepositoryHelper {

	private RepositoryHelper() {
		//
	}

	public static ORMFilter toORMFilter(final String sql) {
		return RepositoryHelper.toORMFilter(sql, null, null);
	}

	public static ORMFilter toORMFilter(final String sql, final Map<String, Object> parameters) {
		return RepositoryHelper.toORMFilter(sql, parameters, null);
	}

	public static ORMFilter toORMFilter(final String sql, final Map<String, Object> parameters, final Map<String, Object> options) {
		return RepositoryHelper.toORMFilter(sql, null, null, parameters, options);
	}

	public static ORMFilter toORMFilter(final String sql, final String countSQL, final Page page, final Map<String, Object> parameters) {
		return RepositoryHelper.toORMFilter(sql, countSQL, page, parameters, null);
	}

	public static ORMFilter toORMFilter(final String sql, final String countSQL, final Page page, final Map<String, Object> parameters, final Map<String, Object> options) {
		ORMFilter filter = new ORMFilter(sql);
		filter.setCountQuery(countSQL);
		filter.setOptions(options);
		filter.setPage(page);
		filter.setParameters(parameters);
		filter.setType(ORMFilterType.JPQL);
		return filter;
	}
}
