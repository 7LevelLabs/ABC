package ua.ll7.slot7.abc.application;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import ua.ll7.slot7.abc.model.letter.Letter;
import ua.ll7.slot7.abc.model.realobject.RealObject;

import java.util.LinkedList;

/**
 * @author Alex Velichko
 *         28.05.14 : 18:22
 */
@Component
public class ActionBean {

	private RestTemplate restTemplate;

	public void action() {
//		String url = "http://localhost:8080/services/letters/getByChar/B";

		String url = "http://localhost:8080/services/letters/update";

		Letter letter = new Letter();
		letter.setId(3);
		letter.setaChar('C');
		letter.setDescription("Letter C");
		letter.setRealObjects(new LinkedList<RealObject>());

		restTemplate.put(url, letter);

		System.out.println("Done");

	}

	public void setRestTemplate(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}
}
