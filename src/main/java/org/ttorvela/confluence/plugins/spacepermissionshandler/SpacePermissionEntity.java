package org.ttorvela.confluence.plugins.spacepermissionshandler;

import javax.xml.bind.annotation.XmlElement;

public class SpacePermissionEntity {
	@XmlElement
	private String permissionType;

	@XmlElement
	private boolean permissionGranted;

	@XmlElement
	private boolean userPermission;

	@XmlElement
	public boolean isUserPermission() {
		return userPermission;
	}

	public void setUserPermission(boolean userPermission) {
		this.userPermission = userPermission;
	}

	public String getPermissionType() {
		return permissionType;
	}

	public void setPermissionType(String permissionType) {
		this.permissionType = permissionType;
	}

	public boolean isPermissionGranted() {
		return permissionGranted;
	}

	public void setPermissionGranted(boolean permissionGranted) {
		this.permissionGranted = permissionGranted;
	}

	public SpacePermissionEntity() {

	}

	public SpacePermissionEntity(String permissionType,
			boolean permissionGranted, boolean userPermission) {
		this.permissionType = permissionType;
		this.permissionGranted = permissionGranted;
		this.userPermission = userPermission;
	}
}
