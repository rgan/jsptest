package com.thoughtworks.jspTest;

import javax.servlet.ServletConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class JspWrapper {
    private String jspFile;

    public JspWrapper(String jspFile) {
        this.jspFile = jspFile;
    }

    public void invokeService(HttpServletRequest request,
                              HttpServletResponse response) throws Exception {
        compile();
        final Class servletClass = Class.forName(getJspClassName());
        HttpServlet servlet = (HttpServlet) servletClass.newInstance();
        ServletConfig config = new ServletConfigStub();
        servlet.init(config);
        servlet.service(request, response);
    }

    public String getJspClassName() {
        return "org.apache.jsp." + jspFile.replace(".", "_");
    }

    public void compile() throws Exception {
        ProcessBuilder processBuilder = new ProcessBuilder("ant", "-f", "jspc-build.xml", "-Djspfiles=" + jspFile);
        processBuilder.redirectErrorStream(true);
        final Process process = processBuilder.start();
        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        StringBuffer output = new StringBuffer();
        String line = "";
        while ((line = reader.readLine()) != null) {
            System.out.println(line);
            output.append(line);
        }
        process.waitFor();
        final int exitValue = process.exitValue();
        System.out.println(exitValue);
        if (exitValue != 0) {
            throw new Exception(output.toString());
        }
    }
}
