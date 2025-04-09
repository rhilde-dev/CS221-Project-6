****************
* Double Linked List Project
* CS221-001
* 4/11/25
* Rylee Hilde
**************** 

OVERVIEW:

 Concisely explain what the program does. If this exceeds a couple
 of sentences, you're going too far. The details go in other
 sections.

 This program goes implements a doubly linked list data structure on an indexed 
 Unsorted List. As well as having an Iterator and List Iterator to go through
 the data.


INCLUDED FILES:

 List the files required for the project with a brief
 explanation of why each is included.

 * IndexedUnsortedList.java - interface file
 * Node.java - source file
 * IUDoubleLinkedList.java - source file
 * ListTester.java - driver file
 * README.txt - this file
//(Check if you need to include GoodList and BadList to run the program)

COMPILING AND RUNNING:

 NOTE: Make sure in ListTester.java that you are testing the DoubleLinkedList
 implementation for this project

 From the directory containing all source files, compile the
 driver class (and all dependencies) with the command:
 $ javac ListTester.java

 Run the compiled class file with the command:
 $ java ListTester

 Console output will give the results after the program finishes.


PROGRAM DESIGN AND IMPORTANT CONCEPTS:

 This is the sort of information someone who really wants to
 understand your program - possibly to make future enhancements -
 would want to know.

 Explain the main concepts and organization of your program so that
 the reader can understand how your program works. This is not a repeat
 of javadoc comments or an exhaustive listing of all methods, but an
 explanation of the critical algorithms and object interactions that make
 up the program.

 Explain the main responsibilities of the classes and interfaces that make
 up the program. Explain how the classes work together to achieve the program
 goals. If there are critical algorithms that a user should understand, 
 explain them as well.
 
 If you were responsible for designing the program's classes and choosing
 how they work together, why did you design the program this way? What, if 
 anything, could be improved? 

 ===============================

 The main concept of my program is the creation of a Doubly Linked List data structure, one that you
 can iterate through and modify the elements of in an effective way. The main advantages I hope to
 provide the user with is a way search, add, remove, etc. without losing efficiency. It should be known
 that with this program, it will use up to three times as much memory in comparison to other data structures
 like a Singly Linked List or an Array List. This is due to the amount of connections and variables the device
 has to keep track of in order to make it a Doubly Linked List.

 My program implements the IndexedUnsortedList interface in order to make sure it has the methods of
 an IndexedUnsortedList, but in a Doubly Linked List data structure. In order to implment this program
 I made the data into what are called nodes. These nodes store the element in their respective spot on
 the list. These nodes, however, have methods that allow them to see and set the next/previous node, increment
 through to the next/previous node, as well as, get/set the element inside the node. As these nodes get added
 they get linked so that they are both pointing at the node before it and the node before it is pointing
 at the node that was added. With this linking of data, we can go through and modify the data in a constant
 way.

 I designed the program the way I did because I focused on functionality. I wanted to minimize code duplication
 while also preserving the functionality of the program. This led to me implementing methods in a way that was
 less effcient than it could be. If anything could be improved, I would probably make my listIterator constructor 
 more effcient by deciding to iterate to an index from the front or back depending on where it is located. Another
 thing I could improve is potentially using my listIterator in some of my main methods to minimize code duplication
 and potentially make it more effcient.

TESTING:

 How did you test your program to be sure it works and meets all of the
 requirements? What was the testing strategy? What kinds of tests were run?
 Can your program handle bad input? Is your program  idiot-proof? How do you 
 know? What are the known issues / bugs remaining in your program?

 ===============================

 I tested my program using the ListTester testing suite, which is included as a driver class in my program labeled
 ListTester.java. In this class, I created about 30 scenarios which tests each method on each scenario given. It will
 tally up all of the tests ran, failed, and passed and give me a percentage of what has passed. The testing strategy was
 to test scenarios for each method in my program to make sure that they all work for the most part. Due to time I was not
 able to implement all possible scenarios for this program, so I did at least one for each method. My program can handle
 bad input as I have exception handling for NoSuchElement, IllegalState, ConcurrentModification, etc. My program is idiot
 proof to the best of my knowledge with the collection of tests that I used, as well as, case handling. There are no known
 issues or bugs remaining in my program.
 

DISCUSSION:
 
 Discuss the issues you encountered during programming (development)
 and testing. What problems did you have? What did you have to research
 and learn on your own? What kinds of errors did you get? How did you 
 fix them?
 
 What parts of the project did you find challenging? Is there anything
 that finally "clicked" for you in the process of working on this project?
 
 ===============================

 A problem that I came across was with my add method for my listIterator. The problem was that I had used my 
 lastReturnedNode as a condition to check for my different cases just like my remove method for the listIterator.
 The problem with this though is that the add method can be called without a lastReturnedNode, so if the 
 lastReturnedNode is null when it's initialized, you can't add anything. I went and got help at the Kount Learning
 Center where they showed me that I was not supposed to be using my LastReturnedNode. To fix this problem I was having
 I used nextNode as a condition and checked the different states as nextNode. This was very challenging for me at first,
 but once I got help it finally clicked how I was supposed to go through the add methods. The rest of the methods that
 I had to implement were pretty easy for me to go through and create. The test creation for the listIterator was quite 
 the challenge at first, but once I parsed through what all the tests did in the example I was given I was able to create
 the tests for two and three element lists just fine.

 
 
EXTRA CREDIT:

 If the project had opportunities for extra credit that you attempted,
 be sure to call it out so the grader does not overlook it.


----------------------------------------------------------------------------

All content in a README file is expected to be written in clear English with
proper grammar, spelling, and punctuation. If you are not a strong writer,
be sure to get someone else to help you with proofreading. Consider all project
documentation to be professional writing for your boss and/or potential
customers.
