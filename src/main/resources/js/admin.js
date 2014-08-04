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
	var html = "";
	
	
	for (i = 0; i < permissions.spacePermissions.length; i++) {
		if (i > 0 && i % 2 == 1) {
			html += '<tr style=" background: #f0f0f0; ">';
		} else {
			html += '<tr>';
		}
		html += '<td>' + permissions.spacePermissions[i].spaceName + '</td>';
		html += printPermissionStatus(permissions.spacePermissions[i].permissions.viewSpace, "viewspace");
		html += printPermissionStatus(permissions.spacePermissions[i].permissions.createPage, "createpage");
		html += printPermissionStatus(permissions.spacePermissions[i].permissions.exportPage, "exportpage");
		html += printPermissionStatus(permissions.spacePermissions[i].permissions.setPagePermissions, "setpagepermissions");
		html += printPermissionStatus(permissions.spacePermissions[i].permissions.removePage, "removepage");
		html += printPermissionStatus(permissions.spacePermissions[i].permissions.createBlogPost, "createblogpost");
		html += printPermissionStatus(permissions.spacePermissions[i].permissions.removeBlog, "removeblog");
		html += printPermissionStatus(permissions.spacePermissions[i].permissions.comment, "comment");
		html += printPermissionStatus(permissions.spacePermissions[i].permissions.removeComment, "removecomment");
		html += printPermissionStatus(permissions.spacePermissions[i].permissions.createAttachment, "createattachment");
		html += printPermissionStatus(permissions.spacePermissions[i].permissions.removeAttachment, "removeattachment");
		html += printPermissionStatus(permissions.spacePermissions[i].permissions.removeMail, "removemail");
		html += printPermissionStatus(permissions.spacePermissions[i].permissions.exportSpace, "exportspace");
		html += printPermissionStatus(permissions.spacePermissions[i].permissions.editSpace, "editspace");
		html += '</tr>';
	}
	
	
	html += '</tbody>';
	html += '</table>';
	
	var $permissionResults = $("#results");
	$permissionResults.append(html);
}

function printPermissionStatus(status, permissionName) {
	
	var html = "";
	html += '<td valign="middle" align="center" data-permission-set="' + status + '"';
	html += 'data-permission-group="" data-permission="' + permissionName + '"';
	html += 'class="permissionCell"><img width="16" height="16"';
	
	if (status == true) {
		html += 'src="' + AJS.params.staticResourceUrlPrefix + '/images/icons/emoticons/check.png">';
	} else {
		html += 'src="' + AJS.params.staticResourceUrlPrefix + '/images/icons/emoticons/error.png">';
	}
	
	html += '</td>';
	return html;
}

AJS.toInit(function() {
	
	//TODO: Get this working from a separate function!!!
	AJS.$("#usernameTo").live('input paste',function() {
		if (AJS.$("#viewButton").is(":visible") && AJS.$("#usernameTo").attr("value").length > 0 && AJS.$("#usernameFrom").attr("value").length > 0 ) {
			AJS.$("#viewButton").hide();
			AJS.$("#copyButton").show();
		} else if (AJS.$("#copyButton").is(":visible") && (AJS.$("#usernameTo").attr("value").length == 0 || AJS.$("#usernameFrom").attr("value").length == 0 )) {
			AJS.$("#copyButton").hide();
			AJS.$("#viewButton").show();
		}});
	
	AJS.$("#usernameFrom").live('input paste',function() {
		if (AJS.$("#viewButton").is(":visible") && AJS.$("#usernameTo").attr("value").length > 0 && AJS.$("#usernameFrom").attr("value").length > 0 ) {
			AJS.$("#viewButton").hide();
			AJS.$("#copyButton").show();
		} else if (AJS.$("#copyButton").is(":visible") && (AJS.$("#usernameTo").attr("value").length == 0 || AJS.$("#usernameFrom").attr("value").length == 0 )) {
			AJS.$("#copyButton").hide();
			AJS.$("#viewButton").show();
		}});

	AJS.$("#admin").submit(
			function(e) {
				e.preventDefault();

				var promise = getPermissions();
				promise.success(function(permissions) {
					if (AJS.$("#usernameTo").attr("value").length > 0) {
						copyPermissions(permissions);
					} else {
						var $permissionResults = $("#copyStatus");
						$permissionResults.html('');
					}
					var $permissionResults = $("#results");
					printHeader(permissions);
					printPermissions(permissions);
					//					$permissionResults.append("<div><pre>"
					//							+ JSON.stringify(permissions, null, 4)
					//							+ "</pre></div>");
				});
			});
});