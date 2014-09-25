package org.ttorvela.confluence.plugins.spacepermissionshandler;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;

public class SpacePermissionsEntity {
	@XmlElement
	private String spaceName;

	@XmlElement
	private String spaceKey;

	@XmlElement(name = "permissions")
	private List<SpacePermissionEntity> permissions;

	@XmlElement(name = "userPermissions")
	private SpacePermissionEntity userPermissions;

	public SpacePermissionsEntity(String spaceName, String spaceKey) {
		this.spaceName = spaceName;
		this.spaceKey = spaceKey;
		permissions = new ArrayList<SpacePermissionEntity>();
	}

	public SpacePermissionsEntity(String spaceName, String spaceKey,
			SpacePermissionEntity permissions) {
		this.spaceName = spaceName;
		this.spaceKey = spaceKey;
		this.permissions = new ArrayList<SpacePermissionEntity>();
	}

	public SpacePermissionsEntity() {
	}

	public void setPermissionStatus(String permission, boolean status,
			boolean userPermission) {
		boolean found = false;

		for (SpacePermissionEntity sPe : permissions) {
			if (sPe.getPermissionType().equalsIgnoreCase(permission)) {
				sPe.setPermissionGranted(status);
				sPe.setUserPermission(userPermission);
				found = true;
			}
		}

		if (!found) {
			SpacePermissionEntity sPEntity = new SpacePermissionEntity(
					permission, status, userPermission);
			permissions.add(sPEntity);
		}
	}

	public boolean getPermissionStatus(String permission) {
		boolean status = false;

		for (SpacePermissionEntity sPe : permissions) {
			if (sPe.getPermissionType().equalsIgnoreCase(permission)) {
				status = sPe.isPermissionGranted();
			}
		}

		return status;
	}
	
	public SpacePermissionEntity getSpacePermissionEntity(String permission) {
		SpacePermissionEntity entity = null;

		for (SpacePermissionEntity sPe : permissions) {
			if (sPe.getPermissionType().equalsIgnoreCase(permission)) {
				entity = sPe;
			}
		}

		return entity;
	}

	public String getSpaceName() {
		return spaceName;
	}

	public void setSpaceName(String spaceName) {
		this.spaceName = spaceName;
	}

	public String getSpaceKey() {
		return spaceKey;
	}

	public void setSpaceKey(String spaceKey) {
		this.spaceKey = spaceKey;
	}

	public List<SpacePermissionEntity> getPermissions() {
		return permissions;
	}

	public void setPermissions(List<SpacePermissionEntity> permissions) {
		this.permissions = permissions;
	}

}
