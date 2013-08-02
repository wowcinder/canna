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
import com.voole.gxt.client.app.MenuToken;
import com.voole.gxt.client.app.ui.MenuCenterView;

public class MenuViewFinderGenerator extends Generator {

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

		Map<String, JClassType> tokenToClass = new HashMap<String, JClassType>();

		for (JClassType jClassType : types) {
			MenuToken menuToken = jClassType.getAnnotation(MenuToken.class);
			if (menuToken != null) {
				tokenToClass.put(menuToken.token(), jClassType);
			}
		}

		ClassSourceFileComposerFactory factory = new ClassSourceFileComposerFactory(
				packageName, simpleSourceName);
		factory.setSuperclass(typeName);
		factory.addImport(Name.getSourceNameForClass(GWT.class));
		factory.addImport(Name.getSourceNameForClass(MenuCenterView.class));
		SourceWriter sw = factory.createSourceWriter(context, pw);

		sw.println("public MenuCenterView findMenuView(String token) {");
		sw.println("MenuCenterView m = null;");
		sw.indent();
		for (String token : tokenToClass.keySet()) {
			JClassType jClassType = tokenToClass.get(token);
			if (token == null) {
				token = "";
			}
			sw.println("if(\"%1$s\".equals(token)){", token);
			sw.println("m = new %1$s();", jClassType.getQualifiedSourceName());
			sw.println("m.setToken(\"%1$s\");", token);
			sw.println("return m;}");
		}
		sw.outdent();
		sw.println("return null;");
		sw.println("}");

		sw.commit(logger);

		return factory.getCreatedClassName();
	}

}
