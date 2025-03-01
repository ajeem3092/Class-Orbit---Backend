package classroom.Filehandlingutils;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FacultyAssignmentUploadResponse {

	private String fileName;
	private String downloadUrl;
	private long size;
	private String filecode;

}
