package cn.edu.sjtu.dclab.test;

import java.io.IOException;

import org.json.JSONException;

import cn.edu.sjtu.dclab.datatemplate.*;
import cn.edu.sjtu.dclab.generator.Classgenerator;

public class Test {
	public static void main(String [] args) throws IOException, JSONException
	{
		System.out.println("Test generate code!");
		User u = UserService.getUserbyid(1);
		System.out.println(u.getId());
		System.out.println(u.getName());
		System.out.println(u.getSex());
		
		System.out.println("Test generate Entity!");
		Classgenerator cg = new Classgenerator();
		cg.generate("./src/cn/edu/sjtu/dclab/datatemplate/", "cn.edu.sjtu.dclab.datatemplate", "./meta.xml");
		
//		User stu = new User();
//		stu = new UserService().getUserbyid(1);
//		System.out.println(stu.getId());
//		System.out.println(stu.getName());
	}
}
