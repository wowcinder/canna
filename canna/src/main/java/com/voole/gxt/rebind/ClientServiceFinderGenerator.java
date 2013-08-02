package com.voole.gxt.rebind;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.ext.Generator;
import com.google.gwt.core.ext.GeneratorContext;
import com.google.gwt.core.ext.TreeLogger;
import com.google.gwt.core.ext.UnableToCompleteException;
import com.google.gwt.core.ext.typeinfo.JClassType;
import com.google.gwt.core.ext.typeinfo.TypeOracle;
import com.google.gwt.dev.util.Name;
import com.google.gwt.user.rebind.ClassSourceFileComposerFactory;
import com.google.gwt.user.rebind.SourceWriter;
import com.voole.gxt.client.service.ServiceConfig;

public class ClientServiceFinderGenerator extends Generator {

	@Override
	public String generate(TreeLogger logger, GeneratorContext context,
			String typeName) throws UnableToCompleteException {
		TypeOracle oracle = context.getTypeOracle();
		JClassType toGenerate = oracle.findType(typeName).isClass();

		// Get the name of the new type
		String packageName = toGenerate.getPackage().getName();
		String simpleSourceName = toGenerate.getName().replace('.', '_')
				+ "Impl";
		PrintWriter pw = context.tryCreate(logger, packageName,
				simpleSourceName);
		if (pw == null) {
			return packageName + "." + simpleSourceName;
		}

		JClassType[] types = oracle.getTypes();

		Map<String, JClassType> pathToClass = new HashMap<String, JClassType>();

		for (JClassType jClassType : types) {
			ServiceConfig serviceConfig = jClassType
					.getAnnotation(ServiceConfig.class);
			if (serviceConfig != null) {
				pathToClass.put(serviceConfig.path(), jClassType);
			}
		}

		ClassSourceFileComposerFactory factory = new ClassSourceFileComposerFactory(
				packageName, simpleSourceName);
		factory.setSuperclass(typeName);
		factory.addImport(Name.getSourceNameForClass(GWT.class));
		SourceWriter sw = factory.createSourceWriter(context, pw);
		sw.println("public %1$s() {", simpleSourceName);
		sw.indent();
		for (String path : pathToClass.keySet()) {
			JClassType jClassType = pathToClass.get(path);
			if (path == null || path.equals("")) {
				continue;
			}
			sw.println("pathMaps.put(%1$s.class, \"%2$s\");",
					jClassType.getQualifiedSourceName(), path);
		}
		sw.outdent();
		sw.println("}");

		sw.println("public Object gwtCreate(Class<?> clazz) {");
		sw.indent();
		for (String path : pathToClass.keySet()) {
			JClassType jClassType = pathToClass.get(path);
			if (path == null || path.equals("")) {
				continue;
			}
			sw.println("if(%1$s.class.equals(clazz)){", jClassType.getQualifiedSourceName());
			sw.println("return GWT.create(%1$s.class);}", jClassType.getQualifiedSourceName());
		}
		sw.outdent();
		sw.println("return null;");
		sw.println("}");

		sw.commit(logger);

		return factory.getCreatedClassName();
	}

}
