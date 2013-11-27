package br.net.woodstock.sp.web.cadastro.je;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Install;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;

import br.net.woodstock.rockframework.core.utils.Conditions;
import br.net.woodstock.sp.orm.Pessoa;
import br.net.woodstock.sp.util.Constantes;
import br.net.woodstock.sp.util.Mensagens;
import br.net.woodstock.sp.web.cadastro.PessoaAction;
import br.net.woodstock.sp.web.cadastro.PessoaForm;

@Name("pessoaAction")
@Install(precedence = 99)
@Scope(ScopeType.CONVERSATION)
public class PessoaActionJE extends PessoaAction {

	private static final long	serialVersionUID	= Constantes.VERSAO;

	public PessoaActionJE() {
		super();
		this.setForm(new PessoaFormJE());
	}

	@Override
	public String pesquisar() {
		if (Conditions.isNotEmpty(this.getFiltro().getNome())) {
			super.pesquisar();
		} else {
			this.addError(Mensagens.getMensagem(Mensagens.MSG_ERRO_FILTRO_VAZIO));
		}

		return null;
	}

	@Override
	protected Pessoa toPessoa(PessoaForm pessoaForm) {
		Pessoa pessoa = super.toPessoa(pessoaForm);
		pessoa.setTituloEleitor(((PessoaFormJE) pessoaForm).getTituloEleitor());
		return pessoa;
	}

}
