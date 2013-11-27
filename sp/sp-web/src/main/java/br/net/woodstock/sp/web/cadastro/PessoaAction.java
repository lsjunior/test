package br.net.woodstock.sp.web.cadastro;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.core.Conversation;

import br.net.woodstock.rockframework.domain.persistence.Page;
import br.net.woodstock.rockframework.domain.persistence.Result;
import br.net.woodstock.rockframework.web.faces.DataRepository;
import br.net.woodstock.sp.api.cadastro.PessoaService;
import br.net.woodstock.sp.orm.Pessoa;
import br.net.woodstock.sp.util.Constantes;
import br.net.woodstock.sp.util.Mensagens;
import br.net.woodstock.sp.web.ConfigAction;
import br.net.woodstock.sp.web.Paginas;
import br.net.woodstock.sp.web.SPAction;
import br.net.woodstock.sp.web.util.RichFacesDataModel;

@Name("pessoaAction")
@Scope(ScopeType.CONVERSATION)
public class PessoaAction extends SPAction {

	private static final long			serialVersionUID	= Constantes.VERSAO;

	private PessoaForm					form;

	private PessoaFiltro				filtro;

	private RichFacesDataModel<Pessoa>	pessoas;

	@In(PessoaService.NAME)
	private PessoaService				pessoaService;

	@In
	private Conversation				conversation;

	public PessoaAction() {
		super();
		this.form = new PessoaForm();
		this.filtro = new PessoaFiltro();
	}

	public String novo() {
		this.form.limpar();
		return Paginas.CADASTRO_PESSOA_SAVE;
	}

	public String cancelar() {
		if (this.pessoas != null) {
			this.pesquisar();
		}
		return Paginas.CADASTRO_PESSOA_LIST;
	}

	public String pesquisar() {
		final PessoaService pessoaService = this.pessoaService;
		final String filtro = this.filtro.getNome();

		DataRepository repository = new DataRepository() {

			private static final long	serialVersionUID	= Constantes.VERSAO;

			@Override
			public Result getResult(final Page page) {
				return pessoaService.pesquisarPorNome(filtro, page);
			}

			@Override
			public Object getObject(final Object id) {
				return pessoaService.recuperarPorId((Integer) id);
			}

		};

		this.pessoas = new RichFacesDataModel<Pessoa>(ConfigAction.PAGE_SIZE, repository);
		return null;
	}

	public String salvar() {
		try {
			Pessoa pessoa = new Pessoa();
			pessoa.setCpf(this.form.getCpf());
			pessoa.setId(this.form.getId());
			pessoa.setLogin(this.form.getLogin());
			pessoa.setNome(this.form.getNome());
			pessoa.setSenha(this.form.getSenha());

			if (pessoa.getId() != null) {
				this.pessoaService.atualizar(pessoa);
			} else {
				this.pessoaService.salvar(pessoa);
				this.form.limpar();
			}
			this.addMessage(Mensagens.getMensagem(Mensagens.MSG_OPERACAO_OK));
		} catch (Exception e) {
			this.addError(e);
		}
		return null;
	}

	public PessoaForm getForm() {
		return this.form;
	}

	public void setForm(final PessoaForm form) {
		this.form = form;
	}

	public PessoaFiltro getFiltro() {
		return this.filtro;
	}

	public void setFiltro(final PessoaFiltro filtro) {
		this.filtro = filtro;
	}

	public RichFacesDataModel<Pessoa> getPessoas() {
		return this.pessoas;
	}

	public void setPessoas(final RichFacesDataModel<Pessoa> pessoas) {
		this.pessoas = pessoas;
	}

}
