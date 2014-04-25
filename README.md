JSONLibrary
===========

A Java library for encoding and decoding JSON structures. It is robust, can work with formatted JSON Strings (which include spaces, tabs, and newline characters), and has a small querying language built in!

JSON Querying Language
----------------------
JSONLibrary contains a small querying language. This is how it can be used.

Say we have a small dataset in our JSON String, e.g.:

```javascript
String eduInstitutions = "{ \"schools\": [ 
    { \"name\" : \"Grinnell College\", \"type\": \"Liberal Arts\", \"numStudents\": 1655 } , 
    { \"name\" : \"Carlton College\", \"type\": \"Liberal Arts\", \"numStudents\": 2018 }, 
    { \"name\" : \"Vassar College\", \"type\": \"Liberal Arts\", \"numStudents\": 2477 }, 
    { \"name\" : \"Middlebury College\", \"type\": \"Liberal Arts\", \"numStudents\": 2499 }, 
    { \"name\" : \"Wesleyan University\", \"type\": \"Liberal Arts\", \"numStudents\": 3262 }, 
    { \"name\" : \"Harvard University\", \"type\": \"University\", \"numStudents\": 28147 }, 
    { \"name\" : \"Stanford University\", \"type\": \"University\", \"numStudents\": 15877 }, 
    { \"name\" : \"University of Chicago\", \"type\": \"University\", \"numStudents\": 15245 }, 
    { \"name\" : \"University of Iowa\", \"type\": \"University\", \"numStudents\": 31065 }, 
    { \"name\": \"Cornell University\", \"type\": \"University\", \"numStudents\": 21000 } 
] }";
```
        
Instantiate the decoder:

```java
JSONDecoder queryDecoder = new JSONDecoder (eduInstitutions);
```

Create an object that we can use to query with
```java
JSONObject queryObj = queryDecoder.jsonDecode ();
```

Query using "get()", "where", "whereLess", and "whereGreater". whereLess and whereGreater are designed to comapre integers.

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