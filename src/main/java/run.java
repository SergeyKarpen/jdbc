import com.karpen.jdbc.repository.jdbc.JdbcAccountsRepositoryImpl;

import java.sql.SQLException;

class run {


    public static void main(String[] args) throws SQLException {

/*
        MainMenu runner = new MainMenu();
        try {
            runner.showMainMenu();
        } catch (Exception e) {
            e.printStackTrace();
        }
*/

        JdbcAccountsRepositoryImpl jdbcSkillsRepository = new JdbcAccountsRepositoryImpl();

        System.out.println(jdbcSkillsRepository.getAll())
        ;
    }

}
