package com.example.waplan.goal.api;

import com.example.waplan.goal.application.MandalartService;
import com.example.waplan.goal.application.dto.*;
import com.example.waplan.goal.domain.Mandalart;
import com.example.waplan.goal.domain.Status;
import com.example.waplan.security.CurrentUser;
import com.example.waplan.user.domain.User;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/mandalart")
public class MandalartApi {

    private final MandalartService mandalartService;

    @PostMapping("/add")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<Long> addFirstGoal(@CurrentUser User user, @Valid @RequestBody MandalartAddRequest mandalartAddRequest) {
        Long mandalartId = mandalartService.addMandalart(user, mandalartAddRequest);
        return ResponseEntity.ok(mandalartId);
    }

    @GetMapping("/{mandalartId}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<MandalartResponse> getMandalart(@CurrentUser User user, @PathVariable("mandalartId") Long mandalartId) {
        Mandalart mandalart = mandalartService.getMandalart(user, mandalartId);
        return ResponseEntity.ok(MandalartResponse.of(mandalart));
    }

    @DeleteMapping("/{mandalartId}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<Void> deleteMandalart(@CurrentUser User user, @PathVariable("mandalartId") Long mandalartId){
        mandalartService.deleteMandalart(user, mandalartId);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/progress")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<Status> updateProgress(@CurrentUser User user, @Valid @RequestBody MandalartProgressRequest mandalartProgressRequest){
        Status mandalartStatus = mandalartService.updateProgress(user, mandalartProgressRequest);
        return ResponseEntity.ok(mandalartStatus);
    }

    @PatchMapping("/bookmark")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<BookmarkUpdateResponse> updateBookmark(@CurrentUser User user, @Valid @RequestBody BookmarkUpdateRequest bookmarkUpdateRequest){
        BookmarkUpdateResponse response = mandalartService.updateBookmark(user, bookmarkUpdateRequest);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/all/{mandalartId}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<MandalartAllResponse> findMandalartAll(@CurrentUser User user, @Valid @PathVariable("mandalartId") Long mandalartId){
        return ResponseEntity.ok(mandalartService.findMandalart(user, mandalartId));
    }
}
