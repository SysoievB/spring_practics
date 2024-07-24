package com.keyset_pagination;

public record PageResponse<T>(
        T content,
        String previousPageCursor,
        String nextPageCursor
) { }
