import com.karpen.jdbc.view.MainMenu;

import java.sql.SQLException;

class Run {


    public static void main(String[] args) throws SQLException {

        MainMenu runner = new MainMenu();
        try {
            runner.showMainMenu();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
