package torimia.socket_client;

import lombok.*;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class BattleProgressDto {
    private SuperheroDtoForBattle attacker;
    private List<SuperheroDtoForBattle> defenders;
}
