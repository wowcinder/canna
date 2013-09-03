/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import xdata.etl.hbase.annotatins.HbaseColumn;
import xdata.etl.hbase.annotatins.HbaseEmbedded;
import xdata.etl.hbase.annotatins.HbaseTable;
import xdata.etl.hbase.column.HbaseColumnInfoManager;
import xdata.etl.hbase.entity.HbaseEntity;
import xdata.etl.web.server.dao.hbasemeta.HbaseTableColumnDao;
import xdata.etl.web.server.dao.hbasemeta.HbaseTableDao;
import xdata.etl.web.server.dao.hbasemeta.HbaseTableVersionDao;
import xdata.etl.web.server.util.ClassScaner;
import xdata.etl.web.shared.entity.hbasemeta.HbaseTableColumn;
import xdata.etl.web.shared.entity.hbasemeta.HbaseTableColumn.HbaseTableColumnType;
import xdata.etl.web.shared.entity.hbasemeta.HbaseTableVersion;

/**
 * @author XuehuiHe
 * @date 2013年8月30日
 */
public class AnalyzeOldHbaseMeta {
	private static ClassPathXmlApplicationContext ac;
	private static HbaseTableDao tableDao;
	private static HbaseTableVersionDao versionDao;
	private static HbaseTableColumnDao columnDao;

	public static void main(String[] args) throws Exception {
		ac = new ClassPathXmlApplicationContext("spring-service.xml");
		init();

		ClassScaner scaner = new ClassScaner("xdata.etl.entity");
		for (Class<?> clazz : scaner.getClazzes()) {
			if (clazz.isAnnotationPresent(HbaseTable.class)) {
				deal(clazz);
			}
		}
		ac.close();

	}

	private static void deal(Class<?> clazz) {
		String name = getTableName(clazz);
		xdata.etl.web.shared.entity.hbasemeta.HbaseTable table = new xdata.etl.web.shared.entity.hbasemeta.HbaseTable();
		table.setDesc(name);
		table.setName(name);
		table.setShortname(name);

		tableDao.save(table);

		HbaseTableVersion v = new HbaseTableVersion();
		v.setTable(table);
		v.setDesc("");
		v.setVersion("");
		versionDao.save(v);

		saveColumns(v, clazz);
	}

	private static void saveColumns(HbaseTableVersion v, Class<?> clazz) {
		for (Field field : getField(clazz)) {
			String name = getColumnName(field);
			HbaseTableColumn column = new HbaseTableColumn();
			column.setName(name);
			column.setShortname(name);
			column.setDesc(name);
			column.setVersion(v);
			column.setType(getType(field));

			columnDao.save(column);
		}
	}

	private static List<Field> getField(Class<?> clazz) {
		List<Field> list = new ArrayList<Field>();
		Class<?> superClass = clazz.getSuperclass();
		if (superClass != null && !superClass.equals(Object.class)) {
			list.addAll(getField(superClass));
		}
		for (Field field : clazz.getDeclaredFields()) {
			if (HbaseColumnInfoManager.isExcludeMod(field.getModifiers())) {
				continue;
			}
			if (HbaseColumnInfoManager.isExcludeField(field)) {
				continue;
			}
			if (HbaseEntity.class.isAssignableFrom(field.getType())) {
			} else if (Collection.class.isAssignableFrom(field.getType())) {
			} else {// 基本属性
				if (field.isAnnotationPresent(HbaseEmbedded.class)) {
					list.addAll(getField(field.getType()));
				}else{
					list.add(field);
				}
			}
		}
		return list;
	}

	private static HbaseTableColumnType getType(Field field) {
		Class<?> type = field.getType();
		if (type.equals(Short.class) || type.equals(short.class)) {
			return HbaseTableColumnType.SHORT;
		} else if (type.equals(Integer.class) || type.equals(int.class)) {
			return HbaseTableColumnType.INT;
		} else if (type.equals(Long.class) || type.equals(long.class)) {
			return HbaseTableColumnType.LONG;
		} else if (type.equals(Float.class) || type.equals(float.class)) {
			return HbaseTableColumnType.FLOAT;
		} else if (type.equals(Double.class) || type.equals(double.class)) {
			return HbaseTableColumnType.DOUBLE;
		} else if (type.equals(String.class)) {
			return HbaseTableColumnType.STRING;
		} else if (type.equals(Character.class) || type.equals(char.class)) {
			return HbaseTableColumnType.CHAR;
		} else if (type.equals(Boolean.class)) {
			return HbaseTableColumnType.BOOLEAN;
		} else if (type.equals(Date.class)) {
			return HbaseTableColumnType.DATETIME;
		}
		return HbaseTableColumnType.STRING;
	}

	private static String getColumnName(Field field) {
		String name = field.getName();
		if (field.isAnnotationPresent(HbaseColumn.class)) {
			HbaseColumn column = field.getAnnotation(HbaseColumn.class);
			if (column.name() != null && column.name().length() != 0) {
				name = column.name();
			}
		}
		return name;
	}

	private static String getTableName(Class<?> clazz) {
		String name = clazz.getSimpleName();
		HbaseTable t = clazz.getAnnotation(HbaseTable.class);
		if (t != null && t.name() != null && t.name().length() != 0) {
			name = t.name();
		}
		return name;
	}

	private static void init() {
		tableDao = ac.getBean(HbaseTableDao.class);
		versionDao = ac.getBean(HbaseTableVersionDao.class);
		columnDao = ac.getBean(HbaseTableColumnDao.class);
	}

}
