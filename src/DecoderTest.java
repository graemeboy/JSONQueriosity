import static org.junit.Assert.*;

import org.junit.Test;

/**
 * A set of simple unit tests for JSONDecode
 */
public class DecoderTest
{

  JSONDecoder decoder = new JSONDecoder ("");
  static JSONConstant t = new JSONConstant ('t');
  static JSONConstant f = new JSONConstant ('f');

  @Test
  public void
    testTrue ()
      throws Exception
  {
    String goodDay = "{\"good day?\": true}";
    JSONDecoder decoder = new JSONDecoder (goodDay);
    JSONObject obj = decoder.jsonDecode ();

    assertEquals ("testing true", true, obj.get ("good day?").get ());
  }// testTrue

  @Test
  public void
    testFalse ()
      throws Exception
  {
    String eatingGrapes = "{\"eating grapes?\": false}";
    JSONDecoder decoder = new JSONDecoder (eatingGrapes);
    JSONObject obj = decoder.jsonDecode ();

    assertEquals ("testing false", false, obj.get ("eating grapes?").get ());
  }// testFasle

  @Test
  public void
    testNull ()
      throws Exception
  {
    String catsName = "{\"cat's name\": null}";
    JSONDecoder decoder = new JSONDecoder (catsName);
    JSONObject obj =  decoder.jsonDecode ();

    assertEquals ("testing null", null, obj.get ("cat's name").get ());

  }// testNull

  @Test
  public void
    testNumber ()
      throws Exception
  {
    String idealScore = "{\"ideal exam score\": 100}";
    JSONDecoder decoder = new JSONDecoder (idealScore);
    JSONObject obj = decoder.jsonDecode ();

    assertEquals ("testing number", 100,
                  ((JSONNumber) obj.get ("ideal exam score")).getInteger ());
  }// testNumber

  /*
   * Added on Friday; no other code was changed.
   */

  @Test
  public void
    testString ()
      throws Exception
  {
    String json = "{\"name\": \"Graeme\"}";
    JSONDecoder decoder = new JSONDecoder (json);
    JSONObject obj = decoder.jsonDecode ();

    assertEquals ("testing string", "Graeme", obj.get ("name").get ());
  }// testString

  @Test
  public void
    testNested ()
      throws Exception
  {
    String scores = "{\"person\":{\"firstname\":\"Graeme\", \"age\": 23}}";
    JSONDecoder decoder = new JSONDecoder (scores);
    // This is messy, so we need to add a wrapper that will take care of this
    // stuff. It ought to be one operation to get the correct data and type.
    JSONObject obj =  decoder.jsonDecode ();
    JSONObject person = (JSONObject) obj.get ("person");
    int idealScore = ((JSONNumber) person.get ("age")).getInteger ();
    assertEquals ("testing nested", 23, idealScore);
  }// testNested

}
