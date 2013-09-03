package net.woodstock.rockframework.test.wicket;

import java.util.List;

import org.apache.wicket.PageParameters;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;

public class PeopleListPage extends WebPage {

	public PeopleListPage() {
		super();
		this.add(new PeopleList("table", (List<People>) PeopleRepository.getInstance().listAll(new People(), null)));
		this.add(new BookmarkablePageLink<PeopleSavePage>("new", PeopleSavePage.class));
	}

	class PeopleList extends ListView<People> {

		private static final long	serialVersionUID	= 1L;

		public PeopleList(String id, List<People> peoples) {
			super(id, peoples);
		}

		@Override
		protected void populateItem(ListItem<People> item) {
			final People people = item.getModelObject();
			item.add(new Label("id", people.getId().toString()));
			item.add(new Label("name", people.getName()));
			item.add(new Label("status", people.getStatus().toString()));
			item.add(new Link<People>("update") {

				private static final long	serialVersionUID	= 1L;

				@Override
				public void onClick() {
					PageParameters pp = new PageParameters();
					pp.add("id", people.getId().toString());
					this.setResponsePage(PeopleSavePage.class, pp);
				}
			});
		}

	}

}
