package DesignPatterns.StructuralPatterns.FlyWeight.solution;

import java.util.HashMap;
import java.util.Map;

//Collection of bullet types
public class BulletTypeFactory {
    private static final Map<String, BulletType> bulletTypes= new HashMap<>();

    public static BulletType getBulletType(String color) {
      if(!bulletTypes.containsKey(color)) {
          bulletTypes.put(color, new BulletType(color));
      }
      return bulletTypes.get(color);
    }
}
