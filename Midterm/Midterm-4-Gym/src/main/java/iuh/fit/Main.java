package iuh.fit;

import iuh.fit.dao.TrainerDao;
import iuh.fit.model.Trainer;
import iuh.fit.util.AppUtil;

import java.util.List;

/**
 * Author : TrungNguyen
 * Date   : 4/2/2026
 * Description:
 *///TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    static void main() {
//        TrainerDao.getNoOfTrainersBySpecialty("Cooley Ltd").forEach((k , v)-> {
//            System.out.println(k + ":" + v);
//        });

//        Trainer trainer = new Trainer("TRN999999", "Nguyen Nam Trung Nguyen", "03489438934", "Cu Ta");
//
//        boolean result = TrainerDao.addTrainerToCenter(trainer, "Cooley Ltd");
//        System.out.println(result?"Them thanh cong": "Them that bai (TRUNG ID)");

//        List<Trainer> res = TrainerDao.listTrainersBySpecialty("Pilates");
//        res.forEach(System.out::println);

        boolean update = TrainerDao.updateSessionDurationst("MEM005", "TRN013", 10F);
        System.out.println(update?"update thanh cong":"update that bai");
    }
}
