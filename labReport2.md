Lab Report 2
========= 
***

Name: Nao Yoshida |
PID:  A18083203 |
Spring 2024 UCSD - CSE 15L

***

Part 1 
--------
Write a web server called `ChatServer` that supports the path and behavior described below. It should keep track of a single string that gets added to by incoming requests. The requests should look like this:

`/add-message?s=<string>&user=<string>`

The effect of this request is to concatenate the string given after `user=`, a colon (`:`), and then the string after `s`, a newline (`\n`), and then respond with the entire string so far. That is, it adds a chat message of the form `<user>: <message>`

So, for example, after

      /add-message?s=Hello&user=jpolitz

The page should show

      jpolitz: Hello

and after

      /add-message?s=How are you&user=yash

the page should show

      jpolitz: Hello 
      yash: How are you

(Some browsers might show this as How%20are%20you with a special character replacing the spaces; don't worry about fixing that for this example. If you want to look it up it has to do with URL encoding, a topic we won't address right now.) You can assume that the s= parameter always comes before the user= parameter, and they are always separated by a & as shown above.

1.Show the code for your ChatServer, and two screenshots of using /add-message.

For each of the two screenshots, answer the followings three questions:
1. Which methods in your code are called?
2. What are the relevant arguments to those methods, and the values of any relevant fields of the class?
3. How do the values of any relevant fields of the class change from this specific request? If no values got changed, explain why.

By values, we mean specific Strings, ints, URIs, and so on. "abc" is a value, 456 is a value, new URI("http://...") is a value, and so on.)

--------



   
Part 2
--------


Part 3
--------

Completed: th, 2024  |  Lab due: th, 2024  |  GitHub link: 
