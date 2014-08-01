function getPermissions() {
	return AJS.$.ajax({
		url : baseUrl + "/rest/userpermissions/1.0/"
				+ AJS.$("#usernameFrom").attr("value"),
		dataType : "json"
	});
}

function copyPermissions(permissions) {
	AJS.$.ajax({
		url : baseUrl + "/rest/userpermissions/1.0/"
				+ AJS.$("#usernameTo").attr("value"),
		type : "PUT",
		contentType : "application/json",
		data : JSON.stringify(permissions),
		processData : false,
	}).fail(function() {
		var $permissionResults = $("#copyStatus");
		$permissionResults.html('<h2>Copy operation failed</h2>');
	 }).success(function () {
		var $permissionResults = $("#copyStatus");
		$permissionResults.html('<h2>Copy operation was successful</h2>');
     });
}

function printHeader(permissions) {
	var $permissionResults = $("#results");
	$permissionResults.html("<h2 id='permissionsHeader'>" + AJS.$("#usernameFrom").attr("value") + "'s permissions in " + permissions.spacePermissions.length + " spaces" + "</h2>");

	var html = "";
	html += '<tbody>';
	html += '<tr>';
	html += '<th width="25%" class="permissionHeading">&nbsp;</th>';
	html += '<th class="permissionHeading">&nbsp;</th>';
	html += '<th class="permissionSuperTab" colspan="4">Pages</th>';
	html += '<th class="permissionSuperTab" colspan="2">Blog</th>';
	html += '<th class="permissionSuperTab" colspan="2">Comments</th>';
	html += '<th class="permissionSuperTab" colspan="2">Attachments</th>';
	html += '<th class="permissionSuperTab">Mail</th>';
	html += '<th class="permissionSuperTab" colspan="2">Space</th>';
	html += '</tr>';
	html += '<tr>';
	html += '<th class="permissionHeading">Space name</th>';
	html += '<th width="40" class="permissionSuperTab">View</th>';
	html += '<th width="40" class="permissionTab">Add</th>';
	html += '<th width="40" class="permissionTab">Export</th>';
	html += '<th width="40" class="permissionTab">Restrict</th>';
	html += '<th width="40" class="permissionTab">Remove</th>';
	html += '<th width="40" class="permissionTab">Add</th>';
	html += '<th width="40" class="permissionTab">Remove</th>';
	html += '<th width="40" class="permissionTab">Add</th>';
	html += '<th width="40" class="permissionTab">Remove</th>';
	html += '<th width="40" class="permissionTab">Add</th>';
	html += '<th width="40" class="permissionTab">Remove</th>';
	html += '<th width="40" class="permissionTab">Remove</th>';
	html += '<th width="40" class="permissionTab">Export</th>';
	html += '<th width="40" class="permissionTab">Admin</th>';
	html += '</tr>';

	$permissionResults.append(html);
}

function printPermissions(permissions) {
	var $permissionResults = $("#results");
	
	for (i = 0; i < permissions.spacePermissions.length; i++) {
		$permissionResults.append('<tr>');
		$permissionResults.append('<td>' + permissions.spacePermissions[i].spaceName + '</td>');
		printPermissionStatus(permissions.spacePermissions[i].permissions.viewSpace, "viewspace");
		printPermissionStatus(permissions.spacePermissions[i].permissions.createPage, "createpage");
		printPermissionStatus(permissions.spacePermissions[i].permissions.exportPage, "exportpage");
		printPermissionStatus(permissions.spacePermissions[i].permissions.setPagePermissions, "setpagepermissions");
		printPermissionStatus(permissions.spacePermissions[i].permissions.removePage, "removepage");
		printPermissionStatus(permissions.spacePermissions[i].permissions.createBlogPost, "createblogpost");
		printPermissionStatus(permissions.spacePermissions[i].permissions.removeBlog, "removeblog");
		printPermissionStatus(permissions.spacePermissions[i].permissions.comment, "comment");
		printPermissionStatus(permissions.spacePermissions[i].permissions.removeComment, "removecomment");
		printPermissionStatus(permissions.spacePermissions[i].permissions.createAttachment, "createattachment");
		printPermissionStatus(permissions.spacePermissions[i].permissions.removeAttachment, "removeattachment");
		printPermissionStatus(permissions.spacePermissions[i].permissions.removeMail, "removemail");
		printPermissionStatus(permissions.spacePermissions[i].permissions.exportSpace, "exportspace");
		printPermissionStatus(permissions.spacePermissions[i].permissions.editSpace, "editspace");
		$permissionResults.append('</tr>');
	}
	
	var html = "";
	html += '</tr>';
	html += '</tbody>';
	html += '</table>';
	
	$permissionResults.append(html);
}

function printPermissionStatus(status, permissionName) {
	var $permissionResults = $("#results");
	
	var html = "";
	html += '<td valign="middle" align="center" data-permission-set="' + status + '"';
	html += 'data-permission-group="" data-permission="' + permissionName + '"';
	html += 'class="permissionCell"><img width="16" height="16"';
	
	if (status == true) {
		html += 'src="/confluence/s/en_GB/3398/c7c65e1e0ae26ebdb7fdb329e831fd2c1a2ed938.1/_/images/icons/emoticons/check.png">';
	} else {
		html += 'src="/confluence/s/en_GB/3398/c7c65e1e0ae26ebdb7fdb329e831fd2c1a2ed938.1/_/images/icons/emoticons/error.png">';
	}
	
	html += '</td>';
	$permissionResults.append(html);
}

AJS.toInit(function() {
	AJS.$("#admin").submit(
			function(e) {
				e.preventDefault();

				var promise = getPermissions();
				promise.success(function(permissions) {
					copyPermissions(permissions);
					var $permissionResults = $("#results");
					printHeader(permissions);
					printPermissions(permissions);
//					$permissionResults.append("<div><pre>"
//							+ JSON.stringify(permissions, null, 4)
//							+ "</pre></div>");
				});
			});
});