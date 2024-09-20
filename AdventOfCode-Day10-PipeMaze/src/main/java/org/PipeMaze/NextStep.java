package org.PipeMaze;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NextStep {
    int line;
    int position;
    String comingFrom;
}
