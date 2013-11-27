package br.net.woodstock.sp.impl.acesso;

import java.util.HashMap;
import java.util.Map;

import org.jboss.seam.annotations.AutoCreate;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.TransactionPropagationType;
import org.jboss.seam.annotations.Transactional;

import br.net.woodstock.rockframework.core.utils.Conditions;
import br.net.woodstock.rockframework.domain.ServiceException;
import br.net.woodstock.rockframework.domain.persistence.Page;
import br.net.woodstock.rockframework.domain.persistence.Result;
import br.net.woodstock.rockframework.domain.persistence.orm.ORMFilter;
import br.net.woodstock.sp.api.acesso.UsuarioService;
import br.net.woodstock.sp.api.persistencia.SimpleRepository;
import br.net.woodstock.sp.impl.persistencia.JPQLHelper;
import br.net.woodstock.sp.impl.persistencia.RepositoryHelper;
import br.net.woodstock.sp.orm.Usuario;
import br.net.woodstock.sp.util.Constantes;
import br.net.woodstock.sp.util.Mensagens;
import br.net.woodstock.sp.util.Senhas;

@Name(UsuarioService.NAME)
@AutoCreate
public class UsuarioServiceImpl implements UsuarioService {

	private static final long	serialVersionUID						= Constantes.VERSAO;

	private static final String	JPQL_RECUPERAR_USUARIO_POR_LOGIN		= "SELECT u FROM Usuario AS u WHERE u.login = :login";

	private static final String	JPQL_RECUPERAR_USUARIO_POR_LOGIN_SENHA	= "SELECT u FROM Usuario AS u WHERE u.login = :login AND u.senha = :senha";

	private static final String	JPQL_PESQUISAR_USUARIO_POR_NOME			= "SELECT u FROM Usuario AS u WHERE u.nome LIKE :nome ORDER BY u.nome";

	private static final String	JPQL_C_PESQUISAR_USUARIO_POR_NOME		= "SELECT COUNT(u) FROM Usuario AS u WHERE u.nome LIKE :nome";

	@In(SimpleRepository.NAME)
	private SimpleRepository	simpleRepository;

	public UsuarioServiceImpl() {
		super();
	}

	@Override
	@Transactional(TransactionPropagationType.SUPPORTS)
	public Usuario recuperarPorId(final Integer id) {
		try {
			return this.simpleRepository.get(Usuario.class, id);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	@Override
	@Transactional(TransactionPropagationType.SUPPORTS)
	public Usuario recuperarPorLogin(final String login) {
		try {
			Map<String, Object> parameters = new HashMap<String, Object>();
			parameters.put("login", login);
			ORMFilter filter = RepositoryHelper.toORMFilter(UsuarioServiceImpl.JPQL_RECUPERAR_USUARIO_POR_LOGIN, parameters);
			Usuario usuario = this.simpleRepository.getSingle(filter);
			return usuario;
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	@Override
	@Transactional(TransactionPropagationType.SUPPORTS)
	public Usuario recuperarPorLoginSenha(final String login, final String senha) {
		try {
			Map<String, Object> parameters = new HashMap<String, Object>();
			parameters.put("login", login);
			parameters.put("senha", Senhas.getPasswordEncoder().encode(senha));
			ORMFilter filter = RepositoryHelper.toORMFilter(UsuarioServiceImpl.JPQL_RECUPERAR_USUARIO_POR_LOGIN_SENHA, parameters);
			Usuario usuario = this.simpleRepository.getSingle(filter);
			return usuario;
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	@Override
	@Transactional(TransactionPropagationType.REQUIRED)
	public void salvar(final Usuario usuario) {
		try {
			Usuario u = this.recuperarPorLogin(usuario.getLogin());
			if (u != null) {
				throw new ServiceException(Mensagens.getMensagem(Mensagens.MSG_ERRO_LOGIN_EXISTENTE));
			}

			usuario.setSenha(Senhas.getPasswordEncoder().encode(usuario.getSenha()));
			usuario.setStatus(Boolean.TRUE);
			this.simpleRepository.save(usuario);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	@Override
	@Transactional(TransactionPropagationType.REQUIRED)
	public void atualizar(final Usuario usuario) {
		try {
			Usuario u = this.simpleRepository.get(Usuario.class, usuario.getId());
			u.setLogin(usuario.getLogin());
			u.setNome(usuario.getNome());

			if (usuario.getStatus() != null) {
				u.setStatus(usuario.getStatus());
			}

			if (Conditions.isNotEmpty(usuario.getSenha())) {
				u.setSenha(Senhas.getPasswordEncoder().encode(usuario.getSenha()));
			}

			this.simpleRepository.update(u);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	@Override
	@Transactional(TransactionPropagationType.SUPPORTS)
	public Result pesquisarPorNome(final String nome, final Page page) {
		try {
			Map<String, Object> parameters = new HashMap<String, Object>();
			parameters.put("nome", JPQLHelper.getLikeValue(nome, false));

			ORMFilter filter = RepositoryHelper.toORMFilter(UsuarioServiceImpl.JPQL_PESQUISAR_USUARIO_POR_NOME, UsuarioServiceImpl.JPQL_C_PESQUISAR_USUARIO_POR_NOME, page, parameters);
			return this.simpleRepository.getCollection(filter);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

}
