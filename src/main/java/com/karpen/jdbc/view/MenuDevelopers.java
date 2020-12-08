package com.karpen.jdbc.view;

import com.karpen.jdbc.controller.AccountController;
import com.karpen.jdbc.controller.DeveloperController;
import com.karpen.jdbc.controller.SkillController;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class MenuDevelopers {

    private final String menuSelectionMessage = "Выберите необходимое действие:\n" +
            "1.Показать всех developers\n" +
            "2.Создать developer\n" +
            "3.Удалить developer\n" +
            "4.Изменить существующего developer\n" +
            "5.Выход";

    private final String AccountSelectionMessage = "Выберите статус аккаунта: " +
            "1-ACTIVE;" +
            "2-DELETED;" +
            "3-BANNED";

    private final String getAllMessage = "Весь список developer:";

    private final String getByIdMessage = "Введите id для выбора developer";

    private final String deleteMessage = "Выберите developer для его удаления из списка";

    private final String incorrectInputMessage = "Неверный ввод, повторите";

    private final String endMessage = "Выход из меню developer";

    public void showMenuDevelopers() throws Exception {
        DeveloperController developerController = new DeveloperController();
        SkillController skillController = new SkillController();
        AccountController accountController = new AccountController();

        boolean isExit = false;
        do {
            System.out.println(menuSelectionMessage);
            Scanner scanner = new Scanner(System.in);
            String inputNumber = scanner.nextLine();
            switch (inputNumber) {
                case ("1"):
                    System.out.println(getAllMessage);
                    System.out.println(developerController.getAll());
                    break;
                case ("2"):
                    System.out.println("Впишите First Name for developer");
                    String firstName = scanner.nextLine();
                    System.out.println("Впишите Last Name for developer");
                    String lastname = scanner.nextLine();
                    Set<Long> skillIds = new HashSet<>();
                    boolean pip = true;
                    do {
                        System.out.println("Выберите скилы из списка:");
                        System.out.println((skillController.getAll()));
                        String inputID = scanner.nextLine();
                        skillIds.add(Long.valueOf((inputID)));
                        System.out.println("Жми Q для выхода или Enter для продолжения");
                        String YN = scanner.nextLine();
                        if (YN.equalsIgnoreCase("Q")) {
                            pip = false;
                        }
                    }
                    while (pip);
                    System.out.println("Выбрать Account по id:");
                    System.out.println(accountController.getAll());
                    String accountId = scanner.nextLine();
                    developerController.create(firstName, lastname, Long.valueOf(accountId), skillIds);
                    break;
                case ("3"):
                    System.out.println(deleteMessage);
                    System.out.println(developerController.getAll());
                    int inputDelDeveloper = scanner.nextInt();
                    developerController.deleteById((long) inputDelDeveloper);
                    break;
                case ("4"):
                    System.out.println(getByIdMessage);
                    System.out.println(developerController.getAll());
                    int idUp = Integer.parseInt((scanner.nextLine()));

                    System.out.println("Впишите First Name for developer");
                    String firstNameUp = scanner.nextLine();

                    System.out.println("Впишите Last Name for developer");
                    String lastNameUp = scanner.nextLine();

                    Set<Long> upSkillIds = new HashSet<>();
                    boolean pup = true;
                    do {
                        System.out.println("Выберите скилы из списка:");
                        System.out.println((skillController.getAll()));
                        String upSks = scanner.nextLine();
                        upSkillIds.add(Long.valueOf((upSks)));
                        System.out.println("Жми Q для выхода или Enter для продолжения");
                        String YN = scanner.nextLine();
                        if (YN.equalsIgnoreCase("Q")) {
                            pup = false;
                        }
                    }
                    while (pup);
                    System.out.println("Выбрать id Account for developer:");
                    System.out.println(accountController.getAll());
                    String upAccountId = scanner.nextLine();
                    developerController.update(firstNameUp,lastNameUp, (long)idUp, Long.valueOf(upAccountId), upSkillIds);
                    break;
                case ("5"):
                    isExit = true;
                    break;
                case ("6"):
                default:
                    System.out.println(incorrectInputMessage);
                    break;
            }
        } while (!isExit);
        System.out.println(endMessage);
    }
}
