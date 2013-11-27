package br.net.woodstock.sp.web.acesso;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.core.Conversation;

import br.net.woodstock.rockframework.domain.persistence.Page;
import br.net.woodstock.rockframework.domain.persistence.Result;
import br.net.woodstock.rockframework.web.faces.DataRepository;
import br.net.woodstock.sp.api.acesso.UsuarioService;
import br.net.woodstock.sp.orm.Usuario;
import br.net.woodstock.sp.util.Constantes;
import br.net.woodstock.sp.util.Mensagens;
import br.net.woodstock.sp.web.ConfigAction;
import br.net.woodstock.sp.web.Paginas;
import br.net.woodstock.sp.web.SPAction;
import br.net.woodstock.sp.web.util.RichFacesDataModel;

@Name("usuarioAction")
@Scope(ScopeType.CONVERSATION)
public class UsuarioAction extends SPAction {

	private static final long			serialVersionUID	= Constantes.VERSAO;

	private UsuarioForm					form;

	private UsuarioFiltro				filtro;

	private RichFacesDataModel<Usuario>	usuarios;

	@In(UsuarioService.NAME)
	private UsuarioService				usuarioService;

	@In
	private Conversation				conversation;

	public UsuarioAction() {
		super();
		this.form = new UsuarioForm();
		this.filtro = new UsuarioFiltro();
	}

	public String novo() {
		this.form.limpar();
		return Paginas.ACESSO_USUARIO_SAVE;
	}

	public String cancelar() {
		if (this.usuarios != null) {
			this.pesquisar();
		}
		return Paginas.ACESSO_USUARIO_LIST;
	}

	public String pesquisar() {
		final UsuarioService usuarioService = this.usuarioService;
		final String filtro = this.filtro.getNome();

		DataRepository repository = new DataRepository() {

			private static final long	serialVersionUID	= Constantes.VERSAO;

			@Override
			public Result getResult(final Page page) {
				return usuarioService.pesquisarPorNome(filtro, page);
			}

			@Override
			public Object getObject(final Object id) {
				return usuarioService.recuperarPorId((Integer) id);
			}

		};

		this.usuarios = new RichFacesDataModel<Usuario>(ConfigAction.PAGE_SIZE, repository);
		return null;
	}

	public String salvar() {
		try {
			Usuario usuario = new Usuario();
			usuario.setId(this.form.getId());
			usuario.setLogin(this.form.getLogin());
			usuario.setNome(this.form.getNome());
			usuario.setSenha(this.form.getSenha());

			if (usuario.getId() != null) {
				this.usuarioService.atualizar(usuario);
			} else {
				this.usuarioService.salvar(usuario);
				this.form.limpar();
			}
			this.addMessage(Mensagens.getMensagem(Mensagens.MSG_OPERACAO_OK));
		} catch (Exception e) {
			this.addError(e);
		}
		return null;
	}

	public UsuarioForm getForm() {
		return this.form;
	}

	public void setForm(final UsuarioForm form) {
		this.form = form;
	}

	public UsuarioFiltro getFiltro() {
		return this.filtro;
	}

	public void setFiltro(final UsuarioFiltro filtro) {
		this.filtro = filtro;
	}

	public RichFacesDataModel<Usuario> getUsuarios() {
		return this.usuarios;
	}

	public void setUsuarios(final RichFacesDataModel<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

}
