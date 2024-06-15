package laptrinhungdungjava.validation.controller;

import jakarta.servlet.ServletContext;
import jakarta.validation.Valid;
import laptrinhungdungjava.validation.models.NhanVien;

import laptrinhungdungjava.validation.services.NhanVienService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@Controller
@RequestMapping("/nhanvien")
public class NhanVienController {
    @Autowired
    @Lazy
    private NhanVienService nhanVienService;

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("nhanvien", new NhanVien());
        return "nhanvien/create";
    }

    @PostMapping("/create")
    public String create(@Valid NhanVien newNhanVien, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("nhanvien", newNhanVien);
            return "nhanvien/create";
        }
        nhanVienService.add(newNhanVien);
        return "redirect:/nhanvien";
    }

    @GetMapping("")
    public String index(Model model) {
        model.addAttribute("listnhanvien", nhanVienService.getAll());
        return "nhanvien/index";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable String id, Model model) {
        NhanVien nhanvien = nhanVienService.get(id);
        if (nhanvien == null) {
            return "redirect:/nhanvien";
        }
        model.addAttribute("nhanvien", nhanvien);
        return "nhanvien/edit";
    }

    @PostMapping("/edit")
    public String edit(@Valid NhanVien editNhanVien, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("nhanvien", editNhanVien);
            return "nhanvien/edit";
        }
        nhanVienService.edit(editNhanVien);
        return "redirect:/nhanvien";
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable String id) {
        nhanVienService.delete(id);
        return "redirect:/nhanvien";
    }
}

