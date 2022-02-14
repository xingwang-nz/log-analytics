package nz.co.xingsoft.log;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class LogParserTest {

    @Test
    void testParse() {
        final LogParser logParser = new LogParser();

        final String line = "50.112.00.11 - admin [11/Jul/2018:17:31:56 +0200] \"GET /asset.js HTTP/1.1\" 200 3574 \"-\" \"Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/536.6 (KHTML, like Gecko) Chrome/20.0.1092.0 Safari/536.6\"";

        final LogLine logLine = logParser.parse(line);

        assertThat(logLine.getIp()).isEqualTo("50.112.00.11");
        assertThat(logLine.getUserName()).isEqualTo("admin");
        assertThat(logLine.getDataTime()).isEqualTo("11/Jul/2018:17:31:56 +0200");
        assertThat(logLine.getPath()).isEqualTo("/asset.js");
        assertThat(logLine.getStatus()).isEqualTo(200);
        assertThat(logLine.getBytes()).isEqualTo(3574);
    }

}
