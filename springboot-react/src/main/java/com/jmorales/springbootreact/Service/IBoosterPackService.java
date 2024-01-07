package com.jmorales.springbootreact.Service;

import com.jmorales.springbootreact.Model.BoosterPack;
import com.jmorales.springbootreact.Model.Card;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.List;

public interface IBoosterPackService {

    List<BoosterPack> getBoosterPacks();

    BoosterPack createBoosterPack(String name, MultipartFile file) throws SQLException, IOException;

    List<Card> getAllCardsFromBoosterPack(String packName);

    void deleteBoosterPack(String packName);
}
