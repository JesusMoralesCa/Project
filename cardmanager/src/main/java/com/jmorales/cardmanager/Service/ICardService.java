package com.jmorales.cardmanager.Service;

import com.jmorales.cardmanager.Models.Card;
import java.util.List;

public interface ICardService {

    Card createCard(String name, String image, String rarity, String color, int dp, String boosterPack);
    Card getCard(String name);
    List<Card> getAllCards();
    void deleteCard(String name);
}
