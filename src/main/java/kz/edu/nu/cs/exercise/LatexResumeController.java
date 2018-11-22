package kz.edu.nu.cs.exercise;
import java.sql.DriverManager;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.bind.DatatypeConverter;

/**
 * Servlet implementation class UserDataServlet
 */
@WebServlet(urlPatterns = { "/latexresume" })
public class LatexResumeController extends HttpServlet {

protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	try {

		  LogModel log = null;
		   try {
		    log = new LogModel();
		   } catch (SQLException e) {
		    // TODO Auto-generated catch block
		    e.printStackTrace();
		   }

		String username = request.getParameter("username");


		HttpSession session = request.getSession();
		System.out.println("Username ins session: " + session.getAttribute("username"));
		username = (String)session.getAttribute("username");
		User u = new User(username);
		String main = "";
		main += this.head;
		
		main += "\\name{"+u.getName()+" "+u.getLastname()+"} % Your name\n" + 
				"\n" + 
				"\\address{"+u.getAddr()+"}\n" + 
				"\n" + 
				"\\begin{document}";
		List<String> fields = new ArrayList<String>();
		fields.add("username");
		List<String> values = new ArrayList<String>();
		values.add(username);
		 
		main += "\\begin{rSection}{Experience}";
		
			 ExperienceModel expModel = new ExperienceModel(username);
			 ResultSet rs = expModel.findWhere(fields, values);
			 while(rs != null) {
				 if(rs.isAfterLast()) break;
				 String num = rs.getString("num");
				 
				 String title = rs.getString("title");
				 String company = rs.getString("company");
				 String dates = rs.getString("dates");
				 String description = rs.getString("description");
				 String location = rs.getString("location");
				 if(title != null && company != null && dates!= null && description!= null) {
					 main += "\\begin{rSubsection}{"+company+"}{"+dates+"}{ "+title+"}{"+location+"}\n" + 
					 		"\\item "+description+"\n" + 
					 		"\\end{rSubsection}\n";
				 }
				 rs.next();
			 }
		main += "\\end{rSection}\n";
		main += "\\begin{rSection}{Education}";
			EducationModel edModel = new EducationModel(username);
			rs = edModel.findWhere(fields, values);
			while(rs != null) {
				 if(rs.isAfterLast()) break;
				 String num = rs.getString("num");
				 
				 String school = rs.getString("school");
				 String type = rs.getString("type");
				 String dates = rs.getString("dates");
				 String description = rs.getString("description");
				 String major = rs.getString("major");
				 if(school != null && type != null && major != null && dates!= null && description!= null) {
					 main += "{\\bf "+school+"} \\hfill {\\em "+dates+"} \\\\ \n" + 
						 		""+type+" in "+major+".\\\\\n" + 
						 		description+".\\\\";
				 }
				 rs.next();
			 }
		main += "\\end{rSection}\n";
		main += "\\begin{rSection}{Projects}";
			ProjectModel projectModel = new ProjectModel(username);
			rs = projectModel.findWhere(fields, values);
			while(rs != null) {
				 if(rs.isAfterLast()) break;
				 String num = rs.getString("num");
				 
				 String name = rs.getString("name");
				 String url = rs.getString("url");
				 String dates = rs.getString("dates");
				 String description = rs.getString("description");
				 if(name != null && url != null && dates!= null && description!= null) {
					 main += "\\begin{rSubsection}{"+name+"}{\\url{"+url+"}}{"+dates+"}{}\n" + 
							 "\\item "+description +"\\end{rSubsection}" ;
				 }
				 rs.next();
			 }
		main += "\\end{rSection}\n";
		
		
		main += "\\begin{rSection}{Skills}";
			SkillsModel skillsModel = new SkillsModel(username);
			rs = skillsModel.findWhere(fields, values);
			while(rs != null) {
				 if(rs.isAfterLast()) break;
				 String skills = rs.getString("content");
				 main += "\\item " + skills;
				 rs.next();
			}
				
		main += "\\end{rSection}\n";
		main += this.foot;
		
		System.out.println(main);
	
		String pdf = generate(main);
		
		int BUFF_SIZE = 1024;
        byte[] buffer = new byte[BUFF_SIZE];
        response.setContentType("application/pdf");
        response.setHeader("Content-Type", "application/pdf");
        File filePDF = new File(pdf);
        FileInputStream fis = new FileInputStream(filePDF);     
        OutputStream os = response.getOutputStream();
        try
        {
            response.setContentLength((int) filePDF.length());
            int byteRead = 0;
            while ((byteRead = fis.read()) != -1)
            {
                os.write(byteRead);
            }
            os.flush();
            log.add("LatexResume",  "Resume successfully generated");

        }
        catch (Exception excp)
        {
            excp.printStackTrace();
        }
        finally
        {
            os.close();
            fis.close();
			log.disconnect();
        }
        
	
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
 }
protected void delete_file(String filename) {
	File file = new File(filename); 
	LogModel log = null;
	   try {
	    log = new LogModel();
	   } catch (SQLException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	   }
    if(file.delete()) 
    { 
    	 try {
    	      log.add("LatexResume",  "LatexResume file deleted successfully");
    	    } catch (Exception e) {
    	      // TODO Auto-generated catch block
    	      e.printStackTrace();
    	    }

        System.out.println("File deleted successfully"); 
    } 
    else
    { 
    	 try {
    	      log.add("LatexResume",  "Failed to delete LatexResume file");
    	    } catch (Exception e) {
    	      // TODO Auto-generated catch block
    	      e.printStackTrace();
    	    }

        System.out.println("Failed to delete the file"); 
    } 

	try {
		log.disconnect();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}
protected String generate(String main) throws IOException, InterruptedException {

	 System.out.println("PATH: "+getServletContext().getRealPath("/") + "main.tex");
	 String mainPath = "main.tex";
	 String clsPath = "resume.cls";
	 String laton =  "laton";
	 delete_file(mainPath);
	 delete_file(clsPath);
	 delete_file(laton);
	 delete_file("main.pdf");
	 
	 BufferedWriter writer = new BufferedWriter(new FileWriter(mainPath));
	 writer.write(main);
	 writer.close();
	 writer = new BufferedWriter(new FileWriter(clsPath));
	 writer.write(this.clsContent);
	 writer.close();
	 Runtime rt = Runtime.getRuntime();
	 String basePath = getServletContext().getRealPath("/");
	 String ex = "curl -L https://raw.githubusercontent.com/aslushnikov/latex-online/master/util/latexonline";
	 Process pr = rt.exec(ex);
	 
	 String line;
	 String laton_content = "";
	 BufferedReader input = new BufferedReader(new InputStreamReader(pr.getInputStream()));
	 while ((line = input.readLine()) != null) {
	    System.out.println(line);
	    laton_content += line + "\n";
	  }
	 writer = new BufferedWriter(new FileWriter(laton));
	 writer.write(laton_content);
	 writer.close();
	 input.close();

	 pr = rt.exec("chmod 755 "+laton);
	 input = new BufferedReader(new InputStreamReader(pr.getInputStream()));
	 while ((line = input.readLine()) != null) {
	    //System.out.println(line);
	  }
	 
	 //pr.wait();
	 
	 pr = rt.exec("./"+laton + " main.tex resume.cls");
	 System.out.println("./laton " +  " main.tex resume.cls");
	 System.out.println(basePath);
	 input = new BufferedReader(new InputStreamReader(pr.getInputStream()));
	 while ((line = input.readLine()) != null) {
	    //System.out.println(line);
	    laton_content += line + "\n";
	  }
	 System.out.println(laton_content);
	 //pr.wait();
	 /*pr.waitFor();
	 pr = rt.exec("."+laton +" "+ mainPath + " " + clsPath);*/
	 
	 
	 return "main.pdf";
}
protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	 try {
         // The newInstance() call is a work around for some
         // broken Java implementations

         Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
     } catch (Exception ex) {
         // handle the errorr
    	 System.out.println(ex.getMessage());
     }
	try {
		HttpSession session = request.getSession();
		System.out.println("Username in session: " + session.getAttribute("username"));
		String username = (String) session.getAttribute("username");
		
		if(username == null) {
			RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
			rd.forward(request, response);
		}
		
		User u = new User(username);
		
	   request.setAttribute("userName", username);
	   request.setAttribute("firstname", u.getName());
	   request.setAttribute("secondname", u.getLastname());
	   request.setAttribute("addr", u.getAddr());
	   request.setAttribute("age", u.getAge());
	   RequestDispatcher rd = request.getRequestDispatcher("latexresume.jsp");
	   rd.forward(request, response);
	}
	catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
 }
protected String foot = "\\end{document}";
protected String head = "\\documentclass{resume}\n" + 
		"\n" + 
		"\\usepackage[parfill]{parskip} % Remove paragraph indentation\n" + 
		"\\usepackage{array} % Required for boldface (\\bf and \\bfseries) tabular columns\n" + 
		"\\usepackage{ifthen} % Required for ifthenelse statements\n" + 
		"\n" + 
		"\\pagestyle{empty} % Suppress page numbers\n" + 
		"\n" + 
		"\n" + 
		"\n" + 
		"\\usepackage[left=0.6in,top=0.3in,right=0.6in,bottom=0.3in]{geometry} % Document margins\n" + 
		"\n" + 
		" \\usepackage{url}";
protected String clsContent = "\\ProvidesClass{resume}[2010/07/10 v0.9 Resume class]\n" + 
		"\n" + 
		"\\LoadClass[10pt,letterpaper]{article} % Font size and paper type\n" + 
		"\n" + 
		"\\usepackage[parfill]{parskip} % Remove paragraph indentation\n" + 
		"\\usepackage{array} % Required for boldface (\\bf and \\bfseries) tabular columns\n" + 
		"\\usepackage{ifthen} % Required for ifthenelse statements\n" + 
		"\n" + 
		"\\pagestyle{empty} % Suppress page numbers\n" + 
		"\n" + 
		"%----------------------------------------------------------------------------------------\n" + 
		"%	HEADINGS COMMANDS: Commands for printing name and address\n" + 
		"%----------------------------------------------------------------------------------------\n" + 
		"\n" + 
		"\\def \\name#1{\\def\\@name{#1}} % Defines the \\name command to set name\n" + 
		"\\def \\@name {} % Sets \\@name to empty by default\n" + 
		"\n" + 
		"\\def \\addressSep {$\\diamond$} % Set default address separator to a diamond\n" + 
		"\n" + 
		"% One, two or three address lines can be specified \n" + 
		"\\let \\@addressone \\relax\n" + 
		"\\let \\@addresstwo \\relax\n" + 
		"\\let \\@addressthree \\relax\n" + 
		"\n" + 
		"% \\address command can be used to set the first, second, and third address (last 2 optional)\n" + 
		"\\def \\address #1{\n" + 
		"  \\@ifundefined{@addresstwo}{\n" + 
		"    \\def \\@addresstwo {#1}\n" + 
		"  }{\n" + 
		"  \\@ifundefined{@addressthree}{\n" + 
		"  \\def \\@addressthree {#1}\n" + 
		"  }{\n" + 
		"     \\def \\@addressone {#1}\n" + 
		"  }}\n" + 
		"}\n" + 
		"\n" + 
		"% \\printaddress is used to style an address line (given as input)\n" + 
		"\\def \\printaddress #1{\n" + 
		"  \\begingroup\n" + 
		"    \\def \\\\ {\\addressSep\\ }\n" + 
		"    \\centerline{#1}\n" + 
		"  \\endgroup\n" + 
		"  \\par\n" + 
		"  \\addressskip\n" + 
		"}\n" + 
		"\n" + 
		"% \\printname is used to print the name as a page header\n" + 
		"\\def \\printname {\n" + 
		"  \\begingroup\n" + 
		"    \\hfil{\\MakeUppercase{\\namesize\\bf \\@name}}\\hfil\n" + 
		"    \\nameskip\\break\n" + 
		"  \\endgroup\n" + 
		"}\n" + 
		"\n" + 
		"%----------------------------------------------------------------------------------------\n" + 
		"%	PRINT THE HEADING LINES\n" + 
		"%----------------------------------------------------------------------------------------\n" + 
		"\n" + 
		"\\let\\ori@document=\\document\n" + 
		"\\renewcommand{\\document}{\n" + 
		"  \\ori@document  % Begin document\n" + 
		"  \\printname % Print the name specified with \\name\n" + 
		"  \\@ifundefined{@addressone}{}{ % Print the first address if specified\n" + 
		"    \\printaddress{\\@addressone}}\n" + 
		"  \\@ifundefined{@addresstwo}{}{ % Print the second address if specified\n" + 
		"    \\printaddress{\\@addresstwo}}\n" + 
		"     \\@ifundefined{@addressthree}{}{ % Print the third address if specified\n" + 
		"    \\printaddress{\\@addressthree}}\n" + 
		"}\n" + 
		"\n" + 
		"%----------------------------------------------------------------------------------------\n" + 
		"%	SECTION FORMATTING\n" + 
		"%----------------------------------------------------------------------------------------\n" + 
		"\n" + 
		"% Defines the rSection environment for the large sections within the CV\n" + 
		"\\newenvironment{rSection}[1]{ % 1 input argument - section name\n" + 
		"  \\sectionskip\n" + 
		"  \\MakeUppercase{\\bf #1} % Section title\n" + 
		"  \\sectionlineskip\n" + 
		"  \\hrule % Horizontal line\n" + 
		"  \\begin{list}{}{ % List for each individual item in the section\n" + 
		"    \\setlength{\\leftmargin}{1em} % Margin within the section\n" + 
		"  }\n" + 
		"  \\item[]\n" + 
		"}{\n" + 
		"  \\end{list}\n" + 
		"}\n" + 
		"\n" + 
		"%----------------------------------------------------------------------------------------\n" + 
		"%	WORK EXPERIENCE FORMATTING\n" + 
		"%----------------------------------------------------------------------------------------\n" + 
		"\n" + 
		"\\newenvironment{rSubsection}[4]{ % 4 input arguments - company name, year(s) employed, job title and location\n" + 
		" {\\bf #1} \\hfill {#2} % Bold company name and date on the right\n" + 
		" \\ifthenelse{\\equal{#3}{}}{}{ % If the third argument is not specified, don't print the job title and location line\n" + 
		"  \\\\\n" + 
		"  {\\em #3} \\hfill {\\em #4} % Italic job title and location\n" + 
		"  }\\smallskip\n" + 
		"  \\begin{list}{$\\cdot$}{\\leftmargin=0em} % \\cdot used for bullets, no indentation\n" + 
		"   \\itemsep -0.5em \\vspace{-0.5em} % Compress items in list together for aesthetics\n" + 
		"  }{\n" + 
		"  \\end{list}\n" + 
		"  \\vspace{0.5em} % Some space after the list of bullet points\n" + 
		"}\n" + 
		"\n" + 
		"% The below commands define the whitespace after certain things in the document - they can be \\smallskip, \\medskip or \\bigskip\n" + 
		"\\def\\namesize{\\huge} % Size of the name at the top of the document\n" + 
		"\\def\\addressskip{\\smallskip} % The space between the two address (or phone/email) lines\n" + 
		"\\def\\sectionlineskip{\\medskip} % The space above the horizontal line for each section \n" + 
		"\\def\\nameskip{\\bigskip} % The space after your name at the top\n" + 
		"\\def\\sectionskip{\\medskip} % The space after the heading section\n";
}