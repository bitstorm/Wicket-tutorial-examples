package com.mycompany;

import java.util.Date;

import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.StringResourceModel;

public class HomePage extends WebPage {
	private static final long serialVersionUID = 1L;

    public HomePage(final PageParameters parameters) {
    	Order order = new Order(new Date(), ORDER_STATUS.IN_PROGRESS);
		add(new Label("orderStatus", new StringResourceModel("orderStatus.${status.code}", Model.of(order))));    
    }
}
