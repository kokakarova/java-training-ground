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

@Data
public class Garden {

    Seed seeds = new Seed();
    List<String[]> tempList = new ArrayList<>();
    boolean addToMultiDimArray = false;
    SeedToSoil seedToSoil = new SeedToSoil();
    SoilToFertilizer soilToFertilizer = new SoilToFertilizer();
    FertilizerToWater fertilizerToWater = new FertilizerToWater();
    WaterToLight waterToLight = new WaterToLight();
    LightToTemperature lightToTemp = new LightToTemperature();
    TemperatureToHumidity tempToHumidity = new TemperatureToHumidity();
    HumidityToLocation humidityToLocation = new HumidityToLocation();

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
            System.out.println("seeds-to-soil: " + Arrays.deepToString(seedToSoil.getSeedToSoilMap()));
//            System.out.println("soil-to-fertilizer: " + Arrays.deepToString(soilToFertilizer.getSoilToFertilizerMap()));
//            System.out.println("fertilizer-to-water: " + Arrays.deepToString(fertilizerToWater.getFertilizerToWaterMap()));
//            System.out.println("water-to-light: " + Arrays.deepToString(waterToLight.getWaterToLightMap()));
//            System.out.println("light-to-temp: " + Arrays.deepToString(lightToTemp.getLightToTemperatureMap()));
//            System.out.println("temp-to-humidity: " + Arrays.deepToString(tempToHumidity.getTemperatureToHumidityMap()));
//            System.out.println("humidity-to-location: " + Arrays.deepToString(getTempToHumidity().getTemperatureToHumidityMap()));
            System.out.println("--------------------------------");
            long meh = 1797371961L + 494535345L;
            System.out.println("1797371961 + 494535345 = " + meh);
           return getLocationForAllSeeds();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
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
                seeds.handleSeedsNumbers(splitLine[1]);
                break;
            case "seed-to-soil map":
                seedToSoil.setActive(true);
                break;
            case "soil-to-fertilizer map":
                seedToSoil.setActive(false);
                soilToFertilizer.setActive(true);
                addToMultiDimArray = true;
                break;
            case "fertilizer-to-water map":
                soilToFertilizer.setActive(false);
                fertilizerToWater.setActive(true);
                addToMultiDimArray = true;
                break;
            case "water-to-light map":
                fertilizerToWater.setActive(false);
                waterToLight.setActive(true);
                addToMultiDimArray = true;
                break;
            case "light-to-temperature map":
                waterToLight.setActive(false);
                lightToTemp.setActive(true);
                addToMultiDimArray = true;
                break;
            case "temperature-to-humidity map":
                lightToTemp.setActive(false);
                tempToHumidity.setActive(true);
                addToMultiDimArray = true;
                break;
            default:
                tempToHumidity.setActive(false);
                humidityToLocation.setActive(true);
                addToMultiDimArray = true;
        }
    }

    private void saveMapToTempList(String stringLine) {
        String[] splitLine = stringLine.split(" ");
        if (seedToSoil.isActive()) {
            tempList.add(splitLine);
            return;
        }
        if (soilToFertilizer.isActive()) {
            if (addToMultiDimArray) {
                seedToSoil.setSeedToSoilMap(tempList);
                tempList.clear();
                addToMultiDimArray = false;
            }
            tempList.add(splitLine);
            return;
        }
        if (fertilizerToWater.isActive()) {
            if (addToMultiDimArray) {
                soilToFertilizer.setSoilToFertilizerMap(tempList);
                tempList.clear();
                addToMultiDimArray = false;
            }
            tempList.add(splitLine);
            return;
        }
        if (waterToLight.isActive()) {
            if (addToMultiDimArray) {
                fertilizerToWater.setFertilizerToWaterMap(tempList);
                tempList.clear();
                addToMultiDimArray = false;
            }
            tempList.add(splitLine);
            return;
        }
        if (lightToTemp.isActive()) {
            if (addToMultiDimArray) {
                waterToLight.setWaterToLightMap(tempList);
                tempList.clear();
                addToMultiDimArray = false;
            }
            tempList.add(splitLine);
            return;
        }
        if (tempToHumidity.isActive()) {
            if (addToMultiDimArray) {
                lightToTemp.setLightToTempMap(tempList);
                tempList.clear();
                addToMultiDimArray = false;
            }
            tempList.add(splitLine);
            return;
        }
        if (humidityToLocation.isActive()) {
            if (addToMultiDimArray) {
                tempToHumidity.setTempToHumidityMap(tempList);
                tempList.clear();
                addToMultiDimArray = false;
            }
            tempList.add(splitLine);
            if (tempList.size() == 2) {
                humidityToLocation.setHumidityToLocationMap(tempList);
            }
        }
    }
    public long getLocationForAllSeeds() {
        for (Long seed : seeds.getSeedsNumbers().keySet()) {
            long location = getLocation(seed);
            seeds.getSeedsNumbers().put(seed, location);
        }
//        System.out.println("seeds.getSeedsNumbers() = " + seeds.getSeedsNumbers());
       return Collections.min(seeds.getSeedsNumbers().values());
    }

    private long getLocation(Long seed) {
        long soilPlace = seedToSoil.getSoilPlacement(seed);
        long fertilizerPlace = soilToFertilizer.getFertilizerPlacement(soilPlace);
        long waterPlace = fertilizerToWater.getWaterPlacement(fertilizerPlace);
        long lightPlace = waterToLight.getLightPlacement(waterPlace);
        long temperaturePlace = lightToTemp.getTemperaturePlacement(lightPlace);
        long humidityPlace = tempToHumidity.getHumidityPlacement(temperaturePlace);
        return humidityToLocation.getLocationPlacement(humidityPlace);
    }
}
