package net.woodstock.rockframework.test.wicket;

import org.apache.wicket.PageParameters;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.CheckBox;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.CompoundPropertyModel;

public class PeopleSavePage extends WebPage {

	public PeopleSavePage(PageParameters parameters) {
		super();
		this.add(new BookmarkablePageLink<PeopleListPage>("back", PeopleListPage.class));
		this.add(new FeedbackPanel("messages"));
		People people = new People();
		if (parameters.containsKey("id")) {
			Integer id = parameters.getAsInteger("id");
			people = PeopleRepository.getInstance().get(new People(id));
		}
		this.add(new PeopleForm("form", people));
	}

	class PeopleForm extends Form<People> {

		private static final long	serialVersionUID	= 1L;

		private boolean				update;

		public PeopleForm(String id, People people) {
			super(id, new CompoundPropertyModel<People>(people));
			this.update = people.getId() != null;

			TextField<Integer> txtId = new TextField<Integer>("id");
			TextField<String> txtName = new TextField<String>("name");
			CheckBox chkStatus = new CheckBox("status");

			txtId.setRequired(true);
			txtName.setRequired(true);

			this.add(txtId);
			this.add(txtName);
			this.add(chkStatus);
		}

		@Override
		protected void onSubmit() {
			People people = this.getModelObject();
			if (this.update) {
				PeopleRepository.getInstance().update(people);
			} else {
				PeopleRepository.getInstance().save(people);
				this.setModelObject(new People());
			}
		}

	}

}
