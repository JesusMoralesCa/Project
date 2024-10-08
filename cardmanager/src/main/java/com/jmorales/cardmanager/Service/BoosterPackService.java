package com.jmorales.cardmanager.Service;

import com.jmorales.cardmanager.Models.BoosterPack;
import com.jmorales.cardmanager.Repository.BoosterPackRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BoosterPackService implements IBoosterPackService{

    private final BoosterPackRepository boosterPackRepository;



    @Override
    public List<BoosterPack> getBoosterPacks() {
        List<BoosterPack> listBoosterPacks = new ArrayList<>();
        listBoosterPacks = boosterPackRepository.findAll();
        return listBoosterPacks;
    }

    @Override
    public List<String> getBoosterPacksName() {
        List<BoosterPack> listBoosterPacks = boosterPackRepository.findAll();
        List<String> nameBoosterPacks = new ArrayList<>();

        for (BoosterPack boosterPack : listBoosterPacks) {
            nameBoosterPacks.add(boosterPack.getName());
        }

        return nameBoosterPacks;
    }

    @Override
    public BoosterPack createBoosterPack(String name, String file) {
        Optional<BoosterPack> optionalBoosterPack = boosterPackRepository.findByName(name);
        if (optionalBoosterPack.isEmpty()){
            BoosterPack boosterPack = new BoosterPack();
            boosterPack.setName(name);
            boosterPack.setImage(file);

            boosterPackRepository.save(boosterPack);
            return boosterPack;
        }else{
            throw new IllegalArgumentException("Pack ya existente");
        }
    }

    @Override
    public void deleteBoosterPack(String packName) {
        Optional<BoosterPack> optionalBoosterPack = boosterPackRepository.findByName(packName);

        if (optionalBoosterPack.isPresent()) {
            BoosterPack boosterPack = optionalBoosterPack.get();
            boosterPackRepository.delete(boosterPack);
        } else {
            throw new IllegalArgumentException("Pack no existente");
        }
    }


    @Override
    public BoosterPack getPack(String packName) {
        Optional<BoosterPack> optionalBoosterPack = boosterPackRepository.findByName(packName);
        if (optionalBoosterPack.isPresent()) {
            BoosterPack boosterPack = optionalBoosterPack.get();
            return boosterPack;
        } else {
            throw new IllegalArgumentException("Pack no existente");
        }
    }
}
