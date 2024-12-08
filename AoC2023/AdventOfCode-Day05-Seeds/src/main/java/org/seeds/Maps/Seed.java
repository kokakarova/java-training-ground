package org.seeds.Maps;

import lombok.Data;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Stream;

@Data
public class Seed {

    private HashMap<Long, Long> seedsNumbers = new HashMap<>();

    public void handleSeedsNumbers(String seeds) {
        String[] seedNumbersString = seeds.trim().split(" ");
        List<Long> listOfSeeds = Stream.of(seedNumbersString).map(Long::parseLong).toList();
        for (Long seed : listOfSeeds) {
            seedsNumbers.put(seed, -1L);
        }
    }
}
