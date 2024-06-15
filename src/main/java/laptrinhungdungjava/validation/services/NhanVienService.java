package laptrinhungdungjava.validation.services;

import laptrinhungdungjava.validation.controller.NhanVienController;
import laptrinhungdungjava.validation.models.NhanVien;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class NhanVienService {
    private List<NhanVien> listNhanVien = new ArrayList<>();

    public void add(NhanVien newNhanVien) {
        listNhanVien.add(newNhanVien);
    }

    public List<NhanVien> getAll() {
        return listNhanVien;
    }

    public NhanVien get(String maNV) { // Sử dụng mã nhân viên (maNV) làm khóa chính
        return listNhanVien.stream().filter(nv -> nv.getMaNV().equals(maNV)).findFirst().orElse(null);
    }

    public void edit(NhanVien editNhanVien) {
        for (int i = 0; i < listNhanVien.size(); i++) {
            if (listNhanVien.get(i).getMaNV().equals(editNhanVien.getMaNV())) {
                listNhanVien.set(i, editNhanVien); // Thay thế đối tượng nhân viên cũ bằng đối tượng mới
                break;
            }
        }
    }

    public void delete(String maNV) {
        listNhanVien.removeIf(nv -> nv.getMaNV().equals(maNV));
    }
}


