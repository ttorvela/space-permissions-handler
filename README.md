Space Permissions Handler for Confluence
========================================

Space Permissions Handler helps copying space permissions from one user to another. It also allows viewing user's space permissions.

Purpose of the plug-in is to provide admin UI to view and copy Confluence user's space permissions to another Confluence user. By default source user's permissions that come from group memberships are copied to the target user as user permissions. These group permissions can also be ignored in the copy process. Plug-in also exposes a new REST interface to get user's all space permissions and to set user's all space permissions.

REST-API
--------

**/rest/userpermissions/1.0/{user-id} [GET, PUT]**
- GET returns users all space permissions as JSON:
{
	"spacePermissions": [
	{
		"spaceName":"Confluence Latest",
		"spaceKey":"DOC",
		"permissions":[
		{
			"permissionType":"VIEWSPACE",
			"permissionGranted":true,
			"userPermission":false
		},
		{
			"permissionType":"EDITSPACE",
			"permissionGranted":true,
			"userPermission":false
		},
		{
			"permissionType":"EXPORTPAGE",
			"permissionGranted":true,
			"userPermission":false
		},
		{
			"permissionType":"SETPAGEPERMISSIONS",
			"permissionGranted":true,
			"userPermission":false
		},
		{
			"permissionType":"REMOVEPAGE",
			"permissionGranted":true,
			"userPermission":false
		},
		{
			"permissionType":"EDITBLOG",
			"permissionGranted":true,
			"userPermission":false
		},
		{
			"permissionType":"REMOVEBLOG",
			"permissionGranted":true,
			"userPermission":false
		},
		{
			"permissionType":"COMMENT",
			"permissionGranted":true,
			"userPermission":false
		},
		{
			"permissionType":"REMOVECOMMENT",
			"permissionGranted":true,
			"userPermission":false
		},
		{
			"permissionType":"CREATEATTACHMENT",
			"permissionGranted":true,
			"userPermission":false
		},
		{
			"permissionType":"REMOVEATTACHMENT",
			"permissionGranted":true,
			"userPermission":false
		},
		{
			"permissionType":"REMOVEMAIL",
			"permissionGranted":true,
			"userPermission":false
		},
		{
			"permissionType":"EXPORTSPACE",
			"permissionGranted":true,
			"userPermission":false
		},
		{
			"permissionType":"SETSPACEPERMISSIONS",
			"permissionGranted":true,
			"userPermission":false
		}]
	}
	]
}
- PUT updates user's permissions according to payload

HELP
====

Atlassian Plugin SDK:
https://developer.atlassian.com/display/DOCS/Introduction+to+the+Atlassian+Plugin+SDK

Debugging a Confluence plugin:
https://developer.atlassian.com/display/DOCS/Creating+a+Remote+Debug+Target

- Start from command prompt with atlas-debug and in Eclipse using 5005 port with debug profile.

Learn and use FastDev:
https://developer.atlassian.com/display/DOCS/Automatic+Plugin+Reinstallation+with+FastDev

- Make changes to the source files and reload the changes with FastDev from development environment Confluence instance from browser. It will compile and deploy the result to the running Confluence. There is no need to restart Confluence. Javascript and Velocity changes do not need to be redeployed. You will just need to reload the page.

CURRENT STATUS
==============

26.9.2014
---------

Removed TODOs from the code.
Updated: Plugin screenshots.


25.9.2014
---------

Added: Show which permissions are group permissions by displaying * character.
Added: Copy only user permissions option (still not working correctly).
Fixed: Export page and edit page permissions were mixed up in the UI.
Finished earlier: Merge 4.3.7 changes to the master branch.
Finished earlier: Rename plugin to Space Permissions Handler for Confluence

13.8.2014
---------

Merged changes from the master branch. Using 4.3.7 UI style in this branch.

9.8.2014
--------

Plugin will be renamed to Space Permissions Handler for Confluence

6.8.2014
--------

Branch 4.3.7 is starting to look good. Changes from that should be merged to master branch for latest Confluence version.

Added: Auto complete for username fields.
Fixed: Several error situations and error handling on UI and service levels.

4.8.2014
--------

Implemented a better UI to show permissions that were copied to the target user.
Implemented UI to display one user's permissions. No copying would be done in this use case.

30.7.2014
---------

Quick and dirty UI added that displays permissions found from the user whose permissions would be copied.

29.7.2014
---------

It is finally working. Extensive tests have not been performed but at least with test users in test environment it is possible to specify a user whose permissions will be copied to another user. If the source user has permission via groups those permissions will be added to the target user as individual permissions even when the target user belongs to the same groups.

There is no UI yet that would display wether the copying was successful or not!
