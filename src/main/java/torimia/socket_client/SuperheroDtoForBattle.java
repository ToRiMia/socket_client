package torimia.socket_client;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class SuperheroDtoForBattle {

    private Long id;

    private String nickname;

    private Integer damage;

    private Integer health;

    private Integer attackCount;
}
