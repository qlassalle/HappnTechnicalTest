# Happn technical test

## Technical choices

* I chose to use Java 8 as Coraline told me that you use this version.
* I used Lombok to avoid writing boilerplate code
* I used JUnit 5 to benefit from Parameterized tests  
* I used the jackson library to easily create JSON response

## Intuition

## Steps
* I first decided to find a way to parse the TSV file. After a little research I found the Univocity-parsers library
 that perfectly does what I want. I added some unit test to assess the correct parsing and creation of objects.
 