package com.jmorales.springbootreact.Service;

import com.jmorales.springbootreact.Exception.BoosterPackNotFoundException;
import com.jmorales.springbootreact.Exception.PackAlreadyExistException;
import com.jmorales.springbootreact.Model.BoosterPack;
import com.jmorales.springbootreact.Model.Card;
import com.jmorales.springbootreact.Repository.BoosterPackRepository;
import com.jmorales.springbootreact.Repository.CardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
    public BoosterPack createBoosterPack(String name) {
        Optional<BoosterPack> boosterPack = boosterPackRepository.findByName(name);

        //Si el pack no existe
        if (!boosterPack.isPresent()){
            BoosterPack newPack = new BoosterPack();
            newPack.setName(name);

            boosterPackRepository.save(newPack);
            return newPack;
        }else {
            throw new PackAlreadyExistException("El pack con nombre " + name + " ya existe");
        }

    }

    @Override
    public List<Card> getAllCardsFromBoosterPack(String packName) {
        Optional<BoosterPack> boosterPack = boosterPackRepository.findByName(packName);

        BoosterPack pack = boosterPack.get();
        List<Card> cardsList = pack.getCardsList();
        return cardsList;
    }

    @Override
    public void deleteBoosterPack(String packName) {
        boosterPackRepository.findByName(packName).orElseThrow(() -> new BoosterPackNotFoundException("Pack not found"));

    }
}
