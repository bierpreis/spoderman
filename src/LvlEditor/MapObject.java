package LvlEditor;

import map.Cube;

import java.awt.*;

public class MapObject {

    private Dimension dimension;

    private Cube[][] cubeArray;

    public MapObject(Dimension requestedDimension){
        dimension = requestedDimension;
        cubeArray = createEmptyCubes(requestedDimension);
    }

    private Cube[][] createEmptyCubes(Dimension requestedDimension){
        cubeArray = new Cube[requestedDimension.width][requestedDimension.height];

        for(int cubeX = 0; cubeX<requestedDimension.width; cubeX++){
            for(int cubeY = 0; cubeY<requestedDimension.height; cubeY++){
                cubeArray[cubeX][cubeY] = new Cube(cubeX, cubeY);
            }

        }
        return cubeArray;
    }

    public Cube getCube(int x, int y){
        return cubeArray[x][y];
    }

    public Dimension getDimension(){
        return dimension;
    }


}
