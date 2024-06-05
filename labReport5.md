Lab Report 5
========= 
***

Name: Nao Yoshida |
PID:  A18083203 |
Spring 2024 UCSD - CSE 15L

***

**Part 1 – Debugging Scenario**
--------

Design a debugging scenario, and write your report as a conversation on Piazza (you don't need to make a post). It should have:

1. The original post from a student with a screenshot showing a symptom and a description of a guess at the bug/some sense of what the failure-inducing input is. (Don't actually make the post! Just write the content that would go in such a post)
2. A response from a TA asking a leading question or suggesting a command to try (To be clear, you are mimicking a TA here.)
3. Another screenshot/terminal output showing what information the student got from trying that, and a clear description of what the bug is.
4. At the end, all the information needed about the setup including:

- The file & directory structure needed
- The contents of each file before fixing the bug
- The full command line (or lines) you ran to trigger the bug
- A description of what to edit to fix the bug

You should actually set up and run the scenario from your screenshots. It should involve **at least a Java file and a bash script**. Describing the bug should involve reading some output at the terminal resulting from running one or more commands. Design an error that produces more interesting output than a single message about a syntax or unbound identifier error – showcase some interesting wrong behavior! Feel free to set this up by cloning and breaking some existing code like the grading script or code from class, or by designing something of your own from scratch, etc.

-----

**1. The original post from a student with a screenshot showing a symptom and a description of a guess at the bug/some sense of what the failure-inducing input is. (Don't actually make the post! Just write the content that would go in such a post)**



Hi, I am creating the java file using ArrayList and use functions to display the element of ArrayList. However, I am stacked working on creating JUnit tests. I am trying to call function `printList(ArrayList)` but it says there is an error and I do not figure out how I can remove the red underline on it.

`assertEquals(new int[]{ 23, 0, -14}, list.printList(list));`

I think I have something problem on code in main java rather than java file for testing. However, I do not figure out how I can solve it... I think `list.printList(list)` has problem, and I expect that call function and insert the object itself is problematic. Could you give me some advise? Also, the result run of bash script is following. I see there is problem on line 25, but I think the for loop is correctly implemented since the index of ArrayList is starting from 0.





Could you give me some advice? Thank you!


**2. A response from a TA asking a leading question or suggesting a command to try (To be clear, you are mimicking a TA here.)**


**3. Another screenshot/terminal output showing what information the student got from trying that, and a clear description of what the bug is.**


**4. At the end, all the information needed about the setup including:**

***


**Part 2 – Reflection**
--------

In a couple of sentences, describe something you learned from your lab experience in the second half of this quarter that you didn't know before. It could be a technical topic we addressed specifically, something cool you found out on your own building on labs, something you learned from a tutor or classmate, and so on. It doesn't have to be specifically related to a lab writeup, we just want to hear about cool things you learned!

----

Sentence here:

***

Completed: th, 2024 

Lab due: th, 2024 

GitHub link: 
