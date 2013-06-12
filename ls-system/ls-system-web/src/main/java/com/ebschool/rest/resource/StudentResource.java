package com.ebschool.rest.resource;

import com.ebschool.model.*;
import com.ebschool.rest.model.StudentElement;
import com.ebschool.rest.utils.RestElementBuilder;
import com.ebschool.rest.utils.RestHelper;
import com.ebschool.rest.utils.paging.PageResult;
import com.ebschool.rest.utils.paging.SetPageResult;
import com.ebschool.rest.utils.transactions.TransactionRequired;
import com.ebschool.service.ClassInfoServiceLocal;
import com.ebschool.service.LevelServiceLocal;
import com.ebschool.service.StudentServiceLocal;
import com.ebschool.service.UserServiceLocal;
import org.jboss.security.auth.spi.Util;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * User: michau
 * Date: 5/22/13
 */
@Path("/students")
@RequestScoped
@TransactionRequired
public class StudentResource {

    @EJB
    UserServiceLocal userService;

    @EJB
    StudentServiceLocal studentService;

    @EJB
    LevelServiceLocal levelService;

    @EJB
    ClassInfoServiceLocal classInfoService;

    @Inject
    RestElementBuilder restElementBuilder;

    public StudentResource() {}

    @GET
    @Path("/{login}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getByLogin(@PathParam("login") String login){
        Student student = (Student) RestHelper.throw404IfNull(userService.getByLogin(login));
        return Response.ok().entity(restElementBuilder.buildStudentElement(student)).build();
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getAll() {
        Set<Student> students = RestHelper.throw404IfNull(studentService.getAll());
        PageResult<StudentElement> results = new SetPageResult<>(
                restElementBuilder.buildElementSet(students, StudentElement.class));
        GenericEntity entities = new GenericEntity<Set<StudentElement>>((Set<StudentElement>)results.getPageElements()){};
        return Response.ok().entity(entities).build();
    }

    @POST
    @Path("/add")
    public Response create(@FormParam("login") String login,
                           @FormParam("firstName") String firstName,
                           @FormParam("lastName") String lastName,
                           @FormParam("email") String email,
                           @FormParam("phoneNumber") String phoneNumber,
                           @FormParam("dateOfBirth") String dateOfBirth,
                           @FormParam("country") String country,
                           @FormParam("city") String city,
                           @FormParam("street") String street,
                           @FormParam("zipCode") String zipCode,
                           @FormParam("levelSelect") Long levelId,
                           @FormParam("classSelect") List<Long> classIds,
                           @FormParam("pin") String pin,
                           // TODO: research for a better way
                           @FormParam("password") String password){
        Student student = new Student();
        DetailedInfo detailedInfo = new DetailedInfo();
        Address address = new Address();
        Level level = levelService.getById(levelId);

        Set<ClassInfo> classSet = classInfoService.getByIds(classIds);

        address.setCity(city);
        address.setCountry(country);
        address.setStreet(street);
        address.setZipCode(zipCode);

        detailedInfo.setAddress(address);
        Date dateJoined = new Date();
        Date dateOfBirthDate = null;
        try {
            dateOfBirthDate = new SimpleDateFormat("mm/dd/yyyy").parse(dateOfBirth);
        } catch (ParseException ex){
            throw new WebApplicationException(ex, Response.Status.NOT_ACCEPTABLE);
        }
        detailedInfo.setDateJoined(dateJoined.getTime());
        detailedInfo.setDateOfBirth(dateOfBirthDate.getTime());
        detailedInfo.setIdentificationNumber(pin);

        student.setFirstName(firstName);
        student.setLastName(lastName);
        student.setLogin(login);
        student.setActive(true);
        student.setEmail(email);
        student.setPhoneNumber(phoneNumber);
        student.setPassword(Util.createPasswordHash("SHA1", Util.BASE64_ENCODING, null, null, password));
        student.setDetailedInfo(detailedInfo);
        student.setLevel(level);
        student.setClasses(classSet);

        userService.signUp(student);

        return Response.ok().entity(restElementBuilder.buildStudentElement(student)).build();
    }

}
