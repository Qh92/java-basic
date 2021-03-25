package com.qinh.item.ui;

import com.qinh.item.bean.Customer;
import com.qinh.item.service.CustomerList;
import com.qinh.item.util.CMUtility;

/**
 * CustomerView为主模块，负责菜单的显示和处理用户操作
 *
 * @Author Qh
 * @Date 2021/3/25 0:15
 * @Version 1.0
 */
public class CustomerView {
    private CustomerList customerList = new CustomerList(10);

    public CustomerView() {
        Customer customer = new Customer("James", '男', 36, "121211222", "james@gmail.com");
        customerList.addCustomer(customer);
    }

    /**
     * 显示 客户信息管理软件 界面
     */
    public void enterMainMenu(){
        boolean isFlag = true;
        while (isFlag){
            System.out.println("\n-----------------客户信息管理软件-----------------\n");
            System.out.println("                   1 添 加 客 户");
            System.out.println("                   2 修 改 客 户");
            System.out.println("                   3 删 除 客 户");
            System.out.println("                   4 客 户 列 表");
            System.out.println("                   5 退       出\n");
            System.out.print("                   请选择(1-5)：");

            char menu = CMUtility.readMenuSelection();

            switch (menu){
                case '1':
                    addNewCustomer();
                    break;
                case '2':
                    modifyCustomer();
                    break;
                case '3':
                    deleteCustomer();
                    break;
                case '4':
                    listAllCustomers();
                    break;
                case '5':
                    System.out.print("确认是否退出(Y/N): ");
                    char isExit = CMUtility.readConfirmSelection();
                    if (isExit == 'Y'){
                        isFlag = false;
                    }

            }

        }

    }

    /**
     * 添加客户
     */
    private void addNewCustomer(){
        System.out.println("---------------------添加客户---------------------");
        System.out.println("姓名: ");
        String name = CMUtility.readString(10);
        System.out.println("性别: ");
        char gender = CMUtility.readChar();
        System.out.println("年龄: ");
        int age = CMUtility.readInt();
        System.out.println("电话: ");
        String phoneNum = CMUtility.readString(13);
        System.out.println("邮箱: ");
        String email = CMUtility.readString(30);
        Customer customer = new Customer(name,gender,age,phoneNum,email);

        boolean isSuccess = customerList.addCustomer(customer);
        if(isSuccess){
            System.out.println("---------------------添加完成---------------------");
        }else {
            System.out.println("----------------记录已满,无法添加-----------------");
        }


    }

    /**
     * 修改客户
     */
    private void modifyCustomer(){
        System.out.println("---------------------修改客户---------------------");
        Customer customer ;
        int number;
        for (;;){
            System.out.println("请选择待修改客户编号(-1退出)：");
            number = CMUtility.readInt();
            if (number == -1){
                return;
            }
            customer = customerList.getCustomer(number - 1);
            if (customer == null){
                System.out.println("无法找到指定客户！");
            }else {
                break;
            }
        }
        //修改客户信息
        System.out.println("姓名(" + customer.getName() + "):");
        String name = CMUtility.readString(10, customer.getName());

        System.out.print("性别(" + customer.getGender() + ")：");
        char gender = CMUtility.readChar(customer.getGender());

        System.out.print("年龄(" + customer.getAge() + ")：");
        int age = CMUtility.readInt(customer.getAge());

        System.out.print("电话(" + customer.getPhone() + ")：");
        String phone = CMUtility.readString(15, customer.getPhone());

        System.out.print("邮箱(" + customer.getEmail() + ")：");
        String email = CMUtility.readString(15, customer.getEmail());

        Customer cust = new Customer(name,gender,age,phone,email);
        boolean isReplace = customerList.replaceCustomer(number - 1, customer);
        if(isReplace){
            System.out.println("---------------------修改完成---------------------");
        }else{
            System.out.println("----------无法找到指定客户,修改失败--------------");
        }

    }

    /**
     * 删除客户
     */
    private void deleteCustomer(){
        System.out.println("---------------------删除客户---------------------");
        int number;
        for (;;){
            System.out.print("请选择待删除客户编号(-1退出)：");
            number = CMUtility.readInt();
            if(number == -1){
                return;
            }
            Customer customer = customerList.getCustomer(number - 1);
            if(customer == null){
                System.out.println("无法找到指定客户！");
            }else{
                break;
            }
        }
        //找到了指定客户
        System.out.print("确认是否删除(Y/N)：");
        char isDelete = CMUtility.readConfirmSelection();
        if (isDelete == 'Y'){
            boolean deleteSuccess = customerList.deleteCustomer(number - 1);
            if(deleteSuccess){
                System.out.println("---------------------删除完成---------------------");
            }else{
                System.out.println("----------无法找到指定客户,删除失败--------------");
            }
        }
    }

    /**
     * 显示客户
     */
    private void listAllCustomers(){
        System.out.println("---------------------------客户列表---------------------------");
        int total = customerList.getTotal();
        if (total == 0){
            System.out.println("没有客户记录！");
        }else {
            System.out.println("编号\t\t姓名\t\t性别\t\t年龄\t\t电话\t\t邮箱");
            Customer[] allCustomers = customerList.getAllCustomers();
            for (int i = 0; i < allCustomers.length; i++){
                Customer c = allCustomers[i];
                System.out.println((i + 1)+"\t"+c.getName()+"\t"+c.getGender()+"\t"+c.getAge()+"\t"+
                        c.getPhone()+"\t"+c.getEmail());
            }
        }

        System.out.println("-------------------------客户列表完成-------------------------");
    }

    public static void main(String[] args){
        CustomerView view = new CustomerView();
        view.enterMainMenu();

    }

}
