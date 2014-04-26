import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class QueryUserInterface
{
  // Fields
  PrintWriter pen;
  BufferedReader br;
  JSONDecoder database;

  public QueryUserInterface (JSONDecoder databaseIn)
  {

    pen = new PrintWriter (System.out, true);
    br = new BufferedReader (new InputStreamReader (System.in));

    pen.println ("* Welcome to the Queriosity User Interface (QUI) *");
    this.database = databaseIn;

    initInterface ();

  } // QueryUserInterface()

  public void
    initInterface ()
  {

    if (this.database.obj.keys.size () > 0)
      {
        this.pen.println ("Type in any select query, and you will receive the filtered data. Type 'end' to quit.");
        String query;
        String result;
        try
          {
            while (!(query = br.readLine ()).equals ("end"))
              {
                result = database.query (query);
                this.pen.println (result);
              }
          }
        catch (Exception e)
          {
            this.pen.println ("There seems to have been an error. Please try restarting the interface.");
          } // while
      } // if
    else
      {
        pen.println ("You have no object in your database. Please load some objects and try again.");
      } // else
  }

}
