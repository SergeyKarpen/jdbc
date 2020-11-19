package com.karpen.jdbc.repository.io;

import com.karpen.jdbc.model.Skill;
import com.karpen.jdbc.repository.SkillRepository;
import com.karpen.jdbc.util.ConnectToDataBase;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class JsonSkillRepositoryImpl implements SkillRepository {
    @Override
    public Skill create(Skill skill) throws IOException {
        return null;
    }

    @Override
    public Skill update(Skill skill) throws IOException {
        return null;
    }

    @Override
    public void getAll() throws SQLException {
        String sql = "SELECT * FROM skills";
        ResultSet resultSet = null;
        try {
            resultSet = ConnectToDataBase.statement(ConnectToDataBase.connectToDB()).executeQuery(sql);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String data = resultSet.getString("name");

            System.out.println("id: " + id);
            System.out.println("Data: " + data);
        }

    }

    @Override
    public Skill getById(Long aLong) throws SQLException {
        String s = String.valueOf(aLong);

        String sql = "SELECT * FROM skills WHERE id =" + s;
        ResultSet resultSet = null;
        try {
            resultSet = ConnectToDataBase.statement(ConnectToDataBase.connectToDB()).executeQuery(sql);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        while (resultSet.next()) {
            String name = resultSet.getString("name");
            System.out.println("name: " + name);
        }
        return null;
    }

    /*
     @Override
     public void writeListObjectsInJson(List<Skill> skills, String fileName) {
         FileWriter writer = null;
         try {
             writer = new FileWriter(getPathToFile(fileName), false);
         } catch (IOException e) {
             e.printStackTrace();
         }
         new Gson().toJson(skills, writer);
         try {
             assert writer != null;
             writer.close();
         } catch (IOException e) {
             e.printStackTrace();
         }
     }

     @Override
     public Long maxIdInList(List<Skill> skills) {
         Long along;
         if (skills.isEmpty()) {
             return 0L;
         } else return along = skills.stream().max(Comparator.comparing(Skill::getId))
                 .get()
                 .getId();
     }

     @Override
     public List<Skill> getListObjectsFromJson(String pathFile) {

         Gson gson = new Gson();
         List<Skill> list;
         try (FileReader reader = new FileReader(pathFile)) {
             list = gson.fromJson(reader, new TypeToken<ArrayList<Skill>>() {
             }.getType());
             return list;
         } catch (IOException e) {
             e.printStackTrace();
         }
         return Collections.emptyList();
     }

    @Override
    public Skill create(Skill skill) throws IOException {
        return null;
    }

    @Override
    public Skill update(Skill skill) throws IOException {
        return null;
    }

    @Override
    public Skill getById(Long aLong) throws IOException {
        return null;
    }

    @Override
    public void deleteById(Long aLong) throws IOException {

    }

    @Override
    public List<Skill> getListObjectsFromJson(String s) throws IOException {
        return null;
    }

    @Override
    public Long maxIdInList(List<Skill> t) throws IOException {
        return null;
    }

    @Override
    public void writeListObjectsInJson(List<Skill> list, String s) throws IOException {
    }
*/

    @Override
    public void deleteById(Long aLong) throws IOException {

    }

    @Override
    public List<Skill> getListObjectsFromJson(String s) throws IOException {
        return null;
    }

    @Override
    public void writeListObjectsInJson(List<Skill> list, String s) throws IOException {

    }

    @Override
    public Long maxIdInList(List<Skill> t) throws IOException {
        return null;
    }
}
