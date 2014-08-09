package org.ttorvela.confluence.plugins.spacepermissionshandler;

import javax.xml.bind.annotation.XmlElement;

public class SpacePermissionEntity {	
	@XmlElement
	private boolean viewSpace;
	
	@XmlElement
	private boolean createPage;
	@XmlElement
	private boolean exportPage;
	@XmlElement
	private boolean setPagePermissions;
	@XmlElement
	private boolean removePage;
	
	
	@XmlElement
	private boolean createBlogPost;
	@XmlElement
	private boolean removeBlog;
	
	@XmlElement
	private boolean comment;
	@XmlElement
	private boolean removeComment;
	
	@XmlElement
	private boolean createAttachment;
	@XmlElement
	private boolean removeAttachment;
	
	@XmlElement
	private boolean removeMail;
	
	@XmlElement
	private boolean exportSpace;
	@XmlElement
	private boolean editSpace; // Admin space	
	
	
	public boolean getViewSpace() {
		return viewSpace;
	}

	public void setViewSpace(boolean viewSpace) {
		this.viewSpace = viewSpace;
	}

	public boolean getComment() {
		return comment;
	}

	public void setComment(boolean comment) {
		this.comment = comment;
	}

	public boolean getEditSpace() {
		return editSpace;
	}

	public void setEditSpace(boolean editSpace) {
		this.editSpace = editSpace;
	}

	public boolean getCreatePage() {
		return createPage;
	}

	public void setCreatePage(boolean createPage) {
		this.createPage = createPage;
	}
	
	public boolean getExportPage() {
		return exportPage;
	}

	public void setExportPage(boolean exportPage) {
		this.exportPage = exportPage;
	}

	public boolean getCreateBlogPost() {
		return createBlogPost;
	}

	public void setCreateBlogPost(boolean createBlogPost) {
		this.createBlogPost = createBlogPost;
	}

	public boolean getRemovePage() {
		return removePage;
	}

	public void setRemovePage(boolean removePage) {
		this.removePage = removePage;
	}

	public boolean getRemoveComment() {
		return removeComment;
	}

	public void setRemoveComment(boolean removeComment) {
		this.removeComment = removeComment;
	}

	public boolean getRemoveBlog() {
		return removeBlog;
	}

	public void setRemoveBlog(boolean removeBlog) {
		this.removeBlog = removeBlog;
	}

	public boolean getCreateAttachment() {
		return createAttachment;
	}

	public void setCreateAttachment(boolean createAttachment) {
		this.createAttachment = createAttachment;
	}

	public boolean getRemoveAttachment() {
		return removeAttachment;
	}

	public void setRemoveAttachment(boolean removeAttachment) {
		this.removeAttachment = removeAttachment;
	}

	public boolean getExportSpace() {
		return exportSpace;
	}

	public void setExportSpace(boolean exportSpace) {
		this.exportSpace = exportSpace;
	}

	public boolean getRemoveMail() {
		return removeMail;
	}

	public void setRemoveMail(boolean removeMail) {
		this.removeMail = removeMail;
	}

	public boolean getSetPagePermissions() {
		return setPagePermissions;
	}

	public void setSetPagePermissions(boolean setPagePermissions) {
		this.setPagePermissions = setPagePermissions;
	}
	
	public SpacePermissionEntity() {

	}
}
