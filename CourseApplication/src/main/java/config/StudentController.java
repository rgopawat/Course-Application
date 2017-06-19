package config;

import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import model.Student;
import service.StudentService;

@RestController
@EnableAutoConfiguration
@ComponentScan({ "dao", "model", "service" })
public class StudentController {

	@Autowired
	private Environment environment;

	@Autowired
	private StudentService studentService;

	@Autowired
	private RestTemplate restTemplate;

	private static final Logger logger = LoggerFactory.getLogger(StudentController.class);

	/**
	 * This method will just launch the home page
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = { "/", "/home" })
	public ModelAndView homePage(ModelMap model) {

		logger.debug("Entry inside the method of launching the home page");

		model.addAttribute("greeting", "Hi, Welcome to Course Application ");
		ModelAndView mav = new ModelAndView("welcome");
		mav.addObject(model);

		logger.debug("Exit the method of launching the home page");

		return mav;
	}

	/**
	 * This method will display will the page for adding new students
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = { "/display_student_form" }, method = RequestMethod.GET)
	public ModelAndView displayAddStudentForm(ModelMap model) {

		logger.debug("Entry inside the method of launching the page for adding new students");

		ModelAndView mav = new ModelAndView("add_student");
		mav.addObject(model);

		logger.debug("Exit the method of launching the page for adding new students");

		return mav;
	}

	/**
	 * This method will just display will the page for displaying all students
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = { "/view_all_students" }, method = RequestMethod.GET)
	public ModelAndView viewAllStudentsPage(ModelMap model) {

		logger.debug("Entry inside the method of launching the page for viewing new students");

		ModelAndView mav = new ModelAndView("view_all_students");
		mav.addObject(model);

		logger.debug("Exit the method of launching the page for viewing new students");

		return mav;
	}

	/**
	 * This method will display will the get the data for all the students
	 * 
	 * @return
	 */
	@RequestMapping(value = { "/getAllStudents" }, method = RequestMethod.GET)
	@ResponseBody
	public List<Student> getAllStudentsPage() {

		logger.debug("Entry inside the method of getting the list of all the students");

		List<Student> allStudents = studentService.getAllStudents();


		if (allStudents.size() > 0) {
			return allStudents;
		} else {
			logger.debug("No students exits in the database");
			List<Student> student = new ArrayList<Student>();
			return student;
		}
	}

	/**
	 * This student will actually add the new student detail or update in case
	 * the student is already existing.
	 * 
	 * @param Student
	 * @return
	 */
	@RequestMapping(value = "/add/students", method = RequestMethod.POST)
	public Student addStudent(@RequestBody Student student) {

		logger.debug("Entry inside the method of adding a new student");

		studentService.create(student);

		return student;

	}

	/**
	 * This method will interact with University API and get details
	 * 
	 * @param student
	 * @return
	 */

	private Object getUniversityDetails(Student student) {

		logger.debug("Entry inside the method of calling the University API");

		String universityDetailService = environment.getRequiredProperty("universityDetailService");

		String universityDetailUrl = universityDetailService + "?name=" + student.getUniversityName() + "&country="
				+ student.getUniversityCountry();


		MultiValueMap<String, String> headersMap = new LinkedMultiValueMap<String, String>();
		headersMap.add("content-type", "application/json");

		HttpEntity<?> entity = new HttpEntity<Object>(headersMap);

		ResponseEntity response = this.restTemplate.exchange(universityDetailUrl, HttpMethod.GET, entity, String.class);



		logger.debug("Exit the method of calling the university detail API");

		return response.getBody().toString();

	}

	/**
	 * This method will get the student detail provided the latitude and
	 * longitude.
	 * 
	 * @param latitude
	 * @param longitude
	 * @return
	 */
	@RequestMapping(value = "/getStudentInfoById", produces = MediaType.APPLICATION_JSON_VALUE)
	public Student getStudentById(@RequestParam("stuId") int studentId) {

		logger.info("Entry inside the method of getting the student info by id");

		Student studentInfo = studentService.getStudentInformationById(studentId);

		logger.info("");
		return studentInfo;

	}

	/**
	 * This method will get the student detail provided the latitude and
	 * longitude.
	 * 
	 * @param latitude
	 * @param longitude
	 * @return
	 */
	@RequestMapping(value = "/deleteStudentInfoById", method = RequestMethod.GET)
	public List<Student> deleteStudentInfoById(@RequestParam("stuId") int studentId) {

		logger.info("Entry inside the method of deleting the student");

		studentService.delete(studentId);

		logger.info("Exit inside the method of deleting the student, student deleted");
		
		return studentService.getAllStudents();

	}
	
	/**
	 * This method will get the student detail provided the latitude and
	 * longitude.
	 * 
	 * @param latitude
	 * @param longitude
	 * @return
	 */
	@RequestMapping(value = "/getUniversityDetailsById", method = RequestMethod.GET)
	public Object getUniversityInfoById(@RequestParam("stuId") int studentId) {

		logger.info("Entry inside the method of getting the university details of the student");

		Student stuInfo = studentService.getStudentInformationById(studentId);
		
		Object st = getUniversityDetails(stuInfo);

		
		logger.info("Exit inside the method of getting the university details of the student");
		
		return st;



	}
}
