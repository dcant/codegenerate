//package cn.edu.sjtu.dclab.datatemplate;
package cn.edu.sjtu.dclab.datatemplate;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import cn.edu.sjtu.dclab.urlutil.Urlstr;
import cn.edu.sjtu.dclab.urlutil.Urlutil;

public class Student {
	private static final String url = Urlstr.url;
	
	private String name;
	private int uid;
	private int id;
	
	public Student()
	{}
	public Student(JSONObject obJson)
	{
		try {
					this.setName(obJson.getString("name"));
					this.setUid(obJson.getInt("uid"));
					this.setId(obJson.getInt("id"));
				} catch (JSONException e) {
			// TODO: handle exception
		}
	}
	
	public void setName(String name)
	{
		this.name = name;
	}

	public String getName()
	{
		return this.name;
	}
	public void setUid(int uid)
	{
		this.uid = uid;
	}

	public int getUid()
	{
		return this.uid;
	}
	public void setId(int id)
	{
		this.id = id;
	}

	public int getId()
	{
		return this.id;
	}
}
