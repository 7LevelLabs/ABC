package ua.ll7.slot7.abc.application;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import ua.ll7.slot7.abc.model.realobject.RealObject;

/**
 * @author Alex Velichko
 *         28.05.14 : 18:22
 */
@Component
public class ActionBean {

	private RestTemplate restTemplate;

	public void action() {
		String url = "http://localhost:8080/services/realObjects/update";

		RealObject realObject = new RealObject();

		realObject.setId(5);

//		realObject.setName("ROB3");
		realObject.setDescription("Real Object C1");

		restTemplate.put(url, realObject);

		System.out.println("Done");

//		System.out.println(result.getId());
//		System.out.println(result.getName());
//		System.out.println(result.getDescription());

	}

	public void setRestTemplate(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}
}
