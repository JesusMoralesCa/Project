package com.jmorales.springbootreact.Service;

import com.jmorales.springbootreact.Model.BoosterPack;
import com.jmorales.springbootreact.Model.Card;

import java.util.List;

public interface IBoosterPackService {

    List<BoosterPack> getBoosterPacks();

    BoosterPack createBoosterPack(String name);

    List<Card> getAllCardsFromBoosterPack(String packName);

    void deleteBoosterPack(String packName);
}
