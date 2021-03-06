package com.example.uploaddownloadfilestodb.repository;

import com.example.uploaddownloadfilestodb.model.Doc;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

public interface DocRepository extends JpaRepository<Doc, Integer> {
    @Transactional
    List<Doc> findAllByIsArchiveIsFalseOrderByIdDesc();

    @Transactional
    List<Doc> findAllByIsArchiveIsTrueOrderByIdDesc();

    @Transactional
    List<Doc> findAll();


    void deleteById (Long id);
    @Transactional
    Optional<Doc> findById(Long id);
}
