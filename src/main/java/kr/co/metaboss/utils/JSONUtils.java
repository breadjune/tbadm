package kr.co.metaboss.utils;

import lombok.extern.log4j.Log4j2;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.security.core.parameters.P;
import org.springframework.util.ObjectUtils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Log4j2
public class JSONUtils {


    /**
     * jsonObject --> map 으로 변경
     * JSONObject 에 JSONArray 없어야 햠.
     * @param obj
     * @return
     */
    public static Map<String, Object> getMapFromJSONObject(JSONObject obj) {
        if (ObjectUtils.isEmpty(obj)) {
            throw new IllegalArgumentException(String.format("BAD REQUEST obj %s", obj));
        }

        try {
            return obj.toMap();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * json array 를 list map 으로 변경.
     *
     * @param jsonArray
     * @return 값이 있으면 list map, 없으면 list 빈 값 return
     */
    public static List<Map<String, Object>> getListMapFromJsonArray(JSONArray jsonArray) {
        if (ObjectUtils.isEmpty(jsonArray)) {
            throw new IllegalArgumentException("jsonArray is null");
        }
        List<Map<String, Object>> list = new ArrayList<>();
        for (Object jsonObject : jsonArray) {
            list.add(getMapFromJSONObject((JSONObject) jsonObject));
        }
        return list;
    }

    /**
     * DTO -> JSONObject convert
     * @param obj (dto parent object)
     * @return JSONObject
     */
    public static JSONObject convertDtoToJsonObject(Object obj) {
        JSONObject json = new JSONObject();
        try {
            for (Field f : obj.getClass().getDeclaredFields()) {
                f.setAccessible(true);
                if (f.get(obj) != null) json.put(f.getName(), f.get(obj));
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return json;
    }
}
