AJS.toInit(function() {
	
	function getPermissions() {
		AJS.$.ajax({
			url : baseUrl + "/rest/userpermissions/1.0/"
					+ AJS.$("#usernameFrom").attr("value"),
			dataType : "json",
			success : function(permissions) {
				AJS.$("#results").html(permissions);
				copyPermissions(permissions);
			}
		});
	}
	
	function getAndCopy() {
		AJS.$.ajax({
			url : baseUrl + "/rest/userpermissions/1.0/"
					+ AJS.$("#usernameFrom").attr("value"),
			dataType : "json",
			success : function(permissions) {
				copyPermissions(permissions);
			}
		});
	}
	
	// http://tepssups-pc:1990/confluence/rest/userpermissions/1.0/user1
	//TODO: Includes example test values!
	function copyPermissions(permissions) {
		AJS.$.ajax({
			url : baseUrl + "/rest/userpermissions/1.0/"
					+ AJS.$("#usernameTo").attr("value"),
			type : "PUT",
			contentType : "application/json",
			//data : '{"spacePermissions":[{"spaceName":"Demonstration Space","spaceKey":"ds","permissions":{"viewSpace":true,"createPage":true,"setPagePermissions":true,"removePage":true,"createBlogPost":true,"removeBlog":true,"comment":true,"removeComment":true,"createAttachment":true,"removeAttachment":true,"removeMail":true,"exportSpace":true,"editSpace":false}},{"spaceName":"NoRights","spaceKey":"NOR","permissions":{"viewSpace":true,"createPage":true,"setPagePermissions":false,"removePage":false,"createBlogPost":true,"removeBlog":false,"comment":true,"removeComment":false,"createAttachment":true,"removeAttachment":false,"removeMail":false,"exportSpace":false,"editSpace":false}},{"spaceName":"Space1","spaceKey":"SPAC","permissions":{"viewSpace":true,"createPage":true,"setPagePermissions":false,"removePage":true,"createBlogPost":false,"removeBlog":false,"comment":false,"removeComment":false,"createAttachment":false,"removeAttachment":false,"removeMail":true,"exportSpace":false,"editSpace":false}}]}',
			data : permissions,
			processData : false
		});
	}

	AJS.$("#admin").submit(function(e) {
		e.preventDefault();
		getAndCopy();
		//getPermissions();
		//copyPermissions();
	});
});