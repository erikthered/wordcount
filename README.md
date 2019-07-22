# Uncommon Words Count

This is a solution for the prompt in this [gist](https://gist.github.com/toddjtidwell/4da226c401f417d3c41f1ebec07d1b91)

Given two arguments:
1. Path for a text file containing a list of common words
2. Path for a text file containing a text file

This program will compute the count of each of the words contained in the file
at path #2, excluding the words defined in the file at path #1.

## Usage

I setup the project with Gradle, so the easiest way to run is the following:

`./gradlew run --args='"src/test/resources/common_words.txt" "src/test/resources/alice_in_wonderland.txt"'`

## Approach

I used streams to break each line of the text file down into a stream of 
Strings, and then accumulated the words into a Map<String, Integer> while
incrementing the count on duplicates. I did try to normalize by shifting
everything to lower case and stripping out punctuation/special characters.

## Areas for Potential Improvement

Sanitizing the text was done pretty naively, I didn't think it was worth the 
effort right now to do thing like balancing quotes and trying not to exclude
contractions.

The speed could potentially be improved by using parallel streams and 
collecting to a ConcurrentHashMap.
