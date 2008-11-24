Actions = {
	actionSuffix: "json",
	_getRequest: function() {
		var request;
		try {
			request = new XMLHttpRequest();
		} catch(e1) {
			try {
				request = new ActiveXObject("Msxml2.XMLHTTP");
		 	} catch (e2) {
				try {
					xmlRequest = new ActiveXObject("Microsoft.XMLHTTP");
				} catch (e3) {
					for (var i = 0; i < this.activeX.length; ++i) {
						try {
							request = new ActiveXObject(this.activeX[i]);
							break;
						} catch(e4) {
							// ignore
						}
					}
				}
			}
		}
		return request;
	},
	_getUrl: function(actionName, model) {
		var url = window.location.href;
		url = url.substring(0, url.lastIndexOf('/') + 1);
		url = url + actionName + "." + Actions.actionSuffix;
		if(model) {
			var first = true;
			for(var key in model) {
				url = url + (first ? "?" : "&") + escape(key) + "=" + escape(model[key]);
				first = false;
		    }
	    }
		return url;
	},
	getAction: function(actionName) {
		return {
			actionName: actionName,
			execute: function(model) {
				var request = Actions._getRequest();
				var url = Actions._getUrl(actionName, model);
				request.open("GET", url, false);
				request.send(null);
				return eval("(" + request.responseText + ")");
			}
		};
	},
	getAsyncAction: function(actionName, callback) {
		return {
			actionName: actionName,
			callback: callback,
			execute: function(model) {
				var request = Actions._getRequest();
				var url = Actions._getUrl(actionName, model);
				request.open("GET", url, true);
				if (request.onreadystatechange) {
					request.onreadystatechange = new function() {
						if (xmlRequest.readyState == 4) {
							callback(eval("(" + request.responseText + ")"));
						}
					};
				} else if (request.onReadyStateChange) {
					request.onReadyStateChange = new function() {
						if (xmlRequest.readyState == 4) {
							callback(eval("(" + request.responseText + ")"));
						}
					};
				} else {
					var interval = window.setInterval(function() {
						if (request.readyState == 4) {
							window.clearInterval(interval);
							callback(eval("(" + request.responseText + ")"));
						}
					}, 100);
				}
				request.send(null);
			}
		}
	}
}