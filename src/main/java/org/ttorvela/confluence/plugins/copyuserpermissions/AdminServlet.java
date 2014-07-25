package org.ttorvela.confluence.plugins.copyuserpermissions;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.net.URI;
import com.atlassian.sal.api.auth.LoginUriProvider;
import com.atlassian.sal.api.user.UserManager;
import com.atlassian.sal.api.transaction.TransactionTemplate;
import com.atlassian.templaterenderer.TemplateRenderer;

import com.atlassian.confluence.spaces.SpaceManager;
import com.atlassian.confluence.security.SpacePermissionManager;
import com.atlassian.confluence.security.PermissionManager;
import com.atlassian.confluence.user.UserAccessor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AdminServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final Logger log = LoggerFactory
			.getLogger(AdminServlet.class);
	private final UserManager userManager;
	private final LoginUriProvider loginUriProvider;
	private final TemplateRenderer renderer;

	public AdminServlet(UserManager userManager,
			LoginUriProvider loginUriProvider, TemplateRenderer renderer,
			SpaceManager spaceManager,
			SpacePermissionManager spacePermissionManager,
			UserAccessor userAccessor, PermissionManager permissionManager,
			TransactionTemplate transactionTemplate) {
		log.debug("AdminServlet initialized");
		this.userManager = userManager;
		this.loginUriProvider = loginUriProvider;
		this.renderer = renderer;
	}

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		log.debug("doGet entered");

		String username = userManager.getRemoteUsername(request);
		if (username == null || !userManager.isSystemAdmin(username)) {
			redirectToLogin(request, response);
			return;
		}

		response.setContentType("text/html;charset=utf-8");
		renderer.render("admin.vm", response.getWriter());
	}

	// @Override
	// protected void doPost(HttpServletRequest req, HttpServletResponse
	// response) throws ServletException, IOException {
	// log.debug("doPost entered");
	// ConfluenceUser userFrom =
	// userAccessor.getUserByName(req.getParameter("usernameFrom"));
	// ConfluenceUser userTo =
	// userAccessor.getUserByName(req.getParameter("usernameTo"));
	//
	// List<Space> spaces = spaceManager.getAllSpaces();
	//
	// for (Space s : spaces) {
	// if (permissionManager.hasPermission(userFrom, Permission.VIEW, s) ) {
	// SpacePermission viewSpace =
	// SpacePermission.createUserSpacePermission(Permission.VIEW.toString(), s,
	// userTo);
	// spacePermissionManager.savePermission(viewSpace);
	// }
	// }
	//
	// log.debug("doPost exiting");
	//
	// response.sendRedirect("test");
	// }

	// Handle submit button with Javascript 
	/*
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public Response put(@Context HttpServletRequest req) {
		String username = userManager.getRemoteUsername(req);
		if (username == null || !userManager.isSystemAdmin(username)) {
			return Response.status(Status.UNAUTHORIZED).build();
		}

		ConfluenceUser userFrom = userAccessor.getUserByName(req
				.getParameter("usernameFrom"));
		ConfluenceUser userTo = userAccessor.getUserByName(req
				.getParameter("usernameTo"));

		List<Space> spaces = spaceManager.getAllSpaces();

		for (Space s : spaces) {
			if (permissionManager.hasPermission(userFrom, Permission.VIEW, s)) {
				SpacePermission viewSpace = SpacePermission
						.createUserSpacePermission(Permission.VIEW.toString(),
								s, userTo);
				spacePermissionManager.savePermission(viewSpace);
			}
		}

		return Response.noContent().build();
	}
	*/

	private void redirectToLogin(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		response.sendRedirect(loginUriProvider.getLoginUri(getUri(request))
				.toASCIIString());
	}

	private URI getUri(HttpServletRequest request) {
		StringBuffer builder = request.getRequestURL();
		if (request.getQueryString() != null) {
			builder.append("?");
			builder.append(request.getQueryString());
		}
		return URI.create(builder.toString());
	}
}