package com.jmorales.cardmanager.Service;

import com.jmorales.cardmanager.Models.BoosterPack;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IBoosterPackService {

    List<BoosterPack> getBoosterPacks();

    List<String> getBoosterPacksName();

    BoosterPack createBoosterPack(String name, String file);

    void deleteBoosterPack(String packName);

    BoosterPack getPack(String packName);

}
