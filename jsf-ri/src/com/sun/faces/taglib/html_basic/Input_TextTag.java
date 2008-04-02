/*
 * $Id: Input_TextTag.java,v 1.5 2003/02/14 01:14:44 craigmcc Exp $
 */

/*
 * Copyright 2002 Sun Microsystems, Inc. All rights reserved.
 * SUN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

// Input_TextTag.java

package com.sun.faces.taglib.html_basic;

import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;

/**
 *
 * @version $Id: Input_TextTag.java,v 1.5 2003/02/14 01:14:44 craigmcc Exp $
 * 
 * @see	Blah
 * @see	Bloo
 *
 */

public class Input_TextTag extends InputTag
{
//
// Protected Constants
//

//
// Class Variables
//

//
// Instance Variables
//
    private String converter = null;

// Attribute Instance Variables

// Relationship Instance Variables

//
// Constructors and Initializers    
//
    public Input_TextTag() {
        super();
    }

//
// Accessors
//
    public void setConverter(String converter) {
        this.converter = converter;
    }

//
// Class methods
//

//
// General Methods
//

    public String getLocalRendererType() { return "Text"; }

    public UIComponent createComponent() {
        return (new UIInput());
    }

    protected void overrideProperties(UIComponent component) {
        super.overrideProperties(component);
        if ((converter != null) &&
            (component.getAttribute("converter") == null)) {
            component.setAttribute("converter", converter);
        }
    }

//
// Methods from TagSupport
//
    


} // end of class Input_TextTag
