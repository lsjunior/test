package br.net.woodstock.jsftest.cadastro;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.net.woodstock.jsftest.JSFLog;
import br.net.woodstock.rockframework.core.utils.Conditions;
import br.net.woodstock.rockframework.domain.persistence.Page;
import br.net.woodstock.rockframework.domain.persistence.orm.ORMResult;
import br.net.woodstock.rockframework.web.faces.EntityRepository;
import br.net.woodstock.rockframework.web.faces.primefaces.EntityDataModel;
import br.net.woodstock.rockframework.web.faces.utils.FacesContexts;

@ManagedBean(name = "usuarioBean")
// @RequestScoped
// @ViewScoped
@SessionScoped
public class UsuarioBean implements Serializable {

	private static final long			serialVersionUID	= 1L;

	private Integer						id;

	private String						nome;

	private String						email;

	private String						nomeFiltro;

	private Integer						indiceUsuario;

	private EntityDataModel<Usuario>	dataModel;

	public UsuarioBean() {
		super();
	}

	@PostConstruct
	public void postConstruct() {
		JSFLog.getLogger().info("PostConstruct em " + this.getClass().getCanonicalName());
	}

	private void reset(final boolean limparDataModel) {
		this.id = null;
		this.nome = null;
		this.email = null;
		if (limparDataModel) {
			this.dataModel = null;
		}
	}

	public String iniciar() {
		this.reset(true);
		return "/cadastro/pesquisar-usuario.xhtml";
	}

	public String novo() {
		this.reset(false);
		return "/cadastro/salvar-usuario.xhtml";
	}

	public String editar() {
		JSFLog.getLogger().info("Usuario : " + this.dataModel.getRowData());
		Usuario u = this.dataModel.getRowData();

		this.email = u.getEmail();
		this.id = u.getId();
		this.nome = u.getNome();
		return "/cadastro/salvar-usuario.xhtml";
	}

	public String pesquisar() {
		final String nomeFiltro = this.nomeFiltro;
		EntityRepository repository = new EntityRepository() {

			private static final long	serialVersionUID	= 1L;

			@Override
			public ORMResult getResult(final Page page) {
				return UsuarioService.getInstance().pesquisarPorNome(nomeFiltro, page);
			}

			@Override
			public Object getEntity(final Object id) {
				return UsuarioService.getInstance().recuperarPorId((Integer) id);
			}
		};
		this.dataModel = new EntityDataModel<>(10, repository);
		return null;
	}

	public String salvar() {
		Usuario u = new Usuario();
		u.setEmail(this.email);
		u.setNome(this.nome);

		if (Conditions.isGreaterThanZeroAndNotNull(this.id)) {
			u.setId(this.id);
			UsuarioService.getInstance().atualizar(u);
		} else {
			UsuarioService.getInstance().salvar(u);
		}
		FacesContexts.addMessage("Operação realizada com sucesso");
		return null;
	}

	public String cancelar() {
		this.pesquisar();
		return "/cadastro/pesquisar-usuario.xhtml";
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(final Integer id) {
		this.id = id;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(final String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(final String email) {
		this.email = email;
	}

	public String getNomeFiltro() {
		return this.nomeFiltro;
	}

	public void setNomeFiltro(final String nomeFiltro) {
		this.nomeFiltro = nomeFiltro;
	}

	public Integer getIndiceUsuario() {
		return this.indiceUsuario;
	}

	public void setIndiceUsuario(final Integer indiceUsuario) {
		JSFLog.getLogger().info("Indice " + indiceUsuario);
		this.indiceUsuario = indiceUsuario;
	}

	public EntityDataModel<Usuario> getDataModel() {
		return this.dataModel;
	}

	public void setDataModel(final EntityDataModel<Usuario> dataModel) {
		this.dataModel = dataModel;
	}

	//
	public int getPrimeiraLinha() {
		if (this.getIndiceUsuario() == null) {
			return 0;
		}
		int index = this.getIndiceUsuario().intValue();
		if (index > 0) {
			index = (index / this.dataModel.getPageSize()) * this.dataModel.getPageSize();
		}
		return index;
	}

}
