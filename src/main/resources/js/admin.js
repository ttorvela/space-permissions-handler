

function getPermissions() {
	return AJS.$.ajax({
		url : baseUrl + "/rest/userpermissions/1.0/"
				+ AJS.escapeHtml(AJS.$("#usernameFrom").attr("value")),
		dataType : "json"
	});
}

function copyPermissions(permissions, onlyUserPermissions) {
	var checked = false;
	
	if (onlyUserPermissions == "checked") {
		checked = true;
	}
	
	AJS.$.ajax({
		url : baseUrl + "/rest/userpermissions/1.0/"
				+ AJS.escapeHtml(AJS.$("#usernameTo").attr("value")) + "?onlyUserPermissions=" + checked,
		type : "PUT",
		contentType : "application/json",
		data : JSON.stringify(permissions),
		processData : false,
	}).fail(function() {
		var $copyStatus = $("#copyStatus");
		$copyStatus.html('<h2>Copy operation failed</h2>');
		$("#results").html('');
	 }).success(function () {
		var $copyStatus = $("#copyStatus");
		$copyStatus.html('<h2>Copy operation was successful</h2>');
     });
}

function showSpinner() {
	killSpinner = Raphael.spinner("throbber", 60, "#666");
	$("#throbber").removeClass("hidden");
}

AJS.toInit(function() {
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
				
				var $permissionResults = $("#results");
				$permissionResults.html('');
				if (AJS.$("#usernameFrom").attr("value").length == 0) {
					var $copyStatus = $("#copyStatus");
					$copyStatus.html('<h2>Operation failed. From username is empty.</h2>');
				} else if (AJS.$("#usernameFrom").attr("value") == AJS.$("#usernameTo").attr("value") ) {
					var $copyStatus = $("#copyStatus");
					$copyStatus.html('<h2>Operation failed. Same usernames.</h2>');	
				} else if (AJS.$("#usernameFrom").attr("value").length > 0) {
					$("#throbber").html('');
					var t = setTimeout("showSpinner()", 100);
					
					var promise = getPermissions();
					promise.success(function(permissions) {
						if (AJS.$("#usernameTo").attr("value").length > 0) {
							copyPermissions(permissions, AJS.$("#copyOnlyUserPermissions").attr("checked"));
						} else {
							var $copyStatus= $("#copyStatus");
							$copyStatus.html('');
						}

						var ver5 = versionNumber.substring(0, 1) == "5";
						
						var html = Confluence.Templates.SpacePermissionsHandler.listSpacePermissions(
								{permissions: permissions, userFrom: AJS.$("#usernameFrom").attr("value"),
								 version5: ver5, emoticonsUrl: AJS.params.staticResourceUrlPrefix + "/images/icons/emoticons"});
						
						var $permissionResults = $("#results");
						$permissionResults.append(html);
						
					});
					promise.fail(function(permissions) {
						var $copyStatus = $("#copyStatus");
						$copyStatus.html('<h2>Operation failed. User not found?</h2>');
					});
					promise.complete(function() {
						clearTimeout(t);
						//killSpinner();
						$("#throbber").addClass("hidden");
					});
				}
			});
});