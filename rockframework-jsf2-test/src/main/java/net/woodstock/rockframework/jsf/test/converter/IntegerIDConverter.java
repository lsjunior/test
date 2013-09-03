package net.woodstock.rockframework.jsf.test.converter;

import javax.faces.component.EditableValueHolder;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter(value = "integerID", forClass = java.lang.Integer.class)
public class IntegerIDConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext context, UIComponent component,
			String str) {
		if ((str != null) && (str.trim().length() > 0)) {
			return new Integer(str);
		}
		if (component instanceof EditableValueHolder) {
			((EditableValueHolder) component).setSubmittedValue(null);
		}
		return null;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component,
			Object obj) {
		if (obj != null) {
			return obj.toString();
		}
		return null;
	}

}
