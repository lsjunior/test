package br.net.woodstock.sp.impl.cadastro.je;

import org.jboss.seam.annotations.AutoCreate;
import org.jboss.seam.annotations.Install;
import org.jboss.seam.annotations.Name;

import br.net.woodstock.rockframework.domain.ServiceException;
import br.net.woodstock.sp.api.cadastro.PessoaService;
import br.net.woodstock.sp.impl.cadastro.PessoaServiceImpl;
import br.net.woodstock.sp.orm.Pessoa;
import br.net.woodstock.sp.util.Constantes;

@Name(PessoaService.NAME)
@AutoCreate
@Install(precedence = 99)
public class PessoaServiceJE extends PessoaServiceImpl {

	private static final long	serialVersionUID	= Constantes.VERSAO;

	@Override
	public void salvar(final Pessoa pessoa) {
		try {
			if (pessoa.getCpf().startsWith("0")) {
				throw new ServiceException("Não aceitamos CPF iniciados com zeros");
			}
			super.salvar(pessoa);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

}
