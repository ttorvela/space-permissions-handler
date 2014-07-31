package org.ttorvela.confluence.plugins.copyuserpermissions;

import javax.xml.bind.annotation.XmlElement;

import com.atlassian.confluence.security.SpacePermission;

public class SpacePermissionsEntity {
	@XmlElement
	private String spaceName;
	
	@XmlElement
	private String spaceKey;

	@XmlElement(name = "permissions")
	private SpacePermissionEntity permissions;

	public SpacePermissionsEntity(String spaceName, String spaceKey) {
		this.spaceName = spaceName;
		this.spaceKey = spaceKey;
		permissions = new SpacePermissionEntity();
	}

	public SpacePermissionsEntity(String spaceName,
			String spaceKey, SpacePermissionEntity permissions) {
		this.spaceName = spaceName;
		this.spaceKey = spaceKey;
		this.permissions = permissions;
	}
	
	public SpacePermissionsEntity() {
	}
	
	@SuppressWarnings("deprecation")
	public void setPermissionStatus(String permission, boolean status) {
		if (permission.equalsIgnoreCase(SpacePermission.SET_PAGE_PERMISSIONS_PERMISSION)) {
			permissions.setSetPagePermissions(status);
		} else if (permission.equalsIgnoreCase(SpacePermission.VIEWSPACE_PERMISSION)) {
			permissions.setViewSpace(status);
		} else if (permission.equalsIgnoreCase(SpacePermission.EXPORT_PAGE_PERMISSION)) {
			permissions.setExportPage(status);
		} else if (permission.equalsIgnoreCase(SpacePermission.ADMINISTER_SPACE_PERMISSION)) {
			permissions.setEditSpace(status);
		} else if (permission.equalsIgnoreCase(SpacePermission.COMMENT_PERMISSION)) {
			permissions.setComment(status);
		} else if (permission.equalsIgnoreCase(SpacePermission.CREATE_ATTACHMENT_PERMISSION)) {
			permissions.setCreateAttachment(status);
		} else if (permission.equalsIgnoreCase(SpacePermission.CREATEEDIT_PAGE_PERMISSION)) {
			permissions.setCreatePage(status);
		} else if (permission.equalsIgnoreCase(SpacePermission.EDITBLOG_PERMISSION)) {
			permissions.setCreateBlogPost(status);
		} else if (permission.equalsIgnoreCase(SpacePermission.EXPORT_SPACE_PERMISSION)) {
			permissions.setExportSpace(status);
		} else if (permission.equalsIgnoreCase(SpacePermission.REMOVE_MAIL_PERMISSION)) {
			permissions.setRemoveMail(status);
		} else if (permission.equalsIgnoreCase(SpacePermission.REMOVE_ATTACHMENT_PERMISSION)) {
			permissions.setRemoveAttachment(status);
		} else if (permission.equalsIgnoreCase(SpacePermission.REMOVE_BLOG_PERMISSION)) {
			permissions.setRemoveBlog(status);
		} else if (permission.equalsIgnoreCase(SpacePermission.REMOVE_COMMENT_PERMISSION)) {
			permissions.setRemoveComment(status);
		} else if (permission.equalsIgnoreCase(SpacePermission.REMOVE_PAGE_PERMISSION)) {
			permissions.setRemovePage(status);
		}
	}
	
	@SuppressWarnings("deprecation")
	public boolean getPermissionStatus(String permission) {
		boolean status = false;
		
		if (permission.equalsIgnoreCase(SpacePermission.SET_PAGE_PERMISSIONS_PERMISSION)) {
			status = permissions.getSetPagePermissions();
		} else if (permission.equalsIgnoreCase(SpacePermission.VIEWSPACE_PERMISSION)) {
			status = permissions.getViewSpace();
		} else if (permission.equalsIgnoreCase(SpacePermission.EXPORT_PAGE_PERMISSION)) {
			status = permissions.getExportPage();
		} else if (permission.equalsIgnoreCase(SpacePermission.ADMINISTER_SPACE_PERMISSION)) {
			status = permissions.getEditSpace();
		} else if (permission.equalsIgnoreCase(SpacePermission.COMMENT_PERMISSION)) {
			status = permissions.getComment();
		} else if (permission.equalsIgnoreCase(SpacePermission.CREATE_ATTACHMENT_PERMISSION)) {
			status = permissions.getCreateAttachment();
		} else if (permission.equalsIgnoreCase(SpacePermission.CREATEEDIT_PAGE_PERMISSION)) {
			status = permissions.getCreatePage();
		} else if (permission.equalsIgnoreCase(SpacePermission.EDITBLOG_PERMISSION)) {
			status = permissions.getCreateBlogPost();
		} else if (permission.equalsIgnoreCase(SpacePermission.EXPORT_SPACE_PERMISSION)) {
			status = permissions.getExportSpace();
		} else if (permission.equalsIgnoreCase(SpacePermission.REMOVE_MAIL_PERMISSION)) {
			status = permissions.getRemoveMail();
		} else if (permission.equalsIgnoreCase(SpacePermission.REMOVE_ATTACHMENT_PERMISSION)) {
			status = permissions.getRemoveAttachment();
		} else if (permission.equalsIgnoreCase(SpacePermission.REMOVE_BLOG_PERMISSION)) {
			status = permissions.getRemoveBlog();
		} else if (permission.equalsIgnoreCase(SpacePermission.REMOVE_COMMENT_PERMISSION)) {
			status = permissions.getRemoveComment();
		} else if (permission.equalsIgnoreCase(SpacePermission.REMOVE_PAGE_PERMISSION)) {
			status = permissions.getRemovePage();
		}
		
		return status;
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

	public SpacePermissionEntity getPermissions() {
		return permissions;
	}

	public void setPermissions(SpacePermissionEntity permissions) {
		this.permissions = permissions;
	}

}
