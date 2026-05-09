package iuh.fit;

import iuh.fit.dao.DoctorDao;
import iuh.fit.model.Doctor;
import iuh.fit.util.AppUtil;

import java.sql.SQLOutput;
import java.util.List;
import java.util.Scanner;

/**
 * Author : TrungNguyen
 * Date   : 4/1/2026
 * Description:
 *///TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Doctor doctor = DoctorDao.findDoctoyById("DR.009");
        System.out.println(doctor);

        System.out.println("Các bác sĩ theo từng chuyên khoa:");
        DoctorDao.getNoOfDoctorsBySpeciality("Internal Medicine").forEach((k,v ) -> {
            System.out.println(k + ":" + v);
        });

        Doctor doctor1 = new Doctor("DR.423", "Bui Thi Kieu Trang","0919925332","Ham Mat", "dpt007");
        boolean result = DoctorDao.addDoctor(doctor1);
        if (result){
            System.out.println("Them Doctor thanh cong");
            System.out.println(doctor1);
        }
        else
            System.out.println("Khong the them doctor moi");

        String keyword = "Ham Mat Chinh Ham";

        List<Doctor> res = DoctorDao.listDoctorsBySpeciality(keyword);
        res.forEach(System.out::println);


        boolean update = DoctorDao.updateDiagnosis("PT005", "DR.011", "Nguyen Qua Dep Trai");

        System.out.println(update?"Update Thanh cong!":"Update that bai");

    }
}