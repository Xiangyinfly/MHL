package com.view;

import com.domain.Bill;
import com.domain.Food;
import com.domain.Table;
import com.service.BillService;
import com.service.EmployeeService;
import com.service.FoodService;
import com.service.TableService;
import com.utils.Utility;

import java.util.List;

public class MHLView {
    private boolean loop = true;
    private boolean loop2 = true;
    private EmployeeService employeeService = new EmployeeService();
    private TableService tableService = new TableService();
    private FoodService foodService = new FoodService();
    private BillService billService = new BillService();

    public void mainMenu() throws Exception {
        while (loop) {
            System.out.println("===================Welcome to Order Menu===================");
            System.out.println("1.Log in");
            System.out.println("2.Exit");
            System.out.print("Your choice: ");
            String key = Utility.readString(1);

            switch (key) {
                case "1":
                    System.out.println("===================Login===================");
                    System.out.print("EmpId:");
                    String empId = Utility.readString(50);
                    System.out.print("Password:");
                    String passwd = Utility.readString(16);

                    if (employeeService.getEmp(empId,passwd) != null) {//check
                        System.out.println("Login succeed!");

                        while (loop2) {
                            System.out.println("===================Welcome Emp[ " + empId + " ]===================");
                            subMenu();
                        }
                    } else {
                        System.out.println("Login failed!");
                    }

                    break;
                case "2":
                    loop = false;
                    System.out.println("exit...");
                    break;
                default:
                    System.out.println("please input 1-2...");
            }
        }
    }

    public void subMenu() throws Exception {
        System.out.println("1.Table state");
        System.out.println("2.Register table");
        System.out.println("3.Show food");
        System.out.println("4.Order service");
        System.out.println("5.Show bill");
        System.out.println("6.Check out");
        System.out.println("0.Log out");
        System.out.print("Your choice: ");
        String key = Utility.readString(1);

        switch (key) {
            case "1":
                getTableState();
                break;
            case "2":
                registerTable();
                break;
            case "3":
                getFoodList();
                break;
            case "4":
                orderFood();
                break;
            case "5":
                getBillList();
                break;
            case "6":
                checkOut();
                break;
            case "0":
                loop2 = false;
                System.out.println("Logout...");
                break;
            default:
                System.out.println("please input 0-6...");
        }
    }

    public void getTableState() throws Exception {
        List<Table> tables = tableService.tableList();
        System.out.println("===================Table List===================");
        System.out.println("  \t\tid\t\t\t\t\t\tstate");
        for (Table table :tables) {
            System.out.println("  \t\t" + table.getId() + "\t\t\t\t\t\t" + table.getState());
        }
    }

    public void registerTable() throws Exception {
        System.out.println("===================Register Table===================");
        System.out.println("Wanted table's number(1-6): ");
        int id = Utility.readInt();
        if (tableService.getTable(id) != null && tableService.getTable(id).getState().equals("no")) {
            System.out.println("Sure to register(y/n): ");
            String key = Utility.readString(1);
            if (key.equals("y")) {
                System.out.println("Your name: ");
                String name = Utility.readString(50);
                System.out.println("Your phone number: ");
                String tel = Utility.readString(50);
                if (tableService.registerTable(id,name,tel)) {
                    System.out.println("Register succeed!");
                } else {
                    System.out.println("Register failed...");
                }
            } else {
                System.out.println("Return to menu...");
            }
        } else {
            System.out.println("No such table or table has registered! Failed!");
        }
    }

    public void getFoodList() throws Exception {
        List<Food> foods = foodService.foodList();
        System.out.println("===================Food List===================");
        System.out.println("\tid\t\tname\t\ttype\t\tprice");
        for (Food food :foods) {
            System.out.println("\t" + food.getId() + "\t\t" + food.getName() + "\t\t" + food.getType() + "\t\t" + food.getPrice());
        }
    }

    public void orderFood() throws Exception {
        System.out.println("===================Order Food===================");
        System.out.println("Your table id: ");
        int tableId = Utility.readInt();
        if (tableService.getTable(tableId) == null || tableService.getTable(tableId).getState().equals("no")) {
            System.out.println("No such registered table!");
            return;
        }
        System.out.println("Wanted food id: ");
        int foodId = Utility.readInt();
        if (!foodService.isFoodExist(foodId)) {
            System.out.println("No such food!");
            return;
        }
        System.out.println("Food number: ");
        int num = Utility.readInt();
        System.out.println("Sure to order(y/n): ");
        String key = Utility.readString(1);
        if (key.equals("y")) {
            if (billService.order(tableId,foodId,num)) {
                System.out.println("Order succeed!");
            } else {
                System.out.println("Order failed!");
            }
        }
    }

    public void getBillList() throws Exception {
        System.out.println("===================Bill List===================");
        System.out.print("Your tableId: ");
        int tableId = Utility.readInt();
        if (tableService.isRegisteredTable(tableId)) {
            Table table = tableService.getTable(tableId);
            System.out.println("id:" + table.getId() + "\torderName:" + table.getOrderName() + "\torderTel:" + table.getOrderTel());
            System.out.println("billId\t\tfoodName\t\tfoodNum\t\tunitPrice\t\tmoney\t\tstate");
            List<Bill> bills = billService.billList(tableId);
            double total = 0;
            for (Bill bill :bills) {
                total += bill.getMoney();
                System.out.printf("%s\t\t%s\t\t%d\t\t%.2f\t\t%.2f\t\t%s\n",bill.getBillId(),foodService.getFoodName(bill.getFoodId()),bill.getNum(),foodService.getFoodPrice(bill.getFoodId()),bill.getMoney(),bill.getState());
            }
            System.out.printf("total: %.2f\n",total);
        } else {
            System.out.println("No such registered table!");
        }
    }

    public void checkOut() throws Exception {
        System.out.println("===================Check Out===================");
        System.out.print("Your tableId: ");
        int tableId = Utility.readInt();
        if (tableService.isRegisteredTable(tableId) && !(billService.isPaid(tableId))) {
            System.out.println("Sure to check out(y/n):");
            String key = Utility.readString(1);
            if (key.equals("y") && billService.checkout(tableId)) {
                System.out.println("Check out succeed!");
            } else {
                System.out.println("Check out failed!");
            }
        } else {
            System.out.println("No such bill!");
        }
    }
}
