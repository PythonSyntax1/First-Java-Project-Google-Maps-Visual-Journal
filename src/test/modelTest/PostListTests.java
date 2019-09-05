package modelTest;

import model.GoalPostList;
import model.JournalPostList;
import model.Post;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class PostListTests {
    private JournalPostList testList;
    private Post testPost;
    private Post testPost2;

    @BeforeEach
    public void setup() {
        testList = new JournalPostList();
        testPost = new Post(null, "Test", "Hike", "This is at test");
        testPost2 = new Post(null, "Test2", "Bike", "This is another test");
        testList.addPost("Test", testPost);
        testList.addPost("Test2", testPost2);

    }

    @Test
    public void TestAddPostAndGetPost() {
        assertEquals(testList.get("Test"), testPost);
        assertEquals(testList.get("Test2"), testPost2);
        testList.addPost("Test2", testPost);
        assertEquals(testList.get("Test2"), testPost);
        assertTrue(testList.contains("Test"));

    }


    @Test
    public void TestPostListRemove(){
        testList.remove("Test");
        assertEquals(testList.getSize(), 1);
        assertEquals(testList.get("Test2"), testPost2);
    }

    @Test
    public void TestPostListAddException () {
        GoalPostList testGPL = new GoalPostList();
        int i = 0;
        String title = "o";

        while (i < 15) {
            i += 1;
            title += "o";
            try {
                testGPL.addPost(title, testPost);
            } catch (GoalPostList.TooManyGoalsException e) {
                System.out.println("No exception should be caught here. Test not written correctly.");
            }
        }
        try {
            testGPL.addPost(title, testPost);
            Assertions.fail("");
        } catch(GoalPostList.TooManyGoalsException e) {
            System.out.println("Hello");
        }

    }

    @Test
    public void getPostList() throws GoalPostList.TooManyGoalsException {
        GoalPostList testGPL = new GoalPostList();
        testGPL.addPost("Test", testPost);
        assertEquals(testList.getPostList().get("Test"), testPost);
        assertEquals(testGPL.getPostList().get("Test"), testPost);
    }



}


