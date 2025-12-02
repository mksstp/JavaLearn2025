package edu.project3.shared;

import java.time.OffsetDateTime;

public record LogRecord(String remoteAddr,
                        String remoteUser,
                        OffsetDateTime localTime,
                        String requestBody,
                        int status,
                        int bytes,
                        String httpReferer,
                        String httpsUserAgent) {


}
