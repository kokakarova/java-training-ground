package org.PipeMaze;

import lombok.Data;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

@Data
public class PipeMaze {
    Map<Integer, List<Integer>> pipeVertexes = new HashMap<>();
    Map<Integer, List<Integer>> dashVertexes = new HashMap<>();
    Map<Integer, List<Integer>> lSignVertexes = new HashMap<>();
    Map<Integer, List<Integer>> jSignVertexes = new HashMap<>();
    Map<Integer, List<Integer>> sevenSignVertexes = new HashMap<>();
    Map<Integer, List<Integer>> fSignVertexes = new HashMap<>();
    NextStep currentStep = new NextStep();
//    float startingPosition;

    private static final float GO_NORTH = -1.0F;
    private static final float GO_SOUTH = 1.0F;
    private static final float GO_EAST = 0.001F;
    private static final float GO_WEST = -0.001F;

    public void readFromFile(String fileName, int part) {
        int lineNumber = 0;
        try (InputStream file = Main.class.getClassLoader().getResourceAsStream(fileName)) {
            assert file != null;
            BufferedReader reader = new BufferedReader(new InputStreamReader(file));
            for (String line = reader.readLine(); line != null; line = reader.readLine()) {
                lineNumber++;
                char[] lineChars = line.toCharArray();
                allocateCharacters(lineNumber, lineChars);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void allocateCharacters(int lineNumber, char[] lineChars) {
        for (int i = 0; i < lineChars.length; i++) {
            switch (lineChars[i]) {
                case 'S' -> {
                    currentStep.setLine(lineNumber);
                    currentStep.setPosition(i);
                    currentStep.setComingFrom("east");
                }
                case '|' -> {
                    pipeVertexes.putIfAbsent(lineNumber, new ArrayList<>());
                    pipeVertexes.get(lineNumber).add(i);
                }
                case '-' -> {
                    dashVertexes.putIfAbsent(lineNumber, new ArrayList<>());
                    dashVertexes.get(lineNumber).add(i);
                }
                case 'L' -> {
                    lSignVertexes.putIfAbsent(lineNumber, new ArrayList<>());
                    lSignVertexes.get(lineNumber).add(i);
                }
                case 'J' -> {
                    jSignVertexes.putIfAbsent(lineNumber, new ArrayList<>());
                    jSignVertexes.get(lineNumber).add(i);
                }
                case '7' -> {
                    sevenSignVertexes.putIfAbsent(lineNumber, new ArrayList<>());
                    sevenSignVertexes.get(lineNumber).add(i);
                }
                case 'F' -> {
                    fSignVertexes.putIfAbsent(lineNumber, new ArrayList<>());
                    fSignVertexes.get(lineNumber).add(i);
                }
                default -> {
                    continue;
                }
            }
        }
    }

    public long getFurthestVertex() {
        long stepCounter = 1;
        int startLine = currentStep.getLine();
        int startPosition = currentStep.getPosition();
        boolean fullCircleComplete = false;

        while (!fullCircleComplete) {
            stepCounter++;

            switch (currentStep.comingFrom) {
                case "west" -> {
                    currentStep.setPosition(currentStep.getPosition() + 1);
                    if (atStartingPoint(currentStep, startLine, startPosition, stepCounter)) {
                        fullCircleComplete = true;
                        continue;
                    }
                    setNextStepComingFromWhenCurrentComingFromWest(currentStep);
                }
                case "east" -> {
                    currentStep.setPosition(currentStep.getPosition() - 1);
                    if (atStartingPoint(currentStep, startLine, startPosition, stepCounter)) {
                        fullCircleComplete = true;
                        continue;
                    }
                    setNextStepComingFromWhenCurrentFromEast(currentStep);
                }
                case "north" -> {
                    currentStep.setLine(currentStep.getLine() + 1);
                    if (atStartingPoint(currentStep, startLine, startPosition, stepCounter)) {
                        fullCircleComplete = true;
                        continue;
                    }
                    setNextStepComingFromWhenCurrentFromNorth(currentStep);
                }
                case "south" -> {
                    currentStep.setLine(currentStep.getLine() - 1);
                    if (atStartingPoint(currentStep, startLine, startPosition, stepCounter)) {
                        fullCircleComplete = true;
                        continue;
                    }
                    setNextStepComingFromWhenCurrentFromSouth(currentStep);
                }
                default -> throw new IllegalStateException("Unexpected value: " + currentStep.getComingFrom());
            }

        }
        return stepCounter / 2;
    }

    private boolean atStartingPoint(NextStep nextStep, int startLine, int startPosition, long counter) {
        return currentStep.getLine() == startLine && currentStep.getPosition() == startPosition && counter > 1;
    }

    private void setNextStepComingFromWhenCurrentFromSouth(NextStep currentVertex) {
        if (pipeVertexes.containsKey(currentVertex.getLine())
                && pipeVertexes.get(currentVertex.getLine()).contains(currentVertex.getPosition())) {
            currentStep.setComingFrom("south");
        } else if (sevenSignVertexes.containsKey(currentVertex.getLine())
                && sevenSignVertexes.get(currentVertex.getLine()).contains(currentVertex.getPosition())) {
            currentStep.setComingFrom("east");
        } else if (fSignVertexes.containsKey(currentVertex.getLine())
                && fSignVertexes.get(currentVertex.getLine()).contains(currentVertex.getPosition())) {
            currentStep.setComingFrom("west");
        } else {
            throw new IllegalStateException("Unexpected value: " + currentStep);
        }
    }

    private void setNextStepComingFromWhenCurrentFromNorth(NextStep currentVertex) {
        if (pipeVertexes.containsKey(currentVertex.getLine())
                && pipeVertexes.get(currentVertex.getLine()).contains(currentVertex.getPosition())) {
            currentStep.setComingFrom("north");
        } else if (lSignVertexes.containsKey(currentVertex.getLine())
                && lSignVertexes.get(currentVertex.getLine()).contains(currentVertex.getPosition())) {
            currentStep.setComingFrom("west");
        } else if (jSignVertexes.containsKey(currentVertex.getLine())
                && jSignVertexes.get(currentVertex.getLine()).contains(currentVertex.getPosition())) {
            currentStep.setComingFrom("east");
        } else {
            throw new IllegalStateException("Unexpected value: " + currentStep);
        }
    }

    private void setNextStepComingFromWhenCurrentFromEast(NextStep currentVertex) {
        if (lSignVertexes.containsKey(currentVertex.getLine())
                && lSignVertexes.get(currentVertex.getLine()).contains(currentVertex.getPosition())) {
            currentStep.setComingFrom("south");
        } else if (fSignVertexes.containsKey(currentVertex.getLine())
                && fSignVertexes.get(currentVertex.getLine()).contains(currentVertex.getPosition())) {
            currentStep.setComingFrom("north");
        } else if (dashVertexes.containsKey(currentVertex.getLine())
                && dashVertexes.get(currentVertex.getLine()).contains(currentVertex.getPosition())) {
            currentStep.setComingFrom("east");
        } else {
            throw new IllegalStateException("Unexpected value: " + currentStep);
        }
    }


    private void setNextStepComingFromWhenCurrentComingFromWest(NextStep currentVertex) {

        if (dashVertexes.containsKey(currentVertex.getLine())
                && dashVertexes.get(currentVertex.getLine()).contains(currentVertex.getPosition())) {
            currentStep.setComingFrom("west");
        } else if (jSignVertexes.containsKey(currentVertex.getLine())
                && jSignVertexes.get(currentVertex.getLine()).contains(currentVertex.getPosition())) {
            currentStep.setComingFrom("south");
        } else if (sevenSignVertexes.containsKey(currentVertex.getLine())
                && sevenSignVertexes.get(currentVertex.getLine()).contains(currentVertex.getPosition())) {
            currentStep.setComingFrom("north");
        } else {
            throw new IllegalStateException("Unexpected value: " + currentStep);
        }
    }

    public float goWestOrEast(float startPosition, String direction) {
        int increment = direction.equals("west") ? -1 : 1;
        String[] floatStringArr = String.valueOf(startPosition).split("\\.");
        String numToIncreaseString = floatStringArr[1];

        return ((float) (Integer.parseInt(numToIncreaseString) + increment) / 1000)
                + (float) Integer.parseInt(floatStringArr[0]);
    }

}
