package xdata.etl;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import xdata.etl.hbase.annotatins.HbaseColumn;
import xdata.etl.hbase.annotatins.HbaseTable;
import xdata.etl.hbase.entity.HbaseAttachment;
import xdata.etl.hbase.entity.HbaseEntity;
import xdata.etl.kafka.annotatins.Kafka;
import xdata.etl.util.ClassScaner;

import com.google.gson.GsonBuilder;
import com.google.gson.annotations.SerializedName;

public class GetGson {
	public static void main(String[] args) throws Exception {

		GsonBuilder gb = new GsonBuilder();
		gb.setPrettyPrinting();

		List<Object> list = new ArrayList<Object>();

		ClassScaner cs = new ClassScaner("xdata.etl.entity.assist");
		for (Class<?> clazz : cs.getClazzes()) {
			if (clazz.isAnnotationPresent(Kafka.class)) {
				list.add(getSimpleJsonMap(clazz));
			}
		}
		String json = gb.create().toJson(list);
		System.out.println(json);
	}

	public static void main2(String[] args) throws Exception {
		GsonBuilder gb = new GsonBuilder();
		gb.setPrettyPrinting();

		List<Object> list = new ArrayList<Object>();

		ClassScaner cs = new ClassScaner("xdata.etl.entity.v3a");
		for (Class<?> clazz : cs.getClazzes()) {
			if (clazz.isAnnotationPresent(Kafka.class)) {
				list.add(getSimpleJsonMap(clazz));
			}
		}
		String json = gb.create().toJson(list);
		System.out.println(json);
	}

	public static Map<String, Object> getSimpleJsonMap(Class<?> clazz) {
		Map<String, Object> map = new HashMap<String, Object>();

		Kafka k = clazz.getAnnotation(Kafka.class);
		HbaseTable h = clazz.getAnnotation(HbaseTable.class);
		map.put("topic", k.topic());
		map.put("table", h.name());
		Map<String, Object> data = new HashMap<String, Object>();
		map.put("data", data);

		fillDataMap(clazz, data, null);

		return map;
	}

	public static Map<String, Object> getItemJsonMap(Class<?> clazz) {
		Map<String, Object> map = new HashMap<String, Object>();
		HbaseTable h = clazz.getAnnotation(HbaseTable.class);
		map.put("table", h.name());
		Map<String, Object> data = new HashMap<String, Object>();
		map.put("data", data);

		fillDataMap(clazz, data, null);

		return map;
	}

	public static void fillDataMap(Class<?> clazz, Map<String, Object> data,
			String parent) {
		List<Field> fields = getFields(clazz);
		for (Field field : fields) {
			FieldType fieldType = getFieldType(field);
			String path = getJsonPath(field, parent);
			if (fieldType.equals(FieldType.FIELD)) {
				if (isPrimitive(field.getType())) {
					data.put(path, getHbaseColumnName(field));
				} else {
					fillDataMap(field.getType(), data, path);
				}
			} else {
				data.put(path, getItemJsonMap(getActualType(field)));
			}
		}
	}

	public static String getHbaseColumnName(Field field) {
		if (field.isAnnotationPresent(HbaseColumn.class)) {
			return field.getAnnotation(HbaseColumn.class).name();
		}
		return field.getName();
	}

	public static String getJsonPath(Field field, String parent) {
		String path = getJsonPath(field);
		if (parent == null) {
			return path;
		} else {
			return parent + "." + path;
		}
	}

	public static String getJsonPath(Field field) {
		if (field.isAnnotationPresent(SerializedName.class)) {
			SerializedName serializedName = field
					.getAnnotation(SerializedName.class);
			return serializedName.value();
		}
		return field.getName();

	}

	public static boolean isPrimitive(Class<?> clazz) {
		if (clazz.isPrimitive()) {
			return true;
		}
		if (clazz.equals(String.class)) {
			return true;
		}
		if (Number.class.isAssignableFrom(clazz)) {
			return true;
		}
		if (Date.class.isAssignableFrom(clazz)) {
			return true;
		}
		return false;
	}

	public static List<Field> getFields(Class<?> clazz) {
		List<Field> list = new ArrayList<Field>();
		for (Field field : clazz.getDeclaredFields()) {
			FieldType fieldType = getFieldType(field);
			if (!fieldType.equals(FieldType.WRONG)) {
				list.add(field);
			}
		}
		if (!clazz.getSuperclass().equals(HbaseEntity.class)
				&& !clazz.getSuperclass().equals(Object.class)
				&& !clazz.getSuperclass().equals(HbaseAttachment.class)) {
			list.addAll(getFields(clazz.getSuperclass()));
		}
		return list;
	}

	public static Class<?> getActualType(Field field) {
		ParameterizedType pType = (ParameterizedType) field.getGenericType();
		return (Class<?>) pType.getActualTypeArguments()[0];
	}

	public static boolean isRightFieldMod(int mod) {
		return !Modifier.isAbstract(mod) && !Modifier.isFinal(mod)
				&& !Modifier.isStatic(mod);
	}

	public static FieldType getFieldType(Field field) {
		if (!isRightFieldMod(field.getModifiers())) {
			return FieldType.WRONG;
		}
		if (Collection.class.isAssignableFrom(field.getType())) {
			return FieldType.LIST;
		}
		return FieldType.FIELD;
	}

	public enum FieldType {
		WRONG, FIELD, LIST;
	}
}
