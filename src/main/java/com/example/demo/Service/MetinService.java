package com.example.demo.Service;

import com.example.demo.Entity.Metin;
import com.example.demo.Repository.MetinRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class MetinService implements MetinServiceImplement{
    @Autowired
    private MetinRepository metinRepository;
    @Override
    public List<Metin> getAll() {
        return metinRepository.findAll();
    }

    @Override
    public Metin saveMetin(Metin metin) {
        return metinRepository.save(metin);
    }

    @Override
    public Optional<Metin> getMetinById(int id) {
        Optional<Metin> metin=metinRepository.findById(id);
        return metin;
    }
}
