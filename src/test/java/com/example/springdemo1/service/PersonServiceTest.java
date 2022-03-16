package com.example.springdemo1.service;

import com.example.springdemo1.model.domain.Person;
import com.example.springdemo1.repository.PersonRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PersonServiceTest {

    @Mock
    private PersonRepository repositoryMock;

    @InjectMocks
    private PersonService service;

    @Test
    public void When_FirstAndLastNamePresent_Expect_PersonsMatchingFirstAndLastName(){
        Person expected = Person.builder()
                .firstName("First")
                .lastName("Last")
                .build();
        when(repositoryMock.findAllByLastNameAndFirstName(anyString(), anyString())).thenReturn(List.of(expected));

        List<Person> actual = service.fetchPersons("First", "Last");

        assertEquals(expected, actual.get(0));
    }

    @Test
    public void When_FirstNamePresent_Expect_PersonsMatchingFirstName(){
        Person expected = Person.builder().firstName("name").build();
        when(repositoryMock.findAllByFirstName(anyString())).thenReturn(List.of(expected));

        List<Person> actual = service.fetchPersons("name", null);

        assertEquals(expected.getFirstName(), actual.get(0).getFirstName());
    }

    @Test
    public void When_LastNamePresent_Expect_PersonsMatchingLastName(){
        Person expected = Person.builder().lastName("name").build();
        when(repositoryMock.findAllByLastName(anyString())).thenReturn(List.of(expected));

        List<Person> actual = service.fetchPersons(null, "name");

        assertEquals(expected.getLastName(), actual.get(0).getLastName());
    }

}
