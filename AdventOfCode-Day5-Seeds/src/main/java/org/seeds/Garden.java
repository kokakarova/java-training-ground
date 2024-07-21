package org.seeds;

import lombok.Data;
import org.seeds.Maps.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

@Data
public class Garden {

    Seed seed = new Seed();
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
        } else {
            System.out.println("Doesn't start with a letter: " + stringLine);
        }


    }

    private void checkTitleLines(String stringLine) {
        if (stringLine.startsWith("seeds")) {
            seed.handleSeedsNumbers(stringLine.split(":")[1]);
            System.out.println("seedsNumbers = " + seed.getSeedsNumbers());
            return;
        }
        if (stringLine.startsWith("seed-to")) {
            seedToSoil.setActive(true);
            return;
        }
        if (stringLine.startsWith("soil-to")) {
            seedToSoil.setActive(false);
            soilToFertilizer.setActive(true);
            return;
        }
        if (stringLine.startsWith("fertilizer-to")) {
            soilToFertilizer.setActive(false);
            fertilizerToWater.setActive(true);
            return;
        }
        if (stringLine.startsWith("water-to")) {
            fertilizerToWater.setActive(false);
            waterToLight.setActive(true);
            return;
        }
        if (stringLine.startsWith("light-to")) {
            waterToLight.setActive(false);
            lightToTemp.setActive(true);
            return;
        }
        if (stringLine.startsWith("temperature-to")) {
            lightToTemp.setActive(false);
            tempToHumidity.setActive(true);
            return;
        }
        if (stringLine.startsWith("humidity-to")) {
            tempToHumidity.setActive(false);
            humidityToLocation.setActive(true);
        }
    }
}
