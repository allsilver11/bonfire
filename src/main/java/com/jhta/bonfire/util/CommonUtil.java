package com.jhta.bonfire.util;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;

import com.jhta.bonfire.vo.LocalMapVo;
import com.jhta.bonfire.vo.geoJsonVo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CommonUtil {
    private static Logger logger = LoggerFactory.getLogger(CommonUtil.class);

    /**
     * Object type 癰귨옙占쎈땾揶쏉옙 �뜮袁⑸선占쎌뿳占쎈뮉筌욑옙 筌ｋ똾寃�
     * 
     * @param obj
     * @return Boolean : true / false
     */
    public static Boolean isEmpty(Object obj) {
        if (obj instanceof String)
            return obj == null || "".equals(obj.toString().trim());
        else if (obj instanceof List)
            return obj == null || ((List<?>) obj).isEmpty();
        else if (obj instanceof Map)
            return obj == null || ((Map<?, ?>) obj).isEmpty();
        else if (obj instanceof Object[])
            return obj == null || Array.getLength(obj) == 0;
        else
            return obj == null;
    }

    public static Boolean isNotEmpty(Object obj) {
        return !isEmpty(obj);
    }

    /**
     * 雅뚯눘堉깍쭪占� 筌뤴뫀諭� 占쎌뵥占쎌쁽揶쏉옙 �뜮袁⑸선占쎌뿳筌욑옙 占쎈륫占쎈뮉筌욑옙 筌ｋ똾寃� 占쎈립占쎈뼄.
     * 
     * @param objects
     * @return
     */
    public static Boolean isNotEmpty(Object... objects) {
        if (objects == null || objects.length == 0) {
            return false;
        }
        for (Object obj : objects) {
            if (isEmpty(obj)) {
                return false;
            }
        }
        return true;
    }

    /**
     * mapper占쎌뒠 �뜮袁㏉꺍
     * 
     * @param str
     * @param obj
     * @return
     */
    public static Boolean equals(Object obj1, Object obj2) {
        if ((obj1 instanceof String || obj1 instanceof Character)
                && (obj2 instanceof String || obj2 instanceof Character))
            return obj1.equals(String.valueOf(obj2));
        else if (obj1 instanceof Integer && obj2 instanceof Integer) {
            return obj1.equals(obj2);
        } else if (obj1 == null || obj2 == null)
            return false;
        return false;
    }

    public static String arrayToString(Object objects) {
        // if (objects.length==0) return null;
        StringBuilder sb = new StringBuilder();
        if (objects instanceof String[]) {
            for (Object string : (String[]) objects) {
                sb.append("field : " + string + "\n");
            }
            return (sb.length() == 0) ? null : sb.toString();
        } else
            return null;
    }

    /**
     * 
     * @param fileName
     * @param boardName
     * @return moved file path
     * @throws IOException
     */
    public static String changePath(ServletContext sc, String content, String boardName, String...fileName){
        if (isNotEmpty((Object[]) fileName)) {
            for (String file : fileName) {
                logger.info(sc.getRealPath("/resources/TemporalFileStorage"));
                Path source = Paths.get(sc.getRealPath("/resources/TemporalFileStorage"), file);
                Path target = Paths.get(sc.getRealPath("/resources/"),boardName, file);
                logger.info(source.toString());
                 logger.info(target.toString());
                File parent = target.getParent().toFile();
                if (!parent.isDirectory()) parent.mkdirs();
                try {
                    // Files.move(source, target);
                    Files.copy(source, target);
                    Files.delete(source);
                } catch (IOException e) {
                    logger.error("filepathnotchanged", e);
                }
            }
            content = content.replaceAll("(<img src=\"/bonfire/resources/)(TemporalFileStorage)", "$1"+boardName);
        }
        return content;
    }
    
    
    public static Boolean pageBlock(String id,String feedId,String feedRole,HashMap<String, Object> map) {
    	if(id==null) {
			return true;
		}else {
			if(map!=null) {
				if(feedRole.equals("ROLE_ADMIN")) {
					return false;
				}else if(feedRole.equals("ROLE_MEMBER")) {
					if(id.equals(feedId) || id.equals("admin")) {
						return false;
					}else {
						return true;
					}
				}else if(feedRole.equals("ROLE_AUTHOR")) {
					return false;
				}else {
					return true;
				}
			}else {
				return true;
			}
		}
    }
    public static geoJsonVo toGeoJSON(LocalMapVo vo){
        Map<String, Object> geometry = new HashMap<>();
        
        String type = vo.getType();
        
        List<String> coordinates = new ArrayList<>();
        coordinates.add(vo.getG_coordinateslng());
        coordinates.add(vo.getG_coordinatesLat());
        geometry.put("type", vo.getG_type());
        geometry.put("coordinates", coordinates);
        
        Map<String, Object> properties = new HashMap<>();
        properties.put("address_name",vo.getP_address_name());
        properties.put("category_group_code",vo.getP_category_group_code());
        properties.put("category_group_name",vo.getP_category_group_name());
        properties.put("category_name",vo.getP_category_name());
        // properties.put("distance",vo.getP_distance());
        properties.put("id",vo.getP_id());
        properties.put("phone",vo.getP_phone());
        properties.put("place_name",vo.getP_place_name());
        properties.put("place_url",vo.getP_place_url());
        properties.put("road_address_name",vo.getP_road_address_name());

        return new geoJsonVo(type, geometry, properties);
    }
    public static LocalMapVo fromGeoJSON(geoJsonVo vo){
        Map<String, Object> geometry = vo.getGeometry();
        Map<String, Object> properties = vo.getProperties();
        
        String type = vo.getType();
        String geometrytype=(String) geometry.get("type");
        ArrayList<String> corrdinates = (ArrayList<String>) geometry.get("coordinates");
        String geometrycoordinateslng = corrdinates.get(0);
        String geometrycoordinatesLat = corrdinates.get(1);
        String propertiesaddress_name = (String) properties.get("address_name");
        String propertiescategory_group_code = (String) properties.get("category_group_code");
        String propertiescategory_group_name = (String) properties.get("category_group_name");
        String propertiescategory_name = (String) properties.get("category_name");
        String propertiesid = (String) properties.get("id");
        String propertiesphone = (String) properties.get("phone");
        String propertiesplace_name = (String) properties.get("place_name");
        String propertiesplace_url = (String) properties.get("place_url");
        String propertiesroad_address_name = (String) properties.get("road_address_name");

        return new LocalMapVo(type, geometrytype, geometrycoordinateslng, geometrycoordinatesLat, propertiesaddress_name, propertiescategory_group_code, propertiescategory_group_name, propertiescategory_name, propertiesid, propertiesphone, propertiesplace_name, propertiesplace_url, propertiesroad_address_name);
    }
}
