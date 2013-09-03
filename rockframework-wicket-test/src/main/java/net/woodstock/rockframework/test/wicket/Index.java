package net.woodstock.rockframework.test.wicket;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;

public class Index extends WebPage {

	public Index() {
		super();
		this.add(new BookmarkablePageLink<Ajax>("ajax", Ajax.class));
		this.add(new BookmarkablePageLink<PeopleListPage>("people", PeopleListPage.class));
	}

}
