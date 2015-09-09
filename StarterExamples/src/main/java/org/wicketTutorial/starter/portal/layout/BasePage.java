package org.wicketTutorial.starter.portal.layout;

import org.apache.wicket.Application;
import org.apache.wicket.authroles.authentication.AuthenticatedWebSession;
import org.apache.wicket.authroles.authorization.strategies.role.Roles;
import org.apache.wicket.authroles.authorization.strategies.role.annotations.AuthorizeInstantiation;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.StatelessLink;
import org.apache.wicket.model.IModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;

@AuthorizeInstantiation(Roles.USER)
public class BasePage extends WebPage
{
	public BasePage()
	{
		super();
	}

	public BasePage(IModel<?> model)
	{
		super(model);
	}

	public BasePage(PageParameters parameters)
	{
		super(parameters);
	}
	
	@Override
	protected void onInitialize()
	{
		super.onInitialize();
		
		add(new Label("title", "name"));
		add(new StatelessLink<Void>("logOut")
		{
			@Override
			public void onClick()
			{
				AuthenticatedWebSession.get().invalidate();
				setResponsePage(Application.get().getHomePage());
			}
		});
	}
	
}
