package api.endpoints;

import io.restassured.RestAssured;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

import api.payload.DeleteUser;
import api.payload.UpdateUser;
import api.payload.User;

public class UserEndPoints {

	public static Response createUser(User payload) {
		Response response = given().log().all().queryParam("key", "qaclick123")
				.header("Content-Type", "application/json").body(payload).when().post(Routes.post_url);
		return response;
	}

	public static Response readUser(String place_id) {
		Response response = given().log().all().queryParam("key", "qaclick123").queryParam("place_id", place_id).when()
				.get(Routes.get_url);
		return response;

	}

	public static Response updateUser(String place_id, UpdateUser payload) {
		Response response = given().log().all().queryParam("key", "qaclick123").queryParam("place_id", place_id)
				.header("Content-Type", "application/json").body(payload).when().put(Routes.update_url);
		return response;

	}

	public static Response deleteUser(DeleteUser payload) {
		Response response = given().log().all().queryParam("key", "qaclick123").body(payload).when()
				.delete(Routes.delete_url);
		return response;

	}
}