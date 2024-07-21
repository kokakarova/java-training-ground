package org.seeds;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

@Data
public class Seed {

    private List<Long> seedsNumbers = new ArrayList<>();

    public void handleSeedsNumbers(String seeds) {
        String[] seedNumbersString = seeds.trim().split(" ");
        seedsNumbers = Stream.of(seedNumbersString).map(Long::parseLong).toList();
    }
}
