package com.leovegas.wallet.player;

import com.leovegas.wallet.entity.Player;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class PlayerServiceImpl implements PlayerService {

    private final PlayerRepository playerRepository;
    private final ModelMapper modelMapper;

    public PlayerServiceImpl(PlayerRepository playerRepository, ModelMapper modelMapper) {
        this.playerRepository = playerRepository;
        this.modelMapper = modelMapper;
    }

    @Transactional
    @Override
    public PlayerDTO addPlayer(PlayerDTO playerDTO) {
        return modelMapper.map(playerRepository.save(modelMapper.map(playerDTO, Player.class)), PlayerDTO.class);
    }

    @Override
    public PlayerDTO findById(Long id) {
        Player player = playerRepository.findById(id).orElse(null);
        if (player == null)
            return null;
        return modelMapper.map(player, PlayerDTO.class);
    }

    @Override
    public List<PlayerDTO> findAll() {
        return modelMapper.map(playerRepository.findAll(), new TypeToken<List<PlayerDTO>>() {
        }.getType());
    }

    @Transactional
    @Override
    public void removeById(Long id) {
        playerRepository.deleteById(id);
    }

    @Override
    public Optional<Player> findPlayerById(Long id) {
        return playerRepository.findById(id);
    }

}
