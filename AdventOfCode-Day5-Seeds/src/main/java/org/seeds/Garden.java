package org.seeds;

import lombok.Data;
import org.seeds.Maps.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
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

    // DELETE MYB
    Seed seedsCls = new Seed();
    List<String[]> tempList = new ArrayList<>();
    boolean addToMultiDimArray = false;
    SeedToSoil seedToSoilCls = new SeedToSoil();
    SoilToFertilizer soilToFertilizerCls = new SoilToFertilizer();
    FertilizerToWater fertilizerToWaterCls = new FertilizerToWater();
    WaterToLight waterToLightCls = new WaterToLight();
    LightToTemperature lightToTempCls = new LightToTemperature();
    TemperatureToHumidity tempToHumidityCls = new TemperatureToHumidity();
    HumidityToLocation humidityToLocationCls = new HumidityToLocation();
    // DELETE MYB - to here

    public long readFromFile(String fileName) {
        try (InputStream file = Garden.class.getClassLoader().getResourceAsStream(fileName)) {
            assert file != null;
            BufferedReader reader = new BufferedReader(new InputStreamReader(file));
            for (String s = reader.readLine(); s != null; s = reader.readLine()) {
                if (s.isEmpty()) {
                    continue;
                }
                processLine(s);
            }
            return getFinalLocation();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }

    private long getFinalLocation() {
        long[] locations = new long[seeds.size()];

        // Convert seeds to soil
        for (int i = 0; i < seeds.size(); i++) {
            locations[i] = convertStage(seeds.get(i), seedToSoil);
        }

        // Convert soil to fertilizer
        for (int i = 0; i < locations.length; i++) {
            locations[i] = convertStage(locations[i], soilToFertilizer);
        }

        // Convert fertilizer to water
        for (int i = 0; i < locations.length; i++) {
            locations[i] = convertStage(locations[i], fertilizerToWater);
        }

        // Convert water to light
        for (int i = 0; i < locations.length; i++) {
            locations[i] = convertStage(locations[i], waterToLight);
        }

        // Convert light to temperature
        for (int i = 0; i < locations.length; i++) {
            locations[i] = convertStage(locations[i], lightToTemp);
        }

        // Convert temperature to humidity
        for (int i = 0; i < locations.length; i++) {
            locations[i] = convertStage(locations[i], tempToHumidity);
        }

        // Convert humidity to location
        for (int i = 0; i < locations.length; i++) {
            locations[i] = convertStage(locations[i], humidityToLocation);
        }

        // Find the lowest location number
        return Arrays.stream(locations).min().orElseThrow();
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

    private void processLine(String stringLine) {
        if (stringLine.substring(0, 1).matches("^[a-zA-Z]")) {
            checkTitleLines(stringLine);
            return;
        } else {
            saveMapToTempList(stringLine);
        }
    }

    private void checkTitleLines(String stringLine) {
        String[] splitLine = stringLine.split(":");
        switch (splitLine[0]) {
            case "seeds":
                seedsCls.handleSeedsNumbers(splitLine[1]);
                handleSeeds(splitLine[1]);
                break;
            case "seed-to-soil map":
                seedToSoilCls.setActive(true);
                break;
            case "soil-to-fertilizer map":
                seedToSoilCls.setActive(false);
                soilToFertilizerCls.setActive(true);
                addToMultiDimArray = true;
                break;
            case "fertilizer-to-water map":
                soilToFertilizerCls.setActive(false);
                fertilizerToWaterCls.setActive(true);
                addToMultiDimArray = true;
                break;
            case "water-to-light map":
                fertilizerToWaterCls.setActive(false);
                waterToLightCls.setActive(true);
                addToMultiDimArray = true;
                break;
            case "light-to-temperature map":
                waterToLightCls.setActive(false);
                lightToTempCls.setActive(true);
                addToMultiDimArray = true;
                break;
            case "temperature-to-humidity map":
                lightToTempCls.setActive(false);
                tempToHumidityCls.setActive(true);
                addToMultiDimArray = true;
                break;
            default:
                tempToHumidityCls.setActive(false);
                humidityToLocationCls.setActive(true);
                addToMultiDimArray = true;
        }
    }

    private void handleSeeds(String s) {
        String[] seedNumbersString = s.trim().split(" ");
        List<Long> listOfSeeds = Stream.of(seedNumbersString).map(Long::parseLong).toList();
        seeds.addAll(listOfSeeds);
    }

    private void saveMapToTempList(String stringLine) {
        String[] splitLine = stringLine.split(" ");
        if (seedToSoilCls.isActive()) {
            seedToSoil.add(new MapEntry(
                    Long.parseLong(splitLine[0]),
                    Long.parseLong(splitLine[1]),
                    Long.parseLong(splitLine[2])));
            return;
        }
        if (soilToFertilizerCls.isActive()) {
            soilToFertilizer.add(new MapEntry(
                    Long.parseLong(splitLine[0]),
                    Long.parseLong(splitLine[1]),
                    Long.parseLong(splitLine[2])));
            return;
        }
        if (fertilizerToWaterCls.isActive()) {
            fertilizerToWater.add(new MapEntry(
                    Long.parseLong(splitLine[0]),
                    Long.parseLong(splitLine[1]),
                    Long.parseLong(splitLine[2])));
            return;
        }
        if (waterToLightCls.isActive()) {
            waterToLight.add(new MapEntry(
                    Long.parseLong(splitLine[0]),
                    Long.parseLong(splitLine[1]),
                    Long.parseLong(splitLine[2])));
            return;
        }
        if (lightToTempCls.isActive()) {
            lightToTemp.add(new MapEntry(
                    Long.parseLong(splitLine[0]),
                    Long.parseLong(splitLine[1]),
                    Long.parseLong(splitLine[2])));
            return;
        }
        if (tempToHumidityCls.isActive()) {
            tempToHumidity.add(new MapEntry(
                    Long.parseLong(splitLine[0]),
                    Long.parseLong(splitLine[1]),
                    Long.parseLong(splitLine[2])));
            return;
        }
        if (humidityToLocationCls.isActive()) {
            humidityToLocation.add(new MapEntry(
                    Long.parseLong(splitLine[0]),
                    Long.parseLong(splitLine[1]),
                    Long.parseLong(splitLine[2])));
        }
    }
}
