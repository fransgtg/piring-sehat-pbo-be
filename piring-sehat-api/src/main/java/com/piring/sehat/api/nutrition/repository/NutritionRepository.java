package com.piring.sehat.api.nutrition.repository;

import com.piring.sehat.api.nutrition.model.Nutrition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

// ==========================================
// PILAR OOP 2: ABSTRAKSI (Abstraction)
// ==========================================
// Interface ini menyembunyikan kompleksitas dari operasi database. 
// Kita hanya mendefinisikan "apa" yang bisa dilakukan (misal: mencari berdasarkan nama),
// tanpa harus memikirkan "bagaimana" query SQL-nya diimplementasikan.
@Repository
// ==========================================
// PILAR OOP 3: PEWARISAN (Inheritance)
// ==========================================
// Kata kunci `extends` menunjukkan bahwa NutritionRepository mewarisi 
// semua method CRUD (seperti save, findAll, delete) dari JpaRepository.
// Kita tidak perlu menulis ulang kode untuk fungsi-fungsi dasar tersebut.
public interface NutritionRepository extends JpaRepository<Nutrition, Long> {
    
    // ==========================================
    // DOKUMENTASI FITUR SEARCH (Repository Layer)
    // ==========================================
    // Method ini merupakan implementasi database untuk fitur search.
    // Secara otomatis Spring menterjemahkannya menjadi query: 
    // SELECT * FROM nutrition WHERE LOWER(name) LIKE LOWER('%name%')
    List<Nutrition> findByNameContainingIgnoreCase(String name);
}