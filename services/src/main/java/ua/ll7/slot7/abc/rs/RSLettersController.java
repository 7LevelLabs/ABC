package ua.ll7.slot7.abc.rs;

import com.wordnik.swagger.annotations.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ua.ll7.slot7.abc.helper.ILetterHelper;
import ua.ll7.slot7.abc.model.letter.Letter;
import ua.ll7.slot7.abc.service.IBLService;
import ua.ll7.slot7.abc.service.ILetterService;

import java.util.List;

/**
 * @author Alex Velichko
 *         22.05.14 : 20:03
 */
@Controller
@RequestMapping("/letters")
@Api(value = "/letters", description = "Operations about Letters")
public class RSLettersController {

	@Autowired
	private ILetterService letterService;

	@Autowired
	private ILetterHelper letterHelper;

	@Autowired
	private IBLService blService;

	@RequestMapping(value = "/existByChar/{aChar}", method = RequestMethod.GET)
	public ResponseEntity<Boolean> existByChar(
		@PathVariable("aChar")
		String aChar) {

		if (aChar == null) {
			return new ResponseEntity<Boolean>(HttpStatus.NOT_ACCEPTABLE);
		}

		if (StringUtils.isBlank(aChar)) {
			return new ResponseEntity<Boolean>(HttpStatus.NOT_ACCEPTABLE);
		}

		if (aChar.length() > 1) {
			return new ResponseEntity<Boolean>(HttpStatus.NOT_ACCEPTABLE);
		}

		Boolean result = letterService.existByChar(aChar.charAt(0));

		if (!result) {
			return new ResponseEntity<Boolean>(result, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<Boolean>(result, HttpStatus.OK);

	}

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public ResponseEntity<Letter> create(
		@RequestBody
		Letter letter) {
		if (letterService.existByChar(letter.getaChar())) {
			return new ResponseEntity<Letter>(HttpStatus.NOT_ACCEPTABLE);
		}

		blService.createAndPersistLetter(letter.getaChar(), letter.getDescription());
		Letter letterRead = letterService.findByCharacter(letter.getaChar());

		if (letterRead == null) {
			return new ResponseEntity<Letter>(HttpStatus.INTERNAL_SERVER_ERROR);

		}

		return new ResponseEntity<Letter>(letterRead, HttpStatus.OK);
	}

	@RequestMapping(value = "/get", method = RequestMethod.GET)
	public ResponseEntity<List<Letter>> getAll() {
		List<Letter> result = letterService.getAll();
		return new ResponseEntity<List<Letter>>(result, HttpStatus.OK);
	}

	@RequestMapping(value = "/getByChar/{aChar}", method = RequestMethod.GET)
	@ApiOperation(value = "Get Letter by Char", response = Letter.class)
	@ApiResponses({
		@ApiResponse(code = 404, message = "Letter with such Character doesn't exists"),
		@ApiResponse(code = 406, message = "Non acceptable argument")
	})
	public ResponseEntity<Letter> getByChar(
		@PathVariable("aChar")
		@ApiParam(value = "Character to find", required = true)
		String aChar) {

		if (aChar == null) {
			return new ResponseEntity<Letter>(HttpStatus.NOT_ACCEPTABLE);
		}

		if (StringUtils.isBlank(aChar)) {
			return new ResponseEntity<Letter>(HttpStatus.NOT_ACCEPTABLE);
		}

		if (aChar.length() > 1) {
			return new ResponseEntity<Letter>(HttpStatus.NOT_ACCEPTABLE);
		}

		Letter result = letterService.findByCharacter(aChar.charAt(0));

		if (result == null) {
			return new ResponseEntity<Letter>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<Letter>(result, HttpStatus.OK);
	}

	@RequestMapping(value = "/getById/{anId}", method = RequestMethod.GET)
	public ResponseEntity<Letter> getById(
		@PathVariable("anId")
		long anId) {

		if (anId < 1) {
			return new ResponseEntity<Letter>(HttpStatus.NOT_ACCEPTABLE);
		}

		Letter result = letterService.findById(anId);

		if (result == null) {
			return new ResponseEntity<Letter>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<Letter>(result, HttpStatus.OK);
	}

	@RequestMapping(value = "/update", method = RequestMethod.PUT)
	public ResponseEntity<Letter> update(
		@RequestBody
		Letter letterContainer) {
		long anId = letterContainer.getId();

		if (anId < 1) {
			return new ResponseEntity<Letter>(HttpStatus.NOT_ACCEPTABLE);
		}

		if (!letterService.existById(anId)) {
			return new ResponseEntity<Letter>(HttpStatus.NOT_FOUND);
		}

		if (!letterService.existByChar(letterContainer.getaChar())) {
			return new ResponseEntity<Letter>(HttpStatus.NOT_ACCEPTABLE);
		}

		Letter letterRead = letterService.findById(letterContainer.getId());

		letterHelper.updateLetter(letterContainer, letterRead);

		letterService.updateLetter(letterRead);

		letterRead = letterService.findById(anId);

		return new ResponseEntity<Letter>(letterRead, HttpStatus.OK);
	}

	@RequestMapping(value = "/deleteById/{anId}", method = RequestMethod.DELETE)
	public ResponseEntity delete(
		@PathVariable("anId")
		long anId
	) {

		if (anId < 1) {
			return new ResponseEntity(HttpStatus.NOT_ACCEPTABLE);
		}

		if (!letterService.existById(anId)) {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}

		blService.deleteLetterById(anId);

		return new ResponseEntity(HttpStatus.OK);
	}

}
