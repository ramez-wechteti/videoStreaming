package com.mord3x.videostreaming.controllers;

import com.mord3x.videostreaming.services.StreamingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1/streaming")
@RequiredArgsConstructor
@Slf4j
public class StreamingController {

    private final StreamingService streamingService;

    @GetMapping(value = "video/{title}", produces = "video/mkv")
    public Mono<Resource> getVideos(@PathVariable String title, @RequestHeader("Range") String range) {
        log.info("range in bytes(): " + range);
        return streamingService.getVideo(title);
    }
}
