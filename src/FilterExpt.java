import java.io.PrintWriter;


public class FilterExpt
{

  public static void main(String[] args)
  {
    PrintWriter pen = new PrintWriter(System.out, true);


    // Queries
    pen.println("** Experimenting with Filtering ** \n");
    String queryJSON =
        "{ \"schools\": [ { \"name\" : \"Grinnell College\", \"type\": \"Liberal Arts\", \"numStudents\": 1655 } ,"
            + "{ \"name\" : \"Carlton College\", \"type\": \"Liberal Arts\", \"numStudents\": 2018 }, "
            + "{ \"name\" : \"Vassar College\", \"type\": \"Liberal Arts\", \"numStudents\": 2477 }, "
            + "{ \"name\" : \"Middlebury College\", \"type\": \"Liberal Arts\", \"numStudents\": 2499 }, "
            + "{ \"name\" : \"Wesleyan University\", \"type\": \"Liberal Arts\", \"numStudents\": 3262 }, "
            + "{ \"name\" : \"Harvard University\", \"type\": \"University\", \"numStudents\": 28147 }, "
            + "{ \"name\" : \"Stanford University\", \"type\": \"University\", \"numStudents\": 15877 }, "
            + "{ \"name\" : \"University of Chicago\", \"type\": \"University\", \"numStudents\": 15245 }, "
            + "{ \"name\" : \"University of Iowa\", \"type\": \"University\", \"numStudents\": 31065 }, "
            + "{ \"name\": \"Cornell University\", \"type\": \"University\", \"numStudents\": 21000 } ] }";
    String filtString = "[ { \"name\" : \"Grinnell College\", \"type\": \"Liberal Arts\", \"numStudents\": 1655 } ,"
            + "{ \"name\" : \"Carlton College\", \"type\": \"Liberal Arts\", \"numStudents\": 2018 }, "
            + "{ \"name\" : \"Vassar College\", \"type\": \"Liberal Arts\", \"numStudents\": 2477 }, "
            + "{ \"name\" : \"Middlebury College\", \"type\": \"Liberal Arts\", \"numStudents\": 2499 }, "
            + "{ \"name\" : \"Wesleyan University\", \"type\": \"Liberal Arts\", \"numStudents\": 3262 }, "
            + "{ \"name\" : \"Harvard University\", \"type\": \"University\", \"numStudents\": 28147 }, "
            + "{ \"name\" : \"Stanford University\", \"type\": \"University\", \"numStudents\": 15877 }, "
            + "{ \"name\" : \"University of Chicago\", \"type\": \"University\", \"numStudents\": 15245 }, "
            + "{ \"name\" : \"University of Iowa\", \"type\": \"University\", \"numStudents\": 31065 }, "
            + "{ \"name\": \"Cornell University\", \"type\": \"University\", \"numStudents\": 21000 } ]";
   
    JSONDecoder queryDecoder = new JSONDecoder(queryJSON);
    JSONObject queryObj = queryDecoder.jsonDecode();
    JSONArray farray = (JSONArray) queryObj.get("schools");
    
    JSONArray filtered = farray.filter(Pred.equal("type", new JSONString("University")));
    System.out.println("Universities:\n" + filtered.toString());
    JSONArray filtered2 = farray.filter(Pred.equal("numStudents", new JSONNumber("21000", false)));
    System.out.println("21000 students:\n" + filtered2.toString());
    JSONArray filtered3 = farray.filter(Pred.greater("numStudents", new JSONNumber("5000", false)));
    System.out.println("More than 5000 students:\n" + filtered3.toString());
    JSONArray filtered4 = farray.filter(Pred.not(Pred.greater("numStudents", new JSONNumber("5000", false))));
    System.out.println("Fewer than 5000 students:\n" + filtered4.toString());
    
    

  } // main (String [])
}
