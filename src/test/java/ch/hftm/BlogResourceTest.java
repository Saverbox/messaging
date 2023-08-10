package ch.hftm;


import io.quarkus.test.junit.QuarkusTest;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

@QuarkusTest
public class BlogResourceTest {

    @Test
    public void testGetEntriesEndpoint() {
        given()
            .when().get("/blogs")
            .then()
            .statusCode(200);
    }

    @Test
    public void testAddAndDeleteBlogEndpoint() {
        // Fügen Sie einen Blog hinzu
        String newBlogJson = "{\"title\": \"Test Blog\", \"content\": \"This is a test content.\"}";

        String location = given()
            .body(newBlogJson)
            .contentType(ContentType.JSON)
            .when()
            .post("/blogs")
            .then()
            .statusCode(201)
            .extract().header("Location");

        // Löschen des soeben hinzugefügten Blogs
        String[] segments = location.split("/");
        String blogId = segments[segments.length - 1];

        given()
            .when().delete("/blogs/" + blogId)
            .then()
            .statusCode(204);
    }

}
