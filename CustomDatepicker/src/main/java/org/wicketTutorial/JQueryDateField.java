package org.wicketTutorial;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.wicket.AttributeModifier;

import org.apache.wicket.datetime.PatternDateConverter;
import org.apache.wicket.extensions.markup.html.form.DateTextField;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.JavaScriptHeaderItem;
import org.apache.wicket.markup.head.OnDomReadyHeaderItem;
import org.apache.wicket.markup.head.OnLoadHeaderItem;
import org.apache.wicket.model.IModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.request.resource.PackageResourceReference;
import org.apache.wicket.util.convert.IConverter;
import org.apache.wicket.util.template.PackageTextTemplate;

public class JQueryDateField extends DateTextField {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5088998263851588184L;
	private PatternDateConverter dateConverter;
	private String datePattern;
	private String countryIsoCode;
	private String jQueryCalendar;
	
	private static final String initCalendarScript = "function initCalendar(localizedArray){" +
														 "localizedArray['changeMonth']= true;" +
														 "localizedArray['changeYear']= true;" +
														 "localizedArray['showOn'] = 'button';" +
														 "localizedArray['buttonImageOnly'] = true;" +
													     "}";
	
	public String getDatePattern() {
		return datePattern;
	}
	
	public JQueryDateField(String id, IModel<Date> dateModel, String datePattern, String countryIsoCode){
		super(id, dateModel);
		
		this.datePattern = datePattern;
		this.countryIsoCode = countryIsoCode;
	}
	
	public JQueryDateField(String id, String datePattern, String countryIsoCode){
		super(id);
		
		this.datePattern = datePattern;
		this.countryIsoCode = countryIsoCode;
	}
	
	@Override
	protected void onInitialize() {
		super.onInitialize();
		
		int elementHash = hashCode();
		
		setMarkupId("" + elementHash);
		
		setOutputMarkupId(true);
		
		PackageTextTemplate textTemplate = new PackageTextTemplate(getClass(), "JQDatePicker.js");		
		Map<String,Object> variables = new HashMap<String, Object>();
		
		variables.put("inputId", elementHash);
		variables.put("countryIsoCode", countryIsoCode);
		
		PackageResourceReference resourceReference = new PackageResourceReference(getClass(), "calendar.jpg");
		
		variables.put("calendarIcon",  urlFor(resourceReference, new PageParameters()));
		jQueryCalendar = textTemplate.asString(variables);
		dateConverter = new PatternDateConverter(datePattern, false);
		
		add(AttributeModifier.replace("size", "12"));
	}	
	
	@Override
	public void renderHead(IHeaderResponse response) {
		super.renderHead(response);
		
		//
		if(!isEnabledInHierarchy())
			return;
		
		if(!response.wasRendered(initCalendarScript))
			response.render(JavaScriptHeaderItem.forScript(initCalendarScript, "initCalendarScript"));
		
		response.render(OnLoadHeaderItem.forScript(jQueryCalendar));
	}
	
	@Override
	public <Date> IConverter<Date> getConverter(Class<Date> type) {
		return (IConverter<Date>) dateConverter;
	}	
}
