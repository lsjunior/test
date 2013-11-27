package br.net.woodstock.sp.api.acesso;

import br.net.woodstock.rockframework.domain.Service;
import br.net.woodstock.rockframework.domain.persistence.Page;
import br.net.woodstock.rockframework.domain.persistence.Result;
import br.net.woodstock.sp.orm.Usuario;

public interface UsuarioService extends Service {

	String	NAME	= "UsuarioService";

	Usuario recuperarPorId(Integer id);

	Usuario recuperarPorLogin(String login);

	Usuario recuperarPorLoginSenha(String login, String senha);

	void salvar(Usuario usuario);

	void atualizar(Usuario usuario);

	Result pesquisarPorNome(String nome, Page page);

}
