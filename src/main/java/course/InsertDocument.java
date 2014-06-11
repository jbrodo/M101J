package course;

import java.net.UnknownHostException;
import java.util.Arrays;

import org.bson.types.ObjectId;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;

public class InsertDocument {
	public static void main(String[]args) throws UnknownHostException{
		MongoClient client = new MongoClient();
		DB courseDB = client.getDB("course");
		DBCollection collection = courseDB.getCollection("insertText");
		DBObject doc = new BasicDBObject("_id",new ObjectId()).append("x",1);
		DBObject doc2 = new BasicDBObject("_id",new ObjectId()).append("x",3);
		System.out.println(doc);
		collection.insert(Arrays.asList(doc,doc2));
		collection.insert(doc);
		System.out.println(doc);
	}
}
