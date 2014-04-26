import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;

/*
 * Here are a few experiments that I made to see how the JSON decoder was working.
 */
public class JSONExpt
{

  public static void
    main (String[] args)
  {
    PrintWriter pen = new PrintWriter (System.out, true);

    // Queries
    File file = new File("data/schools.json");
    try
      {
        FileReader fr = new FileReader (file);
        
        
      }
    catch (FileNotFoundException e)
      {
        e.printStackTrace();
      }
    
    // Queries
    pen.println("** Experimenting with Query Language ** \n");
    String queryJSON = "{ \"schools\": [ { \"name\" : \"Grinnell College\", \"type\": \"Liberal Arts\", \"numStudents\": 1655 } ,"
        + "{ \"name\" : \"Carlton College\", \"type\": \"Liberal Arts\", \"numStudents\": 2018 }, "
        + "{ \"name\" : \"Vassar College\", \"type\": \"Liberal Arts\", \"numStudents\": 2477 }, "
        + "{ \"name\" : \"Middlebury College\", \"type\": \"Liberal Arts\", \"numStudents\": 2499 }, "
        + "{ \"name\" : \"Wesleyan University\", \"type\": \"Liberal Arts\", \"numStudents\": 3262 }, "
        + "{ \"name\" : \"Harvard University\", \"type\": \"University\", \"numStudents\": 28147, \"ivyLeague\": true }, "
        + "{ \"name\" : \"Stanford University\", \"type\": \"University\", \"numStudents\": 15877, \"ivyLeague\": false }, "
        + "{ \"name\" : \"University of Chicago\", \"type\": \"University\", \"numStudents\": 15245, \"ivyLeague\": false }, "
        + "{ \"name\" : \"University of Iowa\", \"type\": \"University\", \"numStudents\": 31065, \"ivyLeague\": false }, "
        + "{ \"name\": \"Cornell University\", \"type\": \"University\", \"numStudents\": 21000, \"ivyLeague\": true } ] }";
    
    
    JSONDecoder queryDecoder = new JSONDecoder (queryJSON);
    JSONObject queryObj = queryDecoder.jsonDecode ();
    
    String universities = ((JSONArray)queryObj.get("schools")).where("type", "University").toString ();
    String liberalArts = ((JSONArray)queryObj.get("schools")).where("type", "Liberal Arts").toString ();
    
    String small = ((JSONArray)queryObj.get("schools")).whereLess ("numStudents", 2000).toString ();
    String big = ((JSONArray)queryObj.get("schools")).whereGreater ("numStudents", 25000).toString ();
    String liberalNames = ((JSONArray)queryObj.get("schools")).select("name", "equals", "type", "Liberal Arts").toString ();
    String liberalSizes = ((JSONArray)queryObj.get("schools")).select("numStudents", "equals", "type", "Liberal Arts").toString ();
    
//    pen.println("* Universities are: * \n" + universities);
//    pen.println("* Liberal Arts Colleges are: * \n" + liberalArts);
    
    pen.println("* Experimenting with the Query Language\n");
    String q1 = ("select name from schools where type = 'Liberal Arts'");
    pen.println("Query 1: " + q1);
    pen.println("Result: " + queryDecoder.query (q1));
    String q2 = ("select name from schools where numStudents < 2000");
    pen.println("Query 2: " + q2);
    pen.println("Result: " + queryDecoder.query (q2));
    String q3 = ("select name from schools where ivyLeague = true");
    pen.println("Query 3: " + q3);
    pen.println(queryDecoder.query (q3));
    pen.println("\n");
    
//    pen.println("* Small Colleges are: * \n" + small);
//    pen.println("* Big Universities are: * \n" + big);
//    
//    pen.println("* Liberal Arts College Names are: * \n" + liberalNames);
//    pen.println("* Liberal Arts College Sizes are: * \n" + liberalSizes);
    
    //pen.println("\n** Experimenting with Getting Values from Complex Objects ** \n");
    String json = "{\n\"person\":\n{\"first\" :\n \"Clark\" , \"last\" : \"Kent\","
                  + "\"age\":23,\"class year\":2014, \"male\":true,"
                  + "\"friends\": [ \"Sam\",\"Joe\", \"Lilly\" ] , \"gpa\": 3.92,\"hasCat\": true, \"crimes\": 0 }}";

    JSONDecoder decoder = new JSONDecoder (json);

    JSONObject obj = (JSONObject) decoder.jsonDecode ();
    pen.println (obj.toString ());

    JSONObject person = (JSONObject) obj.get ("person");

    // Print out names
    pen.println ("First name is " + person.get ("first"));
    pen.println ("Last name is " + person.get ("last"));
    // Print out age
    pen.println ("Age is " + person.get ("age"));
    // Perform arithmetic on age
    int age = ((JSONNumber) person.get ("age")).getInteger ();
    pen.println ("Double age is " + age * 2);
    // Print out class year
    int classyear = ((JSONNumber) person.get ("class year")).getInteger ();
    pen.println ("Class year is " + classyear);
    // Do arithmetic on class year
    pen.println ("Age + class year is " + (age + classyear));
    // Print out booleans: is male and has cat
    pen.println ("Is male? " + person.get ("male"));
    pen.println ("Has a cat? " + person.get ("hasCat?"));
    // Friends?
    JSONArray friends = (JSONArray) person.get ("friends");
    int numFriends = friends.size ();
    pen.println ("Number of friends: " + numFriends);
    pen.println ("First listed friend: " + friends.get (0).toString ());
    pen.println ("Last listed friend: "
                 + friends.get (numFriends - 1).toString ());
    
  } // main (String [])
}
