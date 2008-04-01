/*
 * $Id: CheckboxRenderer.java,v 1.31 2002/08/20 20:43:11 jvisvanathan Exp $
 *
 */

/*
 * Copyright 2002 Sun Microsystems, Inc. All rights reserved.
 * SUN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

// CheckboxRenderer.java

package com.sun.faces.renderkit.html_basic;

import com.sun.faces.RIConstants;
import com.sun.faces.util.Util;

import java.io.IOException;
import java.util.Iterator;

import javax.faces.component.AttributeDescriptor;
import javax.faces.component.UIComponent;
import javax.faces.component.UISelectBoolean;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import javax.faces.render.Renderer;
import javax.faces.FacesException;

import org.apache.commons.beanutils.ConversionException;
import org.apache.commons.beanutils.ConvertUtils;

import org.mozilla.util.Assert;
import org.mozilla.util.Debug;
import org.mozilla.util.Log;
import org.mozilla.util.ParameterCheck;

/**
 *
 *  <B>CheckboxRenderer</B> is a class ...
 *
 * <B>Lifetime And Scope</B> <P>
 *
 * @version $Id: CheckboxRenderer.java,v 1.31 2002/08/20 20:43:11 jvisvanathan Exp $
 * 
 * @see	Blah
 * @see	Bloo
 *
 */

public class CheckboxRenderer extends HtmlBasicRenderer {
    //
    // Protected Constants
    //

    //
    // Class Variables
    //

    //
    // Instance Variables
    //

    // Attribute Instance Variables


    // Relationship Instance Variables

    //
    // Constructors and Initializers    
    //

    public CheckboxRenderer() {
        super();
    }

    //
    // Class methods
    //

    //
    // General Methods
    //

    //
    // Methods From Renderer
    //

    public boolean supportsComponentType(String componentType) {
        if (componentType == null) {
            throw new NullPointerException(Util.getExceptionMessage(Util.NULL_PARAMETERS_ERROR_MESSAGE_ID));
        }
        return (componentType.equals(UISelectBoolean.TYPE));
    }

    public void decode(FacesContext context, UIComponent component) 
        throws IOException {
        if (context == null || component == null) {
            throw new NullPointerException(Util.getExceptionMessage(Util.NULL_PARAMETERS_ERROR_MESSAGE_ID));
        }

        String compoundId = component.getCompoundId();
        Assert.assert_it(compoundId != null );
        String newValue = context.getServletRequest().getParameter(compoundId);
        String modelRef = component.getModelReference();

        //PENDING(rogerk) if there was nothing sent in the request,
        //the checkbox wasn't checked..
        //
        if (newValue == null) {
            newValue = "false";

        // Otherwise, if the checkbox was checked, the value
        // coming in could be "on", "yes" or "true".

        } else if (newValue.equalsIgnoreCase("on") ||
            newValue.equalsIgnoreCase("yes") ||
            newValue.equalsIgnoreCase("true")) {
            newValue = "true";
        }
        component.setValue(Boolean.valueOf(newValue));
    }

    public void encodeBegin(FacesContext context, UIComponent component) 
        throws IOException {
        if (context == null || component == null) {
            throw new NullPointerException(Util.getExceptionMessage(Util.NULL_PARAMETERS_ERROR_MESSAGE_ID));
        }
    }

    public void encodeChildren(FacesContext context, UIComponent component) 
        throws IOException {
        if (context == null || component == null) {
            throw new NullPointerException(Util.getExceptionMessage(Util.NULL_PARAMETERS_ERROR_MESSAGE_ID));
        }

    }

    public void encodeEnd(FacesContext context, UIComponent component) 
        throws IOException {
        ResponseWriter writer = null;

        if (context == null || component == null) {
            throw new NullPointerException(Util.getExceptionMessage(Util.NULL_PARAMETERS_ERROR_MESSAGE_ID));
        }

        Boolean value = (Boolean) component.currentValue(context);
        if (value == null) {
            value = Boolean.FALSE;
        }
        
        writer = context.getResponseWriter();
        Assert.assert_it(writer != null );
        writer.write("<input type=\"checkbox\" ");
        writer.write(" name=\"");
        writer.write(component.getCompoundId());
        writer.write("\"");

        UISelectBoolean boolComp = (UISelectBoolean)component;
        if (value.booleanValue()) {
            writer.write(" checked ");
        }
        writer.write(Util.renderPassthruAttributes(context, component));
	writer.write(Util.renderBooleanPassthruAttributes(context, component));
        writer.write(">");
        String label = null;

        // PENDING (visvan) remove label attribute once we handle nested
        // label tags.
	try {
	    label = getKeyAndLookupInBundle(context, component, "key");
	}
	catch (java.util.MissingResourceException e) {
	    // Do nothing since the absence of a resource is not an
	    // error.
	}
	if (null == label) {
	    label = (String)component.getAttribute("label");
	}

        if (label != null) {
            writer.write(" ");
            writer.write(label);
        }  
    }

} // end of class CheckboxRenderer
