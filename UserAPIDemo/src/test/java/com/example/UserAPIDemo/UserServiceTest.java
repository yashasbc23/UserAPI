package com.example.UserAPIDemo;

import com.example.UserAPIDemo.service.UserService;
import com.example.UserAPIDemo.service.UserServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.BDDMockito.given;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
    @Mock
    private UserServiceImpl userServiceImpl;

    private User user;

    @BeforeEach
    public void setup(){
         user = new User("12345","Renne","Iman","H","re.ima@gmail.com","9874532051","456 main st, chicago","456 main st, chicago");
    }

    @Test
    @DisplayName("Test FindByID Success")
    void testFindByUserId() {
        User user1= new User("12345","Renne","Iman","H","re.ima@gmail.com","9874532051","456 main st, chicago","456 main st, chicago");
        User user2= new User("45234","Sandy","Tanguay","L","tan.san@gmail.com","4798485962","123 main st, Irving","456 main st, Irving");
        User user3= new User("34246","Amanda","Comperhen","","amanda@gmail.com","4493734616","616 main st, Phoenix","456 main st, Phoenix");
        List<User> records = new ArrayList<>(Arrays.asList(user1, user2, user3));
        //Mockito.when(userServiceImpl.getAllUsers()).thenReturn(records);
        assertThat(records.get(0).getLastName()).isEqualTo("Iman");

    }

    @Test
    @DisplayName("Test FindByID Success")
    void testGetAllUsers() {
        User user1= new User("12345","Renne","Iman","H","re.ima@gmail.com","9874532051","456 main st, chicago","456 main st, chicago");
        User user2= new User("45234","Sandy","Tanguay","L","tan.san@gmail.com","4798485962","123 main st, Irving","456 main st, Irving");
        User user3= new User("34246","Amanda","Comperhen","","amanda@gmail.com","4493734616","616 main st, Phoenix","456 main st, Phoenix");
        List<User> userRecords = new ArrayList<>(Arrays.asList(user1, user2, user3));

        assertThat(userRecords.size()).isEqualTo(3);

    }


}
