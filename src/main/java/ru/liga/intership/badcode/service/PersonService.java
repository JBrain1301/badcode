package ru.liga.intership.badcode.service;


import ru.liga.intership.badcode.domain.Person;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class PersonService {
    private Connection connection;

    /**
     * Возвращает средний индекс массы тела всех лиц мужского пола старше 18 лет
     *
     * @return
     */
    public double getAdultMaleUsersAverageBMI() throws SQLException {
        List<Person> personList = getAdultMaleUsers();
        AtomicReference<Double> totalImt = new AtomicReference<>(0.0);
        personList.forEach(person -> {
            double heightInMeters = person.getHeight() / 100d;
            double imt = person.getWeight() / (Double) (heightInMeters * heightInMeters);
            totalImt.updateAndGet(v ->(v + imt));
        });
        double avgBMI = totalImt.get() / personList.size();
        System.out.println("Average imt - " + avgBMI);
        return avgBMI;
    }

    public List<Person> getAdultMaleUsers() throws SQLException {
        List<Person> adultPersons = new ArrayList<>();
        String sql = "SELECT * FROM person WHERE sex = 'male' AND age > 18";
        try {
            connection = ConnectionService.getConnection();
            Statement statement = ConnectionService.getStatement(connection);
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                Person p = new Person();
                //Retrieve by column name
                p.setId(rs.getLong("id"));
                p.setSex(rs.getString("sex"));
                p.setName(rs.getString("name"));
                p.setAge(rs.getLong("age"));
                p.setWeight(rs.getLong("weight"));
                p.setHeight(rs.getLong("height"));
                adultPersons.add(p);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            ConnectionService.closeConnection(connection);
        }
        return adultPersons;
    }

}
