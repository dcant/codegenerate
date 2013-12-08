//package cn.edu.sjtu.dclab.datatemplate;
package cn.edu.sjtu.dclab.datatemplate;
import cn.edu.sjtu.dclab.datatemplate.Student;
import java.util.List;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import cn.edu.sjtu.dclab.urlutil.*;

public class StudentService {
	public static final String baseurl = Urlstr.url;
	public static final String db = new String("Student").toLowerCase();
	
	public static Student getStudentbyid(int id) throws IOException, JSONException
	{
		String urlStr = baseurl + "/" + db + "/" + id;
		Map<String, Object> param = new HashMap<String, Object>();
		String Studentstr = Urlutil.util_get(urlStr, param);
		return new Student(new JSONObject(Studentstr));
	}	
	
	public static List<Student> getStudents(Map<String, Object> param) throws IOException, JSONException
	{
		String urlStr = baseurl + "/" + db + "/list";
		String Studentliststr = Urlutil.util_get(urlStr, param);
		JSONArray array = new JSONArray(Studentliststr);
		List<Student> Studentlist = new ArrayList<Student>();
		for(int i = 0; i < array.length(); i++)
		{
			JSONObject object = (JSONObject)array.get(i);
			Studentlist.add(new Student(object));
		}
		return Studentlist;
	}
	
	public static String deleteStudentbyid(int id) throws IOException, JSONException
	{
		String urlStr = baseurl + "/" + db + "/" + id;
		Map<String, Object> param = new HashMap<String, Object>();
		String retstr = Urlutil.util_delete(urlStr,param);
		JSONObject jsonObj = new JSONObject(retstr);
		String ret = jsonObj.getString("status");
		return ret;
	}
	
	public static int createStudent(Map<String, Object> param) throws JSONException, IOException
	{
		String urlStr = baseurl + "/" + db;
		String retstr = Urlutil.util_post(urlStr, param);
		JSONObject jsonObj = new JSONObject(retstr);
		int ret = jsonObj.getInt("id");
		return ret;		
	}
	
	public static int updateStudent(int id, Map<String, Object> param) throws IOException, JSONException
	{
		String urlStr = baseurl + "/" + db + "/" + id;
		String retstr = Urlutil.util_put(urlStr, param);
		JSONObject jsonObj = new JSONObject(retstr);
		int ret = jsonObj.getInt("id");
		return ret;			
	}
}
