package cn.edu.sjtu.dclab.generator;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.exception.MethodInvocationException;
import org.apache.velocity.exception.ParseErrorException;
import org.apache.velocity.exception.ResourceNotFoundException;

import com.sun.xml.internal.fastinfoset.sax.Properties;

public class Servicegenerator {
//	public static String fileBase = "./src/cn/edu/sjtu/dclab/datatemplate/";
	
	private String changestr(String str)
	{
		String first = str.substring(0, 1).toUpperCase();
		String rest = str.substring(1, str.length());
		return new StringBuffer(first).append(rest).toString();
	}

	public void generate(String fileBase, String pack, String name) throws IOException {
//		String name = "User";
		String fname = changestr(name.toLowerCase());//change string to first Big.
		
		Velocity.init();
		VelocityContext cont = new VelocityContext();
		cont.put("package", pack);
		cont.put("classname", fname);

		Template temp = new Template();

		try {
			temp = Velocity.getTemplate("template/Dataaccess.vm");
		} catch (ResourceNotFoundException e) {
			// TODO: handle exception
			System.out.println("File not found!");
		} catch (ParseErrorException e) {
			System.out.println("Parse error!");
		} catch (MethodInvocationException e) {
			System.out.println("Method Invocation Error!");
		} catch (Exception e) {
			System.out.println("Something wrong!");
		}

		String dest = fileBase ;
		String filename = fname + "Service.java";
		dest = dest + filename;
		File f = new File(dest);
		BufferedWriter bw = new BufferedWriter(new FileWriter(f));
		temp.merge(cont, bw);
		bw.flush();
		bw.close();

	}
}
