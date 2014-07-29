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
	AJS.$("#admin").submit(function(e) {
		e.preventDefault();

		var promise = getPermissions();
		promise.success(function (permissions) {
			copyPermissions(permissions);
		});
	});
});