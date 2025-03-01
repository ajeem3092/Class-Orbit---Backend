	package classroom.entities;
	
	import jakarta.persistence.Column;
	import jakarta.persistence.Entity;
	import jakarta.persistence.JoinColumn;
	import jakarta.persistence.ManyToOne;
	import jakarta.persistence.Table;
	import lombok.Getter;
	import lombok.Setter;
	
	@Getter
	@Setter
	@Entity
	@Table(name = "assignment")
	public class Assignment extends BaseEntity {
	
		@Column(length = 200)
		private String description;
		@ManyToOne
		@JoinColumn(name = "facultyid")
		private User faculty;
		@Column(name = "facultyname", length = 45)
		private String facultyName;
		@Column(name = "filename", length = 45)
		private String fileName;
		@Column(name = "modulename", length = 45)
		private String moduleName;
	
		public Long getFaculty() {
			return faculty.getId();
		}
	
	}
