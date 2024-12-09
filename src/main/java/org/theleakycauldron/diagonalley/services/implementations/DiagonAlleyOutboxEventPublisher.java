package org.theleakycauldron.diagonalley.services.implementations;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.stereotype.Component;
import org.theleakycauldron.diagonalley.dtos.OutboxEventDTO;
import org.theleakycauldron.diagonalley.daos.entities.Outbox;
import org.theleakycauldron.diagonalley.exceptions.OutboxNotExistsException;
import org.theleakycauldron.diagonalley.repositories.DiagonAlleyRDBOutboxRepository;

import java.util.Optional;
import java.util.UUID;

/**
 * @author: Vijaysurya Mandala
 * @github: github/mandalavijaysurya (<a href="https://www.github.com/mandalavijaysurya"> Github</a>)
 */

@Component
public class DiagonAlleyOutboxEventPublisher implements ApplicationEventPublisherAware {

    private ApplicationEventPublisher applicationEventPublisher;
    private final DiagonAlleyRDBOutboxRepository diagonAlleyRDBOutboxRepository;

    public DiagonAlleyOutboxEventPublisher(
            DiagonAlleyRDBOutboxRepository diagonAlleyRDBOutboxRepository
    ) {
        this.diagonAlleyRDBOutboxRepository = diagonAlleyRDBOutboxRepository;
    }
    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }

    public void publishOutboxEvent(Outbox outbox){

        Optional<Outbox> persistedOutbox = diagonAlleyRDBOutboxRepository.findById(outbox.getId());
        if(persistedOutbox.isEmpty()){
            throw new OutboxNotExistsException("Outbox with id: "+outbox.getId()+" does not exist");
        }
        Outbox savedOutbox = persistedOutbox.get();
        OutboxEventDTO outboxEventDTO = OutboxEventDTO.builder()
                .outbox(savedOutbox)
                .build();
        applicationEventPublisher.publishEvent(outboxEventDTO);
    }

    public void publishOutboxUpdateEvent(UUID uuid){
        Optional<Outbox> persistedOutbox = diagonAlleyRDBOutboxRepository.findByUuid(uuid);
        if(persistedOutbox.isEmpty()){
            throw new OutboxNotExistsException("Outbox with uuid: "+uuid+" does not exist");
        }
        Outbox outbox = persistedOutbox.get();
        outbox.setPersisted(false);
        OutboxEventDTO outboxEventDTO = OutboxEventDTO.builder()
                .outbox(outbox)
                .isUpdated(true)
                .build();
        applicationEventPublisher.publishEvent(outboxEventDTO);
    }

}
