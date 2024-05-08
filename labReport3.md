Lab Report 3
========= 
***

Name: Nao Yoshida |
PID:  A18083203 |
Spring 2024 UCSD - CSE 15L

***

Part 1 - Bugs
--------

Choose one of the bugs from week 4's lab.

Provide:

1. A failure-inducing input for the buggy program, as a **JUnit test** and any associated code (write it as a code block in Markdown).
2. An input that doesn't induce a failure, as a **JUnit test** and any associated code (write it as a code block in Markdown).
3. The symptom, as the output of running the two tests above (provide it as a **screenshot** -- one test should pass, one test should fail).
4. The bug, as the before-and-after code change required to fix it (as two **code blocks** in Markdown).
5. Briefly describe (2-3 sentences) why the fix addresses the issue.

***

The bugs which I choosed from week 4's lab : `reversed()` function from `ArrayExamples.java`

        // Returns a *new* array with all the elements of the input array in reversed order
        static int[] reversed(int[] arr) {
              int[] newArray = new int[arr.length];
              for(int i = 0; i < arr.length; i += 1) {
                    arr[i] = newArray[arr.length - i - 1];
              }
              return arr;
        }

<b>1.  A failure-inducing input for the buggy program, as a **JUnit test** and any associated code (write it as a code block in Markdown).</b>

JUnit test code:

        // reversed()
        @Test
        public void testReversedMyExample() {
            int[] input1 = {-1, 3, 5, 0};
        assertArrayEquals(new int[]{0, 5, 3, -1}, ArrayExamples.reversed(input1));
        }

Note that this returns `failure` due not to match expected and actual outputs:

        yoshidanao@yoshidanaonoMacBook-Pro lab3 % javac -cp .:lib/hamcrest-core-1.3.jar:lib/junit-4.13.2.jar *.java
        yoshidanao@yoshidanaonoMacBook-Pro lab3 % java -cp .:lib/hamcrest-core-1.3.jar:lib/junit-4.13.2.jar org.junit.runner.JUnitCore ArrayTests
        JUnit version 4.13.2
        .E
        Time: 0.004
        There was 1 failure:
            1) testReversedMyExample(ArrayTests)
            arrays first differed at element [1]; expected:<5> but was:<0>
                    at org.junit.internal.ComparisonCriteria.arrayEquals(ComparisonCriteria.java:78)
                    at org.junit.internal.ComparisonCriteria.arrayEquals(ComparisonCriteria.java:28)
                    at org.junit.Assert.internalArrayEquals(Assert.java:534)
                    at org.junit.Assert.assertArrayEquals(Assert.java:418)
                    at org.junit.Assert.assertArrayEquals(Assert.java:429)
                    at ArrayTests.testReversedMyExample(ArrayTests.java:32)
                    ... 30 trimmed
            Caused by: java.lang.AssertionError: expected:<5> but was:<0
                    at org.junit.Assert.fail(Assert.java:89)
                    at org.junit.Assert.failNotEquals(Assert.java:835)
                    at org.junit.Assert.assertEquals(Assert.java:120)
                    at org.junit.Assert.assertEquals(Assert.java:146)
                    at org.junit.internal.ExactComparisonCriteria.assertElementsEqual(ExactComparisonCriteria.java:8)
                    at org.junit.internal.ComparisonCriteria.arrayEquals(ComparisonCriteria.java:76)
                    ... 36 more
                  
        FAILURES!!!
        Tests run: 1,  Failures: 1

We see that the error is found in `element [1]` of array that contains actual value `0`, not expected `5`. It happens because there is an error in the `reversed()` function in `ArrayExamples.java` and `ArrayExamples.reversed(input1)` results in `{0,0,0,0}` instead of `{0, 5, 3, -1}`. The reason why we do not see no more explanation of error we face other than `expected:<5> but was:<0>` is because it leave current `test` function immediately when the program find out there is error(exception). 

--------
   
<b>2. An input that doesn't induce a failure, as a **JUnit test** and any associated code (write it as a code block in Markdown).</b>

JUnit test code:

        @Test
        public void testReversedMyExample2() {
           int[] input1 = {0,0,0};
        assertArrayEquals(new int[]{0,0,0}, ArrayExamples.reversed(input1));
        }

The result of processing this test:

        yoshidanao@yoshidanaonoMacBook-Pro lab3 % javac -cp .:lib/hamcrest-core-1.3.jar:lib/junit-4.13.2.jar *.java
        yoshidanao@yoshidanaonoMacBook-Pro lab3 % java -cp .:lib/hamcrest-core-1.3.jar:lib/junit-4.13.2.jar org.junit.runner.JUnitCore ArrayTests
        JUnit version 4.13.2
        .
        Time: 0.003
        
        OK (1 test)

Note that there is no error because the expected and actual values matched.

--------
   
<b>3. The symptom, as the output of running the two tests above (provide it as a **screenshot** -- one test should pass, one test should fail).</b>

![Image](symptom.png)

Note that we will know it processed two tests out and found 1 failure : `Tests run: 2,  Failures: 1`

--------
   
<b>4. The bug, as the before-and-after code change required to fix it (as two **code blocks** in Markdown).</b>

Code before fixed:

        static int[] reversed(int[] arr) {
           int[] newArray = new int[arr.length];
           for(int i = 0; i < arr.length; i += 1) {
           arr[i] = newArray[arr.length - i - 1];
           }
           return arr;
        }

Code after fixed:

        static int[] reversed(int[] arr) {
            int[] newArray = new int[arr.length];
            for(int i = 0; i < arr.length; i += 1) {
               newArray[i] = arr[arr.length - i - 1];
            }
            return newArray;
        }

Note that I modified two points:

1) from `arr[i] = newArray[arr.length - i - 1];` to `newArray[i] = arr[arr.length - i - 1];`
2) from `return arr;` to `return newArray;`

Previous code tried to set value of `newArray` array which is `null` to the `arr`array, so it resulted in returning `null array`. To return new array with the numbers sorted in reversed order, we need to update the content of `newArray` array using `arr` elements.

--------
   
<b>5. Briefly describe (2-3 sentences) why the fix addresses the issue.</b>

The previous code tried to set value of `newArray` array which is `null(0)` to the `arr`array, so it resulted in returning `null(all 0) array` although the inputs are varied. However, the purpose of `reversed()` function is to return the new array with the numbers sorted in reversed order, so we need to update the content of `newArray` array using elements of `arr` array, and then, it work as intended. 

Part 2 - Researching Commands
--------

Consider the commands `less`, `find`, and `grep`. Choose one of them. Online, find 4 interesting command-line options or alternate ways to use **the single command you chose**. Many commands like these have pretty sophisticated behavior possible – it can take years to be exposed to and learn all of the possible tricks and inner workings. To find information about the commands, a simple Web search like “find command-line options” will probably give decent results. There is also a built-in command on many systems called `man` (short for “manual”) that displays information about commands; you can use `man grep`, for example, to see a long listing of information about how `grep` works. Also, consider asking ChatGPT!

For example, we saw the `-name` option for `find` in class.

Instruction: For each of those options, give 2 examples of using it on files and directories from `./technical`. That makes 8 total examples, all focused on a single command. Show **each example** as a code block that shows the command and its output, and write a sentence or two about what it’s doing and why it’s useful.

Along with each option/mode you show, **cite your source** for how you found out about it as a URL or a description of where you found it. See the syllabus on Academic Integrity and how to cite sources like ChatGPT for this class.

***

I used ChatGPT to see what kind of command that I can use with `grep`, and I got as the following:

![Image](chatGPT.cite1.png)

![Image](chatGPT.cite2.png)


1. `-e`

2. `-v`

3. `-c`

4. `-n`

5. `-l/-L`
   
6. `-m`, --max-count=NUM
   
7.  `--color=always`

8.  `^` (I do not see it in chatGPT, but I think it is useful when I saw how it work in the class, so I explore with it)

9.  `–f` (I do not see it in chatGPT, but I think it is useful when I saw how it work in the class, so I explore with it)


--------

Completed: th, 2024  

Lab due: th, 2024  

GitHub link: 
