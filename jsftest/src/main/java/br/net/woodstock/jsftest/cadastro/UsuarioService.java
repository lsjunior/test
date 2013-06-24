package br.net.woodstock.jsftest.cadastro;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import br.net.woodstock.rockframework.core.utils.Collections;
import br.net.woodstock.rockframework.core.utils.Conditions;
import br.net.woodstock.rockframework.domain.persistence.Page;
import br.net.woodstock.rockframework.domain.persistence.orm.ORMResult;

public class UsuarioService implements Serializable {

	private static final long		serialVersionUID	= 1L;

	private static UsuarioService	instance			= new UsuarioService();

	private int						index;

	private List<Usuario>			usuarios;

	private UsuarioService() {
		super();
		this.index = 1;
		this.usuarios = new ArrayList<>();
		for (int i = 0; i < 20; i++) {
			Usuario u = new Usuario();
			u.setId(Integer.valueOf(this.index++));
			u.setEmail("teste" + i + "@woodstock.net.br");
			u.setNome("Teste " + i);
			this.usuarios.add(u);
		}
	}

	public void atualizar(final Usuario usuario) {
		if (usuario.getId() == null) {
			throw new IllegalArgumentException("O ID deve ser preenchido");
		}
		if (Conditions.isEmpty(usuario.getNome())) {
			throw new IllegalArgumentException("O nome deve ser preenchido");
		}
		if (Conditions.isEmpty(usuario.getEmail())) {
			throw new IllegalArgumentException("O email deve ser preenchido");
		}

		Usuario u = this.recuperarPorId(usuario.getId());
		u.setEmail(usuario.getEmail());
		u.setNome(usuario.getNome());
	}

	public ORMResult pesquisarPorNome(final String nome, Page page) {
		List<Usuario> list = new ArrayList<>();
		for (Usuario u : this.usuarios) {
			if ((nome == null) || (u.getNome().toLowerCase().indexOf(nome.toLowerCase()) != -1)) {
				list.add(u);
			}
		}

		int total = list.size();

		if ((total > 0) && (page != null)) {
			int first = (page.getPageNumber() - 1) * page.getResultsPerPage();
			if (first + page.getResultsPerPage() < total) {
				list = list.subList(first, first + page.getResultsPerPage());
			} else {
				list = list.subList(first, total);
			}
		}

		Collections.sort(list);

		return new ORMResult(Integer.valueOf(total), list, page);
	}

	public Usuario recuperarPorId(final Integer id) {
		for (Usuario u : this.usuarios) {
			if (u.getId().equals(id)) {
				return u;
			}
		}
		return null;
	}

	public boolean removerPorId(final Integer id) {
		Iterator<Usuario> iterator = this.usuarios.iterator();
		while (iterator.hasNext()) {
			Usuario u = iterator.next();
			if (u.getId().equals(id)) {
				iterator.remove();
				return true;
			}
		}
		return false;
	}

	public void salvar(final Usuario usuario) {
		if (usuario.getId() != null) {
			throw new IllegalArgumentException("Usuário já possui ID");
		}
		if (Conditions.isEmpty(usuario.getNome())) {
			throw new IllegalArgumentException("O nome deve ser preenchido");
		}
		if (Conditions.isEmpty(usuario.getEmail())) {
			throw new IllegalArgumentException("O email deve ser preenchido");
		}
		for (Usuario u : this.usuarios) {
			if (u.getEmail().equals(usuario.getEmail())) {
				throw new IllegalArgumentException("O email já está cadastrado");
			}
		}
		usuario.setId(Integer.valueOf(this.index++));
		this.usuarios.add(usuario);
	}

	public static UsuarioService getInstance() {
		return UsuarioService.instance;
	}

}
