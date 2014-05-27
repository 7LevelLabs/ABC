package ua.ll7.slot7.abc.model.realobject;

import com.fasterxml.jackson.annotation.JsonBackReference;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.NotBlank;
import ua.ll7.slot7.abc.model.letter.Letter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author Alex Velichko
 *         26.05.14 : 13:23
 */
@Entity
public class RealObject implements Serializable {

	@Id
	@GeneratedValue(generator = "increment")
	@GenericGenerator(name = "increment", strategy = "increment")
	public long getId() {
		return id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(nullable = false)
	@JsonBackReference
	public Letter getLetter() {
		return letter;
	}

	@Column(nullable = false,unique = true)
	public String getName() {
		return name;
	}

	@Column(nullable = false)
	@Type(type = "text")
	public String getDescription() {
		return description;
	}

	public RealObject() {

	}

	public RealObject(final Letter letter,
			    final String name,
			    final String description) {
		this.letter = letter;
		this.name = name;
		this.description = description;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		RealObject that = (RealObject) o;

		if (!name.equals(that.name)) return false;

		return true;
	}

	@Override
	public int hashCode() {
		return name.hashCode();
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder("RealObject{");
		sb.append("id=").append(id);
		sb.append(", letter.id=").append(letter.getId());
		sb.append(", name='").append(name).append('\'');
		sb.append(", description='").append(description).append('\'');
		sb.append('}');
		return sb.toString();
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setLetter(Letter letter) {
		this.letter = letter;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	private long id;

	@NotNull(message = "Letter must be not null")
	private Letter letter;

	@NotBlank(message = "Name must be not blank")
	private String name;

	@NotBlank(message = "Description must be not blank")
	private String description;
}
