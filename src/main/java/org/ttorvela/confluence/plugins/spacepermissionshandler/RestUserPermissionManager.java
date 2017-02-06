package org.ttorvela.confluence.plugins.spacepermissionshandler;

import java.util.ArrayList;
import java.util.List;

import com.atlassian.confluence.security.SpacePermission;
import com.atlassian.confluence.security.SpacePermissionManager;
import com.atlassian.confluence.spaces.Space;
import com.atlassian.confluence.spaces.SpaceManager;
//import com.atlassian.confluence.username.ConfluenceUser;
import com.atlassian.confluence.user.UserAccessor;

public class RestUserPermissionManager {
	private final SpaceManager spaceManager;
	private final SpacePermissionManager spacePermissionManager;
	private final UserAccessor userAccessor;

	public RestUserPermissionManager(SpaceManager spaceManager,
			SpacePermissionManager spacePermissionManager,
			UserAccessor userAccessor) {
		this.spaceManager = spaceManager;
		this.spacePermissionManager = spacePermissionManager;
		this.userAccessor = userAccessor;
	}

	@SuppressWarnings("deprecation")
	public UserPermissionsEntity getPermissionsEntity(String username) {
		UserPermissionsEntity entity = null;

		if (userAccessor.getUser(username) != null) {
			List<Space> spaces = spaceManager.getAllSpaces();
			List<SpacePermissionsEntity> spacePermissions = new ArrayList<SpacePermissionsEntity>();

			SpacePermissionsEntity spacePermissionsEntity;

			for (Space s : spaces) {
				spacePermissionsEntity = new SpacePermissionsEntity(s.getName(), s.getKey());

				setUserSpacePermissionEntity(spacePermissionsEntity, username, SpacePermission.VIEWSPACE_PERMISSION, s);

				if (spacePermissionsEntity.getPermissionStatus(SpacePermission.VIEWSPACE_PERMISSION)) {
					setUserSpacePermissionEntity(spacePermissionsEntity, username,
							SpacePermission.REMOVE_OWN_CONTENT_PERMISSION, s);
					
					setUserSpacePermissionEntity(spacePermissionsEntity, username,
							SpacePermission.CREATEEDIT_PAGE_PERMISSION, s);
					setUserSpacePermissionEntity(spacePermissionsEntity, username,
							SpacePermission.REMOVE_PAGE_PERMISSION, s);
					
					setUserSpacePermissionEntity(spacePermissionsEntity, username, SpacePermission.EDITBLOG_PERMISSION,
							s);
					setUserSpacePermissionEntity(spacePermissionsEntity, username,
							SpacePermission.REMOVE_BLOG_PERMISSION, s);
					
					setUserSpacePermissionEntity(spacePermissionsEntity, username,
							SpacePermission.CREATE_ATTACHMENT_PERMISSION, s);
					setUserSpacePermissionEntity(spacePermissionsEntity, username,
							SpacePermission.REMOVE_ATTACHMENT_PERMISSION, s);
					
					setUserSpacePermissionEntity(spacePermissionsEntity, username, SpacePermission.COMMENT_PERMISSION,
							s);
					setUserSpacePermissionEntity(spacePermissionsEntity, username,
							SpacePermission.REMOVE_COMMENT_PERMISSION, s);
					
					setUserSpacePermissionEntity(spacePermissionsEntity, username,
							SpacePermission.SET_PAGE_PERMISSIONS_PERMISSION, s);
					
					setUserSpacePermissionEntity(spacePermissionsEntity, username,
							SpacePermission.REMOVE_MAIL_PERMISSION, s);
					
					setUserSpacePermissionEntity(spacePermissionsEntity, username,
							SpacePermission.EXPORT_SPACE_PERMISSION, s);
					
					setUserSpacePermissionEntity(spacePermissionsEntity, username,
							SpacePermission.ADMINISTER_SPACE_PERMISSION, s);

					spacePermissions.add(spacePermissionsEntity);
				}
			}

			entity = new UserPermissionsEntity(spacePermissions);
		}

		return entity;
	}

	public void setPermissions(String targetUserName, UserPermissionsEntity userPermissionsEntity,
			boolean onlyUserPermissions) {
		// ConfluenceUser targetUserName =
		// userAccessor.getUserByName(targetUserName);

		List<SpacePermissionsEntity> spacePermissions = userPermissionsEntity.getSpacePermissions();

		for (SpacePermissionsEntity spe : spacePermissions) {
			boolean granted = false;

			if (spe.getPermissionStatus(SpacePermission.VIEWSPACE_PERMISSION)) {
				Space space = spaceManager.getSpace(spe.getSpaceKey());

				granted = setSpacePermissionForUser(spe, targetUserName, SpacePermission.VIEWSPACE_PERMISSION, space,
						onlyUserPermissions);
				granted = setSpacePermissionForUser(spe, targetUserName, SpacePermission.REMOVE_OWN_CONTENT_PERMISSION,
						space, onlyUserPermissions) ? true : granted;
				
				granted = setSpacePermissionForUser(spe, targetUserName, SpacePermission.CREATEEDIT_PAGE_PERMISSION,
						space, onlyUserPermissions) ? true : granted;
				granted = setSpacePermissionForUser(spe, targetUserName, SpacePermission.REMOVE_PAGE_PERMISSION, space,
						onlyUserPermissions) ? true : granted;
				
				granted = setSpacePermissionForUser(spe, targetUserName, SpacePermission.EDITBLOG_PERMISSION, space,
						onlyUserPermissions) ? true : granted;
				granted = setSpacePermissionForUser(spe, targetUserName, SpacePermission.REMOVE_BLOG_PERMISSION, space,
						onlyUserPermissions) ? true : granted;
				
				granted = setSpacePermissionForUser(spe, targetUserName, SpacePermission.CREATE_ATTACHMENT_PERMISSION,
						space, onlyUserPermissions) ? true : granted;
				granted = setSpacePermissionForUser(spe, targetUserName, SpacePermission.REMOVE_ATTACHMENT_PERMISSION,
						space, onlyUserPermissions) ? true : granted;
				
				granted = setSpacePermissionForUser(spe, targetUserName, SpacePermission.COMMENT_PERMISSION, space,
						onlyUserPermissions) ? true : granted;
				granted = setSpacePermissionForUser(spe, targetUserName, SpacePermission.REMOVE_COMMENT_PERMISSION,
						space, onlyUserPermissions) ? true : granted;
				
				granted = setSpacePermissionForUser(spe, targetUserName,
						SpacePermission.SET_PAGE_PERMISSIONS_PERMISSION, space, onlyUserPermissions) ? true : granted;
				
				granted = setSpacePermissionForUser(spe, targetUserName, SpacePermission.REMOVE_MAIL_PERMISSION, space,
						onlyUserPermissions) ? true : granted;
				
				granted = setSpacePermissionForUser(spe, targetUserName, SpacePermission.EXPORT_SPACE_PERMISSION, space,
						onlyUserPermissions) ? true : granted;
				granted = setSpacePermissionForUser(spe, targetUserName, SpacePermission.ADMINISTER_SPACE_PERMISSION,
						space, onlyUserPermissions) ? true : granted;

				if (granted) {
					// Need to set the view permission individually
					// if some other permission was added.
//					SpacePermission spacePermission = SpacePermission
//							.createUserSpacePermission(
//									SpacePermission.VIEWSPACE_PERMISSION,
//									space, targetUserName);
//					spacePermissionManager.savePermission(spacePermission);
				}
			}
		}
	}

	@SuppressWarnings("deprecation")
	private void setUserSpacePermissionEntity(
			SpacePermissionsEntity spacePermissionsEntity, String username,
			String spacePermissionType, Space space) {

		if (spacePermissionManager.hasPermission(spacePermissionType, space,
				userAccessor.getUser(username))) {
			boolean userPermission = false;

			List<SpacePermission> spacePermissions = space.getPermissions();
			for (SpacePermission spacePermission : spacePermissions) {
				if (!spacePermission.isUserPermission()) {
					continue;
				} else if (spacePermission.getUserName().equalsIgnoreCase(
						username)) {
					if (spacePermission.getType().equalsIgnoreCase(
							spacePermissionType)) {
						userPermission = true;
						break;
					}
				}
			}

			spacePermissionsEntity.setPermissionStatus(spacePermissionType,
					true, userPermission);
		} else {
			spacePermissionsEntity.setPermissionStatus(spacePermissionType,
					false, false);
		}
	}

	private boolean setSpacePermissionForUser(
			SpacePermissionsEntity spacePermissionsEntity, String username,
			String spacePermissionType, Space space, boolean onlyUserPermissions) {

		boolean granted = false;

		SpacePermissionEntity entity = spacePermissionsEntity
				.getSpacePermissionEntity(spacePermissionType);

		if (entity.isPermissionGranted()
				&& !spacePermissionManager.hasPermission(spacePermissionType,
						space, userAccessor.getUser(username))) {
			if (!onlyUserPermissions
					|| (onlyUserPermissions && entity.isUserPermission())) {
				@SuppressWarnings("deprecation")
				SpacePermission spacePermission = SpacePermission
						.createUserSpacePermission(spacePermissionType, space,
								username);
				spacePermissionManager.savePermission(spacePermission);
				granted = true;
			}
		}

		return granted;
	}
}
