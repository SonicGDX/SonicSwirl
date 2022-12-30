package com.sonicgdx.sonicswirl;

import com.badlogic.gdx.utils.reflect.ClassReflection;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class TileMapTest {
    @Test
    void checkArrayLengths()
    {
        System.out.println(Arrays.toString(ClassReflection.getFields(TileMap.class)));
    }

}
