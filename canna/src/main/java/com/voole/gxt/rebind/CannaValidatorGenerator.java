package com.voole.gxt.rebind;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;

import org.apache.commons.lang3.StringUtils;

import com.google.gwt.core.ext.Generator;
import com.google.gwt.core.ext.GeneratorContext;
import com.google.gwt.core.ext.TreeLogger;
import com.google.gwt.core.ext.UnableToCompleteException;
import com.google.gwt.core.ext.typeinfo.JClassType;
import com.google.gwt.core.ext.typeinfo.TypeOracle;
import com.google.gwt.dev.util.Name;
import com.google.gwt.user.rebind.ClassSourceFileComposerFactory;
import com.google.gwt.user.rebind.SourceWriter;
import com.google.gwt.validation.client.GwtValidation;

public class CannaValidatorGenerator extends Generator {

	@Override
	public String generate(TreeLogger logger, GeneratorContext context,
			String typeName) throws UnableToCompleteException {
		TypeOracle oracle = context.getTypeOracle();
		JClassType toGenerate = oracle.findType(typeName).isInterface();

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

		List<JClassType> entityTypes = new ArrayList<JClassType>();

		for (JClassType jClassType : types) {
			Entity entity = jClassType.getAnnotation(Entity.class);
			if (entity != null) {
				entityTypes.add(jClassType);
			}
		}
		ClassSourceFileComposerFactory factory = new ClassSourceFileComposerFactory(
				packageName, simpleSourceName);
//		factory.makeInterface();
//		factory.setSuperclass(typeName);
		factory.addImplementedInterface(typeName);
		
		factory.addImport(Name.getSourceNameForClass(GwtValidation.class));
		List<String> annotations = new ArrayList<String>();

		for (JClassType jClassType : entityTypes) {
			factory.addImport(jClassType.getQualifiedSourceName());
			annotations.add(jClassType.getName() + ".class");
		}

		String annotationStr = "@GwtValidation(value = { "
				+ StringUtils.join(annotations, ',') + " })";

		factory.addAnnotationDeclaration(annotationStr);

		SourceWriter sw = factory.createSourceWriter(context, pw);
		sw.commit(logger);

		return factory.getCreatedClassName();
	}

}
