package net.woodstock.rockframework.test.wicket;

import org.apache.wicket.Page;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.util.lang.PackageName;

public class TestApplication extends WebApplication {

	public TestApplication() {
		super();
	}

	@Override
	public Class<? extends Page> getHomePage() {
		return Index.class;
	}

	@Override
	protected void init() {
		super.init();
		this.mount("/pages", PackageName.forPackage(Index.class.getPackage()));
	}

}
