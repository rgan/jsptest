package com.thoughtworks.jspTest;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import java.util.Enumeration;

public class ServletConfigStub implements ServletConfig {
    public String getServletName() {
        return null;
    }

    public ServletContext getServletContext() {
        return null;
    }

    public String getInitParameter(String s) {
        return null;
    }

    public Enumeration getInitParameterNames() {
        return null;
    }
}
