package org.seeds;

import lombok.Data;
import org.seeds.enums.Stage;
import org.seeds.enums.Part;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

@Data
public class Garden {

    static class MapEntry {
        long destination;
        long source;
        long length;

        MapEntry(long destStart, long srcStart, long length) {
            this.destination = destStart;
            this.source = srcStart;
            this.length = length;
        }
    }

    List<Long> seeds = new ArrayList<>();
    List<MapEntry> seedToSoil = new ArrayList<>();
    List<MapEntry> soilToFertilizer = new ArrayList<>();
    List<MapEntry> fertilizerToWater = new ArrayList<>();
    List<MapEntry> waterToLight = new ArrayList<>();
    List<MapEntry> lightToTemp = new ArrayList<>();
    List<MapEntry> tempToHumidity = new ArrayList<>();
    List<MapEntry> humidityToLocation = new ArrayList<>();
    Stage currentStage;

    public long readFromFile(String fileName, Part part) {
        try (InputStream file = Garden.class.getClassLoader().getResourceAsStream(fileName)) {
            assert file != null;
            BufferedReader reader = new BufferedReader(new InputStreamReader(file));
            for (String s = reader.readLine(); s != null; s = reader.readLine()) {
                if (s.isEmpty()) {
                    continue;
                }
                processLine(s, part);
            }
            return getFinalLocation(part);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }

    private void processLine(String stringLine, Part part) {
        if (stringLine.substring(0, 1).matches("^[a-zA-Z]")) {
            checkTitleLines(stringLine, part);
        } else {
            saveMapToTempList(stringLine);
        }
    }

    private void checkTitleLines(String stringLine, Part part) {
        String[] splitLine = stringLine.split(":");
        switch (splitLine[0]) {
            case "seeds":
                if (part.equals(Part.PART_1)) {
                    handleSeedsPart1(splitLine[1]);
                } else {
                    handleSeedsPart2(splitLine[1]);
                }
                break;
            case "seed-to-soil map":
                setCurrentStage(Stage.SEED_TO_SOIL);
                break;
            case "soil-to-fertilizer map":
                setCurrentStage(Stage.SOIL_TO_FERTILIZER);
                break;
            case "fertilizer-to-water map":
                setCurrentStage(Stage.FERTILIZER_TO_WATER);
                break;
            case "water-to-light map":
                setCurrentStage(Stage.WATER_TO_LIGHT);
                break;
            case "light-to-temperature map":
                setCurrentStage(Stage.LIGHT_TO_TEMPERATURE);
                break;
            case "temperature-to-humidity map":
                setCurrentStage(Stage.TEMPERATURE_TO_HUMIDITY);
                break;
            default:
                setCurrentStage(Stage.HUMIDITY_TO_LOCATION);
        }
    }

    private void handleSeedsPart1(String seedNumsString) {
        String[] seedNumbersString = seedNumsString.trim().split(" ");
        List<Long> listOfSeeds = Stream.of(seedNumbersString).map(Long::parseLong).toList();
        seeds.addAll(listOfSeeds);
    }

    private void handleSeedsPart2(String seedNumsString) {

    }

    private void saveMapToTempList(String stringLine) {
        String[] splitLine = stringLine.split(" ");
        switch (currentStage) {
            case SEED_TO_SOIL:
                seedToSoil.add(new MapEntry(
                        Long.parseLong(splitLine[0]),
                        Long.parseLong(splitLine[1]),
                        Long.parseLong(splitLine[2])));
                break;
            case SOIL_TO_FERTILIZER:
                soilToFertilizer.add(new MapEntry(
                        Long.parseLong(splitLine[0]),
                        Long.parseLong(splitLine[1]),
                        Long.parseLong(splitLine[2])));
                break;
            case FERTILIZER_TO_WATER:
                fertilizerToWater.add(new MapEntry(
                        Long.parseLong(splitLine[0]),
                        Long.parseLong(splitLine[1]),
                        Long.parseLong(splitLine[2])));
                break;
            case WATER_TO_LIGHT:
                waterToLight.add(new MapEntry(
                        Long.parseLong(splitLine[0]),
                        Long.parseLong(splitLine[1]),
                        Long.parseLong(splitLine[2])));
                break;
            case LIGHT_TO_TEMPERATURE:
                lightToTemp.add(new MapEntry(
                        Long.parseLong(splitLine[0]),
                        Long.parseLong(splitLine[1]),
                        Long.parseLong(splitLine[2])));
                break;
            case TEMPERATURE_TO_HUMIDITY:
                tempToHumidity.add(new MapEntry(
                        Long.parseLong(splitLine[0]),
                        Long.parseLong(splitLine[1]),
                        Long.parseLong(splitLine[2])));
                break;
            default:
                humidityToLocation.add(new MapEntry(
                        Long.parseLong(splitLine[0]),
                        Long.parseLong(splitLine[1]),
                        Long.parseLong(splitLine[2])));
        }
    }

    private long getFinalLocation(Part part) {
        long keeper = Long.MAX_VALUE;
        if (part.equals(Part.PART_1)) {
            for (Long seed : seeds) {
                keeper = Math.min(keeper, convertToLocation(seed));
            }
        } else {
            for (int i = 0; i < seeds.size(); i += 2) {
                // here goes the logic for part 2
                continue;
            }
        }
        return keeper;

    }

    private long convertToLocation(Long seed) {
        long soil = convertStage(seed, seedToSoil);
        long fertilizer = convertStage(soil, soilToFertilizer);
        long water = convertStage(fertilizer, fertilizerToWater);
        long light = convertStage(water, waterToLight);
        long temperature = convertStage(light, lightToTemp);
        long humidity = convertStage(temperature, tempToHumidity);
        return convertStage(humidity, humidityToLocation);
    }

    private long convertStage(Long value, List<MapEntry> map) {
        for (MapEntry entry : map) {
            if (value >= entry.source && value < entry.source + entry.length) {
                return entry.destination + (value - entry.source);
            }
        }
        // Return a default value if no match is found (this depends on your requirements)
        return value;
    }
}
