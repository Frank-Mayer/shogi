package de.hhn.shogi.basicgamemanager;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class BasicSerializer {
    public static String serialize(Object someObject){
        StringBuilder builder = new StringBuilder();
        Field[] field = someObject.getClass().getDeclaredFields();  //gets all fields in the BasicGameState class
        for (Field field1 : field) {
            try {
                boolean access = field1.canAccess(someObject);
                field1.setAccessible(true);
                Object field_object = field1.get(someObject);   //gets the variable from the field
                TypeSerializer typeSerializer = getSerializer(field_object);
                if(typeSerializer!=null){
                    builder.append(encode(field1.getName(),typeSerializer.serialize(field_object),field_object.getClass().getName()));
                }else{
                    //TODO make some out debug output logger
                    System.out.println(field1.getName()+" does have a registered Serializer");
                }
                field1.setAccessible(access);
            }catch (Exception ignored){}
        }
        return builder.toString();
    }
    public static BasicGameState deserialize(String serialized){
        return (BasicGameState) deserialize(serialized,BasicGameState.class.getName());
    }
    public static Object deserialize(String serialized,String class_name){
        try {
        Object object_obj=Class.forName(class_name).getDeclaredConstructor().newInstance();
        HashMap<String,Field> mapped_fields = new HashMap<>();
        for (Field declaredField : object_obj.getClass().getDeclaredFields()) {     //gets all fields in the BasicGameState/Objects class
            mapped_fields.put(declaredField.getName(),declaredField);
        }
        String[] vars_together = serialized.split("\"");
        HashMap<String,String> mapped_vars=new HashMap<>();
        for (int i = 0; i < vars_together.length; i+=2) {
            mapped_vars.put(vars_together[i].substring(0,vars_together[i].length()-1), vars_together[i+1]);
        }
        for (Map.Entry<String, String> Entry : mapped_vars.entrySet()) {
            if(mapped_fields.containsKey(Entry.getKey())){
                Field field = mapped_fields.get(Entry.getKey());
                String mixed_value = Entry.getValue();
                String type = mixed_value.split(":")[0];
                String value = mixed_value.split(":")[1];
                boolean accessible = field.canAccess(object_obj);
                field.setAccessible(true);
                try {
                    field.set(object_obj,getSerializer(type).deserialize(value));
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
                field.setAccessible(accessible);
            }
        }
            return object_obj;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }
    protected static String encode(String name,String val,String class_name){
        return name+"=\""+class_name+":"+ val+"\"";
    }

    private final static HashMap<String,TypeSerializer> serializers = new HashMap<>();

    protected static <T> TypeSerializer getSerializer(T object) {
        return getSerializer(object.getClass().getName());
    }
    protected static TypeSerializer getSerializer(String class_name) {
        if(serializers.isEmpty()){
            //registers all serializers
            register_serializer(new StringSerializer());
            register_serializer(new ArrayListSerializer());
            register_serializer(new HashMapSerializer());
            register_serializer(new BoolSerializer());
            register_serializer(new IntSerializer());
            register_serializer(new FloatSerializer());
            register_serializer(new LongSerializer());
        }
        return serializers.getOrDefault(class_name,null);
    }
    private static void register_serializer(TypeSerializer serializer){
        serializers.put(serializer.getType().getName(),serializer);
    }

}
