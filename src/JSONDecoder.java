public class JSONDecoder
{
  String jsonString;
  int i;
  JSONObject obj;

  // Constructor
  public JSONDecoder (String stringIn)
  {
    this.i = 0;
    this.jsonString = stringIn;
  } // JSONDecoder ()

  /**
   * Parses the first object found in the jsonString
   * 
   * @return the parsed object
   */
  public JSONObject
    jsonDecode ()
  {
    return obj = parseObject ();
  } // jsonDecode()

  /**
   * function nextChar, increments i before returning
   * 
   * @return character at index i + 1 of jsonString
   * @post this.i is incremented
   */
  public char
    nextChar ()
  {
    return this.jsonString.charAt (++this.i);
  } // nextChar

  /**
   * function curCharInc, returns the current character and then increments i by
   * one
   * 
   * @return character at index i of jsonString
   * @post: increments i after returning the character at index i of jsonString
   */
  public char
    curCharInc ()
  {
    return this.jsonString.charAt (this.i++);
  } // curCharInc ()

  /**
   * functoin currentChar
   * 
   * @return the character at index i of jsonString
   */
  public char
    currentChar ()
  {
    return this.jsonString.charAt (this.i);
  } // currentChar

  /**
   * function incChar, Increments i and returns the value
   * 
   * @return the incremented value of i
   * @post this.i is incremented
   */
  public int
    incChar ()
  {
    return ++this.i;
  } // incChar()

  /**
   * Parses the value in the key-pair of a JSON object. Values are preceeded by
   * colons.
   * 
   * @return JSONVal
   * @pre value is either string, number, object, array, true, false, or null
   * @post the parse function for the type encountered is called
   */
  public JSONVal
    parseVal ()
  {
    // Values are always preceeded by a colon.
    incChar (); // move past the colon.
    trim (); // remove whitespace after '"' or '['

    if (currentChar () == ':')
      {
        incChar (); // move past ':'
      }
    trim (); // remove whitespace after ':'

    char ch;
    ch = currentChar ();
    /*
     * According to json.org, the acceptable types are: string, number, object,
     * array, true, false, and null
     */
    switch (ch)
      {
        case '0': // numbers, 0-9
        case '1':
        case '2':
        case '3':
        case '4':
        case '5':
        case '6':
        case '7':
        case '8':
        case '9':
        case '-': // for negative numbers
          return parseNumber ();
        case '"': // string
          return parseString ();
        case '[': // array
          return parseArray ();
        case '{': // new object
          return parseObject ();
          // True
        case 't': // has to be true
        case 'f': // has to be false
        case 'n': // has to be null
          return parseConstant ();
      } // switch
    return null;
  }

  /**
   * parseObject Used to parse JSON objects
   * 
   * @return JSONObject
   * @post all key and value pairs (seperated by commas) will be added
   */
  public JSONObject
    parseObject ()
  {
    JSONObject object = new JSONObject ();
    char ch;
    do
      {
        if (currentChar () == ',' || currentChar () == '{')
          {
            incChar (); // move past ',' or '{'
            trim ();
          } // if
        else if (currentChar () == '}')
          {
            return null;
          } // else if
        if (currentChar () == '"')
          {
            object.addKey (parseKey ());
          } // if
        trim (); // after '"'
        object.addVal (parseVal ()); // this will recurse if objects are inside
        ch = currentChar (); // reset char to current
        if (ch != '}' && ch != ',')
          {
            incChar ();
          } // if
        trim ();
      } // do
    while (currentChar () == ',');

    return object;
  } // parseObject

  /**
   * While there is whitespace ahead, increment char
   * 
   * @post this.i is incremented by the number of consecutive whitespaces,
   *       newlines, and then whitespaces again (as is common in formatted JSON)
   */
  public void
    trim ()
  {
    trimSpace ();
    trimLines ();
    trimSpace ();
  } // trim ()

  /**
   * Trims whitespace
   * 
   * @post this.i is incremented by the number of consecutive whitespaces
   */
  public void
    trimSpace ()
  {
    while (currentChar () == ' ')
      {
        incChar ();
      } // while
  } // trim space

  public void
    trimLines ()
  {
    while (currentChar () == '\n')
      {
        incChar ();
      } // while
  }

  public JSONConstant
    parseConstant ()
  {
    char ch = currentChar ();
    while (currentChar () != '}' && nextChar () != ',')
      // scan past the constant value
      ; // no Operation
    return new JSONConstant (ch);
  } // parseSpecial

  /**
   * 
   * @return a JSONString character containing
   */
  public JSONString
    parseString ()
  {
    return new JSONString (parseStringInner ());
  } // parseString ()

  /**
   * Parse the array
   * 
   * @return JSONArray
   */
  public JSONArray
    parseArray ()
  {
    JSONArray array = new JSONArray ();
    while (currentChar () != ']')
      {
        array.add (parseVal ());
        if (currentChar () == '"')
          {
            incChar ();
          } // if
        trim ();
        // End of objects can be skipped once their objects are parsed
        if (currentChar () == '}')
          {
            incChar ();
          } // if
        trim ();
      } // while
    return array;
  } // parseArray()

  // select name from schools where type = 'Liberal Arts'

  /*
   * { schools: [{ name: "grinnell", numStudents: 2500 }, { // carlton,
   * numStudents: 1500 }, ] }
   */

  // "0select 1name 2from 3schools 4where 5type 6= [7]2000"

  /**
   * "is" is for equality. (<, >) are for numbers.
   * 
   * @param queryIn
   * @return
   */
  public String
    query (String queryIn)
  {

    // ((JSONArray)queryObj.get("schools")).select("name", "equals", "type",
    // "Liberal Arts").toString ();
    String[] args = queryIn.split (" ");

    if (args[0].equalsIgnoreCase ("select"))
      {

        int i = 7;
        
        String temp = "";
        String val;
        
        // 'Liberal Arts' => args[7] = 'Liberal, args[8] = Arts'
        // System.out.println(args[7]);
        if (args[i].charAt (0) == '\'')
          {
            val = args[i].substring (1, args[i].length ());
            // The use is checking a string, which can have multiple words.
            // E.g. 'Liberal Arts'
            // Go through each subsequent word, and check to see whether that
            // word
            // has a "'" at the end. If it does, then we stop adding it to the
            // val variable.
            // Each time we do this, we must add the word, without the quotation
            // mark, to val.
            
            while ((++i < args.length) && (temp = args[i]).charAt (temp.length () - 1) != '\'')
              {
                // Concatenate the values, and add a space, because they are
                // seperate words.
                val += ' ' + temp;
              } // while
            val += ' ' + temp.substring (0, temp.length () - 1);

          } // if
        else
          {
            val = args[7];
          }
        
//        System.out.println("Val: " + val + " and comparator is " + args[6]);
        return ((JSONArray) this.obj.get (args[3])).select (args[1], args[6],
                                                            args[5], val)
                                                   .toString ();
      } // if

    return "No results";
  } // query ()

  /**
   * function parseNumber
   * 
   * @return a JSONNumber object that contains the value of the number
   */
  public JSONNumber
    parseNumber ()
  {
    // As with string, go until reach a '"' and then get substring
    int startingIndex = this.i;
    char ch;
    boolean decimal = false;
    while ((ch = currentChar ()) != ',' && currentChar () != '}')
      {
        if (ch == '.')
          {
            decimal = true; // contains a ., therefore is decimal
          } // if
        ch = nextChar ();
      } // while
    return new JSONNumber (this.jsonString.substring (startingIndex, this.i),
                           decimal);
  } // parseNumber()

  /**
   * function parseStringInner
   * 
   * @return the value within the string quotes
   * @post this.i is incremented by the number of characters within the string
   */
  public String
    parseStringInner ()
  {
    String stringOut = "";
    int startingIndex = incChar (); // skip the first character

    while (nextChar () != '"')
      ; // No operation.
    stringOut = this.jsonString.substring (startingIndex, this.i);

    return stringOut;
  } // parseStringInner()

  /**
   * Function, parse key
   * 
   * @return the key of the key-value pair
   */
  public String
    parseKey ()
  {
    return parseStringInner (); // this is the key
  } // parseKey (String)
}
