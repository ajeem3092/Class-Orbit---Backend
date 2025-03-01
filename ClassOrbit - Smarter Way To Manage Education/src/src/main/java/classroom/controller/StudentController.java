package classroom.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import classroom.Filehandlingutils.FileDownloadUtil;
import classroom.Filehandlingutils.FileUploadResponse;
import classroom.Filehandlingutils.FileUploadUtils;
import classroom.Service.IStudentService;
import classroom.entities.Assignment;
import classroom.entities.AssignmentAnswer;
import classroom.entities.NoticeBoard;
import classroom.entities.TimeTable;
import classroom.entities.User;

@RestController
@RequestMapping("/student")
@CrossOrigin(origins = "http://localhost:3000")
//@CrossOrigin(origins = "https://cdac-project-front-end.vercel.app/")
public class StudentController {

	@Autowired
	IStudentService studentService;

	@GetMapping("/assignment")
	public List<Assignment> getAllAssignment() {
		// List<Assignment> assignments=new ArrayList<Assignment>();
		return studentService.getAllAssignment();
	}

	@GetMapping("/noticeboard")
	public List<NoticeBoard> getAllNoticeBoard() {
		return studentService.getAllNoticeBoard();
	}

	@GetMapping("/timetable")
	public List<TimeTable> getAllTimetable() {
		return studentService.getAllTimeTable();
	}

	@GetMapping("/faculty")
	public List<User> getAllFaculty() {

		return studentService.getAllFacultyByRoleFaculty();
	}

	@GetMapping("/downloadFile/{fileCode}")
	public ResponseEntity<?> downloadFile(@PathVariable("fileCode") String fileCode) {
		FileDownloadUtil downloadUtil = new FileDownloadUtil();

		Resource resource = null;
		try {
			resource = downloadUtil.getFileAsResource(fileCode);
		} catch (IOException e) {
			return ResponseEntity.internalServerError().build();
		}

		if (resource == null) {
			return new ResponseEntity<>("File not found", HttpStatus.NOT_FOUND);
		}

		String contentType = "application/octet-stream";
		String headerValue = "attachment; filename=\"" + resource.getFilename() + "\"";

		return ResponseEntity.ok().contentType(MediaType.parseMediaType(contentType))
				.header(HttpHeaders.CONTENT_DISPOSITION, headerValue).body(resource);
	}

	@PostMapping("/uploadAssignment/{assignId}")
	public ResponseEntity<FileUploadResponse> uploadAssignmentAnswer(@PathVariable Long assignId,
			@RequestParam Long studentId, @RequestParam("file") MultipartFile multipartFile) throws IOException {
		try {
			String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
			long size = multipartFile.getSize();
			String filecode = FileUploadUtils.saveFile(fileName, multipartFile);
			FileUploadResponse response = new FileUploadResponse();
			response.setFileName(fileName);
			response.setSize(size);
			response.setDownloadUri("/downloadFile/" + filecode);
			// String filelocation = "/downloadFile/" + filecode;
			studentService.saveAssignmentAnswerFile(assignId, studentId, filecode);
			response.setAssignmentId(assignId);
			response.setStudentId(studentId);
			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (RuntimeException e) {
			throw new RuntimeException("Something went wrong");
		}
	}

	@GetMapping("/result/{studentId}")
	public List<AssignmentAnswer> getAllAssignmentWithStudentId(@PathVariable Long studentId) {

		System.out.println("id" + studentId);

		return studentService.getAllAssignmentByStudentIdWithGrade(studentId);
	}
}
