package org.ttorvela.confluence.plugins.spacepermissionshandler;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.atlassian.confluence.security.PermissionManager;
import com.atlassian.confluence.security.SpacePermissionManager;
import com.atlassian.confluence.spaces.SpaceManager;
import com.atlassian.confluence.user.UserAccessor;
import com.atlassian.sal.api.transaction.TransactionCallback;
import com.atlassian.sal.api.transaction.TransactionTemplate;
import com.atlassian.sal.api.user.UserManager;


@Path("/")
public class UserPermissionsResource {
	private final UserManager userManager;
	private final TransactionTemplate transactionTemplate;
	private final UserAccessor userAccessor;

	private RestUserPermissionManager restUserPermissionManager;

	public UserPermissionsResource(UserManager userManager,
			TransactionTemplate transactionTemplate, SpaceManager spaceManager,
			SpacePermissionManager spacePermissionManager,
			UserAccessor userAccessor, PermissionManager permissionManager) {
		this.userManager = userManager;
		this.transactionTemplate = transactionTemplate;
		this.userAccessor = userAccessor;
		this.restUserPermissionManager = new RestUserPermissionManager(
				spaceManager, spacePermissionManager, userAccessor); // ,
																		// permissionManager);
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{username}")
	public Response getPermissions(
			@PathParam("username") String targetUsername,
			@Context HttpServletRequest request) {
		String currentUsername = userManager.getRemoteUsername(request);
		if (currentUsername == null
				|| !userManager.isSystemAdmin(currentUsername)) {
			return Response.status(Status.UNAUTHORIZED).build();
		}

		UserPermissionsEntity entity = restUserPermissionManager
				.getPermissionsEntity(targetUsername);
		return entity == null ? Response.status(Response.Status.NOT_FOUND)
				.build() : Response.ok(entity).build();
	}

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/{username}")
	public Response put(final @PathParam("username") String targetUsername,
			@DefaultValue("false") @QueryParam("onlyUserPermissions") final boolean onlyUserPermissions,
			final UserPermissionsEntity userPermissionsEntity,
			@Context HttpServletRequest request) {
		String username = userManager.getRemoteUsername(request);
		if (username == null || !userManager.isSystemAdmin(username)) {
			return Response.status(Status.UNAUTHORIZED).build();
		}
		
		if (userAccessor.getUser(targetUsername) == null) {
			return Response.status(Status.NOT_FOUND).build();
		}

		transactionTemplate.execute(new TransactionCallback() {
			public Object doInTransaction() {
				restUserPermissionManager.setPermissions(targetUsername,
						userPermissionsEntity, onlyUserPermissions);
				return null;
			}
		});
		return Response.noContent().build();
	}
}
