import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.*;
import org.junit.*;

public class TestYoutube{

    //object User
    User u1 = new User("test_username1", "Test User Full Name 1");
    User u2 = new User("test_username2", "Test User Full Name 2");

    //object VideoComment
    Comment vc1 = new VideoComment("This is a great example to use the Tester Library!", 10, 5, u1);
    Comment vc2 = new VideoComment("It is fun to learn Java language!", 13, 3, u2);
    
    //object ReplyComment
    Comment rc1 = new ReplyComment("Yeah, I agree!", 7, u2, vc1);
    Comment rc2 = new ReplyComment("Thanks for acknowledgment!", 4, u1, rc1);


   @Test
   public void test() {
     List<String> l1 = new ArrayList<String>(Arrays.asList("x", "y"));
		List<String> l2 = new ArrayList<String>(Arrays.asList("a", "b"));
		assertArrayEquals(new String[]{ "a", "b", "x", "y"}, ListExamples.merge(l1, l2).toArray());
  }
  
   @Test
   public void IsCoomentByTest(){
	   //the same author
	   assertArrayEquals(true, vc1.isCommentByAuthor(u1))
   }
	
   @Test
   public void IsCoomentByTest(){
	   //not the same author
	   assertArrayEquals(false, vc1.isCommentByAuthor(u2))
   }

   @Test
   public void totalLikesTest(){
	   assertArrayEquals(10, vc1.totalLikes())
   }
	
   @Test
   public void unrollCommentThreadTest(){
          result = "test_username\n" + "10 likes; 5 replies\n" + "This is a great example to use the Tester Library!\n"
	  String thread1 = vc1.unrollCommentThread();
	  assertArrayEquals(result, thread1)
   }
	
   @Test
   public void totalInteractions(){
         assertArrayEquals(15, vc1.totalInteractions())
   }
	
}
