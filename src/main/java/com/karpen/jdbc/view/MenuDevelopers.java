package com.karpen.jdbc.view;

import com.karpen.jdbc.controller.AccountController;
import com.karpen.jdbc.controller.DeveloperController;
import com.karpen.jdbc.controller.SkillController;
import com.karpen.jdbc.model.Account;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class MenuDevelopers {

    private final String menuSelectionMessage = "Выберите необходимое действие:\n" +
            "1.Показать всех developers\n" +
            "2.Создать developers\n" +
            "3.Удалить developer\n" +
            "4.Изменить существующего developer\n" +
            "5.Выход";

    private final String AccountSelectionMessage = "Выберите статус аккаунта: " +
            "1-ACTIVE;" +
            "2-DELETED;" +
            "3-BANNED";

    private final String getAllMessage = "Весь список Developers:";

    private final String getByIdMessage = "Введите id для выбора Developers ";

    private final String deleteMessage = "Выберите Developers для его удаления из списка";

    private final String incorrectInputMessage = "Неверный ввод, повторите";

    private final String endMessage = "Выход из меню Developers";

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
                    System.out.println("Впишите имя developer");
                    String name = scanner.nextLine();
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

                    System.out.println("Выбрать Account:");
                    System.out.print(accountController.getAll());
                    String accountId = scanner.nextLine();
                    Account account = accountController.getById(Long.valueOf(accountId));

                    System.out.println(AccountSelectionMessage);
                    String idstatus = scanner.nextLine();
                    if (idstatus.equalsIgnoreCase("1")) {
                        idstatus = String.valueOf(1);
                    } else if (idstatus.equalsIgnoreCase("2")) {
                        idstatus = String.valueOf(2);
                    } else if (idstatus.equalsIgnoreCase("3")) {
                        idstatus = String.valueOf(3);
                    } else idstatus = String.valueOf(3);
                    developerController.create(name, account.getId(), skillIds, Long.valueOf(idstatus));
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
                    int upId = Integer.parseInt((scanner.nextLine()));

                    System.out.println("Впишите имя developer");
                    String upName = scanner.nextLine();
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

                    System.out.println("Выбрать Account:");
                    System.out.println(accountController.getAll());
                    String upAccountId = scanner.nextLine();
                    Account UpAccount = accountController.getById(Long.valueOf(upAccountId));
                    System.out.println(AccountSelectionMessage);
                    String upStatusId = scanner.nextLine();
                    if (upStatusId.equalsIgnoreCase("1")) {
                        upStatusId = String.valueOf(1);
                    } else if (upStatusId.equalsIgnoreCase("2")) {
                        upStatusId = String.valueOf(2);
                    } else if (upStatusId.equalsIgnoreCase("3")) {
                        upStatusId = String.valueOf(3);
                    } else upStatusId = String.valueOf(3);

                    developerController.update(upName,(long) upId, UpAccount.getId(), upSkillIds, Long.valueOf(upStatusId));
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
