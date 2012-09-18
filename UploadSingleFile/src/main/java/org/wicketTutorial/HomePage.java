package org.wicketTutorial;

import java.io.File;
import java.io.IOException;

import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.util.lang.Bytes;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.upload.FileUpload;
import org.apache.wicket.markup.html.form.upload.FileUploadField;
import org.apache.wicket.markup.html.WebPage;

public class HomePage extends WebPage {
	private static final long serialVersionUID = 1L;
	private FileUploadField fileUploadField;

    public HomePage(final PageParameters parameters) {
    	fileUploadField = new FileUploadField("fileUploadField");
    	
    	Form form = new Form("form"){
    		@Override
    		protected void onSubmit() {
    			super.onSubmit();
    			
    			FileUpload fileUpload = fileUploadField.getFileUpload();
    			
    			try {
    				File file = new File(System.getProperty("java.io.tmpdir") + 
    						fileUpload.getClientFileName());
    				
					fileUpload.writeTo(file);
				} catch (IOException e) {
					e.printStackTrace();
				}
    		}
    	};
		
		form.setMultiPart(true);
		form.setMaxSize(Bytes.kilobytes(100));
		form.add(fileUploadField);
		
		add(form);
    }
}
