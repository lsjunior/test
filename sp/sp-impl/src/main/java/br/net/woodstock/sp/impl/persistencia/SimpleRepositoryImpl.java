package br.net.woodstock.sp.impl.persistencia;

import java.io.Serializable;

import javax.persistence.EntityManager;

import org.jboss.seam.annotations.AutoCreate;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.TransactionPropagationType;
import org.jboss.seam.annotations.Transactional;

import br.net.woodstock.rockframework.domain.Entity;
import br.net.woodstock.rockframework.domain.persistence.orm.ORMFilter;
import br.net.woodstock.rockframework.domain.persistence.orm.ORMResult;
import br.net.woodstock.rockframework.domain.persistence.orm.impl.AbstractJPARepository;
import br.net.woodstock.sp.api.persistencia.SimpleRepository;
import br.net.woodstock.sp.util.Constantes;

@Name(SimpleRepository.NAME)
@AutoCreate
@SuppressWarnings("rawtypes")
public class SimpleRepositoryImpl extends AbstractJPARepository implements SimpleRepository {

	private static final long	serialVersionUID	= -Constantes.VERSAO;

	@In
	private EntityManager		entityManager;

	public SimpleRepositoryImpl() {
		super();
	}

	@Override
	@Transactional(TransactionPropagationType.REQUIRED)
	public void save(final Entity e) {
		super.save(e);
	}

	@Override
	@Transactional(TransactionPropagationType.REQUIRED)
	public void update(final Entity e) {
		super.update(e);
	}

	@Override
	@Transactional(TransactionPropagationType.REQUIRED)
	public void delete(final Entity e) {
		super.delete(e);
	}

	@Override
	@Transactional(TransactionPropagationType.SUPPORTS)
	public <E extends Entity<T>, T extends Serializable> E get(final Class<E> clazz, final T id) {
		return super.get(clazz, id);
	}

	@Override
	@Transactional(TransactionPropagationType.SUPPORTS)
	public <E> E getSingle(final ORMFilter filter) {
		return super.getSingle(filter);
	}

	@Override
	@Transactional(TransactionPropagationType.SUPPORTS)
	public ORMResult getCollection(final ORMFilter filter) {
		return super.getCollection(filter);
	}

	@Override
	@Transactional(TransactionPropagationType.REQUIRED)
	public void executeUpdate(final ORMFilter filter) {
		super.executeUpdate(filter);
	}

	@Override
	protected EntityManager getEntityManager() {
		return this.entityManager;
	}

}
