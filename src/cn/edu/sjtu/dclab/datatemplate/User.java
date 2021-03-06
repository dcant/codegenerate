package cn.edu.sjtu.dclab.datatemplate;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import cn.edu.sjtu.dclab.urlutil.Urlstr;
import cn.edu.sjtu.dclab.urlutil.Urlutil;

public class User {
	private int id;
	private String name;
	private int sex;
	private static final String url = Urlstr.url;
	
	public User()
	{}
	
	public User(JSONObject userJson)
	{
		try {
			this.setId(userJson.getInt("id"));
			this.setName(userJson.getString("name"));
			this.setSex(userJson.getInt("sex"));			
		} catch (JSONException e) {
			// TODO: handle exception
		}
	}	
	public void setId(int id)
	{
		this.id = id;
	}
	public void setName(String name)
	{
		this.name = name;
	}
	public void setSex(int sex)
	{
		this.sex = sex;
	}
	public int getId()
	{
		return this.id;
	}
	public String getName()
	{
		return this.name;
	}
	public int getSex()
	{
		return this.sex;
	}
}
