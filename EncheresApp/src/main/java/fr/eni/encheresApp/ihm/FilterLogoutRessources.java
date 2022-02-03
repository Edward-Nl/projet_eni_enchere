package fr.eni.encheresApp.ihm;

import java.io.IOException;

import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebFilter(urlPatterns = { "/Profil/*" }, dispatcherTypes = { DispatcherType.FORWARD, DispatcherType.INCLUDE,
		DispatcherType.REQUEST, DispatcherType.ERROR, }

)

public class FilterLogoutRessources implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		if (httpRequest.getSession().getAttribute("utilisateurCourant") != null) {
			chain.doFilter(request, response);
		} else {
			HttpServletResponse httpResponse = (HttpServletResponse) response;
			httpResponse.sendRedirect(httpRequest.getContextPath() + "/");
		}
	}

}
