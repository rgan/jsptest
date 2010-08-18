package com.thoughtworks.jspTest;

import org.junit.Test;

import java.io.File;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class JspWrapperTest {
    private static final String WORK_DIR = "src/test/work";

    @Test
    public void shouldCompile() throws Exception {
        final JspWrapper jspWrapper = new JspWrapper("main.jsp");
        final File servletFile = new File(WORK_DIR + "/org/apache/jsp/main_jsp.java");
        if (servletFile.exists()) {
            servletFile.delete();
        }
        assertFalse(servletFile.exists());
        jspWrapper.compile();
        assertTrue("java file was not generated", servletFile.exists());
        assertTrue("class file was not generated", new File(WORK_DIR + "/org/apache/jsp/main_jsp.class").exists());
    }

    @Test
    public void shouldReturnClassForJsp() {
        assertEquals("org.apache.jsp.main_jsp", new JspWrapper("main.jsp").getJspClassName());
    }
}
