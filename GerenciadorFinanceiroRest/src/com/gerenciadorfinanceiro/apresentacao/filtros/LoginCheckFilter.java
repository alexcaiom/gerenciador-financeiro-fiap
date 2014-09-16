package com.gerenciadorfinanceiro.apresentacao.filtros;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.gerenciadorfinanceiro.orm.model.usuario.Usuario;

public class LoginCheckFilter extends AbstractFilter implements Filter {
	private static List<String> allowedURIs;

	private static List<String> noSecurityActions = new ArrayList<String>();
	static {
		noSecurityActions.add(SecurityConstants.ACAO_CADASTRO);
	}
	
	public void init(FilterConfig fConfig) throws ServletException {
		if(allowedURIs == null){
			allowedURIs = new ArrayList<String>();
			allowedURIs.add(fConfig.getInitParameter("loginActionURI"));
			allowedURIs.add("/Aeroporto/javax.faces.resource/main.css.xhtml");
			allowedURIs.add("/Aeroporto/javax.faces.resource/theme.css.xhtml");
			allowedURIs.add("/Aeroporto/javax.faces.resource/primefaces.js.xhtml");
			allowedURIs.add("/Aeroporto/javax.faces.resource/primefaces.css.xhtml");
			allowedURIs.add("/Aeroporto/javax.faces.resource/jquery/jquery.js.xhtml");
			allowedURIs.add("/Aeroporto/javax.faces.resource/messages/messages.png.xhtml");
			allowedURIs.add("/Aeroporto/javax.faces.resource/images/ui-icons_2e83ff_256x240.png.xhtml");
			allowedURIs.add("/Aeroporto/javax.faces.resource/images/ui-icons_38667f_256x240.png.xhtml");
		}
	}

	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpSession session = req.getSession();
		
		if(session.isNew() && !noSecurityActions.contains(getUrl(req.getRequestURI()))) {
			doLogin(request, response, req);
			return;
		}

		Usuario user = (Usuario) session.getAttribute("usuario");

		if(user == null && !allowedURIs.contains(req.getRequestURI()) && !noSecurityActions.contains(getUrl(req.getRequestURI()))) {
			System.out.println(req.getRequestURI());
			doLogin(request, response, req);
			return;
		}

		chain.doFilter(request, response);
	}
	
	private String getUrl(ServletRequest request){
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		String pathBrowser = httpRequest.getHeader("referer");
		String url = pathBrowser.substring(pathBrowser.lastIndexOf("/")+1);
		return url;
	}
	
	private String getUrl(String requestURI){
		return requestURI.substring(requestURI.lastIndexOf("/")+1);
	}
}