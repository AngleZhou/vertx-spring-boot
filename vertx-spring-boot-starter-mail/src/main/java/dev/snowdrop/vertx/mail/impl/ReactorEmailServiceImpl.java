package dev.snowdrop.vertx.mail.impl;

import java.util.concurrent.CompletionStage;

import dev.snowdrop.vertx.mail.ReactorEmailService;
import io.smallrye.reactive.converters.ReactiveTypeConverter;
import dev.snowdrop.vertx.mail.axel.Email;
import dev.snowdrop.vertx.mail.axel.ReactiveEmailService;
import reactor.core.publisher.Mono;

public class ReactorEmailServiceImpl implements ReactorEmailService {

    private final ReactiveEmailService reactiveEmailService;

    private final ReactiveTypeConverter<Mono> monoConverter;

    public ReactorEmailServiceImpl(ReactiveEmailService reactiveEmailService,
            ReactiveTypeConverter<Mono> monoConverter) {
        this.reactiveEmailService = reactiveEmailService;
        this.monoConverter = monoConverter;
    }

    @Override
    public Mono<Void> send(Email email) {
        CompletionStage<Void> completionStage = reactiveEmailService.send(email);
        return monoConverter.fromCompletionStage(completionStage);
    }

}