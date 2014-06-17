package course;

import java.net.UnknownHostException;
import java.util.ListIterator;

import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;

public class TerzoEsercizio31 {
	public static void main(String[]args) throws UnknownHostException{
		MongoClient client = new MongoClient();
		DB courseDB = client.getDB("school");
		DBCollection collection = courseDB.getCollection("students");
		
		System.out.println("Find all:");
		DBCursor cursor = collection.find();
		try{
			while(cursor.hasNext()){
				DBObject cur = cursor.next();
				System.out.println(cur);

				System.out.println("cercato: "+cur);
				BasicDBList dbobjvet = (BasicDBList) cur.get("scores");
				ListIterator<Object> g = dbobjvet.listIterator();
				DBObject primoHW = null;
				DBObject daEliminareHW = null;
				boolean primo = true;
				while(g.hasNext()){
					DBObject e = (DBObject) g.next();
					System.out.println(e);
					if(primo && e.get("type").equals("homework")){
						primo=false;
						primoHW = e;
					}
					if(!primo && e.get("type").equals("homework")){
						if((Double)primoHW.get("score")<(Double)e.get("score")){
							daEliminareHW = primoHW;
						}else{
							daEliminareHW = e;
						}
					}
				}
				dbobjvet.remove(daEliminareHW);
				DBObject o = new BasicDBObject()
				.append("_id", cur.get("_id"));
				System.out.println("trovato: "+o);
				collection.update(o, cur);
//				DBCursor subcursor = collection.find(o);
//				try{
//					while(subcursor.hasNext()){
//						DBObject l =  subcursor.next();
//						if((Double)l.get("score")>(Double)cur.get("score")){
////							collection.remove(cur);
//							System.out.println("cancellato cur: "+cur);
//							System.out.println("non cancellato l: "+l);
//						}
////						else{
////							System.out.println("cancellato l: "+l);
////							System.out.println("non cancellato cur: "+cur);
////							collection.remove(l);
////						}
//					}
//				}finally{
//					subcursor.close();
//				}
			}
		}finally{
			cursor.close();
		}

		System.out.println("Count:");
		System.out.println(cursor.count());
//		cursor.sort(new BasicDBObject().append("", val));
	}
}
