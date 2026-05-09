package iuh.fit;

import iuh.fit.dao.DoctorDao;
import iuh.fit.model.Doctor;

import java.util.List;

/**
 * Author : TrungNguyen
 * Date   : 4/2/2026
 * Description:
 *///TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    static void main() {

//        Doctor doctor = new Doctor("DR.9393", "Nguyen Nam Trung Nguyen", "0938383838", "Ham Rang Mat");
//        boolean result = DoctorDao.addDoctor(doctor);
//        System.out.println(result ? "Them bac si moi thanh cong" : "them that bai");

//        DoctorDao.getNoOfDoctorsBySpectiality("Internal Medicine").forEach((k, v)->{
//            System.out.println(k + " : " + v);
//        });
//
//        List<Doctor> res = DoctorDao.listDoctorBySpeciality("Pediatrics and Adolescent Medicine");
//        res.forEach(System.out::println);

        boolean update = DoctorDao.updateDiagnosis( "PT004","DR.010", "Khong ai dep trai qua anh Nguyen");
        System.out.println(update?"Update thanh cong":"Update that bai");
    }
}
