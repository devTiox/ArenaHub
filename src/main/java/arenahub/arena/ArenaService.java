package arenahub.arena;

import arenahub.owner.Owner;
import arenahub.reservation.ReservationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
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
