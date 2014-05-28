package ua.ll7.slot7.abc.rs;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ua.ll7.slot7.abc.helper.ILetterHelper;
import ua.ll7.slot7.abc.service.ILetterService;

import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.collection.IsIn.isOneOf;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:springConfigIT.xml")
public class RSControllersIT extends Assert {

	@Autowired
	private ILetterHelper letterHelper;

	@Autowired
	private ILetterService letterService;

	@Test
	public void testGetLetterByChar() throws Exception {

		//setup
//		Letter aLetter = letterHelper.getNewLetter('A', "Letter A");
//		letterService.createLetter(aLetter);

		//test
		String stringToGet = "/services/letters/getByChar/A";

		given()
			.log().everything()
			.when()
			.get(stringToGet)
			.then()
			.statusCode(isOneOf(200, 201))
			.body("description", equalTo("Letter A"))
			.body("aChar", equalTo("A"))
		;

		//down
//		letterService.deleteLetterByChar('A');

	}


}