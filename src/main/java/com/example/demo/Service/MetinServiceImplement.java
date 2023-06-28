package com.example.demo.Service;

import com.example.demo.Entity.Metin;
import com.example.demo.Repository.MetinRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface MetinServiceImplement {

    List<Metin> getAll();
    Metin saveMetin(Metin metin);

  Optional<Metin> getMetinById(int id);


}
