package ua.ll7.slot7.abc.rs;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ua.ll7.slot7.abc.model.realobject.RealObject;
import ua.ll7.slot7.abc.service.IBLService;
import ua.ll7.slot7.abc.service.ILetterService;
import ua.ll7.slot7.abc.service.IRealObjectService;

/**
 * @author Alex Velichko
 *         27.05.14 : 21:01
 */
@Controller
@RequestMapping("/realObjects")
public class RSRealObjectsController {
	@Autowired
	private ILetterService letterService;

	@Autowired
	private IRealObjectService realObjectService;

	@Autowired
	private IBLService blService;

	@RequestMapping(value = "/getById/{anId}", method = RequestMethod.GET)
	public ResponseEntity<RealObject> getRealObjectById(
		@PathVariable("anId")
		long anId) {

		if (anId<1) {
			return new ResponseEntity<RealObject>(HttpStatus.NOT_ACCEPTABLE);
		}

		RealObject result = realObjectService.findRealObjectById(anId);

		if (result==null) {
			return new ResponseEntity<RealObject>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<RealObject>(result, HttpStatus.OK);
	}

	@RequestMapping(value = "/deleteById/{aName}", method = RequestMethod.DELETE)
	public ResponseEntity deleteRealObjectById(
		@PathVariable("aName")
		long anId
	) {
		if (anId<1) {
			return new ResponseEntity(HttpStatus.NOT_ACCEPTABLE);
		}

		if (!realObjectService.existById(anId)) {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}

		blService.deleteRealObjectById(anId);
		return new ResponseEntity(HttpStatus.OK);
	}

	@RequestMapping(value = "/deleteByName/{aName}", method = RequestMethod.DELETE)
	public ResponseEntity deleteRealObjectByName(
		@PathVariable("aName")
		String aName
	) {
		if (StringUtils.isBlank(aName)) {
			return new ResponseEntity(HttpStatus.NOT_ACCEPTABLE);
		}

		if (!realObjectService.existByName(aName)) {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}

		blService.deleteRealObjectByName(aName);
		return new ResponseEntity(HttpStatus.OK);
	}



}
