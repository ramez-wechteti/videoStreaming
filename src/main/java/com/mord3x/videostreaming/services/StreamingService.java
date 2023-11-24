package com.mord3x.videostreaming.services;

import com.mord3x.videostreaming.exceptions.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class StreamingService {

    private static final String FORMAT = "classpath:videos/%s.mkv";
    private final ResourceLoader resourceLoader;

    public Mono<Resource> getVideo(String title) {
        return Mono.defer(() -> {
            Resource videoResource = resourceLoader.getResource(String.format(FORMAT, title));
            if (videoResource.exists()) {
                return Mono.just(videoResource);
            } else {
                return Mono.error(new NotFoundException("Video not found"));
            }
        });
    }
}

