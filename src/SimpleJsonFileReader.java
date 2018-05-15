import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.IOUtils;

import com.google.gson.stream.JsonReader;

// Reads json file, parse it and print to the console the content of the array

public class SimpleJsonFileReader {

	public static void main(String[] args) {
		
		try {
			
			InputStream input = new FileInputStream("array.json");
			String json = IOUtils.toString(input);
			System.out.println(json);
			JsonReader reader = new JsonReader(new StringReader(json));
			readArray(reader);
			
		} catch(FileNotFoundException fnfe) {
			 fnfe.printStackTrace(); 
	      } catch(IOException e) { 
	         e.printStackTrace();
	      } catch (IllegalStateException ise) {
	    	  ise.printStackTrace();
	      }
	}
	
	public static List<Student> readArray(JsonReader reader) throws IOException {
		List<Student> student = new ArrayList<Student>();
		reader.beginArray();
		while (reader.hasNext()) {
			student.add(read(reader));
		}
			reader.endArray();
	return student;
	}
	
	public static Student read(JsonReader reader) throws IOException {
		
		String name = null;
		
		reader.beginObject();
		while (reader.hasNext()) {
			String person = reader.nextName();
			
			if(person.equals("name")) {
				person = reader.nextString();
			}
			System.out.print( person + " ");
		}		
		reader.endObject();	
	return new Student(name);
	}
	
}
