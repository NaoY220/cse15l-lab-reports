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

`grep` : we can use this command for search and collect data in many ways.

I used ChatGPT to see what kind of command that I can use with `grep`, and I got as the following:

![Image](chatGPT.cite1.png)

![Image](chatGPT.cite2.png)

First setup for doing this task:

        yoshidanao@yoshidanaonoMacBook-Pro ~ % cd docsearch
        yoshidanao@yoshidanaonoMacBook-Pro docsearch % ls
        DocSearchServer.java    biomednplos-sizes.txt   labrep3.txt             plos-sizes.txt          technical
        README.md               count-txts.sh           lib                     size.txt
        Server.java             find-results.txt        mostWord.txt            sizeOfstringSearch.txt
        TestDocSearch.java      findMost.sh             newCount-txt.sh         sort.txt
        biomed-sizes.txt        grep-results.txt        plos-size.txt           string.txt
        yoshidanao@yoshidanaonoMacBook-Pro docsearch % find ./technical > labrep3.txt

Note that I saved all contents of both txt files and directories inside `./technical` into `labrep3.txt`.

1. `-e` : It will help us to specify several pattern to search the document if it contains at least one of patterns specified. 

First example with files:

        yoshidanao@yoshidanaonoMacBook-Pro docsearch % grep -e "ti" -e "hi" labrep3.txt                          
        ./technical/government/About_LSC/diversity_priorities.txt
        ./technical/government/About_LSC/reporting_system.txt
        ./technical/government/About_LSC/conference_highlights.txt
        ./technical/government/Env_Prot_Agen/multi102902.txt
        ./technical/government/Env_Prot_Agen/section-by-section_summary.txt
        ./technical/government/Env_Prot_Agen/tech_sectiong.txt
        ./technical/government/Gen_Account_Office/Testimony_cg00010t.txt
        ./technical/government/Gen_Account_Office/GovernmentAuditingStandards_yb2002ed.txt
        ./technical/government/Gen_Account_Office/Testimony_Jul15-2002_d02940t.txt
        ./technical/government/Gen_Account_Office/Testimony_d01609t.txt
        ./technical/government/Gen_Account_Office/Testimony_Jul17-2002_d02957t.txt
        ./technical/government/Post_Rate_Comm/Cohenetal_Cost_Function.txt
        ./technical/government/Media/balance_scales_of_justice.txt
        ./technical/government/Media/Legal-aid_chief.txt
        ./technical/government/Media/Targeting_Domestic_Violence.txt
        ./technical/government/Media/highlight_Senior_Day.txt
        ./technical/government/Media/Supporting_Legal_Center.txt
        ./technical/government/Media/Domestic_Violence_Ruling.txt
        ./technical/government/Media/Abuse_penalties.txt
        ./technical/government/Media/Justice_for_all.txt
        ./technical/government/Media/Philly_Lawyers.txt
        ./technical/government/Media/Eviction_law.txt
        ./technical/government/Media/Higher_Registration_Fees.txt
        ./technical/government/Media/Fire_Victims_Sue.txt
        ./technical/government/Media/Justice_requests.txt
        ./technical/government/Media/Low-income_children.txt
        ./technical/government/Media/man_on_national_team.txt
        ./technical/government/Media/Attorney_gives_his_time.txt
        ./technical/government/Media/All_May_Have_Justice.txt
        ./technical/government/Media/Domestic_violence_aid.txt
        ./technical/government/Media/fight_domestic_abuse.txt
        ./technical/government/Media/CommercialAppealMemphis2.txt
        ./technical/government/Media/The_Bend_Bulletin.txt
        ./technical/government/Media/Entities_Merge.txt
        ./technical/government/Media/Politician_Practices.txt
        ./technical/government/Media/Retirement_Has_Its_Appeal.txt

To see if the file really contain either `"ti"`, `"hi"`, or both, I use `grep "hi" labrep3.txt` and `grep "ti" labrep3.txt`:

        yoshidanao@yoshidanaonoMacBook-Pro docsearch % grep "hi" labrep3.txt 
        ./technical/government/About_LSC/conference_highlights.txt
        ./technical/government/Media/Legal-aid_chief.txt
        ./technical/government/Media/highlight_Senior_Day.txt
        ./technical/government/Media/Philly_Lawyers.txt
        ./technical/government/Media/Low-income_children.txt
        ./technical/government/Media/Attorney_gives_his_time.txt
        ./technical/government/Media/CommercialAppealMemphis2.txt
        
        yoshidanao@yoshidanaonoMacBook-Pro docsearch % grep "ti" labrep3.txt
        ./technical/government/About_LSC/diversity_priorities.txt
        ./technical/government/About_LSC/reporting_system.txt
        ./technical/government/Env_Prot_Agen/multi102902.txt
        ./technical/government/Env_Prot_Agen/section-by-section_summary.txt
        ./technical/government/Env_Prot_Agen/tech_sectiong.txt
        ./technical/government/Gen_Account_Office/Testimony_cg00010t.txt
        ./technical/government/Gen_Account_Office/GovernmentAuditingStandards_yb2002ed.txt
        ./technical/government/Gen_Account_Office/Testimony_Jul15-2002_d02940t.txt
        ./technical/government/Gen_Account_Office/Testimony_d01609t.txt
        ./technical/government/Gen_Account_Office/Testimony_Jul17-2002_d02957t.txt
        ./technical/government/Post_Rate_Comm/Cohenetal_Cost_Function.txt
        ./technical/government/Media/balance_scales_of_justice.txt
        ./technical/government/Media/Targeting_Domestic_Violence.txt
        ./technical/government/Media/Supporting_Legal_Center.txt
        ./technical/government/Media/Domestic_Violence_Ruling.txt
        ./technical/government/Media/Abuse_penalties.txt
        ./technical/government/Media/Justice_for_all.txt
        ./technical/government/Media/Eviction_law.txt
        ./technical/government/Media/Higher_Registration_Fees.txt
        ./technical/government/Media/Fire_Victims_Sue.txt
        ./technical/government/Media/Justice_requests.txt
        ./technical/government/Media/man_on_national_team.txt
        ./technical/government/Media/Attorney_gives_his_time.txt
        ./technical/government/Media/All_May_Have_Justice.txt
        ./technical/government/Media/Domestic_violence_aid.txt
        ./technical/government/Media/fight_domestic_abuse.txt
        ./technical/government/Media/The_Bend_Bulletin.txt
        ./technical/government/Media/Entities_Merge.txt
        ./technical/government/Media/Politician_Practices.txt
        ./technical/government/Media/Retirement_Has_Its_Appeal.txt
        yoshidanao@yoshidanaonoMacBook-Pro docsearch % 

From the above, we can see that `./technical/government/Media/Attorney_gives_his_time.txt` appear in both of the result of each command, so it has both `"ti"` and `"hi"`. Also, it is case-sensitive. 

Another example with directories:

        yoshidanao@yoshidanaonoMacBook-Pro docsearch % grep -e "ti" -e "hi" technical/biomed 
        grep: technical/biomed: Is a directory

If I tried to use `grep -e` command for directory, terminal indicates that this command does not interact with directory. However, `-e` can work with directory if we use other command like `-re` for finding the patterns in multiple files across multiple directories.

        yoshidanao@yoshidanaonoMacBook-Pro docsearch % grep -re "hi " -e "gone"  technical/biomed 
        technical/biomed/1471-2350-4-3.txt:            Caucasian adult subjects from Alabama who had undergone
        technical/biomed/1471-2350-4-3.txt:            Alabama who had undergone testing to establish
        technical/biomed/cc991.txt:          for suctioning included rhonchi on auscultation, audible
        technical/biomed/cc991.txt:        described [ 27]. Horiuchi 
        technical/biomed/1471-2164-2-9.txt:          undergone a "concerted" phylogenetic evolution, with the
        technical/biomed/1471-2148-2-12.txt:          etc.) and has undoubtedly undergone some general
        technical/biomed/1471-2202-2-8.txt:          undergone considerable change, including CSPG
        technical/biomed/1471-2121-3-13.txt:        that observed with cCAF, and therefore gone unnoticed.
        technical/biomed/gb-2003-4-4-r26.txt:          unspliced introns) or have undergone more rapid molecular
        technical/biomed/1471-2148-1-8.txt:            M. jannaschi (Table 1). The value
        technical/biomed/bcr568.txt:        Among 181 NNBC patients who had undergone
        technical/biomed/1477-7827-1-17.txt:          (10 μM, DEVD sequence; Phi Phi Lux; OncoImmunin, Inc,
        technical/biomed/1477-7827-1-17.txt:          Phi Phi Lux) was easily detected by 8 h after TNFα
        technical/biomed/gb-2003-4-7-r42.txt:          is that members of the K box-family have undergone
        technical/biomed/ar68.txt:        undergone (auto)antigen and T cell selection and rescue,
        technical/biomed/1472-6750-1-12.txt:        undergone a successful homologous recombination event
        technical/biomed/1471-2334-1-24.txt:          were carried out using virus that had undergone a single
        technical/biomed/1472-6874-2-1.txt:        age of 65, 1 of 3 women will have undergone a hysterectomy
        technical/biomed/1472-6874-2-1.txt:        had undergone a TAH or SCH at The New York-Presbyterian
        technical/biomed/1472-6874-2-1.txt:        patients who were on HRT had undergone BSO. Perhaps this is
        technical/biomed/1472-6793-2-8.txt:          Sacchi [ 32 ] . RNA preparations were quantified by
        technical/biomed/1471-2474-2-1.txt:          a control cohort of 241 patients who had undergone
        technical/biomed/1471-2474-2-1.txt:        student's t test, Chi square analysis or Mann-Whitney U
        technical/biomed/1471-2474-2-1.txt:        undergone total hip arthroplasty, however only 41 of the
        technical/biomed/1471-2474-2-1.txt:        100 Swedish patients who had undergone hip arthroplasty,
        technical/biomed/1471-2474-2-1.txt:        undergone total hip arthroplasty (30 of whom had a
        technical/biomed/rr171.txt:          and major bronchi were then excised and the lung cut into
        technical/biomed/1471-2350-4-4.txt:          All affected individuals had undergone pacemaker
        technical/biomed/ar297.txt:            comparison, DNASIS software (Hitachi Europe, Olivet,
        technical/biomed/gb-2002-3-9-research0051.txt:          Chlamydial species have generally undergone reductive
        technical/biomed/1471-2415-3-3.txt:          gold and viewed on a Hitachi S-3500N scanning electron
        technical/biomed/gb-2002-3-9-research0045.txt:          and local duplications [ 2, 43]. Maize has undergone an
        technical/biomed/1477-7819-1-10.txt:        (Table 2). All the patients had undergone primary surgical
        technical/biomed/1477-7819-1-10.txt:        I bilaterally and the patient had undergone bilateral vein
        technical/biomed/1471-2229-2-4.txt:          leaves and have cotyledons that have undergone little
        technical/biomed/1472-6904-2-5.txt:          measurements of Takahashi et. al. [ 25 ] of the plasma
        technical/biomed/1472-6904-2-5.txt:          of Takahashi et. al [ 25 ] in which the oral absorption
        ...(I just cut because it is too long)

???


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
