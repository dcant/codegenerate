package cn.edu.sjtu.dclab.generator;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.exception.MethodInvocationException;
import org.apache.velocity.exception.ParseErrorException;
import org.apache.velocity.exception.ResourceNotFoundException;

public class Classgenerator {
//	public static String fileBase = "./src/cn/edu/sjtu/dclab/datatemplate/";
	
	private String changestr(String str)
	{
		String first = str.substring(0, 1).toUpperCase();
		String rest = str.substring(1, str.length());
		return new StringBuffer(first).append(rest).toString();
	}
	
	public class Myparam {
		private String name;
		private String bname;
		private String type;
		private String btype;
		public void setName(String name)
		{
			this.name = name;
		}
		public String getName()
		{
			return this.name;
		}
		public void setBname(String bname)
		{
			this.bname = bname;
		}
		public String getBname()
		{
			return this.bname;
		}
		public void setType(String type)
		{
			this.type = type;
		}
		public String getType()
		{
			return this.type;
		}
		public void setBtype(String btype)
		{
			this.btype = btype;
		}
		public String getBtype()
		{
			return this.btype;
		}
	}

	public void generate(String fileBase, String pack, String file) {
//		String name = "User";
		
		Velocity.init();
		VelocityContext cont = new VelocityContext();
		List<Myparam> ap= new ArrayList<Myparam>();
		
		Map<String, Map<String, String>> param = new Xmlparse().parse(file);
		String tablename = null;
		
		for(String key : param.keySet())
		{
			tablename = changestr(key.toLowerCase());
			Map<String, String> names = (Map<String, String>) param.get(key);
			for(String k : names.keySet())
			{
				Myparam p = new Myparam();
				p.name = k.toLowerCase();
				p.bname = changestr(p.name);
				p.type = (String)names.get(k);
				p.btype = changestr(p.type);
				ap.add(p);
//				System.out.println("name:" + k);
//				System.out.println("type:" + n);
			}
		}
		
		cont.put("entityname", tablename);
		cont.put("attributes", ap);
		cont.put("package", pack);

		Template temp = new Template();

		try {
			temp = Velocity.getTemplate("template/Entity.vm");
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
		String filename = tablename + ".java";
		dest = dest + filename;
		File f = new File(dest);
		BufferedWriter bw = null;
		try {
			bw = new BufferedWriter(new FileWriter(f));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		temp.merge(cont, bw);
		try {
			bw.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			bw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			new Servicegenerator().generate(fileBase, pack, tablename);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
