package com.piring.sehat.api.nutrition.controller;

import com.piring.sehat.api.nutrition.model.Nutrition;
import com.piring.sehat.api.nutrition.service.NutritionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/nutrition")
@CrossOrigin(origins = "*") // Penting: Mengizinkan akses dari frontend React
public class NutritionController {

    @Autowired
    private NutritionService nutritionService;

    // ==========================================
    // DOKUMENTASI FITUR SEARCH (Controller Layer)
    // ==========================================
    // Endpoint REST API untuk menerima permintaan pencarian dari Frontend (React).
    // Menerima parameter URL 'q' (misal: /api/nutrition/search?q=ayam)
    // 
    // ==========================================
    // PILAR OOP 4: POLIMORFISME (Polymorphism)
    // ==========================================
    // Tipe kembalian `ResponseEntity<?>` menggunakan wildcard. Ini adalah bentuk 
    // polimorfisme di mana method dapat mengembalikan tipe respons yang berbeda-beda
    // (misalnya mereturn `ResponseEntity.badRequest` saat error atau `ResponseEntity.ok` saat sukses)
    // menggunakan satu interface/tipe umum, tergantung pada kondisi yang terjadi saat runtime.
    @GetMapping("/search")
    public ResponseEntity<?> searchFood(@RequestParam("q") String query) {
        if (query == null || query.trim().isEmpty()) {
            return ResponseEntity.badRequest().body(Map.of("error", "Query pencarian tidak boleh kosong"));
        }

        // Memanggil Service layer untuk menangani logika bisnis pencarian
        List<Nutrition> results = nutritionService.searchFoodByName(query.trim());
        
        // Membungkus respons agar mirip dengan struktur sebelumnya
        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        response.put("count", results.size());
        response.put("data", results);

        return ResponseEntity.ok(response);
    }
}