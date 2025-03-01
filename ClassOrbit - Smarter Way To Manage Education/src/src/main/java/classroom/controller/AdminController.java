package classroom.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import classroom.Service.IAdminService;
import classroom.dto.ApiResponse;
import classroom.entities.User;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
//@CrossOrigin(origins = "https://cdac-project-front-end.vercel.app/")
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	IAdminService adminService;

	@PostMapping("/addfaculty")
	public ResponseEntity<?> addFaculty(@RequestBody User u) {
		try {
			return new ResponseEntity<>(adminService.addFaculty(u), HttpStatus.CREATED);
		} catch (RuntimeException e) {
			System.out.println("err in add Faculty " + e);
			return new ResponseEntity<>(new ApiResponse(e.getMessage()), HttpStatus.BAD_REQUEST);// => invalid data from
		}
	}

	@GetMapping("/viewfaculty")
	public List<User> getAllFaculty() {
		return adminService.getAllFaculty();
	}

	@PostMapping("/addstudent")
	public ResponseEntity<?> addStudent(@RequestBody User u) {
		try {
			return new ResponseEntity<>(adminService.addStudent(u), HttpStatus.CREATED);
		} catch (RuntimeException e) {
			System.out.println("err in add Faculty " + e);
			return new ResponseEntity<>(new ApiResponse(e.getMessage()), HttpStatus.BAD_REQUEST);// => invalid data from
		}
	}

	@GetMapping("/editfaculty/{id}")
	public ResponseEntity<?> getFacultyByFacultyId(@PathVariable Long id) {
		User u = adminService.getFacultyById(id);
		HashMap<String, Object> ht = new HashMap<String, Object>();
		if (u == null)
			return new ResponseEntity<>(new ApiResponse("Invalid Emp ID !!!!!!!!!!!!!!!!"), HttpStatus.NOT_FOUND);
		ht.put("status", new String("success"));
		ht.put("data", u);
		return ResponseEntity.ok(ht);
	}

	@PutMapping("/editfaculty/{id}")
	public ResponseEntity<?> updateFacultyDetails(@RequestBody User detachedFaculty, @PathVariable Long id) {

		System.out.println("in update Faculty" + detachedFaculty.getName());
		try {
			return ResponseEntity.ok(adminService.updateFacultyDetails(detachedFaculty, id));
		} catch (RuntimeException e) {
			System.out.println("err in update  Faculty " + e);
			return new ResponseEntity<>(new ApiResponse(e.getMessage()), HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/viewfaculty/delete/{id}")
	public ResponseEntity<?> deleteFacultyDetails(@PathVariable Long id) {
		System.out.println("in del Faculty" + id);
		try {
			return ResponseEntity.ok(new ApiResponse(adminService.deleteFaculty(id)));
		} catch (RuntimeException e) {
			System.out.println("err in del  Faculty " + e);
			return new ResponseEntity<>(new ApiResponse("Invalid Faculty ID !!!!!!!!!!!!!!!!"), HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/viewstudent")
	public List<User> getAllStudent() {
		System.out.println("Getting students");
		return adminService.getAllStudent();
	}

	@GetMapping("/editstudent/{id}")
	public ResponseEntity<?> getStudentByStudentId(@PathVariable Long id) {
		User u = adminService.getStudentById(id);
		HashMap<String, Object> ht = new HashMap<String, Object>();
		if (u == null)
			return new ResponseEntity<>(new ApiResponse("Invalid Emp ID !!!!!!!!!!!!!!!!"), HttpStatus.NOT_FOUND);
		ht.put("status", new String("success"));
		ht.put("data", u);
		return ResponseEntity.ok(ht);
	}

	@PutMapping("/editstudent/{id}")
	public ResponseEntity<?> updateStudentDetails(@RequestBody User detachedStudent, @PathVariable Long id) {

		System.out.println("in update Student" + detachedStudent.getName());
		try {
			return ResponseEntity.ok(adminService.updateStudentDetails(detachedStudent, id));
		} catch (RuntimeException e) {
			System.out.println("err in update  Student " + e);
			return new ResponseEntity<>(new ApiResponse(e.getMessage()), HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/viewstudent/delete/{id}")
	public ResponseEntity<?> deleteStudentDetails(@PathVariable Long id) {
		System.out.println("in del Student" + id);
		try {
			return ResponseEntity.ok(new ApiResponse(adminService.deleteStudent(id)));
		} catch (RuntimeException e) {
			System.out.println("err in del  Student " + e);
			return new ResponseEntity<>(new ApiResponse("Invalid Student ID !!!!!!!!!!!!!!!!"), HttpStatus.NOT_FOUND);
		}
	}
}
