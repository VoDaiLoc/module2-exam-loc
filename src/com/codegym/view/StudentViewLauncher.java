package com.codegym.view;

import com.codegym.module.Student;

import java.util.InputMismatchException;
import java.util.Scanner;

public class StudentViewLauncher {
    public static void run() {
        int number;
        do {
            Scanner scanner = new Scanner(System.in);
            StudentView studentView = new StudentView();
            mainMenu();
            try {
                System.out.println(" \n Chọn chức năng: ");
                System.out.print("⭆ ");
                number = scanner.nextInt();
                switch (number) {
                    case 1:
                        studentView.showStudents(InputOption.SHOW);
                        break;
                    case 2:
                        studentView.add();
                        break;
                    case 3:
                        studentView.update();
                        break;
                    case 4:
                        studentView.remove();
                        break;
                    case 5:
                        studentView.sortByScoreASC();
                        break;
                    case 6:
                        studentView.sortByScoreDES();
                        break;
                    case 7:
                        com.codegym.utils.AppUtils.exit();
                    default:
                        System.err.println("Chon chu nang khong dung! Vui long chon lai");
                        run();
                }
            } catch (InputMismatchException inputMismatchException) {
                System.out.println("Nhap sai! Vui long nhap lai");
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println(e.getMessage());
            }
        } while (true);
    }
    public static void mainMenu() {
        System.out.println();
        System.out.println("* * * * * * * * * *CHƯƠNG TRÌNH QUẢN LÝ SINH VIÊN* * * * * * * * * * *");
        System.out.println("*                                                                    *");
        System.out.println("*    1. Xem danh sách sinh viên                                      *");
        System.out.println("*    2. Thêm mới                                                     *");
        System.out.println("*    3. Cập nhật                                                     *");
        System.out.println("*    4. Xóa                                                          *");
        System.out.println("*    5. Sắp xếp điểm tăng dần                                        *");
        System.out.println("*    6. Sắp xếp điểm giảm dần                                        *");
        System.out.println("*    7. Thoát                                                        *");
        System.out.println("* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * ");
    }

}
