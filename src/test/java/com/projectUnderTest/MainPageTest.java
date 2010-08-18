package com.projectUnderTest;

import com.thoughtworks.jspTest.JspWrapper;
import org.junit.Test;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;
import java.io.StringWriter;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class MainPageTest {

    @Test
    public void shouldRenderMainPage() throws Exception {
        final HttpServletRequest request = mock(HttpServletRequest.class);
        final HttpServletResponse response = mock(HttpServletResponse.class);
        final HttpSession session = mock(HttpSession.class);
        when(request.getSession()).thenReturn(session);
        final StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);
        when(request.getAttribute("comment")).thenReturn("Hello World");

        final JspWrapper jspWrapper = new JspWrapper("main.jsp");
        jspWrapper.invokeService(request, response);

        assertEquals("Hello World", stringWriter.toString());
    }
}
