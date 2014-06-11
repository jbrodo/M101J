package course;

import java.net.UnknownHostException;
import java.util.Map;
import java.util.Set;

import org.bson.BSONObject;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;

public class SecondoEs {
	public static void main(String[]args) throws UnknownHostException{
		MongoClient client = new MongoClient();
		DB courseDB = client.getDB("students");
		DBCollection collection = courseDB.getCollection("grades");
		
		System.out.println("Find all:");
		DBCursor cursor = collection.find();
		try{
			while(cursor.hasNext()){
				DBObject cur = cursor.next();
				System.out.println(cur);
				DBObject o = new BasicDBObject()
				.append("type", cur.get("type"))
				.append("student_id", cur.get("student_id"));
				System.out.println("trovato: "+o);
				System.out.println("cercato: "+cur);
				DBCursor subcursor = collection.find(o);
				try{
					while(subcursor.hasNext()){
						DBObject l =  subcursor.next();
						if((Double)l.get("score")>(Double)cur.get("score")){
							collection.remove(cur);
							System.out.println("cancellato cur: "+cur);
							System.out.println("non cancellato l: "+l);
						}
//						else{
//							System.out.println("cancellato l: "+l);
//							System.out.println("non cancellato cur: "+cur);
//							collection.remove(l);
//						}
					}
				}finally{
					subcursor.close();
				}
			}
		}finally{
			cursor.close();
		}

		System.out.println("Count:");
		System.out.println(cursor.count());
//		cursor.sort(new BasicDBObject().append("", val));
	}
}
