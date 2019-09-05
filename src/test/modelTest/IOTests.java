/*
package modelTest;

import model.JournalPostList;
import model.Post;
import model.PostList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class IOTests {
    private JournalPostList testList;
    private JournalPostList testList2;
    private PostList testList3;
    private Post testPost;
    private Post testPost2;


    @BeforeEach
    public void Setup() {
        testList = new JournalPostList();
        testList2 = new JournalPostList();
        testPost = new Post(null, "Test", "Hike", "This is at test");
        testPost.setPostDate(null);
        testPost2 = new Post(null, "Test2", "Bike", "This is another test");
        testPost2.setPostDate(null);
        testList.addPost("Test", testPost);
        LocalDate date = LocalDate.parse("2018-02-15");
        testPost.setPostDate(date);
        testList.addPost("Test2", testPost2);
    }

    @Test
    public void TestLoad() throws IOException {
        testList.addPost("Test", testPost);
        testList.addPost("Test2", testPost2);
        testList2.load("testpostlist.txt");
        assertEquals(testList.get("Test"), testList.get("Test"));
        assertEquals(testList.get("Test2"), testList.get("Test2"));
    }

    @Test
    public void TestSave() throws IOException {
        String testoutput;
        String currentline;
        testoutput = "==Hike=This is at test=2018-02-15=Test";
        testList.addPost("Test", testPost);
        testList.save("testsavepostlist.txt");
        File currentList = new File("testsavepostlist.txt");
        FileInputStream fis = new FileInputStream(currentList);
        Scanner sc = new Scanner(fis);
        currentline = sc.nextLine();
        assertEquals(currentline, testoutput);


    }
}
*/

