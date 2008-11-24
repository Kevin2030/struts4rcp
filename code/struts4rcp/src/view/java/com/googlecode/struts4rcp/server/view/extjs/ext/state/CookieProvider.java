package com.googlecode.struts4rcp.server.view.extjs.ext.state;

import com.googlecode.struts4rcp.server.view.extjs.Description;

@Description("The default Provider implementation which saves state via cookies."
		+ "Usage: "
		+ "var cp = new Ext.state.CookieProvider({"
		+ "       path: '/cgi-bin/',"
		+ "       expires: new Date(new Date().getTime()+(1000*60*60*24*30)), //30 days"
		+ "       domain: 'extjs.com'"
		+ "   });"
		+ "   Ext.state.Manager.setProvider(cp);")
public class CookieProvider extends Provider {
	private String domain;
	private String expires;
	private String path;
	private Boolean secure;

	public String getDomain() {
		return domain;
	}

	@Description("domain : String \n"
			+ "The domain to save the cookie for. Note that you cannot specify a different domain than your page is on, but you can specify a sub-domain, or simply the domain itself like 'extjs.com' to include all sub-domains if you need to access cookies across different sub-domains (defaults to null which uses the same domain the page is running on including the 'www' like 'www.extjs.com') ")
	public void setDomain(String domain) {
		this.domain = domain;
	}

	public String getExpires() {
		return expires;
	}

	@Description("expires : Date \n"
			+ "The cookie expiration date (defaults to 7 days from now) ")
	public void setExpires(String expires) {
		this.expires = expires;
	}

	public String getPath() {
		return path;
	}

	@Description("path : String \n"
			+ "The path for which the cookie is active (defaults to root '/' which makes it active for all pages in the site) ")
	public void setPath(String path) {
		this.path = path;
	}

	public Boolean isSecure() {
		return secure;
	}

	@Description("secure : Boolean \n"
			+ "True if the site is using SSL (defaults to false) ")
	public void setSecure(Boolean secure) {
		this.secure = secure;
	}

}
