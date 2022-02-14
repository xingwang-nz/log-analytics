package nz.co.xingsoft.log;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LogAnalytics {

    public static void main(final String[] args) {
        final String logFile = args.length > 0 ? args[0] : "programming-task-example-data.log";

        new LogAnalytics().process(logFile);
    }

    private void process(final String logFile) {

        final LogParser logParser = new LogParser();

        final Map<String, Integer> ipCountMap = new HashMap<>();
        final Map<String, Integer> urlCount = new HashMap<>();

        try (final Stream<String> stream = Files.lines(Paths.get(logFile))) {

            stream.forEach(line -> {
                final LogLine logLine = logParser.parse(line);
                updateCount(ipCountMap, logLine.getIp());
                updateCount(urlCount, logLine.getPath());
            });

            System.out.println("----------------------------------------------------------");

            final List<CountFields> ipTop3Count = getTop3Counts(ipCountMap);

            final List<CountFields> urlTop3Count = getTop3Counts(urlCount);

            System.out.println(String.format("The number of unique IP addresses: %s", ipCountMap.size()));

            System.out.println(String.format("The top 3 most visited URLs: %s", ipTop3Count));

            System.out.println(String.format("The top 3 most active IP addresses: %s", urlTop3Count));

            System.out.println("----------------------------------------------------------");

        } catch (final IOException e) {
            e.printStackTrace();
        }
    }

    private List<CountFields> getTop3Counts(final Map<String, Integer> fieldCountMap) {
        final List<CountFields> top3CountList = fieldCountMap.values().stream().distinct().sorted((c1, c2) -> c2 - c1)
                .limit(3)
                .map(CountFields::new)
                .collect(Collectors.toList());

        top3CountList.forEach(cf -> fieldCountMap.entrySet().stream()
                .forEach(e -> {
                    if (e.getValue() == cf.getCount()) {
                        cf.addField(e.getKey());
                    }
                }));

        return top3CountList;

    }

    private void updateCount(final Map<String, Integer> map, final String key) {
        if (map.containsKey(key)) {
            map.put(key, map.get(key) + 1);
        } else {
            map.put(key, 1);
        }
    }

    @AllArgsConstructor
    @Getter
    private static class CountFields {
        private final int count;

        private final List<String> fields = new ArrayList<>();

        public void addField(final String field) {
            fields.add(field);
        }

        @Override
        public String toString() {
            return "(".concat(count + " visits: ").concat(fields.toString()).concat(")");
        }
    }

}
