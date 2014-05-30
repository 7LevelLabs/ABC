package ua.ll7.slot7.abc.application;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import ua.ll7.slot7.abc.model.letter.Letter;
import ua.ll7.slot7.abc.model.realobject.RealObject;

import java.util.LinkedHashMap;
import java.util.List;

/**
 * @author Alex Velichko
 *         28.05.14 : 18:22
 */
@Component
public class ActionBean {

	private RestTemplate restTemplate;

	public void action() {
		String url = "http://localhost:8080/services/letters/get";

		RealObject realObject = new RealObject();

		realObject.setId(5);

//		realObject.setName("ROB3");
		realObject.setDescription("Real Object C1");

		List<LinkedHashMap<String, Letter>> response = restTemplate.getForObject(url, List.class);

		for (int i = 0; i < response.size(); i++) {
			LinkedHashMap<String, Letter> stringLetterLinkedHashMap = response.get(i);

			System.out.println(stringLetterLinkedHashMap);
			System.out.println("===");


		}

//		System.out.println(responseEntity);

//		System.out.println(result.getId());
//		System.out.println(result.getName());
//		System.out.println(result.getDescription());

	}

	public void setRestTemplate(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}
}
