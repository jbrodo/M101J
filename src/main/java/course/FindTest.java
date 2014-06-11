package course;

import java.net.UnknownHostException;
import java.util.Random;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;

public class FindTest {
	public static void main(String[]args) throws UnknownHostException{
		MongoClient client = new MongoClient();
		DB courseDB = client.getDB("course");
		DBCollection collection = courseDB.getCollection("findTest");
		collection.drop();
		
		for(int i=0;i<10;i++){
			collection.insert(new BasicDBObject("x", new Random().nextInt(100)));
		}
		
		System.out.println("Find one:");
		DBObject one = collection.findOne();
		System.out.println(one);
		
		System.out.println("Find all:");
		DBCursor cursor = collection.find();
		try{
		while(cursor.hasNext()){
			DBObject cur = cursor.next();
			System.out.println(cur);
		}
		}finally{
			cursor.close();
		}
		
		System.out.println("Count:");
		System.out.println(cursor.count());
	}
}
