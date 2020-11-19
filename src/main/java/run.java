import com.karpen.jdbc.repository.io.JsonSkillRepositoryImpl;
import com.karpen.jdbc.util.ConnectToDataBase;

import java.sql.*;

class run {

    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DATABASE_URL = "jdbc:mysql://localhost/developers?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";

    static final String USER = "root";
    static final String PASSWORD = "paranido2";

        /*
        MainMenu runner = new MainMenu();
        runner.showMainMenu();

         */

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        JsonSkillRepositoryImpl jsonSkillRepository = new JsonSkillRepositoryImpl();
        jsonSkillRepository.getAll();
        ConnectToDataBase connectToDataBase = new ConnectToDataBase();

        jsonSkillRepository.getById((long) 2);

    }
}