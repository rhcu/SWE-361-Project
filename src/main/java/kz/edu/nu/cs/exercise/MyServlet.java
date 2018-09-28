package kz.edu.nu.cs.exercise;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class MyServlet.
 * 
 * This class is generated as part of the pagingexercise361
 * archetype. This archetype generates a maven project with some more up-to-date
 * features compared with the org.apache quickstart webapp archetype.
 * 
 * Access at url <code>hostname/...projectname.../myservlet</code>
 * 
 * Some of the updates included in this archetype
 * <ul>
 * <li>Version 4.12 of JUnit
 * <li>Build with Java 8
 * <li>Servlet API 3.0.1
 * </ul>
 * <p>
 * If you have obtained this project then you can build and install to your
 * local maven repository. To build the project, go to the project root and run
 * <code>mvn archetype:create-from-project</code>.
 * <p>
 * After building, navigate to <code>target/generated-sources/archetype</code>
 * and run <code>mvn install</code>. This will add the archetype to your local
 * maven repository.
 * <p>
 * Add <code>.m2/archetype-catalog.xml</code> as a local catalog in eclipse
 * 
 * @author marks1024
 */
@WebServlet(urlPatterns = { "/myservlet" })
public class MyServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public MyServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.getWriter().append("Served at: ").append(request.getContextPath());
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
