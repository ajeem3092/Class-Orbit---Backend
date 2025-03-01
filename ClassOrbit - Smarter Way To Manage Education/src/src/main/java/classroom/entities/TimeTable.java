package classroom.entities;

import java.sql.Date;
import java.time.LocalTime;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "timetable")
public class TimeTable extends BaseEntity {

	private Date date;
	@Column(name = "starttime")
	private LocalTime startTime;
	@Column(name = "endtime")
	@DateTimeFormat(pattern = "")
	private LocalTime endTime;
	@ManyToOne
	@JoinColumn(name = "facultyid")
	private User faculty;
	@Column(name = "facultyname", length = 45)
	private String facultyName;
	@NotBlank(message = "platform  is required")
	@Column(length = 20)
	private String platform;
	@NotBlank(message = "link is required")
	@Column(length = 200)
	private String link;
	@NotBlank(message = "module name  is required")
	@Column(name = "modulename", length = 45)
	private String moduleName;

	public Long getFaculty() {
		return faculty.getId();
	}

}
