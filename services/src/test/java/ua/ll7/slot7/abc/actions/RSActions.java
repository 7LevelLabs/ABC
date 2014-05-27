package ua.ll7.slot7.abc.actions;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ua.ll7.slot7.abc.helper.ILetterHelper;
import ua.ll7.slot7.abc.helper.IRealObjectHelper;
import ua.ll7.slot7.abc.model.letter.Letter;
import ua.ll7.slot7.abc.model.realobject.RealObject;
import ua.ll7.slot7.abc.service.IBLService;
import ua.ll7.slot7.abc.service.ILetterService;
import ua.ll7.slot7.abc.service.IRealObjectService;

/**
 * @author Alex Velichko
 *         26.05.14 : 13:17
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:springConfigIT.xml")
public class RSActions {

	@Autowired
	private ILetterHelper letterHelper;

	@Autowired
	private IRealObjectHelper realObjectHelper;

	@Autowired
	private ILetterService letterService;

	@Autowired
	private IRealObjectService realObjectService;

	@Autowired
	private IBLService blService;

	@Test
	public void actionCreateLetter() throws Exception {
		Letter aLetter = letterHelper.getNewLetter('A', "Letter A");

		RealObject realObjectA1 = realObjectHelper.getNewRealObject(aLetter,"ROA1","Real Object A1");
		RealObject realObjectA2 = realObjectHelper.getNewRealObject(aLetter,"ROA2","Real Object A2");

		letterHelper.addRealObject(aLetter, realObjectA1);
		letterHelper.addRealObject(aLetter, realObjectA2);

		letterService.createLetter(aLetter);

//		realObjectService.createRealObject(realObjectA1);
//		realObjectService.createRealObject(realObjectA2);

	}

	@Test
	public void actionFindLetter() throws Exception {
		Letter letter = letterService.findByCharacter('A');

		System.out.println(letter);

	}

	@Test
	public void actionDeleteLetter() throws Exception {
		letterService.deleteLetterByChar('A');
	}

}
