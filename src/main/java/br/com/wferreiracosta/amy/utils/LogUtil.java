package br.com.wferreiracosta.amy.utils;

import lombok.NoArgsConstructor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;

import static java.lang.String.format;
import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public final class LogUtil {

    public static String getLogMessage(final MapSqlParameterSource params, final Exception e,
                                        final String query, final String message) {
        return format("Params: %s | Exception: %s | Query: %s | Message: %s", params, e, query, message);
    }

    public static String getLogMessage(final MapSqlParameterSource params,
                                       final String query, final String message) {
        return format("Params: %s | Query: %s | Message: %s", params, query, message);
    }

}
