# Happn technical test

## Technical choices

### Environment
* I chose to use Java 8 as Coraline told me that you use this version.

### Libs
* I used Lombok to avoid writing boilerplate code
* I used JUnit 5 to benefit from Parameterized tests  
* I used the jackson library to easily create JSON response

## Intuition

Second question immediately made me think of the "Top K frequent numbers" question that is an interesting question
 and can be solve in a few different ways (heap, TreeMap or bucket).
 
### Coding choices
* For my unit test, I created mmy test case without relying on my parser as I don't want to couple my test and make
 them rely on each other. 

## Steps
* I first decided to find a way to parse the TSV file. After a little research I found the Univocity-parsers library
 that perfectly does what I want. I added some unit test to assess the correct parsing and creation of objects.
 