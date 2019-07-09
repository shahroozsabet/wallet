package com.leovegas.wallet.player;

import com.leovegas.wallet.entity.Player;

import java.util.List;
import java.util.Optional;

public interface PlayerService {

    PlayerDTO addPlayer(PlayerDTO playerDTO);

    PlayerDTO findById(Long id);

    List<PlayerDTO> findAll();

    void removeById(Long id);

    Optional<Player> findPlayerById(Long id);
}
