package br.net.woodstock.jee6test.web;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.net.woodstock.jee6test.api.CounterService;

@WebServlet(urlPatterns = "/counter")
public class CounterServlet extends HttpServlet {

	private static final long	serialVersionUID	= 1L;

	@EJB
	private CounterService		counterService;

	public CounterServlet() {
		super();
	}

	@Override
	protected void doGet(final HttpServletRequest req, final HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/plain");
		resp.getWriter().println(this.counterService.getValue());
	}

}
