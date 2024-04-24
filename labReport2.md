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

1. Show the code for your ChatServer, and two screenshots of using /add-message.

For each of the two screenshots, answer the followings three questions:
1. Which methods in your code are called?
2. What are the relevant arguments to those methods, and the values of any relevant fields of the class?
3. How do the values of any relevant fields of the class change from this specific request? If no values got changed, explain why.

By values, we mean specific Strings, ints, URIs, and so on. "abc" is a value, 456 is a value, new URI("http://...") is a value, and so on.)

--------

My code for ChatServer: 

            import java.io.IOException;
            import java.net.URI;
            //import java.io.FileWriter;
            //import java.util.List;
            //import java.io.File;
            //import java.io.FileReader;
            //import java.nio.file.Files;
            //import java.nio.file.Paths;
            
            //Lab report 2 Chat Server
            //If I enter like ".../add-message?s=<string>&user=<string>", the message will be displayed like : "string(user): string(s)"
            
            class Handler implements URLHandler {
              public String handleRequest(URI url){
                    String username = "", message = "";
                    //List<String> outputs;
            
                    if (url.getPath().equals("/")) {
                    return "it is home page! to add message, use the format: /add-message?s=<string>&user=<string>";
                    }
                    else{
                        if (url.getPath().equals("/add-message")) {
                            //FileWriter addSentence = new FileWriter("Thread.txt");
            
                            String[] parameters = url.getQuery().split("&");
                            if (parameters[0].substring(0,2).equals("s=")) {
                                message = parameters[0].substring(2, parameters[0].length());
                            }
                            if (parameters.length==2 && parameters[1].substring(0,5).equals("user=")) {
                                username = parameters[1].substring(5, parameters[1].length());
                            }
                            /*
                            outputs = Files.readAllLines(Paths.get("Thread.txt"));
                            addSentence.write(String.format("%s: %s\n", username, message));
                            addSentence.close();
                            */
                            //return String.join("\n", outputs) + "\n" + String.format("%s: %s\n", username, message);
            
                            return String.format("%s: %s\n", username, message);
                        }
                        return "invalid path!";
                    }
                }
            }
            
            class ChatServer {
              public static void main(String[] args) throws IOException {
            
                  /* 
                  File threadFile = new File("Thread.txt");
                  if(!threadFile.exists()){
                      threadFile.createNewFile();
                  }*/
            
                  if(args.length == 0){
                      System.out.println("Missing port number! Try any number between 1024 to 49151");
                      return;
                  }
                  int port = Integer.parseInt(args[0]);
                  Server.start(port, new Handler());
                }
            }

Note: I was struggled with how I can leave the previous data like `jpolitz: Hello`

   
Part 2
--------


Part 3
--------

Completed: th, 2024  |  Lab due: th, 2024  |  GitHub link: 
