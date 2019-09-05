package modelTest;

import model.Post;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PostTests {

    private Post TestGoalPost;

    @BeforeEach
    public void setup(){
        TestGoalPost = new Post(null, "Garibaldi Lake", "Hike", "Completed Garibaldi Lake");

    }

    @Test
    public void GoalPostConstructorAndGetterTest() {
        LocalDate date = LocalDate.parse("2018-02-15");
        TestGoalPost.setPostDate(date);
        assertEquals("Hike", TestGoalPost.getPostType());
        assertEquals("Completed Garibaldi Lake", TestGoalPost.getPostContent());
        assertEquals("Garibaldi Lake", TestGoalPost.getPostTitle());
        assertEquals(date, TestGoalPost.getPostDate());
    }

    @Test
    public void PostStatusTest() {
        TestGoalPost.setCompletedStatus("True");
        assertEquals(TestGoalPost.getCompletedStatus(), "True");

    }


}
