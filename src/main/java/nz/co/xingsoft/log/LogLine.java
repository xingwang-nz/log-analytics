package nz.co.xingsoft.log;

import lombok.Builder;
import lombok.Value;

import java.util.Optional;

@Builder
@Value
public class LogLine {
    String ip;
    String userName;
    String dataTime;
    String request;
    int status;
    int bytes;
    String userAgent;

    public String getPath() {
        return Optional.ofNullable(request)
                .map(r -> r.split(" ")[1])
                .orElse("");
    }
}
