Copy user permissions Confluence plug-in
========================================

Purpose of this plug-in is to provide admin UI to copy Confluence user's space permissions to another Confluence user. Plug-in also exposes a new REST interface to get user's all space permissions and to set user's all space permissions.

REST-API
--------

**/userpermissions/{user-id} [GET, PUT]**
- GET returns users all space permissions as JSON:
{
	"spacePermissions": [
		{
			"space": "spaceKey1",
			"permissions": {
				"REMOVE_PAGE_PERMISSION": false,
				"ADMINISTER_SPACE_PERMISSION": false,
				"COMMENT_PERMISSION": true,
				"CREATE_ATTACHMENT_PERMISSION": true,
				"CREATEEDIT_PAGE_PERMISSION": true,
				"EDITBLOG_PERMISSION": true,
				"EXPORT_SPACE_PERMISSION": true,
				"REMOVE_ATTACHMENT_PERMISSION": true,
				"REMOVE_BLOG_PERMISSION": true,
				"REMOVE_COMMENT_PERMISSION": true,
				"REMOVE_MAIL_PERMISSION": true,
				"REMOVE_PAGE_PERMISSION": true,
				"SET_PAGE_PERMISSIONS_PERMISSION": true,
				"VIEWSPACE_PERMISSION": true
				}
		},
		{
			"space": "spaceKey2",
			  "permissions": {
				"REMOVE_PAGE_PERMISSION": false,
				"ADMINISTER_SPACE_PERMISSION": false,
				"COMMENT_PERMISSION": true,
				"CREATE_ATTACHMENT_PERMISSION": true,
				"CREATEEDIT_PAGE_PERMISSION": true,
				"EDITBLOG_PERMISSION": true,
				"EXPORT_SPACE_PERMISSION": true,
				"REMOVE_ATTACHMENT_PERMISSION": true,
				"REMOVE_BLOG_PERMISSION": true,
				"REMOVE_COMMENT_PERMISSION": true,
				"REMOVE_MAIL_PERMISSION": true,
				"REMOVE_PAGE_PERMISSION": true,
				"SET_PAGE_PERMISSIONS_PERMISSION": true,
				"VIEWSPACE_PERMISSION": true
				}
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

TODO
====

1. Fix TODOs from the code.
2. Plugin screenshots.
3. Merge 4.3.7 changes to the master branch.