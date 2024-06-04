//Nao Yoshida
//CSE15L SP2024
//PID A18083203

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
  
}


