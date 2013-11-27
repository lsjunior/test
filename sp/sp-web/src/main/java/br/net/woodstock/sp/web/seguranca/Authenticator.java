package br.net.woodstock.sp.web.seguranca;

import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.security.Credentials;
import org.jboss.seam.security.Identity;

import br.net.woodstock.sp.api.acesso.UsuarioService;
import br.net.woodstock.sp.orm.Usuario;

@Name("authenticator")
public class Authenticator {

	@In
	private Credentials		credentials;

	@In
	private Identity		identity;

	@In
	private UsuarioService	usuarioService;

	public Authenticator() {
		super();
	}

	public boolean authenticate() {
		Usuario usuario = this.usuarioService.recuperarPorLoginSenha(this.credentials.getUsername(), this.credentials.getPassword());
		if ((usuario != null) && (usuario.getStatus().booleanValue())) {
			return true;
		}
		return false;
	}

}
