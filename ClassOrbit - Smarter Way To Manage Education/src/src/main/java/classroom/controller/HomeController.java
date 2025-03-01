	package classroom.controller;
	
	import java.util.HashMap;
	
	import org.springframework.beans.factory.annotation.Autowired;
	import org.springframework.http.HttpStatus;
	import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
	import org.springframework.web.bind.annotation.RequestBody;
	import org.springframework.web.bind.annotation.RequestMapping;
	import org.springframework.web.bind.annotation.RestController;
	
	import classroom.Service.ILoginService;
	import classroom.dto.ApiResponse;
	import classroom.dto.CredentialDto;
	import classroom.entities.User;
	import jakarta.validation.Valid;
	
	@RestController
	@RequestMapping("/")
	@CrossOrigin(origins = "http://localhost:3000")
	//@CrossOrigin(origins = "https://cdac-project-front-end.vercel.app/")
	public class HomeController {
		@Autowired
		ILoginService loginService;
	
		@PostMapping
		public ResponseEntity<?> authenticateUser(@RequestBody @Valid CredentialDto cred) {
			User u = loginService.authenticateUser(cred.getEmail(), cred.getPassword());
			HashMap<String, Object> ht = new HashMap<String, Object>();
			if (u == null)
				return new ResponseEntity<>(new ApiResponse("Invalid Credential  !!!!!!!!!!!!!!!!"), HttpStatus.NOT_FOUND);// =>
			ht.put("status", new String("success"));
			ht.put("data", u);
			return ResponseEntity.ok(ht);
		}
	}
