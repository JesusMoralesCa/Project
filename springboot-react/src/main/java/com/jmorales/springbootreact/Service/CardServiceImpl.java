package com.jmorales.springbootreact.Service;

import com.jmorales.springbootreact.Exception.BoosterPackNotFoundException;
import com.jmorales.springbootreact.Exception.CardNotFoundException;
import com.jmorales.springbootreact.Model.BoosterPack;
import com.jmorales.springbootreact.Model.Card;
import com.jmorales.springbootreact.Repository.BoosterPackRepository;
import com.jmorales.springbootreact.Repository.CardRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Blob;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CardServiceImpl implements ICardService{

    private final CardRepository cardRepository;
    private final BoosterPackRepository boosterPackRepository;

    @Override
    public Card createCard(String name, Blob image, String packName) {
        Optional<BoosterPack> boosterPack = boosterPackRepository.findByName(packName);

        // El pack existe, crea la carta
        if (boosterPack.isPresent()){
            BoosterPack pack = boosterPack.get();
            Card newCard = new Card();
            newCard.setName(name);
            newCard.setImage(image);
            newCard.setBoosterPack(pack);

            //Guarda la carta en el repositorio de cartas
            cardRepository.save(newCard);

            // Agrega la carta al pack y actualiza el pack en la base de datos
            pack.getCardsList().add(newCard);
            boosterPackRepository.save(pack);

            return newCard;
        }else {
            throw new BoosterPackNotFoundException("Pack con el nombre " + packName + " no encontrado");
        }

    }

    @Override
    public List<Card> getCards() {
        return cardRepository.findAll();
    }

    @Transactional
    @Override
    public void deleteCard(String name) {

        Optional<Card> card = cardRepository.findByName(name);
        cardRepository.deleteById(card.get().getId());

    }

    @Override
    public Card getCard(String name) {
        return cardRepository.findByName(name).orElseThrow(() -> new CardNotFoundException("Card not found"));
    }
}
