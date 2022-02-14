package nz.co.xingsoft.log;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LogParser {

    private final Pattern pattern = Pattern.compile("^([\\d.]+) (\\S+) (\\S+) \\[([\\w:/]+\\s[+-]\\d{4})\\] \"(.+?)\" (\\d{3}) (\\d+) \"([^\"]+)\" \"(.+?)\"");

    public LogLine parse(final String line) {
        final Matcher matcher = pattern.matcher(line);
        if (matcher.find()) {
            return LogLine.builder()
                    .ip(matcher.group(1))
                    .userName(matcher.group(3))
                    .dataTime(matcher.group(4))
                    .request(matcher.group(5))
                    .status(Integer.parseInt(matcher.group(6)))
                    .bytes(Integer.parseInt(matcher.group(7)))
                    .userAgent(matcher.group(9))
                    .build();
        }

        return null;
    }
}
