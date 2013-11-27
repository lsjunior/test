package br.net.woodstock.sp.web.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.context.FacesContext;

import org.ajax4jsf.model.DataVisitor;
import org.ajax4jsf.model.Range;
import org.ajax4jsf.model.SequenceRange;
import org.ajax4jsf.model.SerializableDataModel;

import br.net.woodstock.rockframework.core.util.Assert;
import br.net.woodstock.rockframework.core.util.Identifiable;
import br.net.woodstock.rockframework.domain.persistence.Page;
import br.net.woodstock.rockframework.domain.persistence.Result;
import br.net.woodstock.rockframework.web.faces.DataRepository;
import br.net.woodstock.sp.util.Constantes;

public class RichFacesDataModel<E extends Identifiable<?>> extends SerializableDataModel {

	private static final long	serialVersionUID	= Constantes.VERSAO;

	private int					rows;

	private DataRepository		repository;

	private Result				queryResult;

	private Object				currentId;

	private Map<Object, E>		wrappedData			= new HashMap<Object, E>();

	private List<Object>		wrappedKeys			= null;

	public RichFacesDataModel(final int rows, final DataRepository repository) {
		super();
		Assert.greaterThan(rows, 0, "rows");
		Assert.notNull(repository, "repository");
		this.rows = rows;
		this.repository = repository;
	}

	@Override
	public void walk(final FacesContext context, final DataVisitor visitor, final Range range, final Object argument) throws IOException {
		Page page = this.toPage(range);

		if ((this.queryResult == null) || (!page.equals(this.queryResult.getCurrentPage()))) {
			this.queryResult = this.repository.getResult(page);
		}

		this.wrappedKeys = new ArrayList<Object>();
		this.wrappedData = new HashMap<Object, E>();

		Collection<E> collection = this.queryResult.getItems();
		for (E e : collection) {
			this.wrappedKeys.add(e.getId());
			this.wrappedData.put(e.getId(), e);
			visitor.process(context, e.getId(), argument);
		}
	}

	@Override
	public boolean isRowAvailable() {
		if (this.currentId == null) {
			return false;
		}
		return true;
	}

	@Override
	public int getRowCount() {
		if (this.queryResult == null) {
			Page page = new Page(1, this.rows);
			this.queryResult = this.repository.getResult(page);
		}

		return this.queryResult.getTotal().intValue();
	}

	@Override
	@SuppressWarnings("unchecked")
	public E getRowData() {
		if (this.currentId == null) {
			return null;
		}
		E e = this.wrappedData.get(this.currentId);
		if (e == null) {
			e = (E) this.repository.getObject(this.currentId);
			this.wrappedData.put(this.currentId, e);
		}
		return e;
	}

	@Override
	public Object getRowKey() {
		return this.currentId;
	}

	@Override
	public void setRowKey(final Object key) {
		this.currentId = key;
	}

	protected Page toPage(final Range range) {
		if (range instanceof SequenceRange) {
			SequenceRange sequenceRange = (SequenceRange) range;
			if ((sequenceRange.getFirstRow() >= 0) && (sequenceRange.getRows() >= 0)) {
				int div = sequenceRange.getFirstRow() / sequenceRange.getRows();

				div++;

				Page page = new Page(div, sequenceRange.getRows());
				return page;
			}

		}
		return null;
	}

	@Override
	public int getRowIndex() {
		throw new UnsupportedOperationException();
	}

	@Override
	public void setRowIndex(final int index) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Object getWrappedData() {
		throw new UnsupportedOperationException();
	}

	@Override
	public void setWrappedData(final Object wrappedData) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void update() {
		throw new UnsupportedOperationException();
	}

}
