package com.sohaib.app.service;

import com.sohaib.app.entity.Student;
import com.sohaib.app.feignclient.AddressFeignClient;
import com.sohaib.app.repository.StudentRepository;
import com.sohaib.app.request.CreateStudentRequest;
import com.sohaib.app.response.AddressResponse;
import com.sohaib.app.response.StudentResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;



@Service
public class StudentService{

    private static final Logger LOGGER=  LoggerFactory.getLogger(StudentService.class);

    @Autowired
    StudentRepository studentRepository;

   // @Autowired
    //AddressFeignClient addressFeignClient;

    @Autowired
    CommonService commonService;
    //@Autowired
    //WebClient webClient;



    public StudentResponse createStudent(CreateStudentRequest createStudentRequest) {


        Student student = new Student();
        student.setFirstName(createStudentRequest.getFirstName());
        student.setLastName(createStudentRequest.getLastName());
        student.setEmail(createStudentRequest.getEmail());
        student.setAddressId(createStudentRequest.getAddressId());
        student = studentRepository.save(student);
        StudentResponse studentResponse = new StudentResponse(student);
        //studentResponse.getAddressResponse(getAddressById(Long.valueOf(student.getAddressId())));
       // studentResponse.setAddressResponse(addressFeignClient.getById(student.getId()));
        studentResponse.setAddressResponse(commonService.getAddressById(student.getId()));
        //studentResponse.getAddressResponse(addressFeignClient.getById(student.getId()));
        return new StudentResponse(student);
    }

    public StudentResponse getById(long id) {

        LOGGER.info("Get student by id: {}", id);
        Student student = studentRepository.findById(id).get();
        StudentResponse studentResponse = new StudentResponse(student);
       studentResponse.setAddressResponse(commonService.getAddressById(id));
       // studentResponse.setAddressResponse(addressFeignClient.getById(student.getId()));
        LOGGER.info(String.valueOf(commonService.getAddressById(id)));
        return studentResponse;
    }


}
