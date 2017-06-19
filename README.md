# Course-Application

Course Application API

The Course Application Information is an application which provides an API for adding new students in the in-memory database provided with the Student Name, Student Age, Student Course, Student University Name and Student University Country. Application will call the University API to get the complete University Detail based on the provided University Details.
Also, user can request to view all the list of Students with their details in a tabular view, where he can delete any Student, and get the University detail for each Student.

Provided a rich UI based on Angular to perform all the above activities.

#PreRequisities

Download and install STS 3.8.4.RELEASE. Install the Gradle Plugin and configure the environment variables. Clone the Project Source Code from GITHUB URI: "https://github.com/rgopawat/Course-Application.git" 
Can also Install POSTMAN in your chrome broswer just to test the API's

#Installing

'gradle clean install' on the local GIT repository. Ensure all tests are successfull as part of build.

#How to Run and Test

Run it as Spring Boot App. 

LAUNCH URL http://localhost:8085/

TEST VIA POSTMAN http://localhost:8085/add/students

Sample JSON: { "studentName": "ABCD", "studentAge": 28, "studentCourse": "IT Development", "universityName: "Stevenson University", "universityCountry" : "United States"
GET ALL URL (Sample) http://localhost:8085/getAllStudents
