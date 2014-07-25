package org.ttorvela.confluence.plugins.copyuserpermissions;

import java.util.ArrayList;
import java.util.List;

import com.atlassian.confluence.security.SpacePermission;
import com.atlassian.confluence.security.SpacePermissionManager;
import com.atlassian.confluence.spaces.Space;
import com.atlassian.confluence.spaces.SpaceManager;
import com.atlassian.confluence.user.ConfluenceUser;
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

	public UserPermissionsEntity getPermissionsEntity(String username) {
		ConfluenceUser user = userAccessor.getUserByName(username);

		List<Space> spaces = spaceManager.getAllSpaces();
		List<SpacePermissionsEntity> spacePermissions = new ArrayList<SpacePermissionsEntity>();

		SpacePermissionsEntity spacePermissionsEntity;

		for (Space s : spaces) {
			spacePermissionsEntity = new SpacePermissionsEntity(s.getName(),
					s.getKey());

			setUserSpacePermissionEntity(spacePermissionsEntity, user,
					SpacePermission.VIEWSPACE_PERMISSION, s);

			if (spacePermissionsEntity
					.getPermissionStatus(SpacePermission.VIEWSPACE_PERMISSION)) {
				setUserSpacePermissionEntity(spacePermissionsEntity, user,
						SpacePermission.CREATEEDIT_PAGE_PERMISSION, s);
				setUserSpacePermissionEntity(spacePermissionsEntity, user,
						SpacePermission.SET_PAGE_PERMISSIONS_PERMISSION, s);
				setUserSpacePermissionEntity(spacePermissionsEntity, user,
						SpacePermission.REMOVE_PAGE_PERMISSION, s);
				setUserSpacePermissionEntity(spacePermissionsEntity, user,
						SpacePermission.EDITBLOG_PERMISSION, s);
				setUserSpacePermissionEntity(spacePermissionsEntity, user,
						SpacePermission.REMOVE_BLOG_PERMISSION, s);
				setUserSpacePermissionEntity(spacePermissionsEntity, user,
						SpacePermission.COMMENT_PERMISSION, s);
				setUserSpacePermissionEntity(spacePermissionsEntity, user,
						SpacePermission.REMOVE_COMMENT_PERMISSION, s);
				setUserSpacePermissionEntity(spacePermissionsEntity, user,
						SpacePermission.CREATE_ATTACHMENT_PERMISSION, s);
				setUserSpacePermissionEntity(spacePermissionsEntity, user,
						SpacePermission.REMOVE_ATTACHMENT_PERMISSION, s);
				setUserSpacePermissionEntity(spacePermissionsEntity, user,
						SpacePermission.REMOVE_MAIL_PERMISSION, s);
				setUserSpacePermissionEntity(spacePermissionsEntity, user,
						SpacePermission.EXPORT_SPACE_PERMISSION, s);
				setUserSpacePermissionEntity(spacePermissionsEntity, user,
						SpacePermission.ADMINISTER_SPACE_PERMISSION, s);

				spacePermissions.add(spacePermissionsEntity);
			}
		}

		UserPermissionsEntity entity = new UserPermissionsEntity(
				spacePermissions);

		return entity;
	}

	public void setPermissions(String targetUserName,
			UserPermissionsEntity userPermissionsEntity) {
		ConfluenceUser userT = userAccessor.getUserByName(targetUserName);

		List<SpacePermissionsEntity> spacePermissions = userPermissionsEntity.getSpacePermissions();

		for (SpacePermissionsEntity spe : spacePermissions) {
			if (spe.getPermissionStatus(SpacePermission.VIEWSPACE_PERMISSION)) {
				Space space = spaceManager.getSpace(spe.getSpaceKey());
				setSpacePermissionForUser(spe, userT,
						SpacePermission.VIEWSPACE_PERMISSION, space);
				setSpacePermissionForUser(spe, userT,
						SpacePermission.CREATEEDIT_PAGE_PERMISSION, space);
				setSpacePermissionForUser(spe, userT,
						SpacePermission.SET_PAGE_PERMISSIONS_PERMISSION, space);
				setSpacePermissionForUser(spe, userT,
						SpacePermission.REMOVE_PAGE_PERMISSION, space);
				setSpacePermissionForUser(spe, userT,
						SpacePermission.EDITBLOG_PERMISSION, space);
				setSpacePermissionForUser(spe, userT,
						SpacePermission.REMOVE_BLOG_PERMISSION, space);
				setSpacePermissionForUser(spe, userT,
						SpacePermission.COMMENT_PERMISSION, space);
				setSpacePermissionForUser(spe, userT,
						SpacePermission.REMOVE_COMMENT_PERMISSION, space);
				setSpacePermissionForUser(spe, userT,
						SpacePermission.CREATE_ATTACHMENT_PERMISSION, space);
				setSpacePermissionForUser(spe, userT,
						SpacePermission.REMOVE_ATTACHMENT_PERMISSION, space);
				setSpacePermissionForUser(spe, userT,
						SpacePermission.REMOVE_MAIL_PERMISSION, space);
				setSpacePermissionForUser(spe, userT,
						SpacePermission.EXPORT_SPACE_PERMISSION, space);
				setSpacePermissionForUser(spe, userT,
						SpacePermission.ADMINISTER_SPACE_PERMISSION, space);
			}
		}
	}

	private void setUserSpacePermissionEntity(
			SpacePermissionsEntity spacePermissionsEntity, ConfluenceUser user,
			String spacePermissionType, Space space) {

		if (spacePermissionManager.hasPermission(spacePermissionType, space,
				user)) {
			spacePermissionsEntity.setPermissionStatus(spacePermissionType,
					true);
		}
	}

	private void setSpacePermissionForUser(
			SpacePermissionsEntity spacePermissionsEntity, ConfluenceUser user,
			String spacePermissionType, Space space) {

		if (spacePermissionsEntity.getPermissionStatus(spacePermissionType)) {
			SpacePermission spacePermission = SpacePermission
					.createUserSpacePermission(spacePermissionType, space, user);
			spacePermissionManager.savePermission(spacePermission);
		}
	}

}
