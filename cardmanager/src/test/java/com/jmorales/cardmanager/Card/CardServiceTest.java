package com.jmorales.cardmanager.Card;

import com.jmorales.cardmanager.Models.Card;
import com.jmorales.cardmanager.Repository.CardRepository;
import com.jmorales.cardmanager.Service.CardService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class CardServiceTest {

    @Mock
    private CardRepository cardRepository;

    @InjectMocks
    private CardService cardService;

    @Test
    void createCardWhenNotExistsReturnsCard(){
        Card card = new Card();
        card.setName("card");
        card.setImage("image.png");
        card.setRarity("rare");
        card.setColor("blue");
        card.setDp(10);
        card.setBoosterPack("booster1");

        Mockito.when(cardRepository.findByName(card.getName()))
                .thenReturn(Optional.empty());
        Mockito.when(cardRepository.save(Mockito.any(Card.class)))
                .thenReturn(card);


        Card result = cardService.createCard("card", "image.png", "rare", "blue", 10, "booster1");
        Assertions.assertEquals(card.getName(), result.getName());
        Assertions.assertEquals("image.png", result.getImage());
    }


    @Test
    void createCardWhenAlreadyExistsThrowsException() {
        Card existingCard = new Card();
        existingCard.setName("Carta1");

        Mockito.when(cardRepository.findByName("Carta1")).thenReturn(Optional.of(existingCard));

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            cardService.createCard("Carta1", "image.png", "rare", "blue", 10, "booster1");
        });
    }

    @Test
    void getExistingCardReturnsCard() {
        String name = "Carta1";
        Card card = new Card();
        card.setName(name);

        Mockito.when(cardRepository.findByName(name)).thenReturn(Optional.of(card));

        Card result = cardService.getCard(name);

        Assertions.assertEquals(name, result.getName());
    }

    @Test
    void getNonExistingCardThrowsException() {
        String name = "CartaInexistente";

        Mockito.when(cardRepository.findByName(name)).thenReturn(Optional.empty());

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            cardService.getCard(name);
        });
    }

    @Test
    void getAllCardsReturnsListOfCards() {
        Card card1 = new Card();
        card1.setName("Carta1");
        Card card2 = new Card();
        card2.setName("Carta2");

        Mockito.when(cardRepository.findAll()).thenReturn(Arrays.asList(card1, card2));

        var result = cardService.getAllCards();

        Assertions.assertEquals(2, result.size());
        Assertions.assertEquals("Carta1", result.get(0).getName());
        Assertions.assertEquals("Carta2", result.get(1).getName());
    }

    @Test
    void deleteExistingCardDeletesCard() {
        String name = "Carta1";
        Card card = new Card();
        card.setName(name);

        Mockito.when(cardRepository.findByName(name)).thenReturn(Optional.of(card));

        Assertions.assertDoesNotThrow(() -> cardService.deleteCard(name));
        Mockito.verify(cardRepository).delete(card);
    }

    @Test
    void deleteNonExistingCardThrowsException() {
        String name = "CartaInexistente";

        Mockito.when(cardRepository.findByName(name)).thenReturn(Optional.empty());

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            cardService.deleteCard(name);
        });
    }

}
