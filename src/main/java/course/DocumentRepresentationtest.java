package course;

import java.util.Arrays;
import java.util.Date;

import com.mongodb.BasicDBObject;

public class DocumentRepresentationtest {
	public static void main(String[] args){
		BasicDBObject doc = new BasicDBObject();
		doc.put("userName", "jyome");
		doc.put("birthDate", new Date(1231231232));
		doc.put("programmer", true);
		doc.put("age", 8);
		doc.put("languages", Arrays.asList("Java","c++"));
		doc.put("address", new BasicDBObject("street","20 main").append("town", "westfield").append("zip", "56789"));
		
		BasicDBObject doc2 =new BasicDBObject("_id", "user1").append("interests", Arrays.asList("basketball", "drumming"));

	}
}
