package ua.ll7.slot7.abc.client;

import ua.ll7.slot7.abc.model.letter.Letter;
import ua.ll7.slot7.abc.model.realobject.RealObject;

import java.util.Collection;

/**
 * @author Alex Velichko
 *         30.05.14 : 17:42
 */
public interface IABCRSClient {

	//Letter

	public boolean letterExist(char aChar);

	public Letter letterCreate(Letter letterContainer);

	public Collection<Letter> letterGetAll();

	public Letter letterGet(long anId);

	public Letter letterGet(char aChar);

	public void letterUpdate(Letter letterContainer);

	public void letterDelete(long anId);

	//RealObject

	public boolean roExist(String name);

	public RealObject roCreate(long letterId, RealObject roContainer);

	public RealObject roGet(long anId);

	public void roUpdate(RealObject roContainer);

	public void roDelete(long anId);

	//BL

	public long roGetLetterId(long anId);

	public Letter roGetLetter(long anId);

}
