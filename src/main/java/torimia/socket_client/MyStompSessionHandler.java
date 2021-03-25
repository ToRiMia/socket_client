package torimia.socket_client;

import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaders;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.messaging.simp.stomp.StompSessionHandler;

import java.lang.reflect.Type;
import java.util.Optional;

@Slf4j
public class MyStompSessionHandler implements StompSessionHandler {

    @Override
    public void afterConnected(
            StompSession session, StompHeaders connectedHeaders) {
        session.subscribe("/topic/battle_progress", this);
        log.info("Subscribed to: topic/battle_progress");
        session.subscribe("/topic/battle_result", this);
        log.info("Subscribed to: topic/battle_result");
    }

    @Override
    public void handleException(StompSession stompSession, StompCommand stompCommand, StompHeaders stompHeaders, byte[] bytes, Throwable throwable) {
        log.error("Throwable: {}", throwable.toString());
    }

    @Override
    public void handleTransportError(StompSession stompSession, Throwable throwable) {
        log.error("TransportError: {}", throwable.toString());
    }

    @Override
    public Type getPayloadType(StompHeaders stompHeaders) {
        String topic = Optional.of(stompHeaders.getDestination()).orElseThrow(() -> new NullPointerException("Topic is null"));
        switch (topic) {
            case "/topic/battle_progress":
                return BattleProgressDto.class;
            case "/topic/battle_result":
                return BattleDtoResult.class;
            default:
                throw new NullPointerException("Unknown topic");
        }
    }

    @Override
    public void handleFrame(StompHeaders headers, Object payload) {
        String topic = Optional.of(headers.getDestination()).orElseThrow(() -> new NullPointerException("Topic is null"));
        switch (topic) {
            case "/topic/battle_progress": {
                BattleProgressDto progress = (BattleProgressDto) payload;
                log.info("-------------Received-------------");
                log.info("Attacker: " + progress.getAttacker());
                log.info("Defenders: " + progress.getDefenders());
                break;
            }
            case "/topic/battle_result": {
                BattleDtoResult progress = (BattleDtoResult) payload;
                log.info("-------------Received-------------");
                log.info("Result: " + progress);
                break;
            }
            default:
                log.info("Unknown object: " + payload);
        }
    }
}
