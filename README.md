Space Permissions Handler for Confluence
========================================

Space Permissions Handler helps copying space permissions from one user to another. It also allows viewing user's space permissions.

Purpose of the plug-in is to provide admin UI to view and copy Confluence user's space permissions to another Confluence user. By default source user's permissions that come from group memberships are copied to the target user as user permissions. These group permissions can also be ignored in the copy process. Plug-in also exposes a new REST interface to get user's all space permissions and to set user's all space permissions.

REST-API
--------

### GET returns users all space permissions as JSON:
**/rest/userpermissions/1.0/{user-id}**
`{
	"spacePermissions": [
	{
		"spaceName":"Confluence Latest",
		"spaceKey":"DOC",
		"permissions":[
		{
          "permissionType": "VIEWSPACE",
          "permissionGranted": true,
          "userPermission": false
        },
        {
          "permissionType": "REMOVEOWNCONTENT",
          "permissionGranted": true,
          "userPermission": false
        },
        {
          "permissionType": "EDITSPACE",
          "permissionGranted": true,
          "userPermission": false
        },
        {
          "permissionType": "REMOVEPAGE",
          "permissionGranted": true,
          "userPermission": false
        },
        {
          "permissionType": "EDITBLOG",
          "permissionGranted": true,
          "userPermission": false
        },
        {
          "permissionType": "REMOVEBLOG",
          "permissionGranted": true,
          "userPermission": false
        },
        {
          "permissionType": "CREATEATTACHMENT",
          "permissionGranted": true,
          "userPermission": false
        },
        {
          "permissionType": "REMOVEATTACHMENT",
          "permissionGranted": true,
          "userPermission": false
        },
        {
          "permissionType": "COMMENT",
          "permissionGranted": true,
          "userPermission": false
        },
        {
          "permissionType": "REMOVECOMMENT",
          "permissionGranted": true,
          "userPermission": false
        },
        {
          "permissionType": "SETPAGEPERMISSIONS",
          "permissionGranted": true,
          "userPermission": false
        },
        {
          "permissionType": "REMOVEMAIL",
          "permissionGranted": true,
          "userPermission": false
        },
        {
          "permissionType": "EXPORTSPACE",
          "permissionGranted": true,
          "userPermission": false
        },
        {
          "permissionType": "SETSPACEPERMISSIONS",
          "permissionGranted": true,
          "userPermission": false
        }
		]
	}
	]
}`

### PUT updates user's permissions according to payload
**/rest/userpermissions/1.0/{user-id}?onlyUserPermissions={true/false}**

JSON payload for HTTP PUT is in same format as the payload received from HTTP GET. Example of this format is shown above.

HELP
====

Plugin's User Guide:
https://github.com/ttorvela/space-permissions-handler/wiki

Atlassian Plugin SDK:
https://developer.atlassian.com/display/DOCS/Introduction+to+the+Atlassian+Plugin+SDK

Debugging a Confluence plugin:
https://developer.atlassian.com/display/DOCS/Creating+a+Remote+Debug+Target

- Start from command prompt with atlas-debug and in Eclipse using 5005 port with debug profile.

Learn and use enableQuickReload
- Make changes to the source files and run atlas-mvn package. Check from command prompt that is running atlas-debug that reload was successful.

CURRENT STATUS
==============

20.8.2018
---------

Changed: Space Permissions Handler menu item is now in Admin Console's Security section.

7.2.2017
--------

Fixed: View permission was added to a user several times when permissions were copied. Later when admin wanted to remove all user permissions from a user that had to be done several times to clean the hidden view permissions.
Created: Branch for 1.x that contains releases that should work with 4.3.7 - 5.9.X

5.2.2017
--------

Added: Support for Confluence 6.x: Remove own permission added.
Removed: Support for Confluence 5.X and older.
Added: Support for quick reload.
Not fixed: Lots of deprecated APIs still in use

22.8.2015
--------

Released 1.3 version. Only updated pom.xml to support Confluence 5.8.9 version and removed test data from pom.xml.


31.3.2015
---------

Released 1.2 version.

Added: Use SOY templates (Google Closure) to generate permission tables.
Added: Use separate graphic for group permissions instead of * character.
Fixed: Images do not probably show correctly in localized Confluence installation. (Needs to be confirmed).

7.3.2015
--------

Released 1.1 version. Only updated pom.xml to support Confluence 5.7 version and removed test data from pom.xml.

29.9.2014
---------

Created user guide to the wiki and updated README.md.

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
