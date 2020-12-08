package com.karpen.jdbc.view;

import com.karpen.jdbc.controller.SkillController;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;

public class MenuSkills {

    private final String menuSelectionMessage = "Выберите необходимое действие:\n" +
            "1.Просмотреть список навыков\n" +
            "2.Добавить навык\n" +
            "3.Удалить навык\n" +
            "4.Изменить существующий навык\n" +
            "5.Выход";

    private final String getAllMessage = "Список навыков:";

    private final String getByIdMessage = "Введите id для выбора навыка ";

    private final String saveMessage = "Введите навык для добавления его в существующий список";

    private final String deleteMessage = "Выберите навык для его удаления из списка";

    private final String editMessage = "Введите необходимые изменения";

    private final String incorrectInputMessage = "Неверный ввод, повторите";

    private final String endMessage = "Выход из меню Skills";

    public void showMenuSkills() throws IOException {
        SkillController skillController = new SkillController();

        boolean isExit = false;
        do {
            System.out.println(menuSelectionMessage);
            Scanner scanner = new Scanner(System.in);
            String inputNumber = scanner.nextLine();
            switch (inputNumber) {
                case ("1"):
                    System.out.println(getAllMessage);
                    try {
                        System.out.println(skillController.getAll());
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                    break;
                case ("2"):
                    System.out.println(saveMessage);
                    try {
                        System.out.println(skillController.getAll());
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                    String inputNewStringSkill = scanner.nextLine();
                    try {
                        skillController.create(inputNewStringSkill);
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                    break;
                case ("3"):
                    System.out.println(deleteMessage);
                    try {
                        System.out.println(skillController.getAll());
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                    int inputDelSkill = scanner.nextInt();
                    try {
                        skillController.deleteById((long) inputDelSkill);
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                    break;
                case ("4"):
                    System.out.println(getByIdMessage);
                    try {
                        System.out.println(skillController.getAll());
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                    int inputUpdateSkillId = Integer.parseInt(scanner.nextLine());
                    System.out.println(editMessage);
                    String inputUpdateSkillName = scanner.nextLine();
                    try {
                        skillController.update((long) inputUpdateSkillId, inputUpdateSkillName);
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                    break;
                case ("5"):
                    isExit = true;
                    break;
                default:
                    System.out.println(incorrectInputMessage);
                    break;
            }
        } while (!isExit);
        System.out.println(endMessage);
    }
}