package org.seeds;

import lombok.Data;
import org.seeds.Maps.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Data
public class Garden {

    Seed seed = new Seed();
    List<String[]> tempList = new ArrayList<>();
    boolean addToMultiDimArray = false;
    SeedToSoil seedToSoil = new SeedToSoil();
    SoilToFertilizer soilToFertilizer = new SoilToFertilizer();
    FertilizerToWater fertilizerToWater = new FertilizerToWater();
    WaterToLight waterToLight = new WaterToLight();
    LightToTemperature lightToTemp = new LightToTemperature();
    TemperatureToHumidity tempToHumidity = new TemperatureToHumidity();
    HumidityToLocation humidityToLocation = new HumidityToLocation();

    public void readFromFile(String fileName) {
        try (InputStream file = Garden.class.getClassLoader().getResourceAsStream(fileName)) {
            assert file != null;
            BufferedReader reader = new BufferedReader(new InputStreamReader(file));
            for (String s = reader.readLine(); s != null; s = reader.readLine()) {
                if (s.isEmpty()) {
                    continue;
                }
                processLine(s);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void processLine(String stringLine) {
        if (stringLine.substring(0, 1).matches("^[a-zA-Z]")) {
            checkTitleLines(stringLine);
            return;
        } else {
            System.out.println("Doesn't start with a letter: " + stringLine);
            saveMapToTempList(stringLine);
        }
    }

    private void checkTitleLines(String stringLine) {
        String[] splitLine = stringLine.split(":");
        System.out.println("Arrays.toString(splitLine) = " + Arrays.toString(splitLine));
        switch (splitLine[0]) {
            case "seed":
                seed.handleSeedsNumbers(splitLine[1]);
                System.out.println("seedsNumbers = " + seed.getSeedsNumbers());
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
}
