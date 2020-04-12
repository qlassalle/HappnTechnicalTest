# Happn technical test

## How to run

Clone this project and import it in your favorite IDE. Install dependencies with a `mvn install`. You can provide
 program arguments to the Main method as specified in the PDF:
 
` --nbpoi '{"min_lat":6.5,"min_lon":-7}' `
 
 ` --densest '{"n":2}' `
 
If you don't provide any argument, a web service is available with two routes with the same behavior of the program
 arguments. Both are available for POST requests accepting objects defined in the input package.
* `/nbpoi` 
* `/densest`  

## Technical choices

### Environment
* I chose to use Java 8 as Coraline told me that you mainly use this version.
* I used maven for managing dependencies

### Libs
* I used the univocity-parsers library to parse the TSV file
* I used Lombok to avoid writing boilerplate code
* I used JUnit 5 to benefit from Parameterized tests  
* I used the jackson library to easily create JSON response
* I used the Spark library to provide a simple web service 

## Intuition

The first question was pretty straightforward. I simply decided to check if a point of interest was above the min_lat
and min_lon and incremented a counter.
 
However, the second question immediately made me think of the "Top K frequent numbers" question that is an interesting
question and can be solved in a few different ways (with a (min/max)heap, a TreeMap or a bucket). I was familiar with
this problem (solved it on Leetcode a few month ago) and used a heap to solve it at the time. This time, I used a
bucket that provides great performances. TreeMap might be the easiest way to implement this as we could rely on the
natural ordering of the SortedMap interface. Implementation with a heap is quite nice too and pretty straightforward
. The implementation with a bucket however is less intuitive but has great performances. After having created
the frequency map (needed in all implementations), we simply add at each index (frequency) the grid zones. This
operation is O(n) where n is the size of our map. After this, we have the sorted list but in reverse order. That's
why we iterate again on the list in reverse order as the top frequency will be at the end of the list. This operation
, in the worst case, is O(n) too.
The caveat of this implementation is that we have to check for nulls as there might be "gaps" in our array because
some frequency might not have any zone. We also have to check that we do not add more elements than wanted.

## Architecture
 
I decoupled my application in five packages:
### Controller
I created a controller layer to decouple services and controller responses. It allows us to have services that only
returns the expected result and isn't tied to any implementation (returning JSON, returning XML or any output). The
controller is in charge of deciding of the correct output. In our example, it simply calls the relevant service and
returns the response, wrapped in JSON.
    
### Input
I created an input package that contains simple classes to handle deserialization of inputs as defined in the
handout:
 
 ` --nbpoi '{"min_lat":6.5,"min_lon":-7}' `
 
 ` --densest '{"n":2}' `
     
For the web service, I decided to take the same objects as input. 

### Model
I created a model layer to contain all the classes representing the objects such as PointOfInterest or a Zone in our
"world". Classes in the model package should only be used by the classes in our _service_ package.

### Service
I created a service layer to contain all the classes embedding the logic of this little app. They should only be
 called by the controllers and other services.

### Util
I created a util layer to contain all the utility classes that I need. It contains a JsonFormatter which allows us to
create JSON objects easily. It also contains our TSV parser permitting us to parse a TSV file.
 
## Coding choices
* For my unit tests, I created my test cases without relying on my parser as I don't want to couple my tests and make
 them rely on each other.
* I decided not to test the controller part as it simply does the equivalent of a writeValueAsString(Object o)
relying on the Jackson library by calling my JsonFormatter class. I don't want to test the lib therefore I didn't add
test cases for this part. **This point can be discussed and may vary between developers**.
* I decided to use post requests for the two routes of my web service as this allows me to receive JSON inputs.