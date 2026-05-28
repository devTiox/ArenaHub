package arenahub.service;

import arenahub.model.Arena;
import arenahub.model.Owner;
import arenahub.repository.ArenaRepository;
import arenahub.repository.ReservationRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ArenaService {

    private final ArenaRepository arenaRepository;
    private final ReservationRepository reservationRepository;


    public void deleteByOwner(Owner owner) {
        List<Arena> arenas = arenaRepository.findByOwner_Id(owner.getId());
        for(Arena arena : arenas)
            reservationRepository.deleteByArena_Id(arena.getId());
        arenaRepository.deleteByOwner_Id(owner.getId());
    }
}
