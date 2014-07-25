package org.ttorvela.confluence.plugins.copyuserpermissions;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import java.util.List;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class UserPermissionsEntity {
	
	@XmlElement(name = "spacePermissions")
	private List<SpacePermissionsEntity> spacePermissions;

	public List<SpacePermissionsEntity> getSpacePermissions() {
		return spacePermissions;
	}

	public void setSpacePermissions(List<SpacePermissionsEntity> spacePermissions) {
		this.spacePermissions = spacePermissions;
	}

	public UserPermissionsEntity(List<SpacePermissionsEntity> spacePermissions) {
		this.spacePermissions = spacePermissions;
	}

	public UserPermissionsEntity() {
		
	}
}
