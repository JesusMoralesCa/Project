package com.jmorales.springbootreact.Service;

import com.jmorales.springbootreact.Exception.PackAlreadyExistException;
import com.jmorales.springbootreact.Model.BoosterPack;
import com.jmorales.springbootreact.Model.Card;
import com.jmorales.springbootreact.Payload.Response.BoosterPackResponse;
import com.jmorales.springbootreact.Payload.Response.CardResponse;
import com.jmorales.springbootreact.Repository.BoosterPackRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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
public class BoosterPackServiceImpl implements IBoosterPackService {

    private final BoosterPackRepository boosterPackRepository;


    @Override
    public List<BoosterPack> getBoosterPacks() {
        return boosterPackRepository.findAll();
    }

    @Override
    public BoosterPack createBoosterPack(String name, MultipartFile file) throws SQLException, IOException {
        Optional<BoosterPack> boosterPack = boosterPackRepository.findByName(name);

        // Si el pack no existe
        if (!boosterPack.isPresent()) {
            BoosterPack newPack = new BoosterPack();
            newPack.setName(name);

            if (!file.isEmpty()){
                byte[] photoBytes = file.getBytes();
                Blob photoBlob = new SerialBlob(photoBytes);
                newPack.setImage(photoBlob);
            }

            boosterPackRepository.save(newPack);
            return newPack;
        } else {
            throw new PackAlreadyExistException("El pack con nombre " + name + " ya existe");
        }
    }

    @Override
    public List<Card> getAllCardsFromBoosterPack(String packName) {

        BoosterPack pack = getPack(packName);
        List<Card> cardsList = pack.getCardsList();
        return cardsList;
    }

    @Override
    public List<CardResponse> getAllCardResponseFromBoosterPack(String packName) {
        BoosterPack pack = getPack(packName);
        List<Card> cardsList = pack.getCardsList();

        List<CardResponse> cardResponses = cardsList.stream()
                .map(card -> new CardResponse(card.getId(), card.getName(), convertBlobToBase64(card.getImage()),card.getDescription(), card.getBoosterPackName()))
                .collect(Collectors.toList());

        return cardResponses;
    }





    @Transactional
    @Override
    public void deleteBoosterPack(String packName) {
        BoosterPack pack = getPack(packName);
        if (pack != null){
            boosterPackRepository.deleteByName(packName);
        }
    }

    @Override
    public BoosterPack getPack(String packName) {
        return boosterPackRepository.findByName(packName).orElseThrow(() -> new UsernameNotFoundException("Pack not Found"));
    }


    @Override
    public BoosterPackResponse getBoosterPackResponse(String packName) throws SQLException {
        List<CardResponse> cardResponseList = getAllCardResponseFromBoosterPack(packName);
        BoosterPack pack = getPack(packName);

        return new BoosterPackResponse(pack.getId(),
                pack.getName(),
                convertBlobToBase64(pack.getImage()),
                cardResponseList
                );
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
