package com.jmorales.springbootreact.Service;

import com.jmorales.springbootreact.Model.Card;

import java.sql.Blob;
import java.util.List;

public interface ICardService {

    Card createCard(String name, Blob image, String packName);

    List<Card> getCards();

    void deleteCard(String name);

    Card getCard(String name);

}
