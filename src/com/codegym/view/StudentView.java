package com.codegym.view;

import com.codegym.module.Student;
import com.codegym.service.StudentService;
import com.codegym.utils.AppUtils;

import java.util.List;
import java.util.Scanner;

public class StudentView {
    private final StudentService studentService;
    private final Scanner scanner = new Scanner(System.in);

    public StudentView() {
        studentService = StudentService.getInstance();
    }

    public void add() {
        do {
            long MSV = System.currentTimeMillis() / 1000;
            String name = inputName(InputOption.ADD);
            int age = inputAge(InputOption.ADD);
            String gender = inputGender(InputOption.ADD);
            String address = inputAddess(InputOption.ADD);
            double avgScore = inputAvgScore(InputOption.ADD);
            Student student = new Student(MSV, name, age, gender, address, avgScore);
            studentService.add(student);
            System.out.println("Thêm sinh viên thành công! \n");

        } while (com.codegym.utils.AppUtils.isRetry(InputOption.ADD));
    }

    public void update() {
        boolean isRetry;
        do {
            showStudents(InputOption.UPDATE);
            long msv = inputMSV(InputOption.UPDATE);
            System.out.println("┌ - - SỬA - - -  ┐");
            System.out.println("| 1.Sửa tên SV   |");
            System.out.println("| 2.Sửa giới tính |");
            System.out.println("| 3.Sửa tuổi   |");
            System.out.println("| 4.Sửa địa chỉ   |");
            System.out.println("| 5.Điểm trung bình   |");
            System.out.println("| 6.Quay lại MENU|");
            System.out.println("| 0.Thoát|");
            System.out.println("└ - - - - - - -  ┘");
            System.out.println("Chọn chức năng: ");
            int option = com.codegym.utils.AppUtils.retryChoose(0, 6);
            Student newStudent = new Student();
            newStudent.setMSV(msv);
            switch (option) {
                case 1:
                    String name = inputName(InputOption.UPDATE);
                    newStudent.setName(name);
                    studentService.update(newStudent);
                    System.out.println("Tên sinh viên đã cập nhật thành công");
                    break;
                case 2:
                    String gender = inputGender(InputOption.UPDATE);
                    newStudent.setGender(gender);
                    studentService.update(newStudent);
                    System.out.println("Giới tính đã cập nhật thành công");
                    break;
                case 3:
                    int age = inputAge(InputOption.UPDATE);
                    newStudent.setAge(age);
                    studentService.update(newStudent);
                    System.out.println("Thay đổi tuổi thành công");
                    break;
                case 4:
                    String address = inputAddess(InputOption.UPDATE);
                    newStudent.setName(address);
                    studentService.update(newStudent);
                    System.out.println("Địa chỉ sinh viên đã cập nhật thành công");
                    break;
                case 5:
                    int avgScore = inputAge(InputOption.UPDATE);
                    newStudent.setAge(avgScore);
                    studentService.update(newStudent);
                    System.out.println("Thay đổi điểm trung bình thành công");
                    break;
                case 6:
                    break;
                case 0:
                    AppUtils.exit();
            }
            isRetry = option != 6 && com.codegym.utils.AppUtils.isRetry(InputOption.UPDATE);
        } while (isRetry);
    }

    public void showStudents(InputOption inputOption) {

        System.out.println("================================================================DANH SÁCH SINH VIÊN===============================================================");
        System.out.printf("%-15s %-22s %-15s %-22s %-15s %-15s\n", "MSV", "Tên sinh viên", "Tuổi", "Giới tính", "Địa chỉ", "Điểm trung bình");
        System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------------");
        List<Student> students = studentService.findAll();
        for (Student student : students) {
            System.out.printf("%-15s %-22s %-15s %-22s %-15s %-15s\n",
                    student.getMSV(),
                    student.getName(),
                    student.getAge(),
                    student.getGender(),
                    student.getAddress(),
                    student.getAvgScore()
            );
        }
        System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------------");
        if (inputOption == InputOption.SHOW) com.codegym.utils.AppUtils.isRetry(InputOption.SHOW);
    }

    public void remove() {
        showStudents(InputOption.DELETE);
        long MSV;
        while (!studentService.existsById(MSV = inputMSV(InputOption.DELETE))) {
            System.out.println("Không tìm thấy sản phẩm cần xóa");
            System.out.println("Nhấn 'y' để thêm tiếp \t|\t 'q' để quay lại \t|\t 't' để thoát chương trình");
            System.out.print(" ⭆ ");
            String option = scanner.nextLine();
            switch (option) {
                case "y":
                    break;
                case "q":
                    return;
                case "t":
                    com.codegym.utils.AppUtils.exit();
                    break;
                default:
                    System.out.println("Chọn chức năng không đúng! Vui lòng chọn lại");
                    break;
            }
        }
        System.out.println("❄ ❄ ❄ ❄ REMOVE COMFIRM ❄ ❄ ❄");
        System.out.println("❄  1. Nhấn 1 để xoá        ❄");
        System.out.println("❄  2. Nhấn 2 để quay lại   ❄");
        System.out.println("❄ ❄ ❄ ❄ ❄ ❄ ❄  ❄ ❄ ❄ ❄ ❄ ❄ ❄");
        int option = com.codegym.utils.AppUtils.retryChoose(1, 2);
        if (option == 1) {
            studentService.deleteById(MSV);
            System.out.println("Đã xoá sản phẩm thành công! \uD83C\uDF8A");
            com.codegym.utils.AppUtils.isRetry(InputOption.DELETE);
        }

    }

    public void showSortStudent(List<Student> students) {
        System.out.println("---------------------------------- STUDENT SORT LIST------------------------------------- \n");
        System.out.printf("%-15s %-20s %-12s %-12s %-12s %-15s \n", "ID", "Full Name", "Age", "Gender", "Address", "Average Score");
        System.out.println("-----------------------------------------------------------------------------------------");
        for (Student student : students) {
            System.out.printf("%-15s %-20s %-12s %-12s %-12s %-15s \n", student.getMSV(), student.getName(), student.getAge(), student.getGender(), student.getAddress(), student.getAvgScore());
        }
        System.out.println("-----------------------------------------------------------------------------------------");
        System.out.println();
    }

    public void sortByScoreASC() {
        showSortStudent(studentService.sortByScoreASC());
    }

    public void sortByScoreDES() {
        showSortStudent(studentService.sortByScoreDES());
    }



    private String inputName(InputOption option) {
        switch (option) {
            case ADD:
                System.out.println("Nhập tên sinh viên: ");
                break;
            case UPDATE:
                System.out.println("Nhập tên muốn thay đổi: ");
                break;
        }
        System.out.print("⭆ ");
        return scanner.nextLine();
    }


    private long inputMSV(InputOption option) {
        long MSV;
        switch (option) {
            case ADD:
                System.out.println("Nhập MSV: ");
                break;
            case UPDATE:
                System.out.println("Nhập MSV muốn thay đổi: ");
                break;
            case DELETE:
                System.out.println("Nhập MSV muốn xóa: ");
                break;
        }
        boolean isRetry = false;
        do {
            MSV = com.codegym.utils.AppUtils.retryParseInt();
            boolean exist = studentService.existsById(MSV);
            switch (option) {
                case ADD:
                    if (exist) {
                        System.out.println("Id đã tồn tại! Vui lòng nhập lại");
                    }
                    isRetry = exist;
                    break;
                case UPDATE:
                    if (!exist) {
                        System.out.println("Không tìm thấy id! Vui lòng nhập lại");
                    }
                    isRetry = !exist;
                    break;
            }
        } while (isRetry);
        return MSV;
    }

    private String inputGender(InputOption option) {
        switch (option) {
            case ADD:
                System.out.println("Nhập giới tính: ");
                break;
            case UPDATE:
                System.out.println("Nhập giới tính cần thay đổi");
                break;
        }
        String gender;
        gender = com.codegym.utils.AppUtils.retryString();
        return gender;
    }

    private int inputAge(InputOption option) {
        switch (option) {
            case ADD:
                System.out.println("Nhập tuổi: ");
                break;
            case UPDATE:
                System.out.println("Nhập tuổi muốn thay đổi: ");
                break;
        }
        int age;
        do {
            age = com.codegym.utils.AppUtils.retryParseInt();
            if (age <= 0)
                System.out.println("tuổi phải lớn hơn 0");
        } while (age <= 0);
        return age;
    }

    private double inputAvgScore(InputOption option) {
        switch (option) {
            case ADD:
                System.out.println("Nhập điểm trung bình: ");
                break;
            case UPDATE:
                System.out.println("Nhập điểm trung bình muốn thay đổi: ");
                break;
        }
        double avg;
        do {
            avg = com.codegym.utils.AppUtils.retryParseDouble();
            if (avg < 0||avg>10)
                System.out.println("điểm trung bình phải >=0 và <=10");
        } while (avg < 0||avg>10);
        return avg;
    }

    private String inputAddess(InputOption option) {
        switch (option) {
            case ADD:
                System.out.println("Nhập địa chỉ sinh viên: ");
                break;
            case UPDATE:
                System.out.println("Nhập địa chỉ muốn thay đổi: ");
                break;
        }
        System.out.print("⭆ ");
        return scanner.nextLine();
    }

    public void showStudentsSort(InputOption inputOption, List<Student> products) {
        System.out.println("-----------------------------------------DANH SÁCH SẢN PHẨM-------------------------------------------");
        System.out.printf("%-15s %-30s %-25s %-10s %-20s\n", "MSV", "Tên sinh viên", "Giới tính", "Địa chỉ", "Điểm trung bình");
        for (Student student : products) {
            System.out.printf("%-15s %-30s %-25s %-10s %-20s\n",
                    student.getMSV(),
                    student.getName(),
                    student.getGender(),
                    student.getAddress(),
                    student.getAvgScore()
            );
        }
        System.out.println("--------------------------------------------------------------------------------------------------\n");
        if (inputOption == InputOption.SHOW)
            com.codegym.utils.AppUtils.isRetry(InputOption.SHOW);
    }

}
