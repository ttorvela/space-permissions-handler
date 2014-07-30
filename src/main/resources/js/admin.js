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
		processData : false
	});
}

AJS.toInit(function() {
	AJS.$("#admin").submit(
			function(e) {
				e.preventDefault();

				var promise = getPermissions();
				promise.success(function(permissions) {
					copyPermissions(permissions);
					var $permissionResults = $("#results");
					$permissionResults.append("<h2 id='permissions'>Permissions copied</h2>");
					$permissionResults.append("<div><pre>"
							+ JSON.stringify(permissions, null, 4)
							+ "</pre></div>");
				});
			});
});