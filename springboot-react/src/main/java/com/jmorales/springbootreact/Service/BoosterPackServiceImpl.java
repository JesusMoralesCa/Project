package com.jmorales.springbootreact.Service;

import com.jmorales.springbootreact.Exception.BoosterPackNotFoundException;
import com.jmorales.springbootreact.Exception.PackAlreadyExistException;
import com.jmorales.springbootreact.Model.BoosterPack;
import com.jmorales.springbootreact.Model.Card;
import com.jmorales.springbootreact.Repository.BoosterPackRepository;
import com.jmorales.springbootreact.Repository.CardRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.sql.Blob;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BoosterPackServiceImpl implements IBoosterPackService {

    private final BoosterPackRepository boosterPackRepository;
    private final CardRepository cardRepository;

    @Override
    public List<BoosterPack> getBoosterPacks() {
        return boosterPackRepository.findAll();
    }

    @Override
    public BoosterPack createBoosterPack(String name, Blob image) {
        Optional<BoosterPack> boosterPack = boosterPackRepository.findByName(name);

        // Si el pack no existe
        if (!boosterPack.isPresent()) {
            BoosterPack newPack = new BoosterPack();
            newPack.setName(name);
            newPack.setImage(image); // Asigna la imagen al nuevo BoosterPack

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

    @Transactional
    @Override
    public void deleteBoosterPack(String packName) {
        BoosterPack pack = getPack(packName);
        if (pack != null){
            boosterPackRepository.deleteByName(packName);
        }
    }

    public BoosterPack getPack(String packName) {
        return boosterPackRepository.findByName(packName).orElseThrow(() -> new UsernameNotFoundException("Pack not Found"));
    }


}
