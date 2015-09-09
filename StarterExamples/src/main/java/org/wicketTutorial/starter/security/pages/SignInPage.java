package org.wicketTutorial.starter.security.pages;

import org.apache.wicket.Session;
import org.apache.wicket.authroles.authentication.AuthenticatedWebSession;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.StatelessForm;
import org.apache.wicket.markup.html.form.SubmitLink;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.util.string.Strings;

public class SignInPage extends WebPage
{
	private static final long serialVersionUID = 1L;

	private String username;
	private String password;
	
	public SignInPage(final PageParameters parameters)
	{
		super(parameters);
	}

	@Override
	protected void onInitialize()
	{
		super.onInitialize();
		
		StatelessForm<SignInPage> form = new StatelessForm<SignInPage>("form")
		{
			@Override
			protected void onSubmit()
			{
				if (Strings.isEmpty(username))
					return;
				
				boolean authResult = AuthenticatedWebSession.get().signIn(username, password);
				// if authentication succeeds redirect user to the requested page
			System.out.println("authResult " + authResult);	
			System.out.println("session temp " + Session.get().isTemporary());	
			
				if (authResult)
					continueToOriginalDestination();
			}
		};
		form.setDefaultModel(new CompoundPropertyModel<>(this));
		form.add(new TextField<>("username"));
		form.add(new PasswordTextField("password"));
		form.add(new SubmitLink("submit"));
		
		add(form);
	}
}
