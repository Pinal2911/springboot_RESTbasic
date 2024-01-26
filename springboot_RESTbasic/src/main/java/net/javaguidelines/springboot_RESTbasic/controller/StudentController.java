package net.javaguidelines.springboot_RESTbasic.controller;

import net.javaguidelines.springboot_RESTbasic.bean.Student;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class StudentController {
    //response_entity_example
    //ResponseEntity represents the whole HTTP response : status code,headers, and body
    //as a result we can use it to fully configure the HTTP response
    //if we want to use it, we have to return it from the endpoint; spring takes care of the rest
    //ResponseEntity if a generic type. consequently we can use any type as the response body


    @GetMapping("/student")
    public ResponseEntity<Student> getStudent(){
        Student s =new Student(
                1,
                "Pinal",
                "Parmar"
        );
        return new ResponseEntity<>(s,HttpStatus.OK);
        //or second way to return response
        //below does the same as above
        //return ResponseEntity.ok(s);
    }

    //following is the way to return List as a json using REST API

    @GetMapping("/students")
    public List<Student> getStudents(){
        List<Student> students=new ArrayList<>();
        students.add(new Student(1,"Pinal","Parmar"));
        students.add(new Student(2,"Tom","Dsouza"));
        students.add(new Student(3,"ABC","PQR"));

        return students;
    }

    //SPRING BOOT REST API with path variable
    //http://localhost:8080/students/1 - handling single path variable

    @GetMapping("/students/{id}")
    public Student studentPathVar(@PathVariable int id){
        return new Student(id,"Pin","Parmar");
    }

    //handling multiple path variables with different
    //name in URI and in function
    //http://localhost:8080/students/1/tom/jery
    @GetMapping("/students/{id}/{first-name}/{last-name}")
    public Student studentPathMultiple(@PathVariable("id") int sid,
                                       @PathVariable("first-name") String fname,
                                       @PathVariable("last-name") String lname){
        return new Student(sid,fname,lname);
    }

    //request param
    //http://localhost:8080/students/query?id=1
    @GetMapping("students/query")
    public Student studentRequestVariable(@RequestParam int id){
        return new Student(id,"pinalll","parmarr");
    }

    //@POSTMapping- used for mapping HTTP POST request onto specific handler method
    //@RequestBody- is responsible for retrieving the HTTP request body and automatically
    //converting it to the java object
    //@ResponseStatus is used for indicating status of request (200,404,etc)
    @PostMapping("/students/create")
    @ResponseStatus(HttpStatus.CREATED)
    public Student createStudent(@RequestBody Student student){
        System.out.println(student.getId());
        System.out.println(student.getFirstName());
        System.out.println(student.getLastName());
        return student;
    }



    //Springboot REST API that handles HTTP PUT Request-updating existing resource
    //putmapping-usd for mapping HTTP PUT request onto specific handler method

    @PutMapping("students/{id}/update")
    public Student updateStudent(@RequestBody Student student,@PathVariable("id") int studentId){
        System.out.println(student.getFirstName());
        System.out.println(student.getLastName());
        System.out.println(student.getId());
        return student;
    }


    //spring boot REST API that handles HTTP DELETE Request - deleting the existing resource
    @DeleteMapping("students/{id}/delete")
    public String deleteStudent(@PathVariable("id") int studentId){
        System.out.println(studentId);
        return "student deleted successfully";
    }
}
