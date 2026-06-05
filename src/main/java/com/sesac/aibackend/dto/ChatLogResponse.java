package com.sesac.aibackend.dto;

import com.sesac.aibackend.domain.ChatLog;

import java.time.LocalDateTime;

public record ChatLogResponse(
        Long id,
        Long userId,
        String username,
        String prompt,
        String response,
        LocalDateTime createdAt
) {

    /**
     * getId 버전 — username은 채우지 않습니다(null).
     * LAZY 프록시의 식별자만 읽으므로 fetch join이 필요 없습니다.
     */
    public static ChatLogResponse from(ChatLog chatLog) {
        return new ChatLogResponse(
                chatLog.getId(),
                chatLog.getUser().getId(),
                null,
                chatLog.getPrompt(),
                chatLog.getResponse(),
                chatLog.getCreatedAt()
        );
    }

    /**
     * getName(getUsername) 버전 — fetch join으로 user가 미리 로딩되어 있어야 합니다.
     */
    public static ChatLogResponse fromWithUsername(ChatLog chatLog) {
        return new ChatLogResponse(
                chatLog.getId(),
                chatLog.getUser().getId(),
                chatLog.getUser().getUsername(),
                chatLog.getPrompt(),
                chatLog.getResponse(),
                chatLog.getCreatedAt()
        );
    }
}
