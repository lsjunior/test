package br.net.woodstock.sp.impl.cadastro;

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
import br.net.woodstock.sp.api.cadastro.PessoaService;
import br.net.woodstock.sp.api.persistencia.SimpleRepository;
import br.net.woodstock.sp.impl.persistencia.JPQLHelper;
import br.net.woodstock.sp.impl.persistencia.RepositoryHelper;
import br.net.woodstock.sp.orm.Pessoa;
import br.net.woodstock.sp.orm.Usuario;
import br.net.woodstock.sp.util.Constantes;
import br.net.woodstock.sp.util.Mensagens;
import br.net.woodstock.sp.util.Senhas;

@Name(PessoaService.NAME)
@AutoCreate
public class PessoaServiceImpl implements PessoaService {

	private static final long	serialVersionUID					= Constantes.VERSAO;

	private static final String	JPQL_RECUPERAR_PESSOA_POR_CPF		= "SELECT u FROM Pessoa AS u WHERE u.cpf = :cpf";

	private static final String	JPQL_PESQUISAR_PESSOA_POR_NOME		= "SELECT u FROM Pessoa AS u WHERE u.nome LIKE :nome ORDER BY u.nome";

	private static final String	JPQL_C_PESQUISAR_PESSOA_POR_NOME	= "SELECT COUNT(u) FROM Pessoa AS u WHERE u.nome LIKE :nome";

	@In(SimpleRepository.NAME)
	private SimpleRepository	simpleRepository;

	@In(UsuarioService.NAME)
	private UsuarioService		usuarioService;

	public PessoaServiceImpl() {
		super();
	}

	@Override
	@Transactional(TransactionPropagationType.SUPPORTS)
	public Pessoa recuperarPorId(final Integer id) {
		try {
			return this.simpleRepository.get(Pessoa.class, id);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	@Override
	@Transactional(TransactionPropagationType.SUPPORTS)
	public Pessoa recuperarPorCpf(final String cpf) {
		try {
			Map<String, Object> parameters = new HashMap<String, Object>();
			parameters.put("cpf", cpf);
			ORMFilter filter = RepositoryHelper.toORMFilter(PessoaServiceImpl.JPQL_RECUPERAR_PESSOA_POR_CPF, parameters);
			Pessoa pessoa = this.simpleRepository.getSingle(filter);
			return pessoa;
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	@Override
	@Transactional(TransactionPropagationType.REQUIRED)
	public void salvar(final Pessoa pessoa) {
		try {
			Usuario u = this.usuarioService.recuperarPorLogin(pessoa.getLogin());
			if (u != null) {
				throw new ServiceException(Mensagens.getMensagem(Mensagens.MSG_ERRO_CPF_EXISTENTE));
			}

			Pessoa p = this.recuperarPorCpf(pessoa.getCpf());
			if (p != null) {
				throw new ServiceException(Mensagens.getMensagem(Mensagens.MSG_ERRO_LOGIN_EXISTENTE));
			}

			pessoa.setSenha(Senhas.getPasswordEncoder().encode(pessoa.getSenha()));
			pessoa.setStatus(Boolean.TRUE);
			this.simpleRepository.save(pessoa);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	@Override
	@Transactional(TransactionPropagationType.REQUIRED)
	public void atualizar(final Pessoa pessoa) {
		try {
			Pessoa p = this.simpleRepository.get(Pessoa.class, pessoa.getId());
			p.setCpf(pessoa.getCpf());
			p.setLogin(pessoa.getLogin());
			p.setNome(pessoa.getNome());

			if (pessoa.getStatus() != null) {
				p.setStatus(pessoa.getStatus());
			}

			if (Conditions.isNotEmpty(pessoa.getSenha())) {
				p.setSenha(Senhas.getPasswordEncoder().encode(pessoa.getSenha()));
			}

			this.simpleRepository.update(p);
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

			ORMFilter filter = RepositoryHelper.toORMFilter(PessoaServiceImpl.JPQL_PESQUISAR_PESSOA_POR_NOME, PessoaServiceImpl.JPQL_C_PESQUISAR_PESSOA_POR_NOME, page, parameters);
			return this.simpleRepository.getCollection(filter);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

}
