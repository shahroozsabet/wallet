package com.leovegas.wallet.player;

import com.leovegas.wallet.entity.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
interface PlayerRepository extends JpaRepository<Player, Long> {

}
