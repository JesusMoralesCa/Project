package com.jmorales.springbootreact.Service;

import com.jmorales.springbootreact.Exception.BoosterPackNotFoundException;
import com.jmorales.springbootreact.Exception.CardNotFoundException;

import com.jmorales.springbootreact.Model.BoosterPack;
import com.jmorales.springbootreact.Model.Card;

import com.jmorales.springbootreact.Payload.Response.CardResponse;
import com.jmorales.springbootreact.Repository.BoosterPackRepository;
import com.jmorales.springbootreact.Repository.CardRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.sql.rowset.serial.SerialBlob;
import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.Base64;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CardServiceImpl implements ICardService{

    private final CardRepository cardRepository;
    private final BoosterPackRepository boosterPackRepository;

    @Override
    public Card createCard(String name, MultipartFile file, String description, String packName) throws IOException, SQLException {
        Optional<BoosterPack> boosterPack = boosterPackRepository.findByName(packName);

        // El pack existe, crea la carta
        if (boosterPack.isPresent()){
            BoosterPack pack = boosterPack.get();
            Card newCard = new Card();
            newCard.setName(name);
            newCard.setDescription(description);
            newCard.setBoosterPack(pack);

            if (!file.isEmpty()){
                byte[] photoBytes = file.getBytes();
                Blob photoBlob = new SerialBlob(photoBytes);
                newCard.setImage(photoBlob);
            }


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
        List<Card> CardList = cardRepository.findAll();
        return CardList;
    }


    @Override
    public List<CardResponse> getAllCardsResponse(){
        List<Card> cardsList = cardRepository.findAll();
        List<CardResponse> cardResponses = cardsList.stream()
                .map(card -> new CardResponse(card.getId(), card.getName(), convertBlobToBase64(card.getImage()),card.getDescription(), card.getBoosterPackName()))
                .collect(Collectors.toList());
        return cardResponses;
    }


    @Override
    public CardResponse getCardResponse(String cardName){
        Card card = getCard(cardName);
        return new CardResponse(card.getId(),
                card.getName(),
                convertBlobToBase64(card.getImage()),
                card.getDescription(),
                card.getBoosterPackName());
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


    private String convertBlobToBase64(Blob blob) {
        if (blob == null) {
            return null;
        }

        try {
            byte[] bytes = blob.getBytes(1, (int) blob.length());
            return Base64.getEncoder().encodeToString(bytes);
        } catch (SQLException e) {
            // Manejar la excepción según tus necesidades
            e.printStackTrace();
            return null;
        }
    }
}
