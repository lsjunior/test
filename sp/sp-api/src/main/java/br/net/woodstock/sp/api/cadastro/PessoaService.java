package br.net.woodstock.sp.api.cadastro;

import br.net.woodstock.rockframework.domain.Service;
import br.net.woodstock.rockframework.domain.persistence.Page;
import br.net.woodstock.rockframework.domain.persistence.Result;
import br.net.woodstock.sp.orm.Pessoa;

public interface PessoaService extends Service {

	String	NAME	= "PessoaService";

	Pessoa recuperarPorId(Integer id);
	
	Pessoa recuperarPorCpf(String cpf);

	void salvar(Pessoa pessoa);

	void atualizar(Pessoa pessoa);

	Result pesquisarPorNome(String nome, Page page);

}
