package api.test;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import api.endpoints.UserEndPoints;
import api.payload.DeleteUser;
import api.payload.Location;
import api.payload.UpdateUser;
import api.payload.User;
import io.restassured.response.Response;

public class UserTests {

	User userPayload;
	String place_id;
	UpdateUser updateUserPayload;
	DeleteUser deleteUserPayload;
	@BeforeClass
	public void setupData() {
		userPayload = new User();
		userPayload.setAccuracy(20);
		userPayload.setName("Deepak");
		userPayload.setPhone_number("8889998889");
		userPayload.setWebsite("www.deepak.com");
		userPayload.setLanguage("English");

		String[] types = { "shoe", "shop" };
		userPayload.setTypes(types);

		Location loc = new Location();
		loc.setLat("284");
		loc.setLng("879");
		userPayload.setLocation(loc);
		
	}

	@Test(priority = 1)
	public void testPostUser() {
		Response response = UserEndPoints.createUser(userPayload);
		response.then().log().all();
		place_id = response.jsonPath().get("place_id");
	}

	@Test(priority = 2)
	public void testGetUserByPlaceID() {
		Response response = UserEndPoints.readUser(place_id);
		response.then().log().all();
	}

	@Test(priority = 3)
	public void testUpdateUserByPlaceID() {
		updateUserPayload = new UpdateUser();
		updateUserPayload.setAddress("New Deepak Address");
		updateUserPayload.setPlace_id(place_id);
		updateUserPayload.setKey("qaclick123");
		
		Response response = UserEndPoints.updateUser(place_id, updateUserPayload);
		response.then().log().all();

		Response responseafterUpdate = UserEndPoints.readUser(place_id);
		responseafterUpdate.then().log().all();
	}
	@Test(priority = 4)
	public void testDeleteUserByPlaceID() {
		deleteUserPayload = new DeleteUser();
		deleteUserPayload.setPlace_id(place_id);
		Response response = UserEndPoints.deleteUser(deleteUserPayload);
		response.then().log().all();
	}

}
