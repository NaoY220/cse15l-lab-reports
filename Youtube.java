import java.util.ArrayList;
import java.util.List;

class User{
    String username;
    String displayName;

    User(String username, String displayName){
        this.username = username;
        this.displayName = displayName;
    }
}

interface Comment{
    public boolean isCommentByAuthor(User author);
    public int totalLikes();
    public String unrollCommentThread();

    public int totalInteractions();
}

//main comment on the video
class VideoComment implements Comment{
    String text;
    int likes;
    int replies;
    User author;

    //constructor
    VideoComment(String text, int likes, int replies, User author){
        this.text = text;
        this.likes = likes;
        this.replies = replies;
        this.author = author;
    }

    public boolean isCommentByAuthor(User author){
        if(author.username == this.author.username)
            return true;
        return false;
    }
    public int totalLikes(){
        return this.likes;
    }
    public String unrollCommentThread(){
        return this.author.username + "\n"+ this.likes + " likes; " + this.replies + " replies\n" + this.text + "\n";
    }
    public int totalInteractions(){
        return this.likes+ this.replies;
    }
}

class ReplyComment implements Comment{
    String text;
    int likes;
    User author;
    Comment replyTo;

    ReplyComment(String text, int likes, User author, Comment replyTo){
        this.text = text;
        this.likes = likes;
        this.author = author;
        this.replyTo = replyTo;
    }

    public boolean isCommentByAuthor(User author){
        if(author.username == this.author.username && replyTo.isCommentByAuthor(author) != false)
            return true;
        return false;
    }
    public int totalLikes(){
        return replyTo.totalLikes() + this.likes;
    }
    public String unrollCommentThread(){
        return replyTo.unrollCommentThread() +this.author.username + "\n"+ this.likes + " likes\n" + this.text + "\n";
    }

    public int totalInteractions(){
        return 0;
    }
}

class Youtube{
    //object User
    User u1 = new User("test_username1", "Test User Full Name 1");
    User u2 = new User("test_username2", "Test User Full Name 2");

    //object VideoComment
    Comment vc1 = new VideoComment("This is a great example to use the Tester Library!", 10, 5, u1);
    Comment vc2 = new VideoComment("It is fun to learn Java language!", 13, 3, u2);
    
    //object ReplyComment
    Comment rc1 = new ReplyComment("Yeah, I agree!", 7, u2, vc1);
    Comment rc2 = new ReplyComment("Thanks for acknowledgment!", 4, u1, rc1);

    //object VideoComment 1
    boolean same1 = vc1.isCommentByAuthor(u1);       //expected: true
    boolean notSame1 = vc1.isCommentByAuthor(u2);    //expected: false
    int likes1 = vc1.totalLikes();                   //expected: 10
    String thread1 = vc1.unrollCommentThread();      //expected: 15
}


