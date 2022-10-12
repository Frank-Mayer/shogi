package de.hhn.shogi.BasicGameManager;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class BasicNetworkHelper {
    public static String serialize(BasicGameState basicGameState){
        StringBuilder builder = new StringBuilder();
        HashMap<String,String> variable_map=new HashMap<>(); // makes a hash map to store all the variables inside
        Field[] field = basicGameState.getClass().getDeclaredFields();  //gets all fields in the BasicGameState class
        for (Field field1 : field) {
            try {
                boolean access = field1.canAccess(basicGameState);
                field1.setAccessible(true);
                Object field_object = field1.get(basicGameState);   //gets the variable from the field
                TypeSerializer typeSerializer = getSerializer(field_object);
                if(typeSerializer!=null){
                    variable_map.put(field1.getName(), typeSerializer.serialize(field_object));
                }
                field1.setAccessible(access);
            }catch (Exception ignored){}
        }
        for (Map.Entry<String, String> variable_map_entry : variable_map.entrySet()) { //not using stream because of performance
            builder.append(encode(variable_map_entry.getKey(),variable_map_entry.getValue()));
        }
        return builder.toString();
    }
    private static String encode(String name,String val){
        return name+"=\""+val.getClass().getName()+"{"+ val+"}\""; //maybe base 64 is better but idk
    }

    private final static HashMap<String,TypeSerializer> serializers = new HashMap<>();

    protected static  <T> TypeSerializer getSerializer(T object) {
        if(serializers.isEmpty()){
            //registers all serializers
            serializers.put(new StringSerializer().getType().getName(),new StringSerializer());
        }
        return serializers.getOrDefault(object.getClass().getName(),null);
    }
}
