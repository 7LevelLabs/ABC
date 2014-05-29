package ua.ll7.slot7.abc.rs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ua.ll7.slot7.abc.helper.IRealObjectHelper;
import ua.ll7.slot7.abc.model.letter.Letter;
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
	private IRealObjectService realObjectService;

	@Autowired
	private ILetterService letterService;

	@Autowired
	private IRealObjectHelper realObjectHelper;

	@Autowired
	private IBLService blService;

	@RequestMapping(value = "/existByName/{aName}", method = RequestMethod.GET)
	public ResponseEntity<Boolean> existByName(
		@PathVariable("aName")
		String aName) {

		Boolean result = realObjectService.existByName(aName);

		if (result) {
			return new ResponseEntity<Boolean>(result, HttpStatus.OK);
		} else {
			return new ResponseEntity<Boolean>(result, HttpStatus.NOT_FOUND);
		}
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public ResponseEntity<RealObject> create(
		@RequestBody
		RealObject realObject) {

		if (realObjectService.existByName(realObject.getName())) {
			return new ResponseEntity<RealObject>(HttpStatus.NOT_ACCEPTABLE);
		}

		Letter letter = blService.getLetterByRealObjectName(realObject.getName());

		blService.createAndPersistRealObject(letter.getaChar(),
			realObject.getName(),
			realObject.getDescription());

		RealObject realObjectRead = realObjectService.findRealObjectById(realObject.getId());

		if (realObjectRead == null) {
			return new ResponseEntity<RealObject>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<RealObject>(realObjectRead, HttpStatus.NOT_FOUND);
	}

	@RequestMapping(value = "/getById/{anId}", method = RequestMethod.GET)
	public ResponseEntity<RealObject> getById(
		@PathVariable("anId")
		long anId) {

		if (anId < 1) {
			return new ResponseEntity<RealObject>(HttpStatus.NOT_ACCEPTABLE);
		}

		RealObject result = realObjectService.findRealObjectById(anId);

		if (result == null) {
			return new ResponseEntity<RealObject>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<RealObject>(result, HttpStatus.OK);
	}

	@RequestMapping(value = "/update", method = RequestMethod.PUT)
	public ResponseEntity<RealObject> update(
		@RequestBody
		RealObject realObjectContainer) {

		long anId = realObjectContainer.getId();

		if (anId < 1) {
			return new ResponseEntity<RealObject>(HttpStatus.NOT_ACCEPTABLE);
		}

		if (!realObjectService.existById(anId)) {
			return new ResponseEntity<RealObject>(HttpStatus.NOT_FOUND);
		}

		if (!realObjectService.existByName(realObjectContainer.getName())) {
			return new ResponseEntity<RealObject>(HttpStatus.NOT_ACCEPTABLE);
		}

		RealObject realObjectRead = realObjectService.findRealObjectById(anId);

		realObjectHelper.updateRealObject(realObjectContainer, realObjectRead);

		realObjectService.updateRealObject(realObjectContainer);

		realObjectRead = realObjectService.findRealObjectById(anId);

		return new ResponseEntity<RealObject>(realObjectRead, HttpStatus.OK);
	}

	@RequestMapping(value = "/deleteById/{aName}", method = RequestMethod.DELETE)
	public ResponseEntity deleteById(
		@PathVariable("aName")
		long anId) {
		if (anId < 1) {
			return new ResponseEntity(HttpStatus.NOT_ACCEPTABLE);
		}

		if (!realObjectService.existById(anId)) {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}

		blService.deleteRealObjectById(anId);
		return new ResponseEntity(HttpStatus.OK);
	}
}
