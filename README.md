Queriosity: A JSON Querying Library
===========

Large datasets are now being rendered into JSON format, which can provide an alternative to traditional databases. Our library can read in formatted and unformatted JSON structures from files or system input, and parse them into a JSON class that users can then query using a built-in querying language.

JSON Querying Language
----------------------
JSONLibrary contains a small querying language, similar to SQL. This is how it can be used.

Say we have a small dataset in our JSON String, e.g.:

```javascript
{
    "schools": [
        {
            "name": "Grinnell College",
            "type": "Liberal Arts",
            "numStudents": 1655,
            "ivyLeague": false
        },
        {
            "name": "Carlton College",
            "type": "Liberal Arts",
            "numStudents": 2018,
            "ivyLeague": false
        },
        {
            "name": "Vassar College",
            "type": "Liberal Arts",
            "numStudents": 2477,
            "ivyLeague": false
        },
        {
            "name": "Middlebury College",
            "type": "Liberal Arts",
            "numStudents": 2499,
            "ivyLeague": false
        },
        {
            "name": "Wesleyan University",
            "type": "Liberal Arts",
            "numStudents": 3262,
            "ivyLeague": false
        },
        {
            "name": "Harvard University",
            "type": "University",
            "numStudents": 28147,
            "ivyLeague": true
        },
        {
            "name": "Stanford University",
            "type": "University",
            "numStudents": 15877,
            "ivyLeague": false
        },
        {
            "name": "Cornell University",
            "type": "University",
            "numStudents": 21000,
            "ivyLeague": true
        },
    ]
}
```

Instantiate the decoder, and decode the String:

```java
JSONDecoder queryDecoder = new JSONDecoder (eduInstitutions);
queryDecoder.jsonDecode ();
```

Query the JSONDecoder using the query language:

```java
queryDecoder.query("select numStudents from schools where type = 'Liberal Arts'");
```

This returns:

```javascript
[1655, 2018, 2477, 2499, 3262]
```

You can also use comparitors, e.g.:

```java
queryDecoder.query("select name from schools where numStudents < 5000");
```

Which returns:

```javascript
[Grinnell College, Carlton College, Vassar College, Middlebury College, Wesleyan University]
```

Finally, you can also use a constant (true, false, null), e.g., remind yourself whether Cornell is in the Ivy League

```java
queryDecoder.query("select name from schools where numStudents < 5000");
```

Which returns:

```javascript
[true]
```

Using the Queriosity User Interface (QUI)
--------------------------

We have built in a small user interface for querying datasets. To use it, simply instantiate the user interface class, like this:

```java
new QueryUserInterface (queryDecoder);
```

This will open an input stream for users to write query statements. This will take place in a dialog:

```
* Welcome to the Queriosity User Interface (QUI) *
Type in any select query, and you will receive the filtered data. Type 'end' to quit.
```

You can simple type in your query, and receive the data result:

```
select name from schools where ivyLeague = true
```

Which returns:
```javascript
[Harvard University, Cornell University, Dartmouth College, Brown University, Princeton University, Columbia University, Yale University]

```

Accessing the Raw Objects
--------------------------
Another need while querying the dataset might be to access the entire object that fits your conditions. For this, we have a where(), whereLess(), and whereGreater() methods. whereLess() and whereGreater() are designed to compare integers.

To use this functionality, create an object that we can use to query with
```java
JSONObject queryObj = queryDecoder.jsonDecode ();
```

### E.g. 1 Query all of the liberal arts colleges

Let's get an ArrayList with all of the liberal arts colleges:
```java
String liberalArts = ((JSONArray)queryObj.get("schools")).where("type", "Liberal Arts").toString ();
```

Given our data set, this returns:
```javascript
[{
    "name": Grinnell College,
    "type": Liberal Arts,
    "numStudents": 1655
    }, {
    "name": Carlton College,
    "type": Liberal Arts,
    "numStudents": 2018
    }, {
    "name": Vassar College,
    "type": Liberal Arts,
    "numStudents": 2477
    }, {
    "name": Middlebury College,
    "type": Liberal Arts,
    "numStudents": 2499
    }, {
    "name": Wesleyan University,
    "type": Liberal Arts,
    "numStudents": 3262
}]
```

### E.g. 12 Query all of the big institutions

What if we only wanted to find institutions that had more than 25,000 students? Use this:

```java
String big = ((JSONArray)queryObj.get("schools")).whereGreater ("numStudents", 25000).toString ();
```

Given our dataset, this produces:
```javascript
[{
    "name": Harvard University,
    "type": University,
    "numStudents": 28147
    }, {
    "name": University of Iowa,
    "type": University,
    "numStudents": 31065
}]
```