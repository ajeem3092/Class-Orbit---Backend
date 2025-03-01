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
import org.springframework.web.bind.annotation.DeleteMapping;
	import org.springframework.web.bind.annotation.GetMapping;
	import org.springframework.web.bind.annotation.PatchMapping;
	import org.springframework.web.bind.annotation.PathVariable;
	import org.springframework.web.bind.annotation.PostMapping;
	import org.springframework.web.bind.annotation.PutMapping;
	import org.springframework.web.bind.annotation.RequestBody;
	import org.springframework.web.bind.annotation.RequestMapping;
	import org.springframework.web.bind.annotation.RequestParam;
	import org.springframework.web.bind.annotation.RestController;
	import org.springframework.web.multipart.MultipartFile;
	
	import classroom.Filehandlingutils.FacultyAssignmentUploadResponse;
	import classroom.Filehandlingutils.FileDownloadUtil;
	import classroom.Filehandlingutils.FileUploadUtils;
	import classroom.Service.IFacultyService;
	import classroom.dto.ApiResponse;
	import classroom.entities.Assignment;
	import classroom.entities.AssignmentAnswer;
	import classroom.entities.NoticeBoard;
	import classroom.entities.TimeTable;
	import classroom.entities.User;
	import jakarta.validation.Valid;
	
	@RestController
	@CrossOrigin(origins = "http://localhost:3000")
	//@CrossOrigin(origins = "https://cdac-project-front-end.vercel.app/")
	@RequestMapping("/faculty")
	public class FacultyController {
	
		@Autowired
		IFacultyService facultyService;
	
		@PostMapping("/addnoticeboard/{userId}")
		public ResponseEntity<?> addNoticeBoard(@RequestBody @Valid NoticeBoard noticeboard, @PathVariable Long userId) {
			try {
				return new ResponseEntity<>(facultyService.addNoticeBoard(noticeboard, userId), HttpStatus.CREATED);
			} catch (RuntimeException e) {
				return new ResponseEntity<>(new ApiResponse(e.getMessage()), HttpStatus.BAD_REQUEST);
			}
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
	
		@PostMapping("/addassignment")
		public ResponseEntity<?> uploadFile(@RequestParam("file") MultipartFile multipartFile, @RequestParam Long facultyId,
				@RequestParam String description, @RequestParam String facultyName, @RequestParam String moduleName)
				throws IOException {
			try {
				System.out.println("Faculty Id " + facultyId);
				Assignment a = new Assignment();
				a.setDescription(description);
				a.setFacultyName(facultyName);
				a.setModuleName(moduleName);
				String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
				long size = multipartFile.getSize();
				String filecode = FileUploadUtils.saveFile(fileName, multipartFile);
				FacultyAssignmentUploadResponse response = new FacultyAssignmentUploadResponse();
				response.setFileName(fileName);
				response.setSize(size);
				response.setDownloadUrl("/downloadFile/" + filecode);
				response.setFilecode(filecode);
				return new ResponseEntity<>(facultyService.addAssignment(a, facultyId, filecode), HttpStatus.CREATED);
			} catch (RuntimeException e) {
				return new ResponseEntity<>(new ApiResponse(e.getMessage()), HttpStatus.BAD_REQUEST);
			}
		}
	
		@GetMapping("/viewnoticeboard/{facultyId}")
		public List<NoticeBoard> getAllNotice(@PathVariable Long facultyId) {
			System.out.println(facultyId);
			return facultyService.getNoticeBoardByFaculty(facultyId);
		}
	
		@GetMapping("/viewstudent")
		public List<User> getAllStudent() {
			return facultyService.getAllStudentByRoleStudent();
		}
	
		@GetMapping("/viewtimetable/{facultyId}")
		public List<TimeTable> getAllTimeTable(@PathVariable Long facultyId) {
			System.out.println(facultyId);
			return facultyService.getAllTimeTableByFacultyId(facultyId);
		}
	
		@GetMapping("/viewassignment/{facultyId}")
		public List<Assignment> getAllAssignmentByFacultyId(@PathVariable Long facultyId) {
			System.out.println(facultyId);
			return facultyService.getAssignmentByFaculty(facultyId);
		}
	
		@PostMapping("/addtimetable/{userId}")
		public ResponseEntity<?> addTimetableByFacultyId(@RequestBody @Valid TimeTable timetable,
				@PathVariable Long userId) {
			try {
				return new ResponseEntity<>(facultyService.addTimeTable(timetable, userId), HttpStatus.CREATED);
			} catch (RuntimeException e) {
				return new ResponseEntity<>(new ApiResponse(e.getMessage()), HttpStatus.BAD_REQUEST);
			}
		}
	
		@DeleteMapping("/viewnoticeboard/delete/{id}")
		public ResponseEntity<?> deleteFacultyDetails(@PathVariable Long id) {
			System.out.println("in del Faculty" + id);
			try {
				return ResponseEntity.ok(new ApiResponse(facultyService.deleteNoticeBoardById(id)));
			} catch (RuntimeException e) {
				System.out.println("err in del  Faculty " + e);
				return new ResponseEntity<>(new ApiResponse("Invalid NoticeBoard ID !!!!!!!!!!!!!!!!"),
						HttpStatus.NOT_FOUND);
			}
		}
	
		@GetMapping("/editnoticeboard/{id}")
		public ResponseEntity<?> getNoticeBoardByNoticeBoardId(@PathVariable Long id) {
			NoticeBoard u = facultyService.getNoticeBoardById(id);
			HashMap<String, Object> ht = new HashMap<String, Object>();
			if (u == null)
				return new ResponseEntity<>(new ApiResponse("Invalid NoticeBoard ID !!!!!!!!!!!!!!!!"),
						HttpStatus.NOT_FOUND);
			ht.put("status", new String("success"));
			ht.put("data", u);
			return ResponseEntity.ok(ht);
		}
	
		@PutMapping("/editnoticeboard/{id}")
		public ResponseEntity<?> updateStudentDetails(@RequestBody NoticeBoard detachedNoticeBoard, @PathVariable Long id) {
	
			System.out.println("in update NoticeBoard" + detachedNoticeBoard.getId());
			try {
				return ResponseEntity.ok(facultyService.updateNoticeBoardDetails(detachedNoticeBoard, id));
			} catch (RuntimeException e) {
				System.out.println("err in update  NoticeBoard " + e);
				return new ResponseEntity<>(new ApiResponse(e.getMessage()), HttpStatus.NOT_FOUND);
			}
		}
	
		@DeleteMapping("/viewtimetable/delete/{id}")
		public ResponseEntity<?> deleteTimeTableDetails(@PathVariable Long id) {
			System.out.println("in del TimeTable" + id);
			try {
				return ResponseEntity.ok(new ApiResponse(facultyService.deleteTimeTableById(id)));
			} catch (RuntimeException e) {
				System.out.println("err in del  Timetable " + e);
				return new ResponseEntity<>(new ApiResponse("Invalid Timetable ID !!!!!!!!!!!!!!!!"), HttpStatus.NOT_FOUND);
			}
		}
	
		@GetMapping("/edittimetable/{id}")
		public ResponseEntity<?> getTimeTableByTimeTableId(@PathVariable Long id) {
			TimeTable t = facultyService.getTimeTableById(id);
			HashMap<String, Object> ht = new HashMap<String, Object>();
			if (t == null)
				return new ResponseEntity<>(new ApiResponse("Invalid TimeTable ID !!!!!!!!!!!!!!!!"), HttpStatus.NOT_FOUND);
			ht.put("status", new String("success"));
			ht.put("data", t);
			return ResponseEntity.ok(ht);
		}
	
		@PutMapping("/edittimetable/{id}")
		public ResponseEntity<?> updateTimeTableDetails(@RequestBody TimeTable detachedTimeTable, @PathVariable Long id) {
	
			System.out.println("in update TimeTable" + detachedTimeTable.getId());
			try {
				return ResponseEntity.ok(facultyService.updateTimeTableDetails(detachedTimeTable, id));
			} catch (RuntimeException e) {
				System.out.println("err in update  TimeTable " + e);
				return new ResponseEntity<>(new ApiResponse(e.getMessage()), HttpStatus.NOT_FOUND);
			}
		}
	
		@GetMapping("/viewassignmentanswer/{facultyId}")
		public List<AssignmentAnswer> getAllAssignmentAnswer(@PathVariable Long facultyId) {
	
			List<AssignmentAnswer> assignAnswer = facultyService.getAllAssignmentAnswerByFacultyId(facultyId);
			return assignAnswer;
		}
	
		@PatchMapping("/viewassignmentanswer/grade/{id}")
		public ResponseEntity<?> updateGradeOfStudent(@PathVariable Long id, @RequestBody AssignmentAnswer aas) {
			System.out.println("id =" + id);
			try {
				return ResponseEntity.ok(facultyService.updateStudentGradeByAssignmentAnswerId(aas.getGrade(), id));
			} catch (RuntimeException e) {
				return new ResponseEntity<>(new ApiResponse(e.getMessage()), HttpStatus.NOT_FOUND);
			}
		}
	
		@PatchMapping("/viewassignmentanswer/remark/{id}")
		public ResponseEntity<?> updateRemarkOfStudent(@PathVariable Long id, @RequestBody AssignmentAnswer aas) {
			System.out.println("id =" + id);
			try {
				return ResponseEntity.ok(facultyService.updateStudentRemarkByAssignmentAnswerId(aas.getRemark(), id));
			} catch (RuntimeException e) {
				return new ResponseEntity<>(new ApiResponse(e.getMessage()), HttpStatus.NOT_FOUND);
			}
		}
	}
