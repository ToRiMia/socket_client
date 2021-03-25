package torimia.socket_client;

import lombok.*;

import java.time.Instant;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class BattleDtoResult {

    private Long id;

    private Long winnerId;

    private Integer attackNumber;

    private Instant startOfBattle;

    private Instant endOfBattle;
}
