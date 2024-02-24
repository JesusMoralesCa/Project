package com.jmorales.springbootreact.Service;

import com.jmorales.springbootreact.Exception.PackAlreadyExistException;
import com.jmorales.springbootreact.Exception.PackNotFoundException;
import com.jmorales.springbootreact.Model.BoosterPack;
import com.jmorales.springbootreact.Model.Card;
import com.jmorales.springbootreact.Payload.Response.BoosterPackResponse;
import com.jmorales.springbootreact.Payload.Response.CardResponse;
import com.jmorales.springbootreact.Repository.BoosterPackRepository;
import com.jmorales.springbootreact.Serialice.BlobUtil;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.sql.rowset.serial.SerialBlob;
import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;
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
/*
    @Override
    public List<Card> getAllCardsFromBoosterPack(String packName) {

        BoosterPack pack = getPack(packName);
        List<Card> cardsList = pack.getCardsList();
        return cardsList;
    }
*/
    @Override
    public List<CardResponse> getAllCardResponseFromBoosterPack(String packName) {
        BoosterPack pack = getPack(packName);
        List<Card> cardsList = pack.getCardsList();

        List<CardResponse> cardResponses = cardsList.stream()
                .map(card -> new CardResponse(card.getId(), card.getName(), BlobUtil.convertBlobToBase64(card.getImage()),card.getDescription(), card.getBoosterPackName()))
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
        return boosterPackRepository.findByName(packName).orElseThrow(() -> new PackNotFoundException("Pack not Found"));
    }


    @Override
    public BoosterPackResponse getBoosterPackResponse(String packName) throws SQLException {
        List<CardResponse> cardResponseList = getAllCardResponseFromBoosterPack(packName);
        BoosterPack pack = getPack(packName);

        return new BoosterPackResponse(pack.getId(),
                pack.getName(),
                BlobUtil.convertBlobToBase64(pack.getImage()),
                cardResponseList
                );
    }

    @Override
    public BoosterPackResponse getBoosterPackResponseLow(String packName) throws SQLException {
        BoosterPack pack = getPack(packName);

        BoosterPackResponse response =new BoosterPackResponse(
                pack.getId(),
                pack.getName());
        return response;
    }



}
