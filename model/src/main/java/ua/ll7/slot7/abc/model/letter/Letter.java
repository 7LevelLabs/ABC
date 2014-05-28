package ua.ll7.slot7.abc.model.letter;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.NotBlank;
import ua.ll7.slot7.abc.model.realobject.RealObject;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * @author Alex Velichko
 *         22.05.14 : 14:53
 */
@Entity
@ApiModel( value = "Letter", description = "Letter resource representation" )
public class Letter implements Serializable {

	@Id
	@GeneratedValue(generator = "increment")
	@GenericGenerator(name = "increment", strategy = "increment")
	public long getId() {
		return id;
	}

	@Column(nullable = false,unique = true)
	@ApiModelProperty( value = "Letter's Character", required = true )
	public Character getaChar() {
		return aChar;
	}

	@Column(nullable = false)
	@Type(type = "text")
	@ApiModelProperty( value = "Letter's description", required = true )
	public String getDescription() {
		return description;
	}

	@OneToMany(fetch = FetchType.LAZY,
		mappedBy = "letter",
		cascade = {CascadeType.ALL})
	@JsonManagedReference
	public List<RealObject> getRealObjects() {
		return realObjects;
	}

	public void setaChar(Character aChar) {
		this.aChar = aChar;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setRealObjects(List<RealObject> realObjects) {
		this.realObjects = realObjects;
	}

	public Letter() {
	}

	public Letter(final Character aChar, final String description) {

		if (StringUtils.isEmpty(description)) {
			throw new IllegalArgumentException("Arguments must be not null or empty");
		}

		this.aChar = aChar;
		this.description = description;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Letter letter = (Letter) o;

		if (!aChar.equals(letter.aChar)) return false;

		return true;
	}

	@Override
	public int hashCode() {
		return aChar.hashCode();
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder("Letter{");
		sb.append("id=").append(id);
		sb.append(", aChar=").append(aChar);
		sb.append(", description='").append(description).append('\'');
		sb.append(", realObjects=").append(realObjects);
		sb.append('}');
		return sb.toString();
	}

	private long id;

	private Character aChar;

	@NotBlank(message = "Character description must be not blank")
	private String description;

	private List<RealObject> realObjects = null;

}
