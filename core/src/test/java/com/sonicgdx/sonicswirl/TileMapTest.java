package com.sonicgdx.sonicswirl;

import com.badlogic.gdx.utils.reflect.Annotation;
import com.badlogic.gdx.utils.reflect.ClassReflection;
import com.badlogic.gdx.utils.reflect.Field;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class TileMapTest {
    @Test
    void checkArrayLengths()
    {
        System.out.println(Arrays.toString(ClassReflection.getFields(TileMap.class)));

        for (Field field:ClassReflection.getDeclaredFields(TileMap.class)) {
            field.setAccessible(true);
            System.out.println(field.getName());
            /*if (field.getType() == byte[])
            {

            }*/
        }
    }

}
