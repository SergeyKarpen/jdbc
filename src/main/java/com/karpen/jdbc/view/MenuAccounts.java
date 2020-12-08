package com.karpen.jdbc.view;

import com.karpen.jdbc.controller.AccountController;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;

public class MenuAccounts {
    private final String menuSelectionMessage = "Выберите необходимое действие:\n" +
            "1.Просмотреть список учётных записей\n" +
            "2.Добавить учётку\n" +
            "3.Удалить учётку\n" +
            "4.Изменить существующую учётку\n" +
            "5.Выход";

    private final String getAllMessage = "Список учётных записей:";

    private final String getByIdMessage = "Введите id для выбора учётки ";

    private final String saveMessage = "Введите наименование учетки для добавления её в существующий список";

    private final String deleteMessage = "Выберите учёку для удаления ";

    private final String editMessage = "Введите необходимые изменения";

    private final String createMessage = "Введите статус учетки";

    private final String incorrectInputMessage = "Неверный ввод, повторите";

    private final String endMessage = "Выход из меню Accounts";

    public void showMenuAccounts() throws IOException {
        AccountController accountController = new AccountController();

        boolean isExit = false;
        do {
            System.out.println(menuSelectionMessage);
            Scanner scanner = new Scanner(System.in);
            String inputNumber = scanner.nextLine();
            switch (inputNumber) {
                case ("1"):
                    System.out.println(getAllMessage);
                    try {
                        System.out.println(accountController.getAll());
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                    break;
                case ("2"):
                    System.out.print(saveMessage);
                    String inputNewAccount = scanner.nextLine();
                    System.out.println(createMessage);
                    String inputNewAccountStatus = scanner.nextLine();
                    try {
                        accountController.create(inputNewAccount, inputNewAccountStatus);
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                    break;
                case ("3"):
                    System.out.print(deleteMessage);
                    int inputDelAccount = scanner.nextInt();
                    try {
                        accountController.deleteById((long) inputDelAccount);
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                    break;
                case ("4"):
                    System.out.print(getByIdMessage);
                    try {
                        System.out.println(accountController.getAll());
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                    int inputUpdateAccountId = Integer.parseInt(scanner.nextLine());
                    System.out.println(editMessage);
                    String inputUpdateAccountName = scanner.nextLine();
                    System.out.println(createMessage);
                    String NewAccountStatus = scanner.nextLine();
                    try {
                        accountController.update(Long.valueOf(inputUpdateAccountId), inputUpdateAccountName, NewAccountStatus);
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
