package com.example.UserAPIDemo.contollers;

import com.example.UserAPIDemo.User;
import com.example.UserAPIDemo.service.UserServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.mock;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebAppConfiguration
public class UserControllerTest {
    @Autowired
    MockMvc mockMvc;
    @Autowired
    ObjectMapper mapper;

    @MockBean
    UserServiceImpl userServiceImpl;
    User user1= new User("12345","Renne","Iman","H","re.ima@gmail.com","9874532051","456 main st, chicago","456 main st, chicago");
    User user2= new User("45234","Sandy","Tanguay","L","tan.san@gmail.com","4798485962","123 main st, Irving","456 main st, Irving");
    User user3= new User("34246","Amanda","Comperhen","","amanda@gmail.com","4493734616","616 main st, Phoenix","456 main st, Phoenix");

    protected String mapToJson(Object obj) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(obj);
    }

    @Test
    public void getAllUsers_success() throws Exception {
        List<User> records = new ArrayList<>(Arrays.asList(user1, user2, user3));

        Mockito.when(userServiceImpl.getAllUsers()).thenReturn(records);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/getAllUsers")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$[1].firstName", is("Sandy")));
    }

    @Test
    public void getUserById_success() throws Exception{
        Mockito.when(userServiceImpl.getUserByID(user3.getId())).thenReturn((user3));

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/findUserById/34246")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$.lastName", is("Comperhen")));

    }

    @Test
    public void addUser_success() throws Exception {
        User user= mock(User.class);//new User("99997","Keith","Smith","","ksmith@gmail.com","4567891230","665 main st, Charlotte","665 main st, Charlotte");
        ArgumentCaptor<String> valueCapture = ArgumentCaptor.forClass(String.class);
        doNothing().when(user).setEmail(valueCapture.capture());
        user.setEmail("ksmith@gmail.com");

    }
}
