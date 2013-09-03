package net.woodstock.rockframework.test.wicket;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxFallbackLink;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

public class Ajax extends WebPage {

	public Ajax() {
		super();
		Model<Integer> model = new Model<Integer>() {

			private static final long	serialVersionUID	= 1L;

			private int					counter				= 0;

			@Override
			public Integer getObject() {
				return new Integer(this.counter++);
			}
		};

		final Label label = new Label("counter", model);
		label.setOutputMarkupId(true);

		add(new AjaxFallbackLink<IModel<String>>("link") {

			private static final long	serialVersionUID	= 1L;

			@Override
			public void onClick(AjaxRequestTarget target) {
				if (target != null) {
					target.addComponent(label);
				}
			}
		});
		add(label);
	}

}
