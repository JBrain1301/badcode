package ru.liga.intership.badcode;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import ru.liga.intership.badcode.service.PersonService;

import java.sql.SQLException;

import static org.assertj.core.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BadcodeApplicationTests {
	static PersonService personService;

	@BeforeClass
	public static void getPersonsService(){
		personService = new PersonService();
	}

	@Test
	public void personListNotNull() throws SQLException {
		assertThat(personService.getAdultMaleUsers()).isNotNull();
	}

	@Test
	public void personsIsMale() throws SQLException {
		personService.getAdultMaleUsers().forEach(person -> assertThat(person.getSex()).isEqualTo("male"));
	}

	@Test
	public void personsIsAdult() throws SQLException {
		personService.getAdultMaleUsers().forEach(person -> assertThat(person.getAge()).matches(age -> age > 18));
	}

	@Test
	public void checkAvgBMI() throws SQLException {
		assertThat(personService.getAdultMaleUsersAverageBMI()).isEqualTo(25.774209960992707);
	}

}
